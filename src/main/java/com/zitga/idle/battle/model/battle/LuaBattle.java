package com.zitga.idle.battle.model.battle;

import com.zitga.idle.battle.constant.BattleConstant;
import com.zitga.idle.battle.model.battle.input.LuaBattleTeamInfo;
import com.zitga.idle.battle.model.hero.heroState.LuaHeroState;
import com.zitga.idle.battle.utils.LuaPathUtils;
import com.zitga.idle.enumeration.common.GameMode;
import com.zitga.idle.lua.model.LuaObject;
import com.zitga.idle.lua.service.LuaRandomHelper;
import org.luaj.vm2.LuaValue;

public class LuaBattle extends LuaObject {

    private LuaBattleTeamInfo attackerTeam;
    private LuaBattleTeamInfo defenderTeam;
    private LuaRandomHelper randomHelper;

    public LuaBattle(GameMode gameMode) {
        super(LuaPathUtils.getBattlePath(""), "Battle");
        invoke("SetGameMode", LuaValue.valueOf(gameMode.getValue()));
        this.randomHelper = new LuaRandomHelper();
    }

    public void parseCsv(String battleCsv, String masteryCsv) {
        invoke("ParseCsv", LuaValue.valueOf(battleCsv), LuaValue.valueOf(masteryCsv));
    }

    public LuaHeroState getHeroState(int teamId, int indexHero) {
        LuaValue value = invoke("GetHeroState", getTeam(teamId).toLua(), LuaValue.valueOf(indexHero));
        if (value.equals(LuaValue.NIL)) {
            return null;
        }

        return new LuaHeroState(value);
    }

    public LuaBattleTeamInfo getTeam(int teamId) {
        if (teamId == BattleConstant.ATTACKER_TEAM) {
            return attackerTeam;
        } else {
            return defenderTeam;
        }
    }

    public LuaRandomHelper getRandomHelper() {
        return randomHelper;
    }

    public void setTeamInfo(LuaBattleTeamInfo attackerTeam, LuaBattleTeamInfo defenderTeam) {
        this.attackerTeam = attackerTeam;
        this.defenderTeam = defenderTeam;

        invoke("SetTeamInfo", attackerTeam.toLua(), defenderTeam.toLua(), randomHelper.toLua());
    }
}
