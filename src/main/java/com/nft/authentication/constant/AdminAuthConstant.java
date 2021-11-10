package com.nft.authentication.constant;

public class AdminAuthConstant {

    // use to concatenate with password to generate a hashed-password
    public static final String PASSWORD_SALT = "zitga6789";

    // number salt loop
    // 10000 loop is recommended by security expert
    // For the simplicity sake of this project, only use a much smaller value
    public static final int PASSWORD_SALT_LOOP = 10;

    public static final int PASSWORD_WRONG_LIMIT = 20;

    public static final int NO_ADMIN_ACTION = -1;
}
