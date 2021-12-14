package com.zitga.idle.enumeration.auth;

public enum AuthMethod {
    LOGIN_BY_TOKEN(0),
    LOGIN_BY_USER_NAME(1),

    REGISTER_BY_TOKEN(2),
    REGISTER_BY_USER_NAME(3),

    SWITCH_ACCOUNT(4),

    LOGIN_BY_FACEBOOK(5),
    REGISTER_BY_FACEBOOK(6),

    BIND_ACCOUNT_BY_USER_NAME(7),
    BIND_ACCOUNT_BY_FACEBOOK(8),

    LOGIN_BY_APPLE(9),
    REGISTER_BY_APPLE(10),

    BIND_ACCOUNT_BY_APPLE(11),

    LOGIN_BY_GOOGLE(12),
    REGISTER_BY_GOOGLE(13),

    BIND_ACCOUNT_BY_GOOGLE(14),

    LOGOUT(15),

    LOGIN_BY_SUN_GAME(16),
    REGISTER_BY_SUN_GAME(17),
    BIND_ACCOUNT_BY_SUN_GAME(18),

    LOGIN_BY_SUN_GAME_USER_ID(19),
    ;

    private final int value;

    AuthMethod(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static AuthMethod toAuthMethod(int method) {
        return AuthMethod.values()[method];
    }
}