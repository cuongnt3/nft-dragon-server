package com.zitga.idle.battle.model.battle;

import com.zitga.idle.battle.model.battle.input.LuaBattleTeamInfo;
import com.zitga.idle.battle.utils.LuaPathUtils;
import com.zitga.idle.lua.model.LuaObject;
import com.zitga.idle.lua.service.LuaServiceManager;

public class LuaTeamPowerCalculator extends LuaObject {

    public LuaTeamPowerCalculator() {
        super(LuaPathUtils.getUtilsPath(""), "TeamPowerCalculator");
    }

    public void setTeamInfo(LuaBattleTeamInfo teamInfo) {
        invoke("SetTeamInfo", teamInfo.toLua());
    }

    public long calculatePower(LuaServiceManager serviceManager) {
        return invoke("CalculatePower", serviceManager.getLuaBattleService().toLua()).tolong();
    }
}
