package com.zitga.idle.battle.model.hero.raw;

import com.zitga.idle.hero.constant.HeroConstant;

public class HeroDataEntryRaw {

    private final String statData;
    private final String[] skillDataList;

    public HeroDataEntryRaw(String statData) {
        this.statData = statData;
        this.skillDataList = new String[HeroConstant.NUMBER_SKILL];
    }

    public String getStatData() {
        return statData;
    }

    public String[] getSkillDataList() {
        return skillDataList;
    }

    public void addSkillData(int index, String skillData) {
        this.skillDataList[index] = skillData;
    }
}
