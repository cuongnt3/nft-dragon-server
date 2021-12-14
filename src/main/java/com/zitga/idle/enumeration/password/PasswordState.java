package com.zitga.idle.enumeration.password;

public enum PasswordState {

    VERIFYING(0),
    VERIFIED(1),
    ;

    private final int passwordValue;

    PasswordState (int value) {
        this.passwordValue = value;
    }

    public int getValue() {
        return this.passwordValue;
    }

    public static PasswordState toPasswordState(int state) {
        return PasswordState.values()[state];
    }
}
