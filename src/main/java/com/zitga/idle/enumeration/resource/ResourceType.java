package com.zitga.idle.enumeration.resource;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum ResourceType {
    DRAGON(1),
    MONEY(2),
    EGG(3),
    EGG_FRAGMENT(4),


    ITEM_EQUIPMENT(5),
    ;

    private static final Map<Integer, ResourceType> resourceTypeMap = new ConcurrentHashMap<>();
    private final int value;

    ResourceType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static ResourceType toResourceType(int type) {
        return resourceTypeMap.get(type);
    }

    static {
        ResourceType[] resourceTypes = ResourceType.values();
        for (ResourceType resourceType : resourceTypes) {
            resourceTypeMap.put(resourceType.getValue(), resourceType);
        }
    }
}
