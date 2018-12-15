package com.angelo.domain;

public class Result<T> {
    private Integer code; // 状态码

    private String message; // 状态描述信息

    private T data; // 定义为范型

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }
}
