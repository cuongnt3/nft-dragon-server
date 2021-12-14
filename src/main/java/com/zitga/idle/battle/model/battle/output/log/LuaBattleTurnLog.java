package com.zitga.idle.battle.model.battle.output.log;

import com.zitga.idle.battle.model.battle.output.log.action.attack.LuaAttackResult;
import com.zitga.idle.battle.model.battle.output.log.action.skill.LuaActiveSkillResult;
import com.zitga.idle.battle.model.message.log.BaseHeroLog;
import com.zitga.idle.enumeration.battle.ActionResultType;
import com.zitga.idle.lua.model.LuaList;
import com.zitga.idle.lua.model.LuaObject;
import org.luaj.vm2.LuaValue;

import java.util.ArrayList;
import java.util.List;

public class LuaBattleTurnLog extends LuaObject {
    public LuaBattleTurnLog(LuaValue luaBinding) {
        super(luaBinding);
    }

    public int getRound() {
        return getField("round").toint();
    }

    public int getTurn() {
        return getField("turn").toint();
    }

    public LuaBaseHero getBaseHero() {
        return new LuaBaseHero(getField("initiator"));
    }

    public int getActionType() {
        return getField("actionType").toint();
    }

    public LuaBattleTeamLog getBattleTeamLog(boolean isAttacker){
        if (isAttacker){
            return new LuaBattleTeamLog(getField("attackerTeamLog"));
        }

        return new LuaBattleTeamLog(getField("defenderTeamLog"));
    }

    public List<LuaBaseActionResult> getActionResults() {
        List<LuaBaseActionResult> results = new ArrayList<>();
        LuaList luaList = new LuaList(getField("actionResults"));
        LuaBaseActionResult actionResult;
        for (int i = 1; i <= luaList.count(); i++) {
            actionResult = new LuaBaseActionResult(luaList.get(i));

            int actionResultType = actionResult.getActionResultType();
            ActionResultType type = ActionResultType.toType(actionResultType);
            switch (type) {
                case ATTACK:
                    actionResult = new LuaAttackResult(luaList.get(i));
                    break;
                case USE_ACTIVE_DAMAGE_SKILL:
                    actionResult = new LuaActiveSkillResult(luaList.get(i));
                    break;
            }
            results.add(actionResult);
        }

        return results;
    }
}
