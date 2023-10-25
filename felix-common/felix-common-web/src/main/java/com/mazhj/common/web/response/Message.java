package com.mazhj.common.web.response;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author mazhj
 */
@Data
public class Message implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    String message;
}
