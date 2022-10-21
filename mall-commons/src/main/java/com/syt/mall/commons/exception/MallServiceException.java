package com.syt.mall.commons.exception;

import com.syt.mall.commons.restful.ResponseCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 业务异常
 *
 * @author sytsnb@gmail.com
 * @date 2022 2022/10/21 15:19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MallServiceException extends RuntimeException {
    private ResponseCode responseCode;

    public MallServiceException(ResponseCode responseCode, String message) {
        super(message);
        setResponseCode(responseCode);
    }
}
