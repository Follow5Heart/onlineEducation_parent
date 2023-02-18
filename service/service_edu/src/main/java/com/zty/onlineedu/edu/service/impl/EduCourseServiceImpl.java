package com.zty.onlineedu.edu.service.impl;

import com.zty.onlineedu.common.base.utils.LocalDateTimeUtils;
import com.zty.onlineedu.common.base.utils.UUIDUtils;
import com.zty.onlineedu.edu.entity.EduCourse;
import com.zty.onlineedu.edu.entity.EduCourseDescription;
import com.zty.onlineedu.edu.entity.form.CourseInfoForm;
import com.zty.onlineedu.edu.mapper.EduCourseMapper;
import com.zty.onlineedu.edu.service.EduCourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author 17939
* @description 针对表【edu_course(课程)】的数据库操作Service实现
* @createDate 2022-12-03 13:52:58
*/
@Service
public class EduCourseServiceImpl implements EduCourseService{

    @Autowired
    private EduCourseMapper eduCourseMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String saveCourseInfo(CourseInfoForm courseInfoForm) {

        //保存课程信息
        EduCourse eduCourse = new EduCourse();
        //复制相同字段的属性值，把courseInfoForm中的属性复制到eduCourse中，不同的字段不会变动
        BeanUtils.copyProperties(courseInfoForm,eduCourse);
        //其他参数自己进行封装
        eduCourse.setGmtCreate(LocalDateTimeUtils.FormatNow());
        String courseId=UUIDUtils.getUUID32();
        eduCourse.setId(courseId);
        eduCourse.setStatus(EduCourse.DRAFT);
        eduCourseMapper.saveCourse(eduCourse);


        //保存课程简介 edu_course_description
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setId(courseId);
        eduCourseDescription.setDescription(courseInfoForm.getDescription());
        eduCourseDescription.setGmtCreate(LocalDateTimeUtils.FormatNow());
        eduCourseMapper.saveCourseDescription(eduCourseDescription);

        return courseId;

    }

    @Override
    public CourseInfoForm getCourseInfo(String id) {

        //获取课程基本信息
        EduCourse eduCourse=eduCourseMapper.getCourseInfo(id);
        if (eduCourse==null){
            return null;
        }
        //获取课程的课程简介
        EduCourseDescription eduCourseDescription=eduCourseMapper.getCourseDescription(id);

        //创建对象,封装
        CourseInfoForm courseInfoForm = new CourseInfoForm();
        BeanUtils.copyProperties(eduCourse,courseInfoForm);
        courseInfoForm.setDescription(eduCourseDescription.getDescription());

        return courseInfoForm;
    }
}
