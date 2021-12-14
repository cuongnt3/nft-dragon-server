package com.zitga.idle.battle.model.battle.output.log;

import com.zitga.idle.lua.model.LuaList;
import com.zitga.idle.lua.model.LuaObject;
import org.luaj.vm2.LuaValue;

import java.util.ArrayList;
import java.util.List;

public class LuaBattleTeamLog extends LuaObject {
    public LuaBattleTeamLog(LuaValue binding) {
        super(binding);
    }

    public int getTeamId() {
        return getField("teamId").toint();
    }

    public List<LuaHeroStatusLog> getHeroStatusList() {
        List<LuaHeroStatusLog> result = new ArrayList<>();
        LuaList heroStatusList = new LuaList(getField("beforeLogs"));
        for (int i = 1; i <= heroStatusList.count(); i++) {
            LuaHeroStatusLog statusLog = new LuaHeroStatusLog(heroStatusList.get(i));
            result.add(statusLog);
        }

        return result;
    }
}
