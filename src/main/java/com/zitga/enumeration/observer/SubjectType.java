package com.zitga.enumeration.observer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum SubjectType {

    DISCONNECTED(1),

    BATTLE_LEAVE(100);

    private static final Map<Integer, SubjectType> subjectTypeMap = new ConcurrentHashMap<>();

    private final int value;

    SubjectType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static SubjectType toSubjectType(int subjectType) {
        return subjectTypeMap.get(subjectType);
    }

    static {
        SubjectType[] subjectTypes = SubjectType.values();
        for (SubjectType subjectType : subjectTypes) {
            subjectTypeMap.put(subjectType.getValue(), subjectType);
        }
    }
}