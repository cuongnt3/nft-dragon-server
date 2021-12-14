package com.zitga.idle.enumeration.chat;

public enum ChatChannel {
    WORLD(0),
    GUILD(1),
    RECRUIT(2),
    SYSTEM(3),
    DOMAINS_CREW(4),
    DOMAINS_RECRUIT(5),
    ;

    private final int value;

    ChatChannel(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static ChatChannel toChatChannel(int channel) {
        return ChatChannel.values()[channel];
    }
}
