package com.zty.onlineedu.edu.mapper;

import com.zty.onlineedu.edu.entity.EduSubject;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 17939
* @description 针对表【edu_subject(课程科目)】的数据库操作Mapper
* @createDate 2022-12-03 13:53:08
* @Entity com.zty.onlineedu.edu.entity.EduSubject
*/
@Service
public interface EduSubjectMapper{

    /**
     * 保存课程数据
     * @param eduSubject
     */
    void saveSubject(EduSubject eduSubject);

    /**
     *  通过父级id，查询所有的数据与参数匹配的父级id
     * @param parentId 父级id
     * @return
     */
    List<EduSubject> querySubjectByParentId(String parentId);


}
