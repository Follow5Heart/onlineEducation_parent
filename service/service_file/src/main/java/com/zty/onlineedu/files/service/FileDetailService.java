package com.zty.onlineedu.files.service;

import cn.xuyanwu.spring.file.storage.recorder.FileRecorder;

/**
 * @Author zty
 * @Date 2023/1/16 0:07
 */

public interface FileDetailService extends FileRecorder {
    /**
     * 删除本地文件和文件信息表数据
     * @param fileId
     * @return
     */
    boolean deleteFile(String fileId);

    /**
     * 通过文件路径删除文件信息
     * @param imageUrl 文件路径
     * @return 成功与否
     */
    boolean deleteFileByUrl(String imageUrl);

}
