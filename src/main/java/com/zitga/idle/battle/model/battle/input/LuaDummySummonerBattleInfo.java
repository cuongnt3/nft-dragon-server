package com.zitga.idle.battle.model.battle.input;

import com.zitga.idle.authentication.basicInfo.constant.BasicInfoConstant;
import com.zitga.idle.battleInfo.constant.BattleInfoConstant;
import com.zitga.idle.hero.constant.HeroConstant;
import org.luaj.vm2.LuaValue;

import java.util.Arrays;

public class LuaDummySummonerBattleInfo extends LuaSummonerBattleInfo {

    private static final LuaValue SUMMONER_NOVICE_ID;
    private static final LuaValue DEFAULT_SUMMONER_STAR;
    private static final LuaValue DEFAULT_PLAYER_LEVEL;

    private static final LuaValue[] DUMMY_SUMMONER_SKILL_LEVEL;

    private static final LuaValue IS_DUMMY;

    static {
        SUMMONER_NOVICE_ID = LuaValue.valueOf(HeroConstant.SUMMONER_NOVICE_ID);
        DEFAULT_SUMMONER_STAR = LuaValue.valueOf(BattleInfoConstant.DEFAULT_SUMMONER_STAR);
        DEFAULT_PLAYER_LEVEL = LuaValue.valueOf(BasicInfoConstant.DEFAULT_PLAYER_LEVEL);

        DUMMY_SUMMONER_SKILL_LEVEL = new LuaValue[HeroConstant.NUMBER_SKILL];
        Arrays.fill(DUMMY_SUMMONER_SKILL_LEVEL, DEFAULT_SUMMONER_STAR);

        IS_DUMMY = LuaValue.valueOf(true);
    }

    public void setInfo(int teamId) {
        // basic info
        invoke("SetInfo", LuaValue.valueOf(teamId), SUMMONER_NOVICE_ID, DEFAULT_SUMMONER_STAR, DEFAULT_PLAYER_LEVEL);

        // skill
        invoke("SetSkills", DUMMY_SUMMONER_SKILL_LEVEL);

        // set dummy
        invoke("SetDummy", IS_DUMMY);
    }
}

