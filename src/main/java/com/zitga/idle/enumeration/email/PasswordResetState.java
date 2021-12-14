package com.zitga.idle.enumeration.email;

public enum PasswordResetState {
    INITIAL(0),
    VERIFYING(1),
    ;

    private final int passwordValue;

    PasswordResetState(int value) {
        this.passwordValue = value;
    }

    public int getPasswordValue() {
        return this.passwordValue;
    }

    public static PasswordResetState toPasswordState(int state) {
        return PasswordResetState.values()[state];
    }
}
