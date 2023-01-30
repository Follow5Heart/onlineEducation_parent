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
    /**
     * 保存文件信息
     * @param eduFileInfo
     */
    void saveFileInfo(EduFileInfo eduFileInfo);

    /**
     * 查询文件信息通过文件路径
     * @param fileId
     * @return
     */
    EduFileInfo queryFileInfoById(String fileId);

    /**
     * 通过主键删除文件信息表数据
     * @param id
     * @return
     */
    Integer deleteFileInfo(String id);
}




