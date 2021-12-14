package com.zitga.idle.battleInfo.utils;

import com.zitga.idle.enumeration.hero.HeroClassType;

public class SummonerCsvPathUtils {

    private static final String SUMMONER_PATH = "summoner";

    private static final String[] HERO_CLASS_SUB_FOLDER_PATH = {"mage", "warrior", "priest", "assassin", "ranger"};

    // ---------------------------------------- Summoner ----------------------------------------
    public static String getSummonerStatPath(HeroClassType classType) {
        String subFolderPath = HERO_CLASS_SUB_FOLDER_PATH[classType.getValue() - 1];
        return String.format("%s/%s/stat.csv", SUMMONER_PATH, subFolderPath);
    }

    public static String getSummonerSkillPath(HeroClassType classType, int skillId) {
        String subFolderPath = HERO_CLASS_SUB_FOLDER_PATH[classType.getValue() - 1];
        return String.format("%s/%s/skill_%s", SUMMONER_PATH, subFolderPath, skillId);
    }

    public static String getSummonerNoviceStatPath() {
        return String.format("%s/novice/stat.csv", SUMMONER_PATH);
    }

    public static String getSummonerNoviceSkillPath() {
        return String.format("%s/novice/skill_1/tier_1.csv", SUMMONER_PATH);
    }

    public static String getSummonerExpPath() {
        return String.format("%s/summoner_exp.csv", SUMMONER_PATH);
    }

    public static String getSummonerEvolvePricePath() {
        return String.format("%s/summoner_evolve_price.csv", SUMMONER_PATH);
    }
}
