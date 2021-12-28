package com.zitga.idle.battle.model.message.log.effect;

import com.zitga.idle.battle.model.battle.output.log.effect.LuaEffectLog;

public class BattleEffectLog {

    private int effectType;

    private int remainRound;

    private boolean isBuff;

    public int getEffectType() {
        return effectType;
    }

    public int getRemainRound() {
        return remainRound;
    }

    public boolean isBuff() {
        return isBuff;
    }

    public BattleEffectLog(LuaEffectLog effectLog) {
        this.effectType = effectLog.getType();
        this.remainRound = effectLog.getRemainingRound();
        this.isBuff = effectLog.isBuff();
    }
}
