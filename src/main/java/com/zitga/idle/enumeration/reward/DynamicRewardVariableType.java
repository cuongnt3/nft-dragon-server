package com.zitga.idle.enumeration.reward;

public enum DynamicRewardVariableType {
    SUMMONER_LEVEL(0),
    VIP_LEVEL(1),
    ;

    private final int value;

    DynamicRewardVariableType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static DynamicRewardVariableType toType(int type) {
        return DynamicRewardVariableType.values()[type];
    }
}
