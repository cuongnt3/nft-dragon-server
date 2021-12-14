package com.zitga.idle.battle.model.battle.output.log;

import com.zitga.idle.lua.model.LuaObject;
import org.luaj.vm2.LuaValue;

public class LuaHeroStatusLog extends LuaObject {
    public LuaHeroStatusLog(LuaValue binding) {
        super(binding);
    }

    public LuaBaseHero getBaseHero(){
        return new LuaBaseHero(getField("myHero"));
    }

    public float getHpPercent(){
        return getField("hpPercent").tofloat();
    }

    public float getPowerPercent(){
        return getField("powerPercent").tofloat();
    }

}
