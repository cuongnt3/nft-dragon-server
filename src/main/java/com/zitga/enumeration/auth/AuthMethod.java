package com.zitga.enumeration.auth;

public enum AuthMethod {
    LOGIN_BY_USER_NAME(1),
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