package com.zty.onlineedu.edu.service;

import com.zty.onlineedu.edu.pojo.dto.CourseInfoFormDto;
import com.zty.onlineedu.edu.pojo.query.CourseQueryParam;
import com.zty.onlineedu.edu.pojo.query.WebCourseQueryParam;
import com.zty.onlineedu.edu.pojo.vo.CoursePublishVo;
import com.zty.onlineedu.edu.pojo.vo.CourseVo;
import com.zty.onlineedu.edu.pojo.vo.WebCourseVo;

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

    /**
     * 通过课程id,获取发布课程信息
     * @param courseId 课程id
     * @return 发布课程数据
     */
    CoursePublishVo getCoursePublishById(String courseId);

    /**
     * 根据课程id，发布课程
     * @param courseId 课程id
     */
    void publishCourse(String courseId);

    /**
     * 前端课程查询参数，查询课程信息列表
     * @param webCourseQueryParam 前端课程查询参数
     * @return 课程信息列表
     */
    List<CourseVo> getCourseList(WebCourseQueryParam webCourseQueryParam);

    /**
     * 获取课程详情页信息并更新浏览量
     * @param courseId 课程id
     * @return 课程详情页信息
     */
    WebCourseVo getWebCourseInfo(String courseId);

    /**
     * 获取课程热门数据
     * @return 课程数据集合
     */
    List<CourseVo> getHotCourse();


}
