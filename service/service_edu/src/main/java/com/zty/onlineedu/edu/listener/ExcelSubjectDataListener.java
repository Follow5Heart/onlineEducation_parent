package com.zty.onlineedu.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.zty.onlineedu.edu.entity.execl.ExcelSubjectData;
import lombok.extern.log4j.Log4j2;

/**
 * @Author zty
 * @Date 2023/2/5 15:31
 */
@Log4j2
public class ExcelSubjectDataListener extends AnalysisEventListener<ExcelSubjectData> {
    /**
     * 遍历每一行数据
     * @param excelSubjectData
     * @param analysisContext
     */
    @Override
    public void invoke(ExcelSubjectData excelSubjectData, AnalysisContext analysisContext) {
        log.info("解析到一条记录：{}",excelSubjectData);
        /*处理读取出来的数据*/
        //一级标题
        String levelOneTitle = excelSubjectData.getLevelOneTitle();
        //二级标题
        String levelTwoTitle = excelSubjectData.getLevelTwoTitle();
        log.info("levelOneTitle:{}",levelOneTitle);
        log.info("levelTwoTitle:{}",levelTwoTitle);



    }

    /**
     * 所有数据读取之后的收尾工作
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("全部数据解析完成");

    }
}
