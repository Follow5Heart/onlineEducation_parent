package com.zty.onlineedu.edu.controller.admin;

import com.zty.onlineedu.common.base.result.Result;
import com.zty.onlineedu.common.base.result.ResultCodeEnum;
import com.zty.onlineedu.common.base.utils.ExceptionUtils;
import com.zty.onlineedu.edu.pojo.entity.EduChapter;
import com.zty.onlineedu.edu.pojo.vo.ChapterVo;
import com.zty.onlineedu.edu.service.EduChapterService;
import com.zty.onlineedu.service.base.exceptions.GeneralException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author zty
 * @Date 2022/12/3 14:02
 */
@Api(tags = "章节管理")
@Log4j2
@CrossOrigin
@RestController
@RequestMapping("/service-edu/admin/edu/chapter")
public class ChapterController {

    @Autowired
    private EduChapterService eduChapterService;


    @ApiOperation("新增章节")
    @PostMapping("/saveChapter")
    public Result saveChapter(@ApiParam(value = "章节信息", required = true)
                              @RequestBody EduChapter eduChapter) {

        try {
            Boolean result = eduChapterService.saveChapter(eduChapter);
            if (result) {
                return Result.ok().message("保存成功");
            } else {
                return Result.error().message("保存失败");
            }
        } catch (Exception e) {
            log.error(ExceptionUtils.getExceptionMessage(e));
            throw new GeneralException(ResultCodeEnum.SAVE_CHAPTER_ERROR);
        }
    }

    @ApiOperation("根据id查询章节")
    @GetMapping("/getChapter/{chapterId}")
    public Result getChapterById(@ApiParam(value = "章节id", required = true)
                                 @PathVariable String chapterId) {
        try {
            EduChapter eduChapter = eduChapterService.getChapterById(chapterId);
            if (eduChapter != null) {
                return Result.ok().data("items", eduChapter);
            } else {
                return Result.error().message("当前章节数据不存在");
            }

        } catch (Exception e) {
            log.error(ExceptionUtils.getExceptionMessage(e));
            throw new GeneralException(ResultCodeEnum.GET_CHAPTER_BY_ID_ERROR);
        }
    }

    @ApiOperation("更新章节")
    @PutMapping("/updateChapter")
    public Result updateChapter(@ApiParam(value = "章节信息", required = true)
                                @RequestBody EduChapter eduChapter) {
        try {
            Boolean result = eduChapterService.updateChapter(eduChapter);
            if (result) {
                return Result.ok().message("更新章节成功");
            } else {
                return Result.error().message("更新章节失败");
            }
        } catch (Exception e) {
            log.error(ExceptionUtils.getExceptionMessage(e));
            throw new GeneralException(ResultCodeEnum.UPDATE_CHAPTER_ERROR);

        }

    }

    @ApiOperation("删除章节")
    @DeleteMapping("deleteChapter/{chapterId}")
    public Result deleteChapter(@ApiParam(value = "章节id", required = true)
                                @PathVariable String chapterId) {
        try {
            Integer flag = eduChapterService.deleteChapter(chapterId);
            if (flag == 1) {
                return Result.ok().message("删除成功");
            } else if (flag == 2) {
                return Result.error().message("删除失败");
            } else if (flag == 0) {
                return Result.error().message("当前章节存在课时，不能删除");
            }else{
                return Result.error().message("删除章节数据异常");
            }
        } catch (Exception e) {
            log.error(ExceptionUtils.getExceptionMessage(e));
            throw new GeneralException(ResultCodeEnum.DELETE_CHAPTER_ERROR);

        }

    }

    @ApiOperation("获取嵌套章节数据列表")
    @GetMapping("getNestedList/{courseId}")
    public Result getNestedListByCourseId(@ApiParam(value = "课程id", required = true)
                                          @PathVariable String courseId) {
        try {
            List<ChapterVo> chapterVoList = eduChapterService.getNestedListByCourseId(courseId);
            if (chapterVoList != null && chapterVoList.size() > 0) {
                return Result.ok().data("items", chapterVoList);
            } else {
                return Result.error().message("获取嵌套章节数据列表为空");
            }
        } catch (Exception e) {
            log.error(ExceptionUtils.getExceptionMessage(e));
            throw new GeneralException(ResultCodeEnum.GET_NESTED_LIST_CHAPTER_DATA_ERROR);
        }

    }


}
