package com.zty.onlineedu.common.base.result;

import lombok.Getter;
import lombok.ToString;

/**
 * @Author zty
 * @Date 2022/12/3 21:52
 * description 返回状态的枚举类
 */
@Getter
@ToString
public enum ResultCodeEnum {

    //创建枚举类对象 枚举类实例对象用逗号分隔
    SUCCESS(true,20000,"成功"),
    UNKNOWN_REASON(false, 20001, "系统发生错误,请联系管理员"),


    BAD_SQL_GRAMMAR(false, 21001, "sql语法错误"),
    JSON_PARSE_ERROR(false, 21002, "json解析异常"),
    PARAM_ERROR(false, 21003, "参数不正确"),
    TEACHER_LIST_ERROR(false, 21004, "讲师列表返回异常"),
    GET_TEACHER_DATA_ERROR(false, 21005, "获取讲师数据异常"),
    DELETE_TEACHER_DATA_ERROR(false, 21006,"删除讲师数据异常"),
    GET_TEACHER_PAGE_DATA_ERROR(false, 21007,"获取讲师分页数据异常"),
    SAVE_TEACHER_DATA_ERROR(false,21008,"保存讲师信息异常"),
    UPDATE_TEACHER_DATA_ERROR(false,21009,"更新讲师信息异常"),
    BATCH_DELETE_TEACHER_DATA_ERROR(false,21010,"批量删除异常"),
    QUERY_LIST_NAME_ERROR(false,21011,"讲师姓名查询异常"),

    FILE_UPLOAD_ERROR(false, 21004, "文件上传异常"),
    FILE_DELETE_ERROR(false, 21005, "文件刪除错误"),
    EXCEL_DATA_IMPORT_ERROR(false, 21006, "Excel数据导入错误"),
    NEXTED_SUBJECT_DATA_ERROR(false, 21007,"获取嵌套课程异常"),
    SAVE_COURSE_DATA_ERROR(false,21008,"保存课程信息异常"),
    GET_CURRENT_SUBJECT_DATA_ERROR(false,21009,"获取课程分类异常"),
    GET_COURSE_BYID_ERROR(false,21010,"查询课程失败"),
    UPDATE_COURSE_ERROR(false,21011,"更新课程异常"),
    GET_COURSE_PAGE_DATA_ERROR(false, 21012,"获取课程分页数据异常"),

    VIDEO_UPLOAD_ALIYUN_ERROR(false, 22001, "视频上传至阿里云失败"),
    VIDEO_UPLOAD_TOMCAT_ERROR(false, 22002, "视频上传至业务服务器失败"),
    VIDEO_DELETE_ALIYUN_ERROR(false, 22003, "阿里云视频文件删除失败"),
    FETCH_VIDEO_UPLOADAUTH_ERROR(false, 22004, "获取上传地址和凭证失败"),
    REFRESH_VIDEO_UPLOADAUTH_ERROR(false, 22005, "刷新上传地址和凭证失败"),
    FETCH_PLAYAUTH_ERROR(false, 22006, "获取播放凭证失败"),

    URL_ENCODE_ERROR(false, 23001, "URL编码失败"),
    ILLEGAL_CALLBACK_REQUEST_ERROR(false, 23002, "非法回调请求"),
    FETCH_ACCESSTOKEN_FAILD(false, 23003, "获取accessToken失败"),
    FETCH_USERINFO_ERROR(false, 23004, "获取用户信息失败"),
    LOGIN_ERROR(false, 23005, "登录失败"),

    COMMENT_EMPTY(false, 24006, "评论内容必须填写"),

    PAY_RUN(false, 25000, "支付中"),
    PAY_UNIFIEDORDER_ERROR(false, 25001, "统一下单错误"),
    PAY_ORDERQUERY_ERROR(false, 25002, "查询支付结果错误"),

    ORDER_EXIST_ERROR(false, 25003, "课程已购买"),

    GATEWAY_ERROR(false, 26000, "服务不能访问"),

    CODE_ERROR(false, 28000, "验证码错误"),

    LOGIN_PHONE_ERROR(false, 28009, "手机号码不正确"),
    LOGIN_MOBILE_ERROR(false, 28001, "账号不正确"),
    LOGIN_PASSWORD_ERROR(false, 28008, "密码不正确"),
    LOGIN_DISABLED_ERROR(false, 28002, "该用户已被禁用"),
    REGISTER_MOBLE_ERROR(false, 28003, "手机号已被注册"),
    LOGIN_AUTH(false, 28004, "需要登录"),
    LOGIN_ACL(false, 28005, "没有权限"),
    SMS_SEND_ERROR(false, 28006, "短信发送失败"),
    SMS_SEND_ERROR_BUSINESS_LIMIT_CONTROL(false, 28007, "短信发送过于频繁"),
    USER_EXIST_ERROR(false, 28008, "用户已经存在"),
    BUSINESS_ERROR(false,29000,"业务异常,请让开发人员进行检查");

    private Boolean success;
    private Integer code;
    private String message;

    /**
     * 枚举类的构造方法是私有的
     * @param success 是否成功
     * @param code 返回状态码
     * @param message 返回信息
     */
    ResultCodeEnum(Boolean success, Integer code,String message) {
        this.success = success;
        this.code = code;
        this.message = message;

    }


}
