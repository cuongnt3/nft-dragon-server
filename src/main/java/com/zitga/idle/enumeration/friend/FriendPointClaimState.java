package com.zitga.idle.enumeration.friend;

public enum FriendPointClaimState {

    NOT_AVAILABLE_TO_CLAIM(0),
    AVAILABLE_TO_CLAIM(1),
    CLAIMED(2),
    ;

    private final int value;

    FriendPointClaimState(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static FriendPointClaimState toClaimState(int raw) {
        return FriendPointClaimState.values()[raw];
    }
}
