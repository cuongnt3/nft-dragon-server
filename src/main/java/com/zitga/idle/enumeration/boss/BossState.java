package com.zitga.idle.enumeration.boss;

public enum BossState {
    NOT_CREATE(1),
    DEAD(2),
    ALIVE(3),
    ;

    private final int value;

    BossState(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static BossState toType(int type) {
        return BossState.values()[type - 1];
    }
}
