package com.mazhj.felix.user.pojo.model;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author mazhj
 */
@Data
public class Bookshelf implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String userId;

    private String bookId;
}
