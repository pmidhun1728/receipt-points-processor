package com.receipt.utils;

import java.util.UUID;

public class CommonUtils {

    public static String randomIdGenerator() {
        String id = UUID.randomUUID().toString();
        return id;
    }

}
