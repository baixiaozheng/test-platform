package com.czn.testplatform.common.vo;

public enum ErrorCode {
    SUCCESS(0, "成功"),
    SERVER_EXCEPTION(250, "系统繁忙,请稍候再试"),
    FAIL(251, "失败"),
    MISS_PARAM(252, "缺少参数"),
    PARAM_ERROR(253, "参数异常"),
    STATUS_ERROR(254, "状态异常"),
//    CONCURRENT_ERROR(255, "操作频繁,请稍候再试"),
//    DATABASE_ERROR(256, "数据库操作错误"),
//    ID_ERROR(257, "id不存在或错误"),
//    OFFLINE(258, "上下线状态不正确"),
//    SERVER_FAILD(259, "服务调用失败"),
//    TOKEN_EXPIRED(261, "token过期"),
//    TOKEN_INVALID(262, "无效的token"),
//    CHECK_PARAM_FAILED(263, "参数校验失败"),
//    SAVE_FAILED(264, "保存失败"),
//    DELETE_FAILED(265, "删除失败"),
//    UPDATE_FAILED(266, "上传失败"),
//    USER_NOT_EXIST(20501, "用户不存在"),
//    INVAILD_USER(20502, "无效的用户"),
//    USER_LOGIN_DEVICE_ERROR(20503, "您的账号已在其他设备登录"),
//    SMS_CODE_ERROR(20610, "验证码无效"),
//    UNKNOW_ERROR(20305, "未知异常，请联系管理员");
;
    private int code;
    private String mesg;

    private ErrorCode(final int code, final String mesg) {
        this.code = code;
        this.mesg = mesg;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(final int code) {
        this.code = code;
    }

    public String getMesg() {
        return this.mesg;
    }

    public void setMesg(final String mesg) {
        this.mesg = mesg;
    }
}
