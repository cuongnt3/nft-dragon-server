package com.zitga.idle.enumeration.feature;

public enum MinorFeatureType {

    CASINO_MULTIPLE_SPIN(1),
    CASINO_PREMIUM_SPIN(2),
    BATTLE_SPEED_UP(3),
    BATTLE_SKIP(4),
    SUMMON_ACCUMULATE(5),
    CAMPAIGN_AUTO_TRAIN(6),
    CAMPAIGN_QUICK_BATTLE(7),
    BATTLE_SKIP_PRE(8),
    BATTLE_SKIP_MIDDLE(9),
    BATTLE_SPEED_UP_2(10),
    CAMPAIGN_AUTO_BATTLE(11),
    ;

    private final int value;

    MinorFeatureType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static MinorFeatureType toMinorFeatureType(int type) {
        if (type >= 1 && type <= MinorFeatureType.values().length) {
            return MinorFeatureType.values()[type - 1];
        }

        return null;
    }
}
