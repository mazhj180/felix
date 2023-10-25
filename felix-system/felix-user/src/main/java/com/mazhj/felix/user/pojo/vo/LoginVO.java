package com.mazhj.felix.user.pojo.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author mazhj
 */
@Data
@Accessors(chain = true)
public class LoginVO {

    private String token;

    private String userId;

    private String nickName;

    private String headImgUrl;
}
