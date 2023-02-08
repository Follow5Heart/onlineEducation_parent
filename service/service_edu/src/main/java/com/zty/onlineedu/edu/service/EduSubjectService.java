package com.zty.onlineedu.edu.service;

import com.zty.onlineedu.edu.entity.vo.NextedSubjectVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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

    /**
     * 获取嵌套课程数据
     * @return
     */
    List<NextedSubjectVo> nextedSubject();

}
