package com.ns.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultJson<T> implements Serializable {
    private static final long serialVersionUID = 783015033603078674L;
    private int errCode;
    private String errMsg;
    private T data;

    public static ResultJson ok() {
        return ok("");
    }

    public static ResultJson ok(Object o) {
        return new ResultJson(ResultCode.SUCCESS, o);
    }

    public static ResultJson failure(ResultCode code) {
        return failure(code, "");
    }

    public static ResultJson failure(ResultCode code, Object o) {
        return new ResultJson(code, o);
    }

    public ResultJson (ResultCode resultCode) {
        setResultCode(resultCode);
    }

    public ResultJson (ResultCode resultCode,T data) {
        setResultCode(resultCode);
        this.data = data;
    }

    public void setResultCode(ResultCode resultCode) {
        this.errCode = resultCode.getCode();
        this.errMsg = resultCode.getMsg();
    }

    @Override
    public String toString() {
        return "{" +
                "\"errCode\":" + errCode +
                ", \"errMsg\":\"" + errMsg + '\"' +
                ", \"data\":\"" + data + '\"'+
                '}';
    }
}
