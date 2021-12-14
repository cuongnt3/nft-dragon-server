package com.zitga.idle.battle.model.message;

import com.zitga.idle.battle.model.battle.output.log.LuaBaseHero;
import com.zitga.idle.battle.model.message.log.BaseHeroLog;

public class HeroStatusLog {

    private BaseHeroLog baseHero;

    private float hpPercent;
    private float powerPercent;

    // ---------------------------------------- Getters ----------------------------------------
    public BaseHeroLog getBaseHero() {
        return baseHero;
    }

    public float getHpPercent() {
        return hpPercent;
    }

    public float getPowerPercent() {
        return powerPercent;
    }

    // ---------------------------------------- Setters ----------------------------------------
    public void setBaseHero(LuaBaseHero luaBaseHero) {
        baseHero = new BaseHeroLog(luaBaseHero);
    }

    public void setHpPercent(float hpPercent) {
        this.hpPercent = hpPercent;
    }

    public void setPowerPercent(float powerPercent) {
        this.powerPercent = powerPercent;
    }
}
