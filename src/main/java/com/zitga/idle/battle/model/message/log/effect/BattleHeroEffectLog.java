package com.zitga.idle.battle.model.message.log.effect;

import com.zitga.idle.battle.model.battle.output.log.LuaHeroStatusLog;
import com.zitga.idle.battle.model.battle.output.log.effect.LuaEffectLog;
import com.zitga.idle.lua.model.LuaList;

import java.util.ArrayList;
import java.util.List;

public class BattleHeroEffectLog {

    private int position;

    private boolean isFrontLine;

    private List<BattleEffectLog> effectLogs;

    public int getPosition() {
        return position;
    }

    public boolean isFrontLine() {
        return isFrontLine;
    }

    public List<BattleEffectLog> getEffectLogs() {
        return effectLogs;
    }

    public BattleHeroEffectLog(LuaHeroStatusLog heroStatusLog) {
        this.position = heroStatusLog.getBaseHero().getPosition();
        this.isFrontLine = heroStatusLog.getBaseHero().isFrontLine();

        effectLogs = new ArrayList<>();
        LuaList effectList = heroStatusLog.getEffectLog();
        int count = effectList.count();
        for (int i = 1; i <= count; i++) {
            LuaEffectLog luaEffectLog = new LuaEffectLog(effectList.get(i));
            BattleEffectLog effectLog = new BattleEffectLog(luaEffectLog);
            effectLogs.add(effectLog);
        }
    }
}
