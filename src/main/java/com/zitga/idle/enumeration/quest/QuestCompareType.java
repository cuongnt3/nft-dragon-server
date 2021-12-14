package com.zitga.idle.enumeration.quest;

public enum QuestCompareType {
    LESS(1),
    LESS_OR_EQUAL(2),
    EQUAL(3),
    GREATER_OR_EQUAL(4),
    GREATER(5),
    ;

    private final int value;

    QuestCompareType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static QuestCompareType toCompareType(int state) {
        return QuestCompareType.values()[state - 1];
    }
}
