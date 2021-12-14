package com.zitga.idle.enumeration.giftCode;

public enum GiftCodeAuthProvider {
    GUEST(0),

    GOOGLE_PLAY_SERVICE(1),
    APPLE_GAME_CENTER(2),
    FACEBOOK(3),
    ;

    private final int value;

    GiftCodeAuthProvider(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static GiftCodeAuthProvider toAuthProvider(int provider) {
        return GiftCodeAuthProvider.values()[provider];
    }
}
