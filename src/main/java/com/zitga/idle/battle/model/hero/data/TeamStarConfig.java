package com.zitga.idle.battle.model.hero.data;

import com.zitga.idle.battle.constant.BattleConstant;
import com.zitga.idle.battle.constant.BattleTag;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TeamStarConfig {

    private int id;

    // key: position
    private Map<Integer, Integer> teamStars;

    public TeamStarConfig(Map<String, String> dataCsv) {
        id = Integer.parseInt(dataCsv.get(BattleTag.ID_TEAM_STAR_TAG));

        teamStars = new ConcurrentHashMap<>();
        for (int i = 0; i < BattleConstant.MAX_NUMBER_SLOT; i++) {
            int star = Integer.parseInt(dataCsv.get(BattleTag.HERO_SLOT_TAG + (i + 1)));
            teamStars.put(i + 1, star);
        }
    }

    public int getId() {
        return id;
    }

    public int getStar(int slot){
        return teamStars.getOrDefault(slot, 0);
    }
}
