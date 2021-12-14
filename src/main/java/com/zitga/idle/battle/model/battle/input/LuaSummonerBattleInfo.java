package com.zitga.idle.battle.model.battle.input;

import com.zitga.idle.battle.utils.LuaPathUtils;
import com.zitga.idle.lua.model.LuaList;
import com.zitga.idle.lua.model.LuaObject;
import org.luaj.vm2.LuaValue;

import java.util.ArrayList;
import java.util.List;

public class LuaSummonerBattleInfo extends LuaObject {

    public LuaSummonerBattleInfo() {
        super(LuaPathUtils.getBattleDataPath(), "SummonerBattleInfo");
    }

    public LuaSummonerBattleInfo(LuaValue luaBinding) {
        super(luaBinding);
    }

    // ---------------------------------------- Getters ----------------------------------------
    public int getSummonerId() {
        return getField("summonerId").toint();
    }

    public int getLevel() {
        return getField("level").toint();
    }

    public int getStar() {
        return getField("star").toint();
    }

    public List<Integer> getSkillList() {
        List<Integer> result = new ArrayList<>();

        LuaList skillList = new LuaList(getField("skills"));
        for (int i = 1; i <= skillList.count(); i++) {
            result.add(skillList.get(i).toint());
        }

        return result;
    }

    // ---------------------------------------- Setters ----------------------------------------
}
