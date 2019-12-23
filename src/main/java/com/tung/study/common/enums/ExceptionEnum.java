package com.tung.study.common.enums;

/**
 * @Description 所有业务异常的枚举
 */
public enum ExceptionEnum {

    OK(200, "成功"),
    PARAM_NULL(201, "参数为空"),
    PARAM_ERROR(202, "参数有误"),
    NOT_FOUND(404, "Not Found"),
    SERVER_ERROR(500, "服务器异常"),


    //token
    TOKEN_NULL(301, "未获取到token!"),
    TOKEN_UNUSE(302, "token失效!"),

    //user
    USER_UNEXIST(401,"用户不存在!"),
    USER_ERROR_USERNAME_OR_PASSWORD(402,"用户名或密码错误!");

    ExceptionEnum(int code, String message) {
        this.friendlyCode = code;
        this.friendlyMsg = message;
    }

    ExceptionEnum(int code, String message, String urlPath) {
        this.friendlyCode = code;
        this.friendlyMsg = message;
        this.urlPath = urlPath;
    }

    private int friendlyCode;

    private String friendlyMsg;

    private String urlPath;

    public int getCode() {
        return friendlyCode;
    }

    public void setCode(int code) {
        this.friendlyCode = code;
    }

    public String getMessage() {
        return friendlyMsg;
    }

    public void setMessage(String message) {
        this.friendlyMsg = message;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }

}
