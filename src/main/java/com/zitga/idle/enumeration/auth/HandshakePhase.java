package com.zitga.idle.enumeration.auth;

public enum HandshakePhase {
    CHALLENGE(0),
    RESPONSE(1),
    HAND_SHAKE(2),
    ;

    private final int value;

    HandshakePhase(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static HandshakePhase toHandshakePhase(int phase) {
        return HandshakePhase.values()[phase];
    }
}
