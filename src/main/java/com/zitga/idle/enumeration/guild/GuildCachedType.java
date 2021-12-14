package com.zitga.idle.enumeration.guild;

public enum GuildCachedType {

    GUILD_BASIC_DATA(0),
    GUILD_RECOMMENDED_DATA(2),
    GUILD_APPLICATION_DATA(3),
    GUILD_LOG_DATA(4),

    GUILD_BOSS_DATA(6),
    GUILD_DUNGEON_DATA(7),
    GUILD_WAR_DATA(8),

    EVENT_GUILD_BOSS_DATA(9),
    GUILD_MEMBER_CREW_DATA(10),
    ;

    private final int value;

    GuildCachedType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static GuildCachedType toCachedType(int raw) {
        return GuildCachedType.values()[raw];
    }
}
