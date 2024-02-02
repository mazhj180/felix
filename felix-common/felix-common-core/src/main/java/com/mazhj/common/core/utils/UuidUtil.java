package com.mazhj.common.core.utils;

import java.util.UUID;

/**
 * @author mazhj
 */
public final class UuidUtil {

    public static String generateUuid(){
        return UUID.randomUUID().toString().replace("-","");
    }

}
