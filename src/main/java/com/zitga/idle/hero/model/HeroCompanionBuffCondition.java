package com.zitga.idle.hero.model;

import com.zitga.idle.battle.constant.BattleTag;
import com.zitga.idle.enumeration.hero.HeroCompanionBuffConditionType;

import java.util.Map;

public class HeroCompanionBuffCondition implements Comparable<HeroCompanionBuffCondition> {

    private final HeroCompanionBuffConditionType conditionType;
    private final int conditionNumber;

    public HeroCompanionBuffCondition(Map<String, String> data) {
        conditionType = HeroCompanionBuffConditionType.toConditionType(Integer.parseInt(data.get(BattleTag.COMPANION_BUFF_CONDITION_TYPE_TAG)));
        conditionNumber = Integer.parseInt(data.get(BattleTag.COMPANION_BUFF_CONDITION_NUMBER_TAG));
    }

    public HeroCompanionBuffConditionType getConditionType() {
        return conditionType;
    }

    public int getConditionNumber() {
        return conditionNumber;
    }

    @Override
    public int compareTo(HeroCompanionBuffCondition other) {
        return conditionNumber - other.conditionNumber;
    }
}