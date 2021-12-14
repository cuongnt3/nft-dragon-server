package com.zitga.idle.battle.model.hero.heroState;

import com.zitga.idle.lua.model.LuaObject;
import org.luaj.vm2.LuaValue;

public class LuaHeroState extends LuaObject {

    public LuaHeroState(LuaValue luaBinding) {
        super(luaBinding);
    }

    public boolean isFrontLine() {
        return invoke("IsFrontLine").toboolean();
    }

    public int getPosition() {
        return invoke("GetPosition").toint();
    }

    public float getHpPercent() {
        return invoke("GetHpPercent").tofloat();
    }

    public int getPowerValue() {
        return invoke("GetPowerValue").toint();
    }
}
