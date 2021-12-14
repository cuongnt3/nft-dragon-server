package com.zitga.idle.authentication.constant;

public class AuthConstant {

    // use to concatenate with password to generate a hashed-password
    public static final String PASSWORD_SALT = "cuongnt3";

    // number salt loop
    // 10000 loop is recommended by security expert
    // For the simplicity sake of this project, only use a much smaller value
    public static final int PASSWORD_SALT_LOOP = 1;
    public static final int PASSWORD_WRONG_LIMIT = 20;

    public static final int VERSION_NUMBER_COUNT_DEFAULT = 3;

    public static final int USER_NAME_LENGTH_MIN = 6;
    public static final int USER_NAME_LENGTH_MAX = 20;

    public static final int CHANGE_DEVICE_INTERVAL = 1800;

    // authorized
    public static final int DEVICE_ID = 1;
    public static final int HASH_PASSWORD = 2;
}
