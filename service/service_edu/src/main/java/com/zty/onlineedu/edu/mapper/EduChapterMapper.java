package com.zty.onlineedu.edu.mapper;

import com.zty.onlineedu.edu.entity.EduChapter;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 17939
 * @description 针对表【edu_chapter(课程)】的数据库操作Mapper
 * @createDate 2022-12-01 21:58:26
 * @Entity com.zty.onlineedu.edu.entity.EduChapter
 */
@Mapper
public interface EduChapterMapper {
    /**
     * 返回所有数据
     * @param id
     * @return
     */
    List<EduChapter> getAllData(String id);



}
