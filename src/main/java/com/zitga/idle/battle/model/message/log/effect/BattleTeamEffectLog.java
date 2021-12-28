package com.zitga.idle.battle.model.message.log.effect;

import com.zitga.idle.battle.model.battle.output.log.LuaHeroStatusLog;

import java.util.ArrayList;
import java.util.List;

public class BattleTeamEffectLog {

    private List<BattleHeroEffectLog> heroEffectLogList = new ArrayList<>();

    public List<BattleHeroEffectLog> getHeroEffectList() {
        return heroEffectLogList;
    }

    public void addBaseHeroEffectLog(LuaHeroStatusLog heroStatusLog) {
        BattleHeroEffectLog heroEffectLog = new BattleHeroEffectLog(heroStatusLog);

        heroEffectLogList.add(heroEffectLog);
    }
}
