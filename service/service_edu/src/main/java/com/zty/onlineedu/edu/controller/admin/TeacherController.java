package com.zty.onlineedu.edu.controller.admin;

import com.zty.onlineedu.edu.entity.EduTeacher;
import com.zty.onlineedu.edu.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author zty
 * @Date 2022/12/3 13:53
 */
@RestController
@RequestMapping("/admin/edu/teacher")
public class TeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @GetMapping("/list")
    public List<EduTeacher> teacherList(){
        List<EduTeacher> teacherList=eduTeacherService.teacherList();
        return teacherList;

    }



}
