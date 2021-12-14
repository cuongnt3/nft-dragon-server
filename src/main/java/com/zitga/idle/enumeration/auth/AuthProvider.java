package com.zitga.idle.enumeration.auth;

public enum AuthProvider {
    WINDOWS_DEVICE(0),

    GOOGLE_PLAY_SERVICE(1),
    APPLE_GAME_CENTER(2),
    FACEBOOK(3),

    ANDROID_DEVICE(4),
    IOS_DEVICE(5),
    ;

    private final int value;

    AuthProvider(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static AuthProvider toAuthProvider(int provider) {
        return AuthProvider.values()[provider];
    }
}
