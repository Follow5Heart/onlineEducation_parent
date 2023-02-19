package com.zty.onlineedu.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.zty.onlineedu.common.base.utils.LocalDateTimeUtils;
import com.zty.onlineedu.common.base.utils.UUIDUtils;
import com.zty.onlineedu.edu.pojo.entity.EduSubject;
import com.zty.onlineedu.edu.pojo.dto.ExcelSubjectDataDto;
import com.zty.onlineedu.edu.mapper.EduSubjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;

/**
 * @Author zty
 * @Date 2023/2/5 15:31
 */
@Log4j2
@AllArgsConstructor
@NoArgsConstructor
public class ExcelSubjectDataListener extends AnalysisEventListener<ExcelSubjectDataDto> {

    private EduSubjectMapper eduSubjectMapper;
    private RedisTemplate redisTemplate;
    /**
     * 遍历每一行数据
     * @param excelSubjectDataDto
     * @param analysisContext
     */
    @Override
    public void invoke(ExcelSubjectDataDto excelSubjectDataDto, AnalysisContext analysisContext) {
        log.info("解析到一条记录：{}", excelSubjectDataDto);
        /*处理读取出来的数据*/
        //一级标题
        String levelOneTitle = excelSubjectDataDto.getLevelOneTitle();
        //二级标题
        String levelTwoTitle = excelSubjectDataDto.getLevelTwoTitle();
        log.info("levelOneTitle:{}",levelOneTitle);
        log.info("levelTwoTitle:{}",levelTwoTitle);

        /*对读取的数据进行保存*/
        //封装数据
        EduSubject excelMapOneLevel = new EduSubject();
        String levelOneId= UUIDUtils.getUUID32();
        excelMapOneLevel.setId(levelOneId);
        excelMapOneLevel.setTitle(levelOneTitle);
        excelMapOneLevel.setParentId("0");
        excelMapOneLevel.setSort("");
        excelMapOneLevel.setGmtCreate(LocalDateTimeUtils.FormatNow());
        excelMapOneLevel.setGmtModified("");

        EduSubject excelMapTwoLevel = new EduSubject();
        String levelTwoId= UUIDUtils.getUUID32();
        excelMapTwoLevel.setId(levelTwoId);
        excelMapTwoLevel.setTitle(levelTwoTitle);
        excelMapTwoLevel.setParentId(levelOneId);
        excelMapTwoLevel.setSort("");
        excelMapTwoLevel.setGmtCreate(LocalDateTimeUtils.FormatNow());
        excelMapTwoLevel.setGmtModified("");

        //在redis判断是否存在
        HashOperations hashOperations = redisTemplate.opsForHash();
        Boolean existResult = hashOperations.hasKey("subjectTitle",levelOneTitle);
        if (!existResult){
            //如果不存在，加入到redis中
            hashOperations.put("subjectTitle",levelOneTitle,levelOneId);

            //保存到数据库
            eduSubjectMapper.saveSubject(excelMapOneLevel);
            eduSubjectMapper.saveSubject(excelMapTwoLevel);
        }else{
            ///取值
            String oldId = (String) hashOperations.get("subjectTitle",levelOneTitle);
            excelMapTwoLevel.setParentId(oldId);
            eduSubjectMapper.saveSubject(excelMapTwoLevel);
        }







    }

    /**
     * 所有数据读取之后的收尾工作
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("全部数据解析完成");
        //删除存在redis中的数据
        Set subjectTitle = redisTemplate.keys("subjectTitle");
        redisTemplate.delete(subjectTitle);


    }
}
