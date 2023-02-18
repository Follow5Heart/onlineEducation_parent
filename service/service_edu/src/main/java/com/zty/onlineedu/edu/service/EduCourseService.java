package com.zty.onlineedu.edu.service;

import com.zty.onlineedu.edu.entity.form.CourseInfoForm;

/**
* @author 17939
* @description 针对表【edu_course(课程)】的数据库操作Service
* @createDate 2022-12-03 13:52:58
*/
public interface EduCourseService{
    /**
     * 新增课程
     * @param courseInfoForm
     */
    String saveCourseInfo(CourseInfoForm courseInfoForm);

    /**
     * 通过id查询课程信息
     * @param id 课程id
     * @return
     */
    CourseInfoForm getCourseInfo(String id);

}
