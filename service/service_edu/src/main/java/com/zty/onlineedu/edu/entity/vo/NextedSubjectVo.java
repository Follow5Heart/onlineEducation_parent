package com.zty.onlineedu.edu.entity.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zty
 * @Date 2023/2/8 0:06
 * 返回给前端的嵌套课程数据
 */
@Data
public class NextedSubjectVo {
    private String title;
    private String id;
    private String sort;

    private List<NextedSubjectVo> children=new ArrayList<>();
}
