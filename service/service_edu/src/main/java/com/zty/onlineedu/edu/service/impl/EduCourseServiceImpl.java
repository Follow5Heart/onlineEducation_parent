package com.zty.onlineedu.edu.service.impl;

import com.zty.onlineedu.common.base.utils.JsonUtils;
import com.zty.onlineedu.common.base.utils.LocalDateTimeUtils;
import com.zty.onlineedu.common.base.utils.StringUtils;
import com.zty.onlineedu.common.base.utils.UUIDUtils;
import com.zty.onlineedu.edu.feign.FileService;
import com.zty.onlineedu.edu.feign.VodService;
import com.zty.onlineedu.edu.mapper.EduCourseMapper;
import com.zty.onlineedu.edu.pojo.dto.CourseInfoFormDto;
import com.zty.onlineedu.edu.pojo.entity.EduCourse;
import com.zty.onlineedu.edu.pojo.entity.EduCourseDescription;
import com.zty.onlineedu.edu.pojo.entity.EduFileInfoRelation;
import com.zty.onlineedu.edu.pojo.query.CourseQueryParam;
import com.zty.onlineedu.edu.pojo.query.WebCourseQueryParam;
import com.zty.onlineedu.edu.pojo.vo.CoursePublishVo;
import com.zty.onlineedu.edu.pojo.vo.CourseVo;
import com.zty.onlineedu.edu.pojo.vo.WebCourseVo;
import com.zty.onlineedu.edu.service.EduCourseService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
* @author 17939
* @description 针对表【edu_course(课程)】的数据库操作Service实现
* @createDate 2022-12-03 13:52:58
*/
@Log4j2
@Service
public class EduCourseServiceImpl implements EduCourseService{

    @Autowired
    private EduCourseMapper eduCourseMapper;

    @Autowired
    private FileService fileService;

    @Autowired
    private VodService vodService;

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

        //处理文件附件信息 进行关联保存
        String fileInfo = courseInfoFormDto.getFileInfo();
        Map<String, Object> map = JsonUtils.jsonToMap(fileInfo);
        EduFileInfoRelation eduFileInfoRelation = new EduFileInfoRelation();
        eduFileInfoRelation.setDatakey(UUIDUtils.getUUID32());
        eduFileInfoRelation.setFileIndirectId(courseId);
        eduFileInfoRelation.setFileType(map.get("contentType")==null?"":map.get("contentType").toString());
        eduFileInfoRelation.setFileId(map.get("id")==null?"":map.get("id").toString());
        if (map.get("originalFilename")!=null){
            eduFileInfoRelation.setFileName(map.get("originalFilename").toString());
        }else if (map.get("name")!=null){
            eduFileInfoRelation.setFileName(map.get("name").toString());
        }else{
            eduFileInfoRelation.setFileName("");
        }
        eduFileInfoRelation.setCreateTime(LocalDateTimeUtils.FormatNow());
        eduCourseMapper.saveFileInfoRelation(eduFileInfoRelation);



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
    @Transactional(rollbackFor = Exception.class)
    public void updateCourse(CourseInfoFormDto courseInfoFormDto) {

        if (courseInfoFormDto.getId()==null){
            throw new RuntimeException("课程id不能为null");
        }
        //更新edu_course表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoFormDto,eduCourse);
        eduCourse.setGmtModified(LocalDateTimeUtils.FormatNow());
        //课程状态 更新方法 默认的为未发布
        eduCourse.setStatus(EduCourse.DRAFT);
        eduCourseMapper.updateCourse(eduCourse);


        //更新edu_course_description表
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoFormDto.getDescription());
        eduCourseDescription.setId(courseInfoFormDto.getId());
        eduCourseDescription.setGmtModified(LocalDateTimeUtils.FormatNow());
        eduCourseMapper.updateCourseDescription(eduCourseDescription);


        //获取当前的fileInfo信息，判断是否为空，如果为空说明没有更新过图像，不做处理，如果不为空，说明更新过图像
        String fileInfo = courseInfoFormDto.getFileInfo();
        if (StringUtils.isNotEmpty(fileInfo)) {
            //删除原来的讲师与附件关联表
            Integer fileRelationNum = eduCourseMapper.queryFileRelationNum(courseInfoFormDto.getId());
            if (fileRelationNum > 0) {
                eduCourseMapper.deleteFileRelation(courseInfoFormDto.getId());
            }

            //保存新的讲师与附件关联表信息
            EduFileInfoRelation eduFileInfoRelation = new EduFileInfoRelation();
            eduFileInfoRelation.setDatakey(UUIDUtils.getUUID32());
            eduFileInfoRelation.setFileIndirectId(courseInfoFormDto.getId());
            Map map = JsonUtils.jsonToMap(fileInfo);
            eduFileInfoRelation.setFileType(map.get("contentType").toString());
            eduFileInfoRelation.setFileId(map.get("id").toString());
            if (map.get("originalFilename") != null) {
                eduFileInfoRelation.setFileName(map.get("originalFilename").toString());
            } else if (map.get("name") != null) {
                eduFileInfoRelation.setFileName(map.get("name").toString());
            } else {
                eduFileInfoRelation.setFileName("");
            }

            eduFileInfoRelation.setCreateTime(LocalDateTimeUtils.FormatNow());
            eduCourseMapper.saveFileInfoRelation(eduFileInfoRelation);

        }
    }

    @Override
    public List<CourseVo> courseList(CourseQueryParam courseQueryParam) {
        List<CourseVo> courseList=eduCourseMapper.courseList(courseQueryParam);
        return courseList;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteCourseInfo(String courseId) {
        //删除课程表信息 edu_course
        eduCourseMapper.deleteCourseById(courseId);

        //删除课程中的简介描述 edu_course_description
        eduCourseMapper.deleteCourseDescriptionById(courseId);

        //通过课程id查询，文件关联表
        String fileId=eduCourseMapper.queryFileRelationFileId(courseId);


        if (StringUtils.isNotEmpty(fileId)){
            //异步删除文件
            CompletableFuture.runAsync(()->{
                //远程调用删除文件
                fileService.deleteFile(fileId);

            });
        }

        //删除文件关联表
        eduCourseMapper.deleteFileRelation(courseId);

        //通过课程id，查询章节id
        String[] chapterIds=eduCourseMapper.queryChapterIds(courseId);
        //如果章节id存在
        if (chapterIds!=null && chapterIds.length>0){
            //删除章节信息
            eduCourseMapper.deleteChapterByIds(chapterIds);

            //通过章节id，获取视频信息
            List<Map<String,Object>> videoMapLists=eduCourseMapper.queryVideoInfoIdByChapterId(chapterIds,courseId);

            //删除视频信息
            eduCourseMapper.deleteVideoByIds(videoMapLists);


            List<String> videoInfoIds = videoMapLists.stream().map(videoMap -> videoMap.get("videoInfoId").toString()).collect(Collectors.toList());
            log.info("----------------->正在批量删除视频");
            vodService.batchRemoveVideoByIds(videoInfoIds);


        }


    }

    @Override
    public CoursePublishVo getCoursePublishById(String courseId) {
        CoursePublishVo coursePublishVo=eduCourseMapper.getCoursePublishById(courseId);
        return coursePublishVo;
    }

    @Override
    public void publishCourse(String courseId) {
        //修改课程的发布状态
        eduCourseMapper.updateCourseStatus(courseId);
    }

    @Override
    public List<CourseVo> getCourseList(WebCourseQueryParam webCourseQueryParam) {
        List<CourseVo> courseVoList=eduCourseMapper.getCourseList(webCourseQueryParam);
        return courseVoList;

    }

    @Override
    public WebCourseVo getWebCourseInfo(String courseId) {
        //通过课程id，查询课程信息
        EduCourse courseInfo = eduCourseMapper.getCourseInfo(courseId);
        courseInfo.setViewCount((Integer.valueOf(courseInfo.getViewCount())+1)+"");
        //更新课程的浏览量
        eduCourseMapper.updateCourse(courseInfo);

        //获取课程的详细信息
        WebCourseVo webCourseVo=eduCourseMapper.getWebCourseInfo(courseId);
        return webCourseVo;
    }

    @Override
    @Cacheable(value = "index",key = "'getHotCourse'")
    public List<CourseVo> getHotCourse() {

        List<CourseVo> courseVoList=eduCourseMapper.getHotCourse();
        return courseVoList;
    }
}
