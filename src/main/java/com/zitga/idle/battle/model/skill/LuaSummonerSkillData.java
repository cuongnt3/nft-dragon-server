package com.zitga.idle.battle.model.skill;

import com.zitga.idle.battle.utils.LuaPathUtils;
import com.zitga.idle.lua.model.LuaObject;
import org.luaj.vm2.LuaValue;

public class LuaSummonerSkillData extends LuaObject {

    public LuaSummonerSkillData(int heroId, int skillId) {
        super(LuaPathUtils.getSkillDataPath(), "SummonerSkillDataCollection",
                LuaValue.valueOf(heroId), LuaValue.valueOf(skillId));
    }

    public void parseCsv(String data) {
        invoke("ParseCsv", LuaValue.valueOf(data));
    }
}
