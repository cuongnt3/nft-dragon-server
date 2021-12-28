package com.zitga.idle.battle.model.battle.output.log.effect;

import com.zitga.idle.lua.model.LuaObject;
import org.luaj.vm2.LuaValue;

public class LuaEffectLog extends LuaObject {

    public LuaEffectLog(LuaValue luaBinding) {
        super(luaBinding);
    }

    public int getType() {
        return getField("type").toint();
    }

    public int getRemainingRound() {
        return getField("remainingRound").toint();
    }

    public boolean isBuff() {
        return getField("isBuff").toboolean();
    }
}
