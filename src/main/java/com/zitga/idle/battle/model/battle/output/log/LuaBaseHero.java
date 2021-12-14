package com.zitga.idle.battle.model.battle.output.log;

import com.zitga.idle.lua.model.LuaObject;
import org.luaj.vm2.LuaValue;

public class LuaBaseHero extends LuaObject {
    public LuaBaseHero(LuaValue luaBinding) {
        super(luaBinding);
    }

    public int getHeroId() {
        return getField("id").toint();
    }

    public String getHeroName() {
        return getField("name").tojstring();
    }

    public int getTeamId() {
        return getField("teamId").toint();
    }

    public int getLevel() {
        return getField("level").toint();
    }

    public int getStar() {
        return getField("star").toint();
    }

    public int getPosition() {
        return invoke("GetPosition").toint();
    }

    public boolean isFrontLine() {
        return invoke("IsFrontLine").toboolean();
    }
}
