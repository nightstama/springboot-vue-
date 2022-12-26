package com.ns.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BusinessException extends RuntimeException {

    private final Long code;

    public BusinessException(long code, String message) {
        super(message);
        this.code = code;
    }

    public Long getCode() {
        return code;
    }

    @Override
    public void printStackTrace() {
        log.error("业务错误: {},错误内容: {}", getCode(), getMessage());
    }
}
