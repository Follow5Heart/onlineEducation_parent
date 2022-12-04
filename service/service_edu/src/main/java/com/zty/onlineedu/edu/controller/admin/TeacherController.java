package com.zty.onlineedu.edu.controller.admin;

import com.zty.onlineedu.common.base.result.Result;
import com.zty.onlineedu.edu.entity.EduTeacher;
import com.zty.onlineedu.edu.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author zty
 * @Date 2022/12/3 13:53
 */
@Api(description  = "讲师管理")
@RestController
@RequestMapping("/admin/edu/teacher")
public class TeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation("所有的讲师列表")
    @GetMapping("/list")
    public Result teacherList(){
        List<EduTeacher> teacherList=eduTeacherService.teacherList();
        return Result.ok().data("items", teacherList);

    }

    @ApiOperation("获取路径变量和路径参数变量并打印")
    @GetMapping("/{id}")
    public void pringParams(@PathVariable("id") String id, @RequestParam("name") String name){
        System.out.println(id);
        System.out.println(name);

    }

    /**
     * 删除接口
     */
    @ApiOperation("删除讲师当条数据")
    @DeleteMapping("/delete")
    public Result deleteData(EduTeacher eduTeacher){

        Integer resultCount=eduTeacherService.deleteData(eduTeacher);
        if (resultCount>0){
            return Result.ok().message("删除成功");
        }
        return Result.error().message("删除失败");

    }



}
