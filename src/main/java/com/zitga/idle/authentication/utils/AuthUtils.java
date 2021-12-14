package com.zitga.idle.authentication.utils;

import com.zitga.idle.authentication.constant.AuthConstant;
import com.zitga.support.encryption.HashHelper;

public class AuthUtils {

    public static String calculateSaltedPassword(String password) {
        String result = password;
        for (int i = 0; i < AuthConstant.PASSWORD_SALT_LOOP; i++) {
            result = HashHelper.hashSHA256(result);
//            result = HashHelper.hashSHA256(result + AuthConstant.PASSWORD_SALT);
        }

        return result;
    }
}