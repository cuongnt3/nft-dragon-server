package com.zitga.idle.enumeration.dungeon;

public enum DungeonShopType {
    CHILD(1),
    WOMEN(2),
    GRANNY(3);

    private final int value;

    DungeonShopType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static DungeonShopType toDungeonShopType(int type) {
        return DungeonShopType.values()[type - 1];
    }
}
