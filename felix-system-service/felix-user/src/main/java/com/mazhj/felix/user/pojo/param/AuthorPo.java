package com.mazhj.felix.user.pojo.param;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * @author mazhj
 */
@Data
@Accessors(chain = true)
public class AuthorPo {

    private String realName;

    private String pseudonym;

    private String idCard;

    private Date date;

    private String sex;

    private String introduction;

    private List<String> type;

}
