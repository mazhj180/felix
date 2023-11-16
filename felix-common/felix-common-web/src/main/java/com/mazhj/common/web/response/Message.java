package com.mazhj.common.web.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author mazhj
 */
@Data
@Builder
public class Message implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    String message;
}
