package com.zitga.idle.enumeration.dungeon;

public enum DungeonBuffType {
    ACTIVE_BUFF(1),
    PASSIVE_BUFF(2);

    private final int value;

    DungeonBuffType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static DungeonBuffType toDungeonBuffType(int type) {
        return DungeonBuffType.values()[type - 1];
    }
}
