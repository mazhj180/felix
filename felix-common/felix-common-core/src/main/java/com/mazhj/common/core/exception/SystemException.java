package com.mazhj.common.core.exception;

import java.io.Serial;

/**
 * 系统异常
 * @author mazhj
 */
public class SystemException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public SystemException(String message) {
        super(message);
    }

    public SystemException(Throwable e){
        super(e);
    }
}
