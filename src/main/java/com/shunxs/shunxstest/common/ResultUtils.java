package com.shunxs.shunxstest.common;

import com.shunxs.shunxstest.BaseResponse;
import lombok.Data;

/**
 * 返回工具类
 */
@Data
public class ResultUtils {

    /**
     * 成功
     * @param data
     * @return
     * @param <T>
     */
    public static<T> BaseResponse success(T data){
        return new BaseResponse(0, data, "ok");
    }

    /**
     * 失败
     * @param errorCode
     * @return
     * @param <T>
     */

    public static<T> BaseResponse error(ErrorCode errorCode){
        return new BaseResponse(errorCode);
    }

    /**
     * 失败
     * @param errorCode
     * @param message
     * @param description
     * @return
     */
    public static BaseResponse error(ErrorCode errorCode, String message, String description){
        return new BaseResponse(errorCode.getCode(), null, message, description);
    }

    /**
     * 失败
     * @param errorCode
     * @param description
     * @return
     */

    public static BaseResponse error(ErrorCode errorCode, String description){
        return new BaseResponse(errorCode.getCode(), null, errorCode.getMessage(), description);
    }


    /**
     * 失败
     * @param code
     * @param message
     * @param description
     * @return
     */

    public static BaseResponse error(int code, String message, String description){
        return new BaseResponse(code, null, message, description);
    }
}

