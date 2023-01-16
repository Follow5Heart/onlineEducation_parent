package com.zty.onlineedu.files.mapper;

import com.zty.onlineedu.files.entity.vo.EduFileInfo;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 17939
* @description 针对表【edu_file_info(文件信息表)】的数据库操作Mapper
* @createDate 2023-01-16 00:18:27
* @Entity com.zty.onlineedu.files.entity.vo.EduFileInfo
*/
@Mapper
public interface EduFileInfoMapper {

    void saveFileInfo(EduFileInfo eduFileInfo);

}




