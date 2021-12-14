package com.zitga.idle.enumeration.log;

public enum BenchMarkRecordType {
    AUTHORIZED_HANDLER(1),
    UNAUTHORIZED_HANDLER(2),
    LISTENER(3),
    ;

    private final int value;

    BenchMarkRecordType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}