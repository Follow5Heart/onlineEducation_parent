package com.zty.onlineedu.edu.service;

import com.zty.onlineedu.edu.pojo.dto.CourseInfoFormDto;
import com.zty.onlineedu.edu.pojo.query.CourseQueryParam;
import com.zty.onlineedu.edu.pojo.vo.CourseVo;

import java.util.List;

/**
* @author 17939
* @description 针对表【edu_course(课程)】的数据库操作Service
* @createDate 2022-12-03 13:52:58
*/
public interface EduCourseService{
    /**
     * 新增课程
     * @param courseInfoFormDto
     */
    String saveCourseInfo(CourseInfoFormDto courseInfoFormDto);

    /**
     * 通过id查询课程信息
     * @param id 课程id
     * @return
     */
    CourseInfoFormDto getCourseInfo(String id);

    /**
     * 更新课程
     * @param courseInfoFormDto 课程基本信息
     */
    void updateCourse(CourseInfoFormDto courseInfoFormDto);

    /**
     * 获取课程分页数据
     * @parm courseQueryParam  课程查询对象
     * @return
     */
    List<CourseVo> courseList(CourseQueryParam courseQueryParam);

    /**
     * 删除课程
     * @param courseId 课程id
     */
    void deleteCourseInfo(String courseId);
}
