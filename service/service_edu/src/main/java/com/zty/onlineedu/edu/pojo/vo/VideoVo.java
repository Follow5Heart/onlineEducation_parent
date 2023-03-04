package com.zty.onlineedu.edu.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author zty
 * @Date 2023/3/4 10:50
 * 章节下的每节视频
 */
@Data
public class VideoVo implements Serializable {
    private static final long serialVersionUID =1L;

    private String id;
    private String title;
    private String isFree;
    private String sort;

    private String videoSourceId;
}
