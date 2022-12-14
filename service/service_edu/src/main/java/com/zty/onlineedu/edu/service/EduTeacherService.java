package com.zty.onlineedu.edu.service;

import com.zty.onlineedu.edu.entity.EduTeacher;
import com.zty.onlineedu.edu.entity.vo.TeacherQueryVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 17939
* @description 针对表【edu_teacher(讲师)】的数据库操作Service
* @createDate 2022-12-03 13:53:11
*/
public interface EduTeacherService{

    /**
     * 返回所有数据
     * @return
     * @param teacherQueryVo
     */
    List<EduTeacher> teacherList(TeacherQueryVo teacherQueryVo);

    /**
     * 删除一条数据*
     * @param eduTeacher
     * @return
     */
    Integer deleteData(EduTeacher eduTeacher);

    /**
     * 保存讲师数据*
     * @param eduTeacher
     * @return
     */
    int saveTeacher(@Param("eduTeacher") EduTeacher eduTeacher);
}
