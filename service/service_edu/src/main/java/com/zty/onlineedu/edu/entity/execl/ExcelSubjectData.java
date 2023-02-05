package com.zty.onlineedu.edu.entity.execl;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Author zty
 * @Date 2023/2/5 15:27
 */
@Data
public class ExcelSubjectData {
    @ExcelProperty(value = "一级分类")
    private String levelOneTitle;

    @ExcelProperty(value = "二级分类")
    private String levelTwoTitle;
}
