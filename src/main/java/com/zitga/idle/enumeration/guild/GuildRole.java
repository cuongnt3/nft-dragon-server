package com.zitga.idle.enumeration.guild;

public enum GuildRole {

    MEMBER(0),
    SUB_LEADER(1),
    LEADER(2),
    ;

    private final int value;

    GuildRole(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static GuildRole toGuildRole(int role) {
        return GuildRole.values()[role];
    }
}