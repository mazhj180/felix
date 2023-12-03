package com.mazhj.common.pojo.claims;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author mazhj
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class Claims {

    private String userId;

    private Long exp;

}
