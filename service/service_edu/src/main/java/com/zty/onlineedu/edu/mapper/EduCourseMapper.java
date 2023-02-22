package com.zty.onlineedu.edu.mapper;

import com.zty.onlineedu.edu.pojo.entity.EduCourse;
import com.zty.onlineedu.edu.pojo.entity.EduCourseDescription;
import com.zty.onlineedu.edu.pojo.query.CourseQueryParam;
import com.zty.onlineedu.edu.pojo.vo.CourseVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 17939
* @description 针对表【edu_course(课程)】的数据库操作Mapper
* @createDate 2022-12-03 13:52:58
* @Entity com.zty.onlineedu.edu.pojo.entity.EduCourse
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

    /**
     * 更新课程信息
     * @param eduCourse 课程基本信息
     */
    void updateCourse(EduCourse eduCourse);

    /**
     * 更新课程简介
     * @param eduCourseDescription 课程简介信息
     */
    void updateCourseDescription(EduCourseDescription eduCourseDescription);

    /**
     * 获取课程分页数据
     * @param courseQueryParam 课程查询对象
     * @return
     */
    List<CourseVo> courseList(CourseQueryParam courseQueryParam);

    /**
     * 通过分类id，查询课程分类标题
     * @param id  主键id
     * @return
     */
    String querySubjectById(String id);

}
