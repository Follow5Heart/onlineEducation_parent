package com.zty.onlineedu.edu.service.impl;


import com.zty.onlineedu.common.base.utils.LocalDateTimeUtils;
import com.zty.onlineedu.common.base.utils.UUIDUtils;
import com.zty.onlineedu.edu.mapper.EduChapterMapper;
import com.zty.onlineedu.edu.pojo.entity.EduChapter;
import com.zty.onlineedu.edu.pojo.entity.EduVideo;
import com.zty.onlineedu.edu.pojo.vo.ChapterVo;
import com.zty.onlineedu.edu.pojo.vo.VideoVo;
import com.zty.onlineedu.edu.service.EduChapterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
* @author 17939
* @description 针对表【edu_chapter(课程)】的数据库操作Service实现
* @createDate 2022-12-01 21:58:26
*/
@Service
public class EduChapterServiceImpl implements EduChapterService{

    @Autowired
    private EduChapterMapper eduChapterMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveChapter(EduChapter eduChapter) {
        eduChapter.setId(UUIDUtils.getUUID32());
        eduChapter.setGmtCreate(LocalDateTimeUtils.FormatNow());
        Boolean result=eduChapterMapper.saveChapter(eduChapter);
        return result;
    }

    @Override
    public EduChapter getChapterById(String chapterId) {
        EduChapter eduChapter=eduChapterMapper.getChapterById(chapterId);
        return eduChapter;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateChapter(EduChapter eduChapter) {
        eduChapter.setGmtModified(LocalDateTimeUtils.FormatNow());
        Boolean result=eduChapterMapper.updateChapter(eduChapter);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteChapter(String chapterId) {

        //在删除之前，先判断当前章节是否有课时，如果有课时，则不能删除，需要先删除课时
        Integer flag=eduChapterMapper.queryVideoDataByChapterId(chapterId);
        if (flag>0){
            //代表章节有课时，不能删除
            return 0;
        }else{
            //删除章节信息通过id
            Boolean result=eduChapterMapper.deleteChapter(chapterId);
            if (result){
                return 1;
            }else{
                return 2;
            }
        }

    }

    @Override
    public List<ChapterVo> getNestedListByCourseId(String courseId) {
        //查询所有数据当前课程id的章节数据
        List<EduChapter> eduChapterList=eduChapterMapper.getChapterByCourseId(courseId);
        //创建返回列表
        ArrayList<ChapterVo> chapterVoArrayList = new ArrayList<>();

        //判断是否有值
        if (eduChapterList!=null && eduChapterList.size()>0){
            //数据进行流化操作
            eduChapterList.stream().forEach(chapter->{
                //创建章节vo
                ChapterVo chapterVo = new ChapterVo();
                //把查询出章节信息赋值到章节vo中
                BeanUtils.copyProperties(chapter, chapterVo);
                //通过课程id和章节id，查询video信息
                List<EduVideo> eduVideoList=eduChapterMapper.getVideoByTwoId(chapter.getId(),courseId);

                //创建List<VideoVo> children 集合
                List<VideoVo> videoVoList = new ArrayList<>();
                //通过stream流，遍历video信息
                eduVideoList.stream().forEach(video->{
                    //创建视频vo
                    VideoVo videoVo = new VideoVo();
                    //把video信息赋值到视频vo中
                    BeanUtils.copyProperties(video, videoVo);
                    //添加到children集合中
                    videoVoList.add(videoVo);

                });
                //给章节vo设置children赋值
                chapterVo.setChildren(videoVoList);
                //把章节vo添加到章节列表中
                chapterVoArrayList.add(chapterVo);

            });



        }
        return chapterVoArrayList;
    }
}
