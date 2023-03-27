package com.ns.exception;

import com.ns.entity.ResultJson;
import lombok.Data;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
    private ResultJson resultJson;
    public CustomException(ResultJson resultJson) {
        this.resultJson = resultJson;
    }
}
