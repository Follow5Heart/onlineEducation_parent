package com.zty.onlineedu.edu.mapper;

import com.zty.onlineedu.edu.pojo.entity.EduChapter;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 17939
 * @description 针对表【edu_chapter(课程)】的数据库操作Mapper
 * @createDate 2022-12-01 21:58:26
 * @Entity com.zty.onlineedu.edu.pojo.entity.EduChapter
 */
@Mapper
public interface EduChapterMapper {
    /**
     * 返回所有数据
     * @param id
     * @return
     */
    List<EduChapter> getAllData(String id);

    /**
     * 保存章节
     * @param eduChapter 章节信息
     * @return 成功与否
     */
    Boolean saveChapter(EduChapter eduChapter);

    /**
     * 通过章节id，查询章节信息
     * @param eduChapterId 章节id
     * @return 章节信息
     */
    EduChapter getChapterById(String eduChapterId);

    /**
     * 更新章节信息
     * @param eduChapter 章节信息
     * @return 成功与否
     */
    Boolean updateChapter(EduChapter eduChapter);

    /**
     * 通过章节id,删除章节信息
     * @param eduChapterId 章节id
     * @return 成功与否
     */
    Boolean deleteChapter(String eduChapterId);


}
