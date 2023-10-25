package com.mazhj.common.core.exception;

import java.io.Serial;

/**
 * @author mazhj
 */
public class BusinessException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

}
