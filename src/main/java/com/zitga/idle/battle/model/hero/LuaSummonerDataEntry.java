package com.zitga.idle.battle.model.hero;

import com.zitga.idle.battle.model.hero.mastery.SummonerMastery;
import com.zitga.idle.battle.model.skill.LuaSummonerSkillData;
import com.zitga.idle.battle.utils.LuaPathUtils;
import com.zitga.idle.enumeration.hero.HeroClassType;
import com.zitga.idle.lua.model.LuaObject;
import org.luaj.vm2.LuaValue;

import java.util.List;

public class LuaSummonerDataEntry extends LuaObject {

    private final HeroClassType heroClassType;

    public LuaSummonerDataEntry(HeroClassType heroClassType) {
        super(LuaPathUtils.getHeroDataPath(), "SummonerDataEntry");
        this.heroClassType = heroClassType;
    }

    public void addSkillData(int skillId, String[] skillDataList) {
        LuaSummonerSkillData skill = new LuaSummonerSkillData(heroClassType.getValue(), skillId);
        for (String skillData : skillDataList) {
            skill.parseCsv(skillData);
        }

        invoke("AddSkillData", LuaValue.valueOf(skillId), skill.toLua());
    }

    public void addMastery(List<String> masteryData) {
        for (String mastery : masteryData) {
            SummonerMastery summonerMastery = new SummonerMastery();
            summonerMastery.parseCsv(mastery);

            invoke("AddMastery", summonerMastery.toLua());
        }
    }

    public void parseCsv(String statData) {
        invoke("ParseCsv", LuaValue.valueOf(statData), LuaValue.valueOf(heroClassType.getValue()));
    }

    public void validate() {
        invoke("ValidateAfterParseCsv");
    }
}
