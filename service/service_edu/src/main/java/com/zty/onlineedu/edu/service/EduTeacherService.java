package com.zty.onlineedu.edu.service;

import com.zty.onlineedu.edu.pojo.entity.EduTeacher;
import com.zty.onlineedu.edu.pojo.query.TeacherQueryParam;

import java.util.List;
import java.util.Map;

/**
* @author 17939
* @description 针对表【edu_teacher(讲师)】的数据库操作Service
* @createDate 2022-12-03 13:53:11
*/
public interface EduTeacherService{

    /**
     * 返回所有数据
     * @return
     * @param teacherQueryParam
     */
    List<EduTeacher> teacherList(TeacherQueryParam teacherQueryParam);

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
    int saveTeacher(EduTeacher eduTeacher);

    /**
     * 通过id查询出教师信息
     * @param id
     * @return
     */
    EduTeacher queryTeacherById(String id);

    /**
     *更新教师信息
     * @param eduTeacher
     */
    void updateTeacher(EduTeacher eduTeacher);

    /**
     * 通过讲师id,查询附件文件信息
     * @param id
     * @return
     */
    Map<String, Object> queryFileInfo(String id);

    /**
     * 通过ids，批量删除讲师数据
     * @param idLists
     */
    int batchDeleteTeacher(List<String> idLists);

    /**
     * 通过关键字，查询所有相关讲师姓名
     * @return
     */
    List<Map<String, Object>> queryListNameByKeyword(String keyword);

    /**
     * 根据教师id,获取老师信息和老师所属下面的课程信息
     * @param id 教师id
     * @return 教师信息列表奥
     */
    Map<String, Object> getTeacherById(String id);

    /**
     * 获取热门讲师数据集合
     * @return 讲师数据集合
     */
    List<EduTeacher> getHotTeacher();

}
