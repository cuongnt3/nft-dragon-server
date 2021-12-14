package com.zitga.idle.enumeration.dungeon;

public enum DungeonRewardType {
    CHEST(1),
    BUFF(2),
    SHOP(3);

    private final int value;

    DungeonRewardType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static DungeonRewardType toDungeonRewardType(int type) {
        return DungeonRewardType.values()[type - 1];
    }
}
