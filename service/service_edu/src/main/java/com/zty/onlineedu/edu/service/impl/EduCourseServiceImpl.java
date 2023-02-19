package com.zty.onlineedu.edu.service.impl;

import com.zty.onlineedu.common.base.utils.LocalDateTimeUtils;
import com.zty.onlineedu.common.base.utils.UUIDUtils;
import com.zty.onlineedu.edu.pojo.entity.EduCourse;
import com.zty.onlineedu.edu.pojo.entity.EduCourseDescription;
import com.zty.onlineedu.edu.pojo.dto.CourseInfoFormDto;
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
    public String saveCourseInfo(CourseInfoFormDto courseInfoFormDto) {

        //保存课程信息
        EduCourse eduCourse = new EduCourse();
        //复制相同字段的属性值，把courseInfoForm中的属性复制到eduCourse中，不同的字段不会变动
        BeanUtils.copyProperties(courseInfoFormDto,eduCourse);
        //其他参数自己进行封装
        eduCourse.setGmtCreate(LocalDateTimeUtils.FormatNow());
        String courseId=UUIDUtils.getUUID32();
        eduCourse.setId(courseId);
        eduCourse.setStatus(EduCourse.DRAFT);
        eduCourseMapper.saveCourse(eduCourse);


        //保存课程简介 edu_course_description
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setId(courseId);
        eduCourseDescription.setDescription(courseInfoFormDto.getDescription());
        eduCourseDescription.setGmtCreate(LocalDateTimeUtils.FormatNow());
        eduCourseMapper.saveCourseDescription(eduCourseDescription);

        return courseId;

    }

    @Override
    public CourseInfoFormDto getCourseInfo(String id) {

        //获取课程基本信息
        EduCourse eduCourse=eduCourseMapper.getCourseInfo(id);
        if (eduCourse==null){
            return null;
        }
        //获取课程的课程简介
        EduCourseDescription eduCourseDescription=eduCourseMapper.getCourseDescription(id);

        //创建对象,封装
        CourseInfoFormDto courseInfoFormDto = new CourseInfoFormDto();
        BeanUtils.copyProperties(eduCourse, courseInfoFormDto);
        courseInfoFormDto.setDescription(eduCourseDescription.getDescription());

        return courseInfoFormDto;
    }

    @Override
    public void updateCourse(CourseInfoFormDto courseInfoFormDto) {

        if (courseInfoFormDto.getId()==null){
            throw new RuntimeException("课程id不能为null");
        }
        //更新edu_course表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoFormDto,eduCourse);
        eduCourse.setGmtModified(LocalDateTimeUtils.FormatNow());
        eduCourseMapper.updateCourse(eduCourse);


        //更新edu_course_description表
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoFormDto.getDescription());
        eduCourseDescription.setId(courseInfoFormDto.getId());
        eduCourseDescription.setGmtModified(LocalDateTimeUtils.FormatNow());
        eduCourseMapper.updateCourseDescription(eduCourseDescription);


    }
}
