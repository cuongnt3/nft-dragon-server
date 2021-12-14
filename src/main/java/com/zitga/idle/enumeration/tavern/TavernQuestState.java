package com.zitga.idle.enumeration.tavern;

public enum TavernQuestState {
    WAITING(0),
    DOING(1),
    DONE_REWARD_NOT_CLAIM(2),
    DONE(3),
    ;

    private final int value;

    TavernQuestState(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static TavernQuestState toQuestState(int state) {
        return TavernQuestState.values()[state];
    }
}
