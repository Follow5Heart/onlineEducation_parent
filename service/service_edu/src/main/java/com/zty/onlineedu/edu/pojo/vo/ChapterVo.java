package com.zty.onlineedu.edu.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author zty
 * @Date 2023/3/4 10:54
 * 每章信息  children存储这每节信息
 */
@Data
public class ChapterVo implements Serializable {
    private static final long serialVersionUID =1L;

    private String id;
    private String title;
    private String sort;
    private List<VideoVo> children=new ArrayList<>();
}
