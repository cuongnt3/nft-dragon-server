package com.zitga.idle.enumeration.domains;

public enum DomainCrewCachedType {

    CREW_BASIC_DATA(0),
    CREW_RECOMMENDED_DATA(1),
    CREW_APPLICATION_DATA(2),
    CREW_INVITATION_DATA(3),
    PUSH_UPDATE_NOTIFICATION(4);

    private final int value;

    DomainCrewCachedType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static DomainCrewCachedType toCachedType(int raw) {
        return DomainCrewCachedType.values()[raw];
    }

}
