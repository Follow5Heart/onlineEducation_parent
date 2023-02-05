package com.zty.onlineedu.edu.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
* @author 17939
* @description 针对表【edu_subject(课程科目)】的数据库操作Service
* @createDate 2022-12-03 13:53:08
*/
public interface EduSubjectService{
    /**
     * 批量导入excel数据
     * @param file
     */
    void batchImport(MultipartFile file) throws IOException;

}
