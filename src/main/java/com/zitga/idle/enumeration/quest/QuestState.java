package com.zitga.idle.enumeration.quest;

public enum QuestState {
    /**
     * before bind data to quest
     */
    INITIAL(0),

    /**
     * locked, but quest is still calculated
     */
    LOCKED(1),

    /**
     * locked, but quest won't be calculated
     */
    LOCKED_NOT_CALCULATED(2),

    /**
     *
     */
    DOING(3),

    /**
     * quest is finished, but player didn't claim quest reward
     */
    DONE_REWARD_NOT_CLAIM(4),

    /**
     * quest is finished and player already claimed quest reward
     */
    COMPLETED(5),

    /**
     * quest should be hidden from player
     */
    HIDDEN(6),

    /**
     * quest should be show from player but not calculate process
     */
    SHOWN_NOT_CALCULATED(7);

    private final int value;

    QuestState(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static QuestState toQuestState(int state) {
        return QuestState.values()[state];
    }
}