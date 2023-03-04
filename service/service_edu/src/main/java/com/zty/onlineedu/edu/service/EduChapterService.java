package com.zty.onlineedu.edu.service;

import com.zty.onlineedu.edu.pojo.entity.EduChapter;
import com.zty.onlineedu.edu.pojo.vo.ChapterVo;

import java.util.List;

/**
* @author 17939
* @description 针对表【edu_chapter(课程)】的数据库操作Service
* @createDate 2022-12-01 21:58:26
*/
public interface EduChapterService {

    /**
     * 保存章节
     * @param eduChapter 章节信息
     * @return 成功与否
     */
    Boolean saveChapter(EduChapter eduChapter);

    /**
     * 通过章节id,查询章节信息
     * @param chapterId 章节id
     * @return 章节信息
     */
    EduChapter getChapterById(String chapterId);

    /**
     * 更新章节
     * @param eduChapter 章节信息
     * @return 成功与否
     */
    Boolean updateChapter(EduChapter eduChapter);

    /**
     * 删除章节
     * @param chapterId 章节id
     * @return 成功与否
     */
    Integer deleteChapter(String chapterId);

    /**
     * 通过课程id,获取嵌套章节列表信息
     * @param courseId 课程id
     * @return 套章节列表信息
     */
    List<ChapterVo> getNestedListByCourseId(String courseId);

}
