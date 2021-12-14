package com.zitga.idle.battle.model.battle.output.log;

import com.zitga.idle.lua.model.LuaObject;
import org.luaj.vm2.LuaValue;

public class LuaBaseActionResult extends LuaObject {
    public LuaBaseActionResult(LuaValue luaBinding) {
        super(luaBinding);
    }

    public LuaBaseHero getInitiator() {
        return new LuaBaseHero(getField("initiator"));
    }

    public LuaBaseHero getTarget() {
        return new LuaBaseHero(getField("target"));
    }

    public int getActionResultType() {
        return getField("type").toint();
    }

    public float getTargetHpPercent() {
        return getField("targetHpPercent").tofloat();
    }
}
