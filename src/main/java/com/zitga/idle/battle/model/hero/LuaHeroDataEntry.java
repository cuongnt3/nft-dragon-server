package com.zitga.idle.battle.model.hero;

import com.zitga.idle.battle.model.skill.LuaHeroSkillDataCollection;
import com.zitga.idle.battle.utils.LuaPathUtils;
import com.zitga.idle.enumeration.hero.HeroClassType;
import com.zitga.idle.lua.model.LuaObject;
import org.luaj.vm2.LuaValue;

public class LuaHeroDataEntry extends LuaObject {

    private final int id;

    public LuaHeroDataEntry(int id) {
        super(LuaPathUtils.getHeroDataPath(), "HeroDataEntry");
        this.id = id;
    }

    public void addSkillData(int skillId, String skillData) {
        if (skillData != null) {
            LuaHeroSkillDataCollection skill = new LuaHeroSkillDataCollection(id, skillId);
            skill.parseCsv(skillData);

            invoke("AddSkillData", LuaValue.valueOf(skillId), skill.toLua());
        }
    }

    public void parseCsv(String statData) {
        invoke("ParseCsv", LuaValue.valueOf(statData), LuaValue.valueOf(id));
    }

    public HeroClassType getHeroClass() {
        int heroClass = invoke("GetHeroClass").toint();
        return HeroClassType.toHeroClassType(heroClass);
    }

    public void validate() {
        invoke("ValidateAfterParseCsv");
    }
}