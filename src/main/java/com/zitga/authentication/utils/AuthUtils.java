package com.zitga.authentication.utils;

import com.zitga.authentication.constant.AdminAuthConstant;
import com.zitga.support.encryption.HashHelper;

public class AuthUtils {

    public static String calculateSaltedPassword(String password) {
        String result = password;
        for (int i = 0; i < AdminAuthConstant.PASSWORD_SALT_LOOP; i++) {
            result = HashHelper.hashSHA256(result + AdminAuthConstant.PASSWORD_SALT);
        }

        return result;
    }
}