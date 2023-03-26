package com.zty.onlineedu.vod.service.impl;

import com.zty.onlineedu.common.base.utils.LocalDateTimeUtils;
import com.zty.onlineedu.service.base.redis.RedisService;
import com.zty.onlineedu.vod.entity.EduVideoInfo;
import com.zty.onlineedu.vod.mapper.VideoManagerMapper;
import com.zty.onlineedu.vod.service.VideoManagerService;
import com.zty.onlineedu.vod.util.ChunkMultipartFileParam;
import com.zty.onlineedu.vod.util.VodProperties;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

/**
 * @Author zty
 * @Date 2023/1/16 0:11
 */
@Log4j2
@Service("videoManagerService")
public class VideoManagerServiceImpl implements VideoManagerService {

    private static final String CHUNK_KEY = "chunkNumberList";

    @Resource(name = "RedisUtils")
    private RedisService redisService;

    @Autowired
    private VodProperties vodProperties;

    @Autowired
    private VideoManagerMapper videoManagerMapper;


    /**
     * 保存分块到路径下，同时将信息保存到redis中
     *
     * @param chunk 分块对象
     * @return 集合数量
     * @throws IOException
     */
    @Override
    public Map<String, Object> saveChunk(ChunkMultipartFileParam chunk) throws IOException {
        Map<String, Object> uploadResult = new HashMap<>();
        //获取文件对象
        MultipartFile file = chunk.getFile();
        if (Objects.isNull(file)) {
            return uploadResult;
        }
        //获取当前的分块数，一般都是由1开始
        Integer chunkNumber = chunk.getChunkNumber();
        //获取文件的唯一标识(有文件的整体进行mdk5运算得出，保证唯一性)
        String identifier = chunk.getIdentifier();


        //获取文件的字节码
        byte[] bytes = file.getBytes();
        //通过固定的路径和chunk对象，生成专门存储的分块的文件，最后返回存储的分块路径
        Path path = Paths.get(generatePath(vodProperties.getChunkPath(), chunk));
        //通过分块路径写入到分块路径
        Files.write(path, bytes);
        log.info("视频分块名为：" + path.getFileName() + ",已经下载完成！");

        //把当前的分块数，通过文件的标识identifier(redis的键)和字段(chunkNumberList)保存到redis中，最后返回存储集合数量
        Integer number = 0;
        //防止self对象在同一时间内被其他线程访问，起到保护线程安全的作用
        synchronized (CHUNK_KEY) {
            number = saveChunkNumberToRedis(chunkNumber, identifier);
        }
        //判断number的数量如果等于chunk的总分块数，那么需要返回给前端需要合并的字段needMerge=true
        if (number.equals(chunk.getTotalChunks())) {
            uploadResult.put("needMerge", true);
        } else {
            uploadResult.put("needMerge", false);
        }
        return uploadResult;

    }


    /**
     * 把分块信息保存到redis中
     *
     * @param chunkNumber 当前分块数
     * @param identifier  文件唯一标识
     * @return 集合数量
     */
    private Integer saveChunkNumberToRedis(Integer chunkNumber, String identifier) {
        //先判断redis中是否存在 文件的唯一标识作为key  键为chunkNumberList
        Set<Integer> oldChunkNumberList = (Set<Integer>) redisService.hmGet(identifier, CHUNK_KEY);
        //如果获取为空，则创建新的set集合，并将当前分块的chunkNumber加入到Redis中
        if (Objects.isNull(oldChunkNumberList)) {
            Set<Integer> newChunkNumberList = new HashSet<>();
            newChunkNumberList.add(chunkNumber);
            redisService.hmSet(identifier, CHUNK_KEY, newChunkNumberList);

            //返回集合的数量
            return newChunkNumberList.size();
        } else {
            //如果不为空，则将当前的分块数chunkNumber添加到chunkNumberList中，并加入redis中
            oldChunkNumberList.add(chunkNumber);
            redisService.hmSet(identifier, CHUNK_KEY, oldChunkNumberList);
            //返回集合的数量
            return oldChunkNumberList.size();
        }

    }

    /**
     * 通过保存文件的路径，分块上传实体类，生成分块文件路径
     *
     * @param uploadFolder 保存分块路径
     * @param chunk        分块上传对象
     * @return
     */
    public static String generatePath(String uploadFolder, ChunkMultipartFileParam chunk) throws IOException {
        StringBuilder pathValue = new StringBuilder();
        //把要保存分块路径与文件的唯一标识进行拼接  File.separator相当于/
        pathValue.append(uploadFolder).append(File.separator).append(chunk.getIdentifier());
        //判断文件夹是否存在，如果不存在就创建
        if (!Files.isWritable(Paths.get(pathValue.toString()))) {
            Files.createDirectories(Paths.get(pathValue.toString()));

        }
        //返回的格式是： f:/xxx/xxx/xxx.xxx-1   f:/xxx/xxx/xxx.xxx-2
        return pathValue.append(File.separator)
                .append(chunk.getFilename())
                .append("-")
                .append(chunk.getChunkNumber()).toString();


    }

    /**
     * 分块是否存在
     *
     * @param chunk 分块上传对象
     * @return 分块信息
     */
    public Map<String, Object> checkChunkExists(ChunkMultipartFileParam chunk) {
        HashMap<String, Object> map = new HashMap<>();

        //获取文件的唯一标识MD5值
        String identifier = chunk.getIdentifier();

        //判断key是否在redis存在，如果存在，获取键为chunkNumberList的值
        if (redisService.existsKey(identifier)) {
            //通过md5值获取redis中的值
            Set<Integer> chunkNumberList = (Set<Integer>) redisService.hmGet(identifier, CHUNK_KEY);
            //把分块数数据封装到map中 例如 [1,2,3,4]
            map.put(CHUNK_KEY, chunkNumberList);
        }

        return map;

    }

    /**
     * 检查是秒传还是断点上传
     *
     * @param chunk 分块上传对象
     * @return 秒传还是断点的返回信息
     */
    @Override
    public Map<String, Object> checkBreakpointOrSecondTransmission(ChunkMultipartFileParam chunk) {
        //检测当前的文件是否已经上传过，通过文件的唯一标识在redis中查询
        Map<String, Object> checkResult = checkChunkExists(chunk);
        //判断当前返回的map中是否包含CHUNK_KEY，如果包含，那么就是断传或者秒传的情况，如果不包含，那么就是没有上传过的新文件
        if (checkResult.containsKey(CHUNK_KEY)) {
            Set<Integer> chunkNumbers = (Set<Integer>) checkResult.get(CHUNK_KEY);
            //如果已经上传过了，那么就是秒传
            if (chunkNumbers.size() == chunk.getTotalChunks()) {
                //TODO:从视频信息表中获取url
                String url = videoManagerMapper.queryVideoUrlById(chunk.getIdentifier());
                checkResult.put("url", url == null ? "" : url);
                checkResult.put("videoId", chunk.getIdentifier());
                checkResult.put("skipUpload", true);
                checkResult.put("filename", chunk.getFilename());
            } else {
                //如果只是断点上传
                checkResult.put("uploaded", chunkNumbers);
            }
            checkResult.put("needMerge", false);
            checkResult.remove(CHUNK_KEY);

        }
        return checkResult;
    }

    /**
     * 合并分块数据
     * @param filename 文件名
     * @param identifier 文件唯一标识
     * @param contentType 文件类型
     * @param filesize 文件大小
     * @return
     * @throws IOException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> chunkMerge(String filename, String identifier, String contentType, String filesize) throws IOException {
        //获取存储合并的路径
        String saveVodPath = vodProperties.getMyVodPath();

        //把文件名换成文件的唯一标识   例如：文件名.mp4---> 文件唯一标识.mp4
        if (!filename.contains(".")) {
            throw new IllegalArgumentException(filename + " is not a video file");
        }

        //获取文件的后缀名
        String suffixName = filename.substring(filename.lastIndexOf("."));
        //拼接成新的文件名
        String newFilename = identifier + suffixName;

        //生成日期 例如2023/12/01
        String dataTime = LocalDateTimeUtils.customFormatDataTime(LocalDateTimeUtils.PATTERN_OTHER_DATE);

        //拼接存储路径
        String target = saveVodPath + "/" + dataTime + "/" + identifier + "/" + newFilename;

        //获取文件夹的路径
        String folderPath = saveVodPath + "/" + dataTime;


        //判断保存的文件夹是否有权限可以修改，如果有有权限，就创建
        if (Files.isWritable(Paths.get(saveVodPath))) {
            //判断这个f:/upload/2023/12/01/xxx存不存在，如果不存在则创建
            if (!Files.exists(Paths.get(folderPath + "/" + identifier))) {
                Files.createDirectories(Paths.get(folderPath + "/" + identifier));
            }

        }

        //判断当前合并的文件是否已经合并过，如果合并过，则不用合并
        if (!Files.exists(Paths.get(target))) {
            //创建文件
            Files.createFile(Paths.get(target));
        }

        //把分块视频合并，最后删除分块视频
        mergeFile(identifier, target);

        //封装EduVideoInfo信息
        EduVideoInfo eduVideoInfo = new EduVideoInfo();
        eduVideoInfo.setId(identifier);
        eduVideoInfo.setName(filename);
        eduVideoInfo.setContenttype(contentType);
        eduVideoInfo.setFilesize(Integer.valueOf(filesize));
        eduVideoInfo.setPath("/" + dataTime + "/" + identifier + "/" + newFilename);
        eduVideoInfo.setUrl(target);
        eduVideoInfo.setSource("LOCAL");
        eduVideoInfo.setIsimg(0);
        eduVideoInfo.setCreatetime(LocalDateTimeUtils.FormatNow());
        //保存文件信息到库中
        videoManagerMapper.saveVideoInfo(eduVideoInfo);

        Map<String, Object> mergeResult = new HashMap<>();
        mergeResult.put("videoId", identifier);
        mergeResult.put("filename", filename);
        mergeResult.put("url", target);
        return mergeResult;
    }


    /**
     * 合并文件方法
     * @param identifier
     * @param target
     * @throws IOException
     */
    public void mergeFile(String identifier, String target) throws IOException {
        //分块存储地址
        String chunkPath = vodProperties.getChunkPath();

        //拼接得到要得到合并的文件夹路径
        String mergeFolderPath = chunkPath + "/" + identifier;

        //判断要合并的文件夹是否存在
        if (!Files.exists(Paths.get(mergeFolderPath))) {
            throw new FileNotFoundException(identifier + "的文件夹不存在");
        }
        //通过chunkFolder路径，生成path对象.它是个文件夹，含有很多个文件，再用Files.list方法生成stream流，进行操作
        Files.list(Paths.get(mergeFolderPath))
                .filter(path -> path.getFileName().toString().contains("-")) //只要文件名含有-的
                .sorted(((o1, o2) -> {
                    String f1 = o1.getFileName().toString();
                    String f2 = o2.getFileName().toString();
                    int i1 = f1.lastIndexOf("-");
                    int i2 = f2.lastIndexOf("-");
                    return Integer.valueOf(f2.substring(i2)).compareTo(Integer.valueOf(f1.substring(i1)));

                })).forEach(path -> {
                    log.info("正在合并文件名为" + path.getFileName());
                    try {
                        //以追加的方式写入文件
                        Files.write(Paths.get(target), Files.readAllBytes(path), StandardOpenOption.APPEND);
                        //合并后删除当前分块视频
                        Files.delete(path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

        //删除文件夹
        Files.delete(Paths.get(mergeFolderPath));


    }


}
