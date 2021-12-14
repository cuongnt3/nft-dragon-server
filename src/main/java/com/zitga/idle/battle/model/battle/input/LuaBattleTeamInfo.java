package com.zitga.idle.battle.model.battle.input;

import com.zitga.idle.battle.utils.LuaPathUtils;
import com.zitga.idle.lua.model.LuaList;
import com.zitga.idle.lua.model.LuaObject;
import org.luaj.vm2.LuaValue;

import java.util.ArrayList;
import java.util.List;

public class LuaBattleTeamInfo extends LuaObject {

    public LuaBattleTeamInfo() {
        super(LuaPathUtils.getBattleDataPath(), "BattleTeamInfo");
    }

    public LuaBattleTeamInfo(LuaValue luaBinding) {
        super(luaBinding);
    }

    // ---------------------------------------- Getters ----------------------------------------
    public int getFormationId() {
        return getField("formation").toint();
    }

    public LuaSummonerBattleInfo getSummoner() {
        return new LuaSummonerBattleInfo(getField("summonerBattleInfo"));
    }

    public List<LuaHeroBattleInfo> getHeroes() {
        List<LuaHeroBattleInfo> result = new ArrayList<>();

        LuaList heroes = new LuaList(getField("listHeroInfo"));
        int count = heroes.count();
        for (int i = 1; i <= count; i++) {
            LuaHeroBattleInfo heroBattleInfo = new LuaHeroBattleInfo(heroes.get(i));
            result.add(heroBattleInfo);
        }

        return result;
    }

    // ---------------------------------------- Setters ----------------------------------------
    public void addHero(LuaHeroBattleInfo info) {
        invoke("AddHero", info.toLua());
    }

    public void setFormationId(int formationId) {
        invoke("SetFormationId", LuaValue.valueOf(formationId));
    }

    public void setSummonerBattleInfo(LuaSummonerBattleInfo info) {
        invoke("SetSummonerBattleInfo", info.toLua());
    }
}
