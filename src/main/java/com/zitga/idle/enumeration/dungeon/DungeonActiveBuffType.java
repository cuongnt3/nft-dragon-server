package com.zitga.idle.enumeration.dungeon;

public enum DungeonActiveBuffType {
    ACTIVE_BUFF_NORMAL(1),
    ACTIVE_BUFF_REVIVE(2);

    private final int value;

    DungeonActiveBuffType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static DungeonActiveBuffType toDungeonActiveBuffType(int type) {
        return DungeonActiveBuffType.values()[type - 1];
    }
}