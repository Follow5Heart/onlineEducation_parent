package com.zty.onlineedu.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.zty.onlineedu.edu.pojo.entity.EduSubject;
import com.zty.onlineedu.edu.pojo.dto.ExcelSubjectDataDto;
import com.zty.onlineedu.edu.pojo.vo.NextedSubjectVo;
import com.zty.onlineedu.edu.listener.ExcelSubjectDataListener;
import com.zty.onlineedu.edu.mapper.EduSubjectMapper;
import com.zty.onlineedu.edu.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 17939
 * @description 针对表【edu_subject(课程科目)】的数据库操作Service实现
 * @createDate 2022-12-03 13:53:08
 */
@Service
public class EduSubjectServiceImpl implements EduSubjectService {

    @Autowired
    private EduSubjectMapper eduSubjectMapper;

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchImport(MultipartFile file) throws IOException {
        //获取文件名
        String fileName = file.getOriginalFilename();
        String[] splitName = fileName.split("\\.");
        String fileType = splitName[splitName.length - 1];
        if ("xls".equals(fileType)) {
            EasyExcel.read(file.getInputStream(), ExcelSubjectDataDto.class, new ExcelSubjectDataListener(eduSubjectMapper, redisTemplate))
                    .excelType(ExcelTypeEnum.XLS)
                    .sheet()
                    .doRead();
        } else if ("xlsx".equals(fileType)) {
            EasyExcel.read(file.getInputStream(), ExcelSubjectDataDto.class, new ExcelSubjectDataListener())
                    .excelType(ExcelTypeEnum.XLSX)
                    .sheet()
                    .doRead();
        } else {
            throw new RuntimeException("只能解析xls,xlsx为后缀的文件");
        }


    }

    @Override
    public List<NextedSubjectVo> nextedSubject() {
        List<NextedSubjectVo> resultList = new ArrayList<>();

        //获取所有父级id为0的数据
        List<EduSubject> subjectList = eduSubjectMapper.querySubjectByParentId("0");

        //判空
        if (subjectList != null && subjectList.size() > 0) {
            //遍历数据
            for (EduSubject eduSubject : subjectList) {
                //获取当前id
                String id = eduSubject.getId();

                //开始封装数据
                NextedSubjectVo nextedSubjectVo = new NextedSubjectVo();
                nextedSubjectVo.setId(id);
                nextedSubjectVo.setTitle(eduSubject.getTitle());
                nextedSubjectVo.setSort(eduSubject.getSort());

                //拿当前的id去查询所有与之匹配的父级id
                List<EduSubject> nextedSubjectData = eduSubjectMapper.querySubjectByParentId(id);
                if (nextedSubjectData != null && nextedSubjectData.size() > 0) {
                    List<NextedSubjectVo> nextSubjectList = new ArrayList<>();
                    for (EduSubject subject : nextedSubjectData) {
                        //开始封装数据
                        NextedSubjectVo nextedSubjectVo1 = new NextedSubjectVo();
                        nextedSubjectVo1.setId(subject.getId());
                        nextedSubjectVo1.setTitle(subject.getTitle());
                        nextedSubjectVo1.setSort(subject.getSort());
                        nextSubjectList.add(nextedSubjectVo1);
                        nextedSubjectVo.setChildren(nextSubjectList);
                    }
                }

                resultList.add(nextedSubjectVo);
            }
        }

        return resultList;
    }

    @Override
    public List<EduSubject> getCurrentSubjectList(String parentId) {
        List<EduSubject> currentSubjectList=eduSubjectMapper.querySubjectByParentId(parentId);
        return currentSubjectList;
    }
}
