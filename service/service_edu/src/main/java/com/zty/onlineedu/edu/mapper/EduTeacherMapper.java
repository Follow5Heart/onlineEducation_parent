package com.zty.onlineedu.edu.mapper;

import com.zty.onlineedu.edu.pojo.entity.EduFileInfoRelation;
import com.zty.onlineedu.edu.pojo.entity.EduTeacher;
import com.zty.onlineedu.edu.pojo.query.TeacherQueryParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* @author 17939
* @description 针对表【edu_teacher(讲师)】的数据库操作Mapper
* @createDate 2022-12-03 13:53:11
* @Entity com.zty.onlineedu.edu.pojo.entity.EduTeacher
*/
@Mapper
public interface EduTeacherMapper {


    /**
     * 获取所有的teacher数据
     * @return
     * @param teacherQueryParam
     */
    List<EduTeacher> getTeacherList(TeacherQueryParam teacherQueryParam);

    /**
     * 删除一条数据
     * @param eduTeacher
     * @return
     */
    Integer deleteData(EduTeacher eduTeacher);

    /**
     * 保存讲师信息*
     * @param eduTeacher
     * @return
     */
    int saveTeacher(EduTeacher eduTeacher);

    /**
     * 通过id查询教师数据*
     * @param id
     * @return
     */
    EduTeacher queryTeacherById(String id);

    /**
     * 更新讲师数据*
     * @param eduTeacher
     */
    void updateTeacher(EduTeacher eduTeacher);

    void saveFileInfoRelation(EduFileInfoRelation eduFileInfoRelation);

    Integer queryFileRelationNum(String id);

    void deleteFileRelation(String id);

    String queryFileInfoRelationByIndirectId(String id);

    Map<String, Object> queryFileInfoById(String fileId);

    int batchDeleteTeacher(@Param("idLists") List<String> idLists);

    List<Map<String, Object>> queryListNameByKeyword(@Param("keyword") String keyword);

    /**
     * 通过主键id,查询文件的url路径
     * @param fileId
     * @return
     */
    String queryFileInfoUrl(String fileId);

    /**
     * 通过主键id,删除文件信息
     * @param fileId
     */
    void deleteFileInfo(String fileId);

    /**
     * 通过教师id，查询讲师下面的所有的课程信息
     * @param id 教师id
     * @return
     */
    List<Map<String, Object>> queryCourseInfoByTeachrId(String id);

    /**
     * 获取热门讲师数据集合
     * @return 讲师数据集合
     */
    List<EduTeacher> getHotTeacher();

}
