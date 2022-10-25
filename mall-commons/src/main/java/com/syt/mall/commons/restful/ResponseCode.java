package com.syt.mall.commons.restful;

import lombok.Getter;

/**
 * 错误代码枚举类型
 *
 * @author sytsnb@gmail.com
 * @date 2022 2022/10/21 17:07
 */
public enum ResponseCode {
    OK(200),
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404),
    NOT_ACCEPTABLE(406),
    CONFLICT(409),
    INTERNAL_SERVER_ERROR(500);

    @Getter
    private final Integer value;

    ResponseCode(Integer value) {
        this.value = value;
    }

}
