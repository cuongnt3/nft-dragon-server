package com.zitga.idle.enumeration.guild;

public enum GuildLogActionType {

    JOIN_GUILD(0),
    LEAVE_GUILD(1),
    ASSIGN_LEADER(2),
    ASSIGN_SUB_LEADER(3),
    KICK_OUT(4),
    ;

    private final int value;

    GuildLogActionType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static GuildLogActionType toActionType(int type) {
        return GuildLogActionType.values()[type];
    }
}