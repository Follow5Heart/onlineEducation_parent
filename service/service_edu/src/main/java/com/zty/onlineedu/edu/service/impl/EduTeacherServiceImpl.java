package com.zty.onlineedu.edu.service.impl;

import com.zty.onlineedu.edu.entity.EduTeacher;
import com.zty.onlineedu.edu.entity.vo.TeacherQueryVo;
import com.zty.onlineedu.edu.mapper.EduTeacherMapper;
import com.zty.onlineedu.edu.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 17939
* @description 针对表【edu_teacher(讲师)】的数据库操作Service实现
* @createDate 2022-12-03 13:53:11
*/
@Service
public class EduTeacherServiceImpl implements EduTeacherService{

    @Autowired
    private EduTeacherMapper eduTeacherMapper;

    @Override
    public List<EduTeacher> teacherList(TeacherQueryVo teacherQueryVo) {
        List<EduTeacher> teacherList=eduTeacherMapper.getTeacherList(teacherQueryVo);
        return teacherList;
    }

    @Override
    public Integer deleteData(EduTeacher eduTeacher) {
        Integer resultCount=eduTeacherMapper.deleteData(eduTeacher);
        return resultCount;
    }

    @Override
    public int saveTeacher(EduTeacher eduTeacher) {
        int result=eduTeacherMapper.saveTeacher(eduTeacher);
        return result;
    }
}
