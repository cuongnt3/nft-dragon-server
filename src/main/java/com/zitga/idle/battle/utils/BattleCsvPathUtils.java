package com.zitga.idle.battle.utils;

public class BattleCsvPathUtils {

    private static final String BATTLE_PATH = "battle";
    private static final String PREDEFINE_TEAM_PATH = "predefine_team";

    // ---------------------------------------- Battle ----------------------------------------
    public static String getBattleFormationPath() {
        return String.format("%s/formation.csv", BATTLE_PATH);
    }

    public static String getBattleFormationBuffPath() {
        return String.format("%s/formation_buff.csv", BATTLE_PATH);
    }

    public static String getBattleHeroPowerGainPath() {
        return String.format("%s/hero_power_gain.csv", BATTLE_PATH);
    }

    public static String getBattleCompanionBuffPath() {
        return String.format("%s/companion_buff.csv", BATTLE_PATH);
    }

    // ---------------------------------------- Predefine Team ----------------------------------------
    public static String getPredefineTeamSummonerPath() {
        return String.format("%s/team_summoner.csv", PREDEFINE_TEAM_PATH);
    }

    public static String getPredefineTeamLevelPath() {
        return String.format("%s/team_level.csv", PREDEFINE_TEAM_PATH);
    }

    public static String getPredefineTeamLevelSpecialPath() {
        return String.format("%s/team_level_special.csv", PREDEFINE_TEAM_PATH);
    }

    public static String getPredefineTeamStarPath() {
        return String.format("%s/team_star.csv", PREDEFINE_TEAM_PATH);
    }

    public static String getPredefineTeamItemPath() {
        return String.format("%s/team_item.csv", PREDEFINE_TEAM_PATH);
    }

    public static String getPredefineTeamMasteryPath() {
        return String.format("%s/team_mastery.csv", PREDEFINE_TEAM_PATH);
    }

    public static String getPredefineBossStatPath() {
        return String.format("%s/boss_stat.csv", PREDEFINE_TEAM_PATH);
    }

    public static String getPredefineBossSlotPath() {
        return String.format("%s/boss_slot.csv", PREDEFINE_TEAM_PATH);
    }

    public static String getBossHeroCheckPath() {
        return String.format("%s/boss_hero_check.csv", PREDEFINE_TEAM_PATH);
    }
}
