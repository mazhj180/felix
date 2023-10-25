package com.mazhj.common.core.exception;

import java.io.Serial;

/**
 * @author mazhj
 */
public class AuthException extends Exception{

    @Serial
    private static final long serialVersionUID = 1L;

    public AuthException(String message) {
        super(message);
    }
}
