package com.ns.common;

import com.alibaba.fastjson.JSON;
import com.ns.exception.BusinessException;
import lombok.Data;

import java.io.Serializable;
import java.util.function.Supplier;

/**
 * 接口统一返回包装类
 */
@Data
public class ResultData<T> implements Serializable {
    private boolean ret;
    private Long errCode;
    private String errMsg;
    private T data;

    public ResultData() {
    }

    public ResultData(T data){
        this.setRet(true);
        this.setData(data);
    }
    public ResultData(long code, String message) {
        this.setRet(false);
        this.setErrCode(code);
        this.setErrMsg(message);
    }

    public ResultData(Supplier<T> s) {
        try {
            this.setData(s.get());
            this.setRet(true);
        } catch (BusinessException be) {
            this.setRet(false);
            this.setErrCode(be.getCode());
            this.setErrMsg(be.getMessage());
            be.printStackTrace();
        } catch (Exception e) {
            this.setRet(false);
            this.setErrCode(50004L);
            this.setErrMsg("服务器错误");
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return  JSON.toJSONString(this);
    }
}
