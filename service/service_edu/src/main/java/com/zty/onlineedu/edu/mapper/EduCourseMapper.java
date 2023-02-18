package com.zty.onlineedu.edu.mapper;

import com.zty.onlineedu.edu.entity.EduCourse;
import com.zty.onlineedu.edu.entity.EduCourseDescription;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 17939
* @description 针对表【edu_course(课程)】的数据库操作Mapper
* @createDate 2022-12-03 13:52:58
* @Entity com.zty.onlineedu.edu.entity.EduCourse
*/
@Mapper
public interface EduCourseMapper{


    /**
     * 保存课程信息
     * @param eduCourse 课程信息
     */
    void saveCourse(EduCourse eduCourse);

    /**
     * 保存课程简介
     * @param eduCourseDescription  简介内容
     */
    void saveCourseDescription(EduCourseDescription eduCourseDescription);

    /**
     * 获取课程信息
     * @param id 课程id
     * @return
     */
    EduCourse getCourseInfo(String id);

    /**
     * 获取课程简介
     * @param id 课程id
     * @return
     */
    EduCourseDescription getCourseDescription(String id);

}
