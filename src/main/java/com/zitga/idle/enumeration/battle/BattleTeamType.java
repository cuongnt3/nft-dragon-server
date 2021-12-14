package com.zitga.idle.enumeration.battle;

public enum BattleTeamType {
    ATTACKER_TEAM(1),
    DEFENDER_TEAM(2);

    private final int value;

    BattleTeamType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static BattleTeamType toType(int type) {
        return BattleTeamType.values()[type - 1];
    }
}
