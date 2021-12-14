package com.zitga.idle.enumeration.chat;

public enum ChatMessageType {
    PLAIN_TEXT(0),
    GUILD_WAR_INVITE(1),
    GUILD_RECRUIT(2),
    ADMIN_MESSAGE(3),
    MAINTENANCE_MESSAGE(4),
    DOMAIN_CREW_RECRUIT(5),
    ;

    private final int value;

    ChatMessageType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static ChatMessageType toChatMessageType(int type) {
        return ChatMessageType.values()[type];
    }
}
