package com.zty.onlineedu.vod.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author zty
 * @Date 2023/3/15 23:40
 * 分块文件上传接受参数 实体类，专门对接了vue-simple-uploader的参数
 */
@Component
@Data
@ApiModel("大文件分块入参实体")
public class ChunkMultipartFileParam {

    @ApiModelProperty("当前为第几块")
    private Integer chunkNumber;

    @ApiModelProperty("当前分块上限大小")
    private Long chunkSize;

    @ApiModelProperty("当前分块的真实大小")
    private Long currentChunkSize;

    @ApiModelProperty("文件的总大小")
    private Long totalSize;

    @ApiModelProperty("文件的唯一标识")
    private String identifier;

    @ApiModelProperty("文件名")
    private String filename;

    @ApiModelProperty("文件的相对路径")
    private String relativePath;

    @ApiModelProperty("总块数")
    private Integer totalChunks;

    @ApiModelProperty("分块文件传输对象")
    private MultipartFile file;


}
