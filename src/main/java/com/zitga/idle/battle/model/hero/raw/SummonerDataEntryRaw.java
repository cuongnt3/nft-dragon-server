package com.zitga.idle.battle.model.hero.raw;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SummonerDataEntryRaw {

    private final String statData;

    // key: skillId
    private final Map<Integer, String[]> skillDataMap;

    private List<String> summonerMasteries;

    public SummonerDataEntryRaw(String statData) {
        this.statData = statData;
        this.skillDataMap = new ConcurrentHashMap<>();
        this.summonerMasteries = new ArrayList<>();
    }

    public String getStatData() {
        return statData;
    }

    public String[] getSkillData(int skillId) {
        return skillDataMap.get(skillId);
    }

    public void addSkillData(int skillId, String[] skillDataList) {
        skillDataMap.put(skillId, skillDataList);
    }

    public List<String> getSummonerMasteries() {
        return summonerMasteries;
    }

    public void setSummonerMasteries(List<String> masteries) {
        this.summonerMasteries = masteries;
    }
}
