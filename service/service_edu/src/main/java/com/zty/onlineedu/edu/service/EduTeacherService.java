package com.zty.onlineedu.edu.service;

import com.zty.onlineedu.edu.entity.EduTeacher;

import java.util.List;

/**
* @author 17939
* @description 针对表【edu_teacher(讲师)】的数据库操作Service
* @createDate 2022-12-03 13:53:11
*/
public interface EduTeacherService{

    List<EduTeacher> teacherList();
}
