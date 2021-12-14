package com.zitga.idle.enumeration.quest;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum QuestDataType {

    // hero
    DAILY_QUEST(1),
    ACHIEVEMENT(2),
    QUEST_TREE(3),
    ;

    private static final Map<Integer, QuestDataType> questTypeMap = new ConcurrentHashMap<>();

    private final int value;

    QuestDataType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static QuestDataType toQuestType(int questType) {
        return questTypeMap.get(questType);
    }

    static {
        QuestDataType[] questTypes = QuestDataType.values();
        for (QuestDataType questType : questTypes) {
            questTypeMap.put(questType.getValue(), questType);
        }
    }
}