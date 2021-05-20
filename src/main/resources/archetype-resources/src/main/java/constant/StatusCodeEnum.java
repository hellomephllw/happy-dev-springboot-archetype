package ${package}.constant;

import ${package}.exception.TokenAlreadyExpiredException;
import ${package}.exception.TokenMissingException;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 错误码枚举
 * @author: liliwen
 * @date: 2019-01-05
 */
@Getter
@AllArgsConstructor
public enum StatusCodeEnum {

    SUCCESS_CODE(1, "请求成功", null),
    ERROR_CODE(0, "请求失败", null),
    TOKEN_ALREADY_EXPIRED(10001, "token已过期", TokenAlreadyExpiredException.class),
    TOKEN_MISSING(10002, "token缺失", TokenMissingException.class),
    ;

    /**错误码*/
    private int errorCode;
    /**错误描述*/
    private String errorDesc;
    /**异常*/
    private Class exceptionClass;

}
