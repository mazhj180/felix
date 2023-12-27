package com.mazhj.common.core.exception;

import java.io.Serial;

/**
 * @author mazhj
 */
public class ExcelParseException extends Exception{
    @Serial
    private static final long serialVersionUID = 1L;

    public ExcelParseException(String message) {
        super(message);
    }
}
