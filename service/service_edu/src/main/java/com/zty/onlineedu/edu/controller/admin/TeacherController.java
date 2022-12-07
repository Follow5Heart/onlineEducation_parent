package com.zty.onlineedu.edu.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zty.onlineedu.common.base.result.Result;
import com.zty.onlineedu.edu.entity.EduTeacher;
import com.zty.onlineedu.edu.entity.vo.TeacherQueryVo;
import com.zty.onlineedu.edu.service.EduTeacherService;
import com.zty.onlineedu.service.base.exceptions.BusinessException;
import com.zty.onlineedu.service.base.exceptions.CheckUserNameException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.zty.onlineedu.common.base.result.ResultCodeEnum.BUSINESS_ERROR;

/**
 * @Author zty
 * @Date 2022/12/3 13:53
 */
@Api(tags  = "讲师管理")
@RestController
@Log4j2
@RequestMapping("/admin/edu/teacher")
public class TeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation("所有的讲师列表")
    @GetMapping("/list")
    public Result teacherList(){
        TeacherQueryVo teacherQueryVo = new TeacherQueryVo();
        List<EduTeacher> teacherList=eduTeacherService.teacherList(teacherQueryVo);
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
    public Result deleteData(@RequestBody EduTeacher eduTeacher){

        Integer resultCount=eduTeacherService.deleteData(eduTeacher);
        if (resultCount>0){
            return Result.ok().message("删除成功");
        }
        return Result.error().message("删除失败");

    }

    /**
     * 讲师分页列表
     * @return
     */
    @ApiOperation("讲师分页列表")
    @GetMapping("/list/{page}/{limit}")
    public Result listPate(@ApiParam("当前页码") @PathVariable("page") int page,
                           @ApiParam("每页记录数") @PathVariable("limit") int limit,
                           @ApiParam("查询对象") @RequestBody TeacherQueryVo teacherQueryVo){
        PageHelper.clearPage();
        PageHelper.startPage(page, limit);
        List<EduTeacher> teacherList = eduTeacherService.teacherList(teacherQueryVo);
        log.info(teacherList);
        PageInfo<EduTeacher> eduTeacherPageInfo = new PageInfo<>(teacherList, limit);

        return Result.ok().data("items",eduTeacherPageInfo);

    }

    /**
     * 保存讲师对象数据
     * @param eduTeacher
     * @return
     */
    @ApiOperation("保存讲师信息")
    @PostMapping("/saveTeacher")
    public Result saveTeacher(@RequestBody @ApiParam("讲师信息对象") EduTeacher eduTeacher){

        int result=eduTeacherService.saveTeacher(eduTeacher);
        if (result>0){
            return Result.ok();
        }else{
            return Result.error().message("保存失败");
        }

    }

    /**
     * 测试自定义异常，如果出现了异常，交由全局异常进行处理，并且有全局异常返回响应体
     * @return
     */
    @GetMapping("/error")
    public Result getError(){
        if (0==0){
            throw new BusinessException(BUSINESS_ERROR.getMessage());
        }
        return Result.ok();


    }

    /**
     * 测试编译期异常
     */
    @GetMapping("/error1/{name}")
    public Result customException(@ApiParam("用户名") @PathVariable String name) throws CheckUserNameException {

        //这里我就先业务层，处理逻辑了
        List<String> list = new ArrayList<>();
        list.add("张三");
        list.add("王五");
        list.add("赵六");
        CheckUserNameException.checkUserName(list,name);

        return Result.ok().message("用户不存在，可以放心使用！");




    }





}
