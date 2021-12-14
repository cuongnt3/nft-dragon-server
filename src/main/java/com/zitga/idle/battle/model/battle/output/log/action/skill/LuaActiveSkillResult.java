package com.zitga.idle.battle.model.battle.output.log.action.skill;

import com.zitga.idle.battle.model.battle.output.log.LuaBaseActionResult;
import org.luaj.vm2.LuaValue;

public class LuaActiveSkillResult extends LuaBaseActionResult {
    public LuaActiveSkillResult(LuaValue luaBinding) {
        super(luaBinding);
    }

    public int getDamage(){
        return getField("damage").toint();
    }

    public float getInitiatorPowerPercent(){
        return getField("initiatorPowerPercent").tofloat();
    }

    public float getTargetPowerPercent(){
        return getField("targetPowerPercent").tofloat();
    }
}
