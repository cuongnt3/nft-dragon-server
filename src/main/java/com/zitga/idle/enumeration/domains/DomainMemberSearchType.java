package com.zitga.idle.enumeration.domains;

public enum DomainMemberSearchType {
    FRIEND_ID(1),
    FRIEND_LIST(2),
    GUILD_MEMBER_LIST(3);

    private final int value;

    DomainMemberSearchType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static DomainMemberSearchType toType(int raw) {
        return DomainMemberSearchType.values()[raw - 1];
    }
}
