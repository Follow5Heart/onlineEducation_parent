package com.zty.onlineedu.edu.mapper;

import com.zty.onlineedu.edu.entity.EduTeacher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 17939
* @description 针对表【edu_teacher(讲师)】的数据库操作Mapper
* @createDate 2022-12-03 13:53:11
* @Entity com.zty.onlineedu.edu.entity.EduTeacher
*/
@Mapper
public interface EduTeacherMapper {


    /**
     * 获取所有的teacher数据
     * @return
     */
    List<EduTeacher> getTeacherList();

    /**
     * 删除一条数据
     * @param eduTeacher
     * @return
     */
    Integer deleteData(EduTeacher eduTeacher);
}
