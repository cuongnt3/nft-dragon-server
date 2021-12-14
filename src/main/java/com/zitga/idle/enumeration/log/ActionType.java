package com.zitga.idle.enumeration.log;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum ActionType {
    AUTHENTICATION(1),
    PURCHASE_IAP(2)
    ;

    private static final Map<Integer, ActionType> actionTypeMap = new ConcurrentHashMap<>();

    private final int value;

    ActionType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static ActionType toActionType(int type) {
        return actionTypeMap.get(type);
    }

    static {
        ActionType[] actionTypes = ActionType.values();
        for (ActionType actionType : actionTypes) {
            actionTypeMap.put(actionType.getValue(), actionType);
        }
    }
}
