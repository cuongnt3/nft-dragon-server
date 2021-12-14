package com.zitga.idle.enumeration.remoteConfig;

public enum RemoteConfigValueType {
    BOOLEAN(1),
    INTEGER(2),
    STRING(3),
    LONG(4),
    ;

    private final int value;

    RemoteConfigValueType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static RemoteConfigValueType toValueType(int type) {
        return RemoteConfigValueType.values()[type - 1];
    }
}
