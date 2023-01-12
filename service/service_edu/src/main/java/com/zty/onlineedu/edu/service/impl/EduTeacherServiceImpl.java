package com.zty.onlineedu.edu.service.impl;

import com.zty.onlineedu.edu.entity.EduTeacher;
import com.zty.onlineedu.edu.entity.vo.TeacherQueryVo;
import com.zty.onlineedu.edu.mapper.EduTeacherMapper;
import com.zty.onlineedu.edu.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

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

        if (teacherQueryVo.getName()!=null) {
            String name = teacherQueryVo.getName();
            teacherQueryVo.setName("%" +name+"%");


        }
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
        String id = UUID.randomUUID().toString();
        String uid = id.replaceAll("-", "");
        eduTeacher.setId(uid);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String createTime = dtf.format(now);
        eduTeacher.setGmtCreate(createTime);
        int result=eduTeacherMapper.saveTeacher(eduTeacher);
        return result;
    }

    @Override
    public EduTeacher queryTeacherById(String id) {
        EduTeacher teacher=eduTeacherMapper.queryTeacherById(id);
        return teacher;
    }

    @Override
    public void updateTeacher(EduTeacher eduTeacher) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String updateTime = dtf.format(now);
        eduTeacher.setGmtModified(updateTime);
        eduTeacherMapper.updateTeacher(eduTeacher);
    }
}
