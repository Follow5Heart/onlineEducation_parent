package com.zty.onlineedu.edu.service.impl;


import com.zty.onlineedu.edu.mapper.EduChapterMapper;
import com.zty.onlineedu.edu.pojo.entity.EduChapter;
import com.zty.onlineedu.edu.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Boolean result=eduChapterMapper.updateChapter(eduChapter);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteChapter(String eduChapterId) {

        //TODO 删除章节下的视频

        //删除章节信息通过id
        Boolean result=eduChapterMapper.deleteChapter(eduChapterId);
        return result;
    }
}
