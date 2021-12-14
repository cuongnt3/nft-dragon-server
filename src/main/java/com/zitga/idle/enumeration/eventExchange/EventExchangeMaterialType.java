package com.zitga.idle.enumeration.eventExchange;

public enum EventExchangeMaterialType {
    HERO(0),
    ITEM_EQUIPMENT(1),
    ITEM_ARTIFACT(2),
    ;

    private final int value;

    EventExchangeMaterialType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static EventExchangeMaterialType toType(int type) {
        return EventExchangeMaterialType.values()[type];
    }
}
