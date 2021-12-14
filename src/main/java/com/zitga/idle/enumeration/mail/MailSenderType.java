package com.zitga.idle.enumeration.mail;

public enum MailSenderType {
    PLAYER(0),
    SYSTEM(1),
    ADMIN(2),
    MODERATOR(3),
    ;

    private final int value;

    MailSenderType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static MailSenderType toMailSenderType(int type) {
        return MailSenderType.values()[type];
    }
}
