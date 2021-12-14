package com.zitga.idle.enumeration.feature;

public enum FeatureType {

    CAMPAIGN(1),
    SUMMON(2),
    ALTAR(3),
    BLACK_SMITH(4),
    TOWER(5),
    MARKET(6),
    TAVERN(7),
    PROPHET_TREE(8),
    DUNGEON(9),
    GUILD(10),
    CASINO(11),
    ARENA(12),
    HAND_OF_MIDAS(13),
    INVENTORY(14),
    SUMMONER(15),
    MASTERY(16),
    FRIEND(17),
    RAID(18),
    HERO_LIST(19),
    RAISE_HERO(20),
    DEFENSE(21),
    HERO_LINKING(22),
    ARENA_TEAM(23),
    REGRESSION(24),
    EVOLVE_MAX_STAR(25),
    DOMAINS(26),
    TALENT(27),
    ITEM_REVOLUTION(28),
    LOCK_SERVER_OPEN(29),
    IDLE_EFFECT(30),
    ;

    private final int value;

    FeatureType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static FeatureType toFeatureType(int type) {
        if (type >= 1 && type <= FeatureType.values().length) {
            return FeatureType.values()[type - 1];
        }

        return null;
    }
}