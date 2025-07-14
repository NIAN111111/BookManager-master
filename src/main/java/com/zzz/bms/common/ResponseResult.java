package com.zzz.bms.common;

public class ResponseResult {
    private int code;
    private String message;
    private Object data;

    // 构造方法
    public ResponseResult() {}

    public ResponseResult(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // Getters 和 Setters
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    // 静态方法，用于快速创建成功或失败的响应
    public static ResponseResult success() {
        return new ResponseResult(0, "操作成功", null);
    }

    public static ResponseResult success(Object data) {
        return new ResponseResult(0, "操作成功", data);
    }

    public static ResponseResult error(String message) {
        return new ResponseResult(500, message, null);
    }
}