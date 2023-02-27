package com.zty.onlineedu.edu.service;

import com.zty.onlineedu.edu.pojo.entity.EduChapter;

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
     * @param eduChapterId 章节id
     * @return 成功与否
     */
    Boolean deleteChapter(String eduChapterId);

}
