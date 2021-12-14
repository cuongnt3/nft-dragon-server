package com.zitga.idle.battle.model.hero.mastery;

import com.zitga.idle.battle.utils.LuaPathUtils;
import com.zitga.idle.lua.model.LuaObject;
import org.luaj.vm2.LuaValue;

public class SummonerMastery extends LuaObject {

    public SummonerMastery() {
        super(LuaPathUtils.getHeroDataPath(), "SummonerMastery");
    }

    public void parseCsv(String statData) {
        invoke("ParseCsv", LuaValue.valueOf(statData));
    }
}