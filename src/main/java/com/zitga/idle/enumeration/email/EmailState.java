package com.zitga.idle.enumeration.email;

public enum EmailState {
    NOT_EXISTED(0),
    VERIFYING(1),
    VERIFIED(2),
    ;

    private final int emailValue;

    EmailState (int value) {
        this.emailValue = value;
    }

    public int getValue() {
        return this.emailValue;
    }

    public static EmailState toEmailState(int state) {
        return EmailState.values()[state];
    }
}
