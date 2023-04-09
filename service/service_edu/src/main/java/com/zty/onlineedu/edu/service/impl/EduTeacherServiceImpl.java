package com.zty.onlineedu.edu.service.impl;

import com.zty.onlineedu.common.base.result.Result;
import com.zty.onlineedu.common.base.utils.JsonUtils;
import com.zty.onlineedu.common.base.utils.LocalDateTimeUtils;
import com.zty.onlineedu.common.base.utils.StringUtils;
import com.zty.onlineedu.common.base.utils.UUIDUtils;
import com.zty.onlineedu.edu.pojo.entity.EduFileInfoRelation;
import com.zty.onlineedu.edu.pojo.entity.EduTeacher;
import com.zty.onlineedu.edu.pojo.query.TeacherQueryParam;
import com.zty.onlineedu.edu.feign.FileService;
import com.zty.onlineedu.edu.mapper.EduTeacherMapper;
import com.zty.onlineedu.edu.service.EduTeacherService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author 17939
* @description 针对表【edu_teacher(讲师)】的数据库操作Service实现
* @createDate 2022-12-03 13:53:11
*/
@Log4j2
@Service
public class EduTeacherServiceImpl implements EduTeacherService{

    @Autowired
    private EduTeacherMapper eduTeacherMapper;

    @Autowired
    private FileService fileService;

    @Override
    public List<EduTeacher> teacherList(TeacherQueryParam teacherQueryParam) {

        if (StringUtils.isNotEmpty(teacherQueryParam.getName())) {
            String name = teacherQueryParam.getName();
            teacherQueryParam.setName("%" +name+"%");


        }
        List<EduTeacher> teacherList=eduTeacherMapper.getTeacherList(teacherQueryParam);
        return teacherList;
    }

    @Override
    @Transactional
    public Integer deleteData(EduTeacher eduTeacher) {
        //先删除存储在本地讲师头像和文件信息表数据
        deleteFileInfoTableAndLocalFile(eduTeacher.getId());

        Integer resultCount=eduTeacherMapper.deleteData(eduTeacher);
        eduTeacherMapper.deleteFileRelation(eduTeacher.getId());
        return resultCount;
    }

    /**
     * Spring框架的事务基础架构代码将默认地
     * * 只抛出个RuntimeException 或其子类例的实例时。（
     * * Errors 也一样 - 默认地 - 标识事务回滚。）
     * * Checked exceptions将 ****不 被标识进行事务回滚。
     * 可查的异常（checked exceptions）:Exception下除了RuntimeException外的异常
     * 不可查的异常（unchecked exceptions）:RuntimeException及其子类和错误（Error）*
     * 使用方法：1 让checked例外也回滚：在整个方法前加上 @Transactional(rollbackFor=Exception.class)
     *         2 让unchecked例外不回滚： @Transactional(notRollbackFor=RunTimeException.class)
     *         3 不需要事务管理的(只查询的)方法：@Transactional(propagation=Propagation.NOT_SUPPORTED)
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveTeacher(EduTeacher eduTeacher) {
        //创建教师的datakey
        String uid=UUIDUtils.getUUID32();
        eduTeacher.setId(uid);

        //通过工具类创建时间
        eduTeacher.setGmtCreate(LocalDateTimeUtils.FormatNow());
        eduTeacher.setIsDeleted(0);

        String fileInfo = eduTeacher.getFileInfo();
        eduTeacher.setFileInfo("");

        //保存教师信息
        int result=eduTeacherMapper.saveTeacher(eduTeacher);

        //封装讲师表与附加的关联表数据
        EduFileInfoRelation eduFileInfoRelation = new EduFileInfoRelation();
        eduFileInfoRelation.setDatakey(UUIDUtils.getUUID32());
        eduFileInfoRelation.setFileIndirectId(uid);

        //获取EduTeacher中附件信息
        //String fileInfo = eduTeacher.getFileInfo();
        Map map = JsonUtils.jsonToMap(fileInfo);
        eduFileInfoRelation.setFileType(map.get("contentType").toString());
        eduFileInfoRelation.setFileId(map.get("id").toString());
        eduFileInfoRelation.setFileName(map.get("originalFilename").toString());
        eduFileInfoRelation.setCreateTime(LocalDateTimeUtils.FormatNow());
        eduTeacherMapper.saveFileInfoRelation(eduFileInfoRelation);

        return result;
    }

    @Override
    public EduTeacher queryTeacherById(String id) {
        EduTeacher teacher=eduTeacherMapper.queryTeacherById(id);
        return teacher;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateTeacher(EduTeacher eduTeacher) {

        //更新讲师列表
        eduTeacher.setGmtModified(LocalDateTimeUtils.FormatNow());
        eduTeacherMapper.updateTeacher(eduTeacher);

        //删除原来的讲师与附件关联表
        Integer fileRelationNum=eduTeacherMapper.queryFileRelationNum(eduTeacher.getId());
        if (fileRelationNum>0){
            eduTeacherMapper.deleteFileRelation(eduTeacher.getId());
        }

        //保存新的讲师与附件关联表信息
        String fileInfo = eduTeacher.getFileInfo();
        EduFileInfoRelation eduFileInfoRelation = new EduFileInfoRelation();
        eduFileInfoRelation.setDatakey(UUIDUtils.getUUID32());
        eduFileInfoRelation.setFileIndirectId(eduTeacher.getId());
        Map map = JsonUtils.jsonToMap(fileInfo);
        eduFileInfoRelation.setFileType(map.get("contentType").toString());
        eduFileInfoRelation.setFileId(map.get("id").toString());
        if (map.get("originalFilename")!=null){
            eduFileInfoRelation.setFileName(map.get("originalFilename").toString());
        }else if (map.get("name")!=null){
            eduFileInfoRelation.setFileName(map.get("name").toString());
        }else{
            eduFileInfoRelation.setFileName("");
        }

        eduFileInfoRelation.setCreateTime(LocalDateTimeUtils.FormatNow());
        eduTeacherMapper.saveFileInfoRelation(eduFileInfoRelation);


    }

    @Override
    public Map<String, Object> queryFileInfo(String id) {
        String fileId=eduTeacherMapper.queryFileInfoRelationByIndirectId(id);
        if (StringUtils.isNotEmpty(fileId)) {
            Map<String,Object> fileInfo=eduTeacherMapper.queryFileInfoById(fileId);
            return fileInfo;
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchDeleteTeacher(List<String> idLists) {
        //删除edu_teacher表数据
        int result=eduTeacherMapper.batchDeleteTeacher(idLists);

        //删除讲师与附件信息关联表
        idLists.stream().forEach(id->eduTeacherMapper.deleteFileRelation(id));

        return result;
    }

    @Override
    public List<Map<String, Object>> queryListNameByKeyword(String keyword) {

        if (StringUtils.isNotEmpty(keyword)){
            keyword="%"+keyword+"%";
        }else{
            keyword="";
        }
        List<Map<String, Object>> ListName=eduTeacherMapper.queryListNameByKeyword(keyword);
        return ListName;
    }



    /**
     * 公用方法
     * 通过讲师id删除文件信息表，和本地存储的文件
     * @param id
     */
    public void deleteFileInfoTableAndLocalFile(String id){
        if (StringUtils.isNotEmpty(id)){
            EduTeacher teacher = eduTeacherMapper.queryTeacherById(id);
            if (teacher != null){
                String fileId = eduTeacherMapper.queryFileInfoRelationByIndirectId(id);
                if (StringUtils.isNotEmpty(fileId)){
                    Result result=fileService.deleteFile(fileId);
                    //下面的代码要注释掉，因为不需要手动抛出异常，已经有sentinel对其实现了熔断降级处理
                    //如果fileService.deleteFile(fileId)调用异常，或超时。会实现已经写好的降级处理，不要影响删除的主要逻辑。
                    if (result.getSuccess()!=true){
                        log.error("file微服务删除文件失败，请联系开发人员进行排查");
                    }
                }else{
                    throw new RuntimeException("关联表中没有匹配的数据");
                }
            }else{
                throw new RuntimeException("库中没有匹配的数据");
            }

        }else{
            throw new RuntimeException("文件id不能为空");
        }

    }


    @Override
    public Map<String, Object> getTeacherById(String id) {
        EduTeacher teacher=eduTeacherMapper.queryTeacherById(id);
        List<Map<String,Object>> courseInfo=eduTeacherMapper.queryCourseInfoByTeachrId(id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("teacher",teacher);
        map.put("courseList",courseInfo);
        return map;
    }

    @Override
    public List<EduTeacher> getHotTeacher() {
        List<EduTeacher> teacherList=eduTeacherMapper.getHotTeacher();
        return teacherList;


    }
}
