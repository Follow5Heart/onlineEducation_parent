package com.zty.onlineedu.files.service.impl;

import cn.xuyanwu.spring.file.storage.FileInfo;
import com.zty.onlineedu.files.entity.vo.EduFileInfo;
import com.zty.onlineedu.files.mapper.EduFileInfoMapper;
import com.zty.onlineedu.files.service.FileDetailService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author zty
 * @Date 2023/1/16 0:11
 */
@Service
public class FileDetailServiceImpl implements FileDetailService {

    @Autowired
    private EduFileInfoMapper mapper;

    /**
     * 保存文件信息到数据库
     */
    @SneakyThrows
    @Override
    public boolean record(FileInfo fileInfo) {
        //封装数据
        EduFileInfo eduFileInfo = new EduFileInfo();
        eduFileInfo.setId(fileInfo.getId());
        eduFileInfo.setName(fileInfo.getFilename());
        return false;
    }

    @Override
    public FileInfo getByUrl(String url) {
        return null;
    }

    @Override
    public boolean delete(String url) {
        return false;
    }
}
