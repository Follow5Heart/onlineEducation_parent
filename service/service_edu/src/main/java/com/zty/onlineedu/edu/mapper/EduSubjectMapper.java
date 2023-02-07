package com.zty.onlineedu.edu.mapper;

import com.zty.onlineedu.edu.entity.EduSubject;
import org.springframework.stereotype.Service;

/**
* @author 17939
* @description 针对表【edu_subject(课程科目)】的数据库操作Mapper
* @createDate 2022-12-03 13:53:08
* @Entity com.zty.onlineedu.edu.entity.EduSubject
*/
@Service
public interface EduSubjectMapper{


    void saveSubject(EduSubject eduSubject);

}
