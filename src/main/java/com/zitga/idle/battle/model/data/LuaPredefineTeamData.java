package com.zitga.idle.battle.model.data;

import com.zitga.idle.battle.utils.LuaPathUtils;
import com.zitga.idle.lua.model.LuaObject;
import org.luaj.vm2.LuaValue;

public class LuaPredefineTeamData extends LuaObject {

    public LuaPredefineTeamData(PredefineTeamData predefineTeamData, int teamId) {
        super(LuaPathUtils.getPredefineTeamPath(), "PredefineTeamData");
        this.initialize(predefineTeamData, teamId);
    }

    private void initialize(PredefineTeamData predefineTeamData, int teamId) {
        invoke("SetTeamId", LuaValue.valueOf(teamId));

        invoke("SetTeamSummonerId", LuaValue.valueOf(predefineTeamData.getTeamSummonerId()));

        invoke("SetFormationId", LuaValue.valueOf(predefineTeamData.getFormationId()));

        invoke("SetTeamLevelId", LuaValue.valueOf(predefineTeamData.getTeamLevelId()));
        invoke("SetTeamStarId", LuaValue.valueOf(predefineTeamData.getTeamStarId()));

        invoke("SetTeamItemId", LuaValue.valueOf(predefineTeamData.getTeamItemId()));
        invoke("SetTeamMasteryId", LuaValue.valueOf(predefineTeamData.getTeamMasteryId()));

        invoke("SetHeroList", predefineTeamData.getLuaHeroDictionary());
        invoke("SetBossSlotId", LuaValue.valueOf(predefineTeamData.getBossSlotId()));
    }
}