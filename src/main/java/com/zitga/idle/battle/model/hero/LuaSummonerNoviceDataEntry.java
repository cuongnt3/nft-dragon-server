package com.zitga.idle.battle.model.hero;

import com.zitga.idle.battle.model.skill.LuaSummonerSkillData;
import com.zitga.idle.battle.utils.LuaPathUtils;
import com.zitga.idle.battleInfo.constant.BattleInfoConstant;
import com.zitga.idle.lua.model.LuaObject;
import org.luaj.vm2.LuaValue;

public class LuaSummonerNoviceDataEntry extends LuaObject {

    public LuaSummonerNoviceDataEntry() {
        super(LuaPathUtils.getHeroDataPath(), "SummonerDataEntry");
    }

    public void addSkillData(String skillData) {
        LuaSummonerSkillData skill = new LuaSummonerSkillData(BattleInfoConstant.SUMMONER_NOVICE_ID, 1);
        skill.parseCsv(skillData);

        invoke("AddSkillData", LuaValue.valueOf(1), skill.toLua());
    }

    public void parseCsv(String statData) {
        invoke("ParseCsv", LuaValue.valueOf(statData), LuaValue.valueOf(0));
    }

    public void validate() {
        invoke("ValidateAfterParseCsv");
    }
}
