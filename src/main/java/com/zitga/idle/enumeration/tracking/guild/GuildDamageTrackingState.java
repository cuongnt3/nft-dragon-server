package com.zitga.idle.enumeration.tracking.guild;

public enum GuildDamageTrackingState {
    GUILD_BOSS(0),
    GUILD_DUNGEON(1),
    GUILD_WAR(2),
    ;

    private final int value;

    GuildDamageTrackingState(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static GuildDamageTrackingState toTrackingState(int state) {
        return GuildDamageTrackingState.values()[state];
    }
}
