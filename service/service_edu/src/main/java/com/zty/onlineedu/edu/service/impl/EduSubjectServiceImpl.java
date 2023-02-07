package com.zty.onlineedu.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.zty.onlineedu.edu.entity.execl.ExcelSubjectData;
import com.zty.onlineedu.edu.listener.ExcelSubjectDataListener;
import com.zty.onlineedu.edu.mapper.EduSubjectMapper;
import com.zty.onlineedu.edu.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
* @author 17939
* @description 针对表【edu_subject(课程科目)】的数据库操作Service实现
* @createDate 2022-12-03 13:53:08
*/
@Service
public class EduSubjectServiceImpl implements EduSubjectService{

    @Autowired
    private EduSubjectMapper eduSubjectMapper;

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    @Transactional
    public void batchImport(MultipartFile file) throws IOException {
        //获取文件名
        String fileName= file.getOriginalFilename();
        String[] splitName = fileName.split("\\.");
        String fileType=splitName[splitName.length-1];
        if ("xls".equals(fileType)){
            EasyExcel.read(file.getInputStream(), ExcelSubjectData.class, new ExcelSubjectDataListener(eduSubjectMapper,redisTemplate))
                    .excelType(ExcelTypeEnum.XLS)
                    .sheet()
                    .doRead();
        }else if("xlsx".equals(fileType)){
            EasyExcel.read(file.getInputStream(), ExcelSubjectData.class, new ExcelSubjectDataListener())
                    .excelType(ExcelTypeEnum.XLSX)
                    .sheet()
                    .doRead();
        }else{
            throw new RuntimeException("只能解析xls,xlsx为后缀的文件");
        }



    }
}
