package com.bluefish.seckill.util;

import java.util.UUID;

/**
 * @author bluefish 2018/1/21
 * @version 1.0.0
 */
public class UUIDUtil {

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

}
