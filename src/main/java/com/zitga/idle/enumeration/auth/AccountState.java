package com.zitga.idle.enumeration.auth;

public enum AccountState {
    NORMAL(0),
    BAN(1)
    ;

    private final int value;

    AccountState(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static AccountState toAccountState(int state) {
        return AccountState.values()[state];
    }
}
