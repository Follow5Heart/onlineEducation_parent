package com.zty.onlineedu.common.base.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author zty
 * @Date 2022/12/3 21:51
 * description 统一返回结果类
 */
@Data
@NoArgsConstructor
@ApiModel("全局统一返回结果")
public class Result implements Serializable {

    private static final long serialVersionUID = 411731814484355577L;

    @ApiModelProperty("是否成功")
    private Boolean success;
    @ApiModelProperty("返回码")
    private Integer code;
    @ApiModelProperty("返回消息")
    private String message;
    @ApiModelProperty("返回的数据体")
    private Map<String,Object> data=new HashMap<String,Object>();

    public static Result ok(){
        Result result = new Result();
        result.setSuccess(ResultCodeEnum.SUCCESS.getSuccess());
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;

    }



    public static Result error(){
        Result result = new Result();
        result.setSuccess(ResultCodeEnum.UNKNOWN_REASON.getSuccess());
        result.setCode(ResultCodeEnum.UNKNOWN_REASON.getCode());
        result.setMessage(ResultCodeEnum.UNKNOWN_REASON.getMessage());
        return result;

    }

    public static Result setResult(ResultCodeEnum resultCodeEnum){
        Result result = new Result();
        result.setSuccess(resultCodeEnum.getSuccess());
        result.setCode(resultCodeEnum.getCode());
        result.setMessage(resultCodeEnum.getMessage());
        return result;

    }

    public Result success(Boolean success){
        this.setSuccess(success);
        return this;

    }


    public Result code(Integer code){
        this.setCode(code);
        return this;

    }

    public Result message(String message){
        this.setMessage(message);
        return this;
    }


    public Result data(String key,Object value){
        this.data.put(key,value);
        return this;

    }


    public Result data(Map<String,Object> map){
        this.setData(map);
        return this;

    }


}
