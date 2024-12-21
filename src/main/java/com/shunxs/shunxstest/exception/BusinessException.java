package com.shunxs.shunxstest.exception;


import com.shunxs.shunxstest.common.ErrorCode;
import lombok.Data;

/**
 * 自定义异常类
 */
@Data
public class BusinessException extends RuntimeException {
    private final String description;
    private final int code;


    public BusinessException(String message, int code, String description) {
        super(message);
        this.code = code;
        this.description = description;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = errorCode.getDescription();
    }

    public BusinessException(ErrorCode errorCode, String description) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = description;
    }


}
