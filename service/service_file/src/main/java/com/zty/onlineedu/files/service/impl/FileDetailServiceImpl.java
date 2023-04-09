package com.zty.onlineedu.files.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.xuyanwu.spring.file.storage.FileInfo;
import com.zty.onlineedu.common.base.utils.LocalDateTimeUtils;
import com.zty.onlineedu.files.pojo.vo.EduFileInfoVo;
import com.zty.onlineedu.files.mapper.EduFileInfoMapper;
import com.zty.onlineedu.files.service.FileDetailService;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.UUID;

/**
 * @Author zty
 * @Date 2023/1/16 0:11
 */
@Log4j2
@Service
public class FileDetailServiceImpl implements FileDetailService {

    @Autowired
    private EduFileInfoMapper mapper;

    @Value("${myFilePath}")
    private String storagePath;




    /**
     * 保存文件信息到数据库
     */
    @SneakyThrows
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean record(FileInfo fileInfo) {

        //封装数据信息
        String datakey=UUID.randomUUID().toString().replace("-", "").toLowerCase();
        EduFileInfoVo eduFileInfoVo = new EduFileInfoVo();
        eduFileInfoVo.setId(datakey);
        eduFileInfoVo.setName(fileInfo.getOriginalFilename());
        //String[] split = fileInfo.getFilename().split(".");

        eduFileInfoVo.setContentType(fileInfo.getContentType());
        eduFileInfoVo.setFileSize(fileInfo.getSize().toString());
        eduFileInfoVo.setPath(fileInfo.getPath());
        eduFileInfoVo.setUrl(fileInfo.getUrl());
        eduFileInfoVo.setSource("LOCAL");
        eduFileInfoVo.setCreateTime(LocalDateTimeUtils.FormatNow());
        mapper.saveFileInfo(eduFileInfoVo);

        //修改回显的id
        fileInfo.setId(datakey);

        return true;


    }

    /**
     * 重写getByUrl方法，查询文件信息是否存在
     * @param url
     * @return
     */
    @Override
    public FileInfo getByUrl(String url) {
       return null;

    }

    /**
     * 通过url删除
     * @param url
     * @return
     */

    @Override
    public boolean delete(String url) {
        return false;
    }


    /**
     * 自己删除文件的方法，通过文件id
     * @param fileId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteFile(String fileId) {
        EduFileInfoVo eduFileInfoVo = mapper.queryFileInfoById(fileId);
        if (eduFileInfoVo !=null){
            //如果存在，删除表中数据
            mapper.deleteFileInfo(eduFileInfoVo.getId());
            //删除本地存储文件
            String url = eduFileInfoVo.getUrl();
            String[] urlList = url.split("/");
            String fileName=urlList[urlList.length-1];
            String finalPath=storagePath+fileName;
            File file = new File(finalPath);
            if (file.exists()){
                log.info("正在删除本地存储文件");
                file.delete();
            }else{
                log.info("本地存储文件缺失");

            }
            return true;

        }else{
            return false;
        }

    }

    @Override
    public boolean deleteFileByUrl(String imageUrl) {
        boolean result=false;
        String fileId=mapper.getIdByImageUrl(imageUrl);
        if(StrUtil.hasBlank(fileId)){
            result=deleteFile(fileId);
        }
        return result;


    }
}
