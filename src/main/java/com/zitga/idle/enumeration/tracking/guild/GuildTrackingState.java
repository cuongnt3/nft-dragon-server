package com.zitga.idle.enumeration.tracking.guild;

public enum GuildTrackingState {
    CREATE(0),
    REQUEST_JOIN(1),
    ACCEPT_JOIN(2),
    LEAVE(3),
    KICK(4),
    GUILD_DELETE(5),
    GUILD_CHECK_IN(6),
    ;

    private final int value;

    GuildTrackingState(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static GuildTrackingState toTrackingState(int state) {
        return GuildTrackingState.values()[state];
    }
}
