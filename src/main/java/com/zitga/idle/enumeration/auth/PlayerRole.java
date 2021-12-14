package com.zitga.idle.enumeration.auth;

public enum PlayerRole {
    PLAYER(0),
    GAME_MASTER(1),
    ADMIN(2),
    BOT(3),
    ;

    private final int value;

    PlayerRole(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static PlayerRole toPlayerRole(int role) {
        return PlayerRole.values()[role];
    }
}
