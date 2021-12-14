package com.zitga.idle.battle.model.battle.output;

import com.zitga.idle.battle.constant.BattleConstant;
import com.zitga.idle.battle.model.battle.output.log.LuaBattleTeamLog;
import com.zitga.idle.enumeration.lua.LuaRunMode;
import com.zitga.idle.lua.model.LuaDictionary;
import com.zitga.idle.lua.model.LuaList;
import com.zitga.idle.lua.model.LuaObject;
import com.zitga.idle.lua.service.LuaRandomHelper;
import org.luaj.vm2.LuaValue;

public class LuaBattleResult extends LuaObject {

    private LuaRandomHelper randomHelper;

    public LuaBattleResult(LuaValue luaBinding, LuaRandomHelper randomHelper) {
        super(luaBinding);
        this.randomHelper = randomHelper;
    }

    public boolean isAttackerWin() {
        return invoke("GetWinnerTeam").toint() == BattleConstant.ATTACKER_TEAM;
    }

    public boolean isDefenderWin() {
        return invoke("GetWinnerTeam").toint() == BattleConstant.DEFENDER_TEAM;
    }

    public int getNumberRounds() {
        return invoke("GetNumberRounds").toint();
    }

    public LuaBattleTeamLog getBattleTeamLog(boolean isAttacker) {
        if (isAttacker) {
            return new LuaBattleTeamLog(getField("attackerTeamLog"));
        } else {
            return new LuaBattleTeamLog(getField("defenderTeamLog"));
        }
    }

    public LuaList getBattleRoundLog() {
        return new LuaList(getField("battleRoundLogs"));
    }

    public int getNumberAttackerHeroDead() {
        return invoke("GetNumberAttackerHeroDead").toint();
    }

    public int getTotalDamageDealToDefender() {
        return invoke("GetTotalDamageDealToDefender").toint();
    }

    public int getAttackerCompanionBuffId() {
        return invoke("GetAttackerCompanionBuffId").toint();
    }

    public int getDefenderCompanionBuffId() {
        return invoke("GetDefenderCompanionBuffId").toint();
    }

    public LuaDictionary getRoundBasicInfoDictionary() {
        LuaValue luaValue = invoke("GetRoundBasicInfoDictionary");
        return new LuaDictionary(luaValue);
    }

    public LuaRandomHelper getRandomHelper() {
        return randomHelper;
    }

    public String toString(LuaRunMode runMode) {
        return invoke("ToString", LuaValue.valueOf(runMode.getValue())).toString();
    }

    public String toShortString(LuaRunMode runMode) {
        return invoke("ToShortString", LuaValue.valueOf(runMode.getValue())).toString();
    }
}