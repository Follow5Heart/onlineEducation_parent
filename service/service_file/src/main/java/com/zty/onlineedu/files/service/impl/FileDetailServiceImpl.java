package com.zty.onlineedu.files.service.impl;

import cn.xuyanwu.spring.file.storage.FileInfo;
import com.zty.onlineedu.common.base.utils.LocalDateTimeUtils;
import com.zty.onlineedu.files.entity.vo.EduFileInfo;
import com.zty.onlineedu.files.mapper.EduFileInfoMapper;
import com.zty.onlineedu.files.service.FileDetailService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

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
    @Transactional(rollbackFor = Exception.class)
    public boolean record(FileInfo fileInfo) {

        //封装数据信息
        String datakey=UUID.randomUUID().toString().replace("-", "").toLowerCase();
        EduFileInfo eduFileInfo = new EduFileInfo();
        eduFileInfo.setId(datakey);
        eduFileInfo.setName(fileInfo.getOriginalFilename());
        //String[] split = fileInfo.getFilename().split(".");

        eduFileInfo.setContentType(fileInfo.getContentType());
        eduFileInfo.setFileSize(fileInfo.getSize().toString());
        eduFileInfo.setPath(fileInfo.getPath());
        eduFileInfo.setUrl(fileInfo.getUrl());
        eduFileInfo.setSource("LOCAL");
        eduFileInfo.setCreateTime(LocalDateTimeUtils.FormatNow());
        mapper.saveFileInfo(eduFileInfo);

        //修改回显的id
        fileInfo.setId(datakey);

        return true;


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
