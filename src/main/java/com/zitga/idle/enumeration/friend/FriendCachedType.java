package com.zitga.idle.enumeration.friend;

public enum FriendCachedType {

    FRIEND_DATA(0),
    FRIEND_REQUEST_DATA(1),
    FRIEND_POINT_ACTION_DATA(2),

    UNUSED_1(3),
    UNUSED_2(4),

    FRIEND_RECOMMENDED_DATA(5),

    FRIEND_SUPPORT_HERO(6),
    FRIEND_CREW_STATE(7),
    ;

    private final int value;

    FriendCachedType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static FriendCachedType toCachedType(int raw) {
        return FriendCachedType.values()[raw];
    }
}
