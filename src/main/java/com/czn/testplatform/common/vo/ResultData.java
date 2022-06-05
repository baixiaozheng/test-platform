package com.czn.testplatform.common.vo;

import java.io.Serializable;

public class ResultData<T> implements Serializable {
    //"错误码"
    private int event;
   //"信息"
    private String message;
    //"数据"
    private T data;

    public ResultData() {
        this.event = ErrorCode.SUCCESS.getCode();
        this.message = ErrorCode.SUCCESS.getMesg();
    }

    public ResultData(T data) {
        this.event = ErrorCode.SUCCESS.getCode();
        this.message = ErrorCode.SUCCESS.getMesg();
        this.data = data;
    }

    public ResultData(int event, String message) {
        this.event = ErrorCode.SUCCESS.getCode();
        this.message = ErrorCode.SUCCESS.getMesg();
        this.event = event;
        this.message = message;
    }

    public ResultData(int event, String message, T data) {
        this.event = ErrorCode.SUCCESS.getCode();
        this.message = ErrorCode.SUCCESS.getMesg();
        this.event = event;
        this.message = message;
        this.data = data;
    }

    public static <T> ResultData<T> success(T data) {
        return new ResultData(data);
    }

    public static <T> ResultData<T> success(String message, T data) {
        return new ResultData(ErrorCode.SUCCESS.getCode(), message, data);
    }

    public static ResultData success() {
        return success((Object)null);
    }

    public static ResultData fail() {
        return new ResultData(ErrorCode.SERVER_EXCEPTION.getCode(), ErrorCode.SERVER_EXCEPTION.getMesg());
    }

    public static ResultData fail(ErrorCode errorCode) {
        return new ResultData(errorCode.getCode(), errorCode.getMesg());
    }

    public static ResultData fail(String errorMessage) {
        return new ResultData(ErrorCode.SERVER_EXCEPTION.getCode(), errorMessage);
    }

    public static ResultData fail(Integer errorcode, String errorMessage) {
        return new ResultData(errorcode, errorMessage);
    }

    public static <T> ResultData<T> fail(String message, T data) {
        return new ResultData(ErrorCode.FAIL.getCode(), message, data);
    }

    public static <T> ResultData<T> fail(Integer errorcode, String errorMessage, T data) {
        return new ResultData(errorcode, errorMessage, data);
    }

    public Boolean isSuccess() {
        return this.event == ErrorCode.SUCCESS.getCode() ? true : false;
    }

    public Boolean isFail() {
        return this.event != ErrorCode.SUCCESS.getCode() ? true : false;
    }

    public void assertSuccess() {
        if (this.event != ErrorCode.SUCCESS.getCode()) {
            throw new RuntimeException( this.message);
        }
    }

    public int getEvent() {
        return this.event;
    }

    public String getMessage() {
        return this.message;
    }

    public T getData() {
        return this.data;
    }

    public void setEvent(final int event) {
        this.event = event;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public void setData(final T data) {
        this.data = data;
    }
}
