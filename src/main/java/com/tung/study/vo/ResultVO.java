package com.tung.study.vo;

import java.io.Serializable;

/**
 *
 * @author wilbert
 * @date 2018/3/29
 */
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = 913786688582274735L;
    private String errMsg;
    private Integer errCode;
    private Boolean success;
    private T data;

    public ResultVO() {
        this.errCode = 0;
        this.errMsg = "操作成功";
    }

    public ResultVO(Integer errCode, String errMsg, T data) {
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.data = data;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
