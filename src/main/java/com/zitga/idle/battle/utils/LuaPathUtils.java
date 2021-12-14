package com.zitga.idle.battle.utils;

import com.zitga.utils.StringUtils;

public class LuaPathUtils {
    private static final String LUA_PATH = "lua";

    private static final String SERVICE_PATH = "lua/logicBattle/services";
    private static final String BATTLE_PATH = "lua/logicBattle/battle";

    private static final String BATTLE_DATA_PATH = "lua/logicBattle/data/battle";

    private static final String HERO_DATA_PATH = "lua/logicBattle/data/hero";
    private static final String SKILL_DATA_PATH = "lua/logicBattle/data/skill";

    private static final String PREDEFINE_TEAM_PATH = "lua/logicBattle/data/predefine";

    private static final String UTILS_PATH = "lua/logicBattle/utils/powerCalculator";

    private static final String LIBRARY_PATH = "lua/libs";

    // ---------------------------------------- Init ----------------------------------------
    public static String getLuaPath() {
        return LUA_PATH;
    }

    // ---------------------------------------- Logic ----------------------------------------
    public static String getBattlePath(String subFolder) {
        if (!StringUtils.isNullOrEmpty(subFolder)) {
            return String.format("%s/%s", BATTLE_PATH, subFolder);
        } else {
            return BATTLE_PATH;
        }
    }

    public static String getServicePath() {
        return SERVICE_PATH;
    }

    // ---------------------------------------- Data ----------------------------------------
    public static String getHeroDataPath() {
        return HERO_DATA_PATH;
    }

    public static String getSkillDataPath() {
        return SKILL_DATA_PATH;
    }

    public static String getPredefineTeamPath() {
        return PREDEFINE_TEAM_PATH;
    }

    // ---------------------------------------- Library ----------------------------------------
    public static String getLibraryPath(String subFolder) {
        if (!StringUtils.isNullOrEmpty(subFolder)) {
            return String.format("%s/%s", LIBRARY_PATH, subFolder);
        } else {
            return LIBRARY_PATH;
        }
    }

    public static String getUtilsPath(String subFolder) {
        if (!StringUtils.isNullOrEmpty(subFolder)) {
            return String.format("%s/%s", UTILS_PATH, subFolder);
        } else {
            return UTILS_PATH;
        }
    }

    public static String getBattleDataPath() {
        return BATTLE_DATA_PATH;
    }
}
