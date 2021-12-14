package com.zitga.idle.enumeration.reward;

public enum RewardState {
    NOT_AVAILABLE(0),
    AVAILABLE_TO_CLAIM(1),
    CLAIMED(2);

    private final int value;

    RewardState(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static RewardState toRewardState(int type) {
        return RewardState.values()[type];
    }
}
