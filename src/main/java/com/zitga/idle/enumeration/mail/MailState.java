package com.zitga.idle.enumeration.mail;

public enum MailState {
    NEW(0),
    OPENED(1),
    REWARD_CLAIMED(2);

    private final int value;

    MailState(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static MailState toMailState(int type) {
        return MailState.values()[type];
    }
}
