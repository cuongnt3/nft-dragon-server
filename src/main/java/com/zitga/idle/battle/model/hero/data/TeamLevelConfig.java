package com.zitga.idle.battle.model.hero.data;

import com.zitga.idle.battle.constant.BattleConstant;
import com.zitga.idle.battle.constant.BattleTag;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TeamLevelConfig {

    private int id;

    // key: position
    private Map<Integer, Integer> teamLevels;

    public TeamLevelConfig(Map<String, String> dataCsv) {
        id = Integer.parseInt(dataCsv.get(BattleTag.ID_TEAM_LEVEL_TAG));

        teamLevels = new ConcurrentHashMap<>();
        for (int i = 0; i < BattleConstant.MAX_NUMBER_SLOT; i++) {
            int level = Integer.parseInt(dataCsv.get(BattleTag.HERO_SLOT_TAG + (i + 1)));
            teamLevels.put(i + 1, level);
        }
    }

    public int getId() {
        return id;
    }

    public int getLevel(int slot){
        return teamLevels.getOrDefault(slot, 0);
    }
}
