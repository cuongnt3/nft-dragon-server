package com.zitga.idle.battle.model.battle.output.log;

import com.zitga.idle.lua.model.LuaList;
import com.zitga.idle.lua.model.LuaObject;
import org.luaj.vm2.LuaValue;

import java.util.ArrayList;
import java.util.List;

public class LuaBattleRoundLog extends LuaObject {
    public LuaBattleRoundLog(LuaValue binding) {
        super(binding);
    }

    public int getRoundNumber() {
        return getField("roundNumber").toint();
    }

    public List<LuaBattleTurnLog> getBattleTurnList() {
        List<LuaBattleTurnLog> result = new ArrayList<>();
        LuaList luaList = new LuaList(getField("battleTurnLogs"));
        for (int i = 1; i <= luaList.count(); i++) {
            LuaBattleTurnLog battleTurnLog = new LuaBattleTurnLog(luaList.get(i));
            result.add(battleTurnLog);
        }

        return result;
    }
}
