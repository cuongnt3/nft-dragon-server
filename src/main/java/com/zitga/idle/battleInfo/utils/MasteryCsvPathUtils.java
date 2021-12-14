package com.zitga.idle.battleInfo.utils;

import com.zitga.idle.enumeration.hero.HeroClassType;

public class MasteryCsvPathUtils {

    private static final String MASTERY_PATH = "mastery";
    private static final String[] HERO_CLASS_SUB_FOLDER_PATH = {"mage", "warrior", "priest", "assassin", "ranger"};

    // ---------------------------------------- Mastery ----------------------------------------
    public static String getMasteryPath(HeroClassType classType) {
        String subFolderPath = HERO_CLASS_SUB_FOLDER_PATH[classType.getValue() - 1];
        return String.format("%s/%s", MASTERY_PATH, subFolderPath);
    }

    public static String getMasteryUpgradePricePath() {
        return String.format("%s/mastery_upgrade_price.csv", MASTERY_PATH);
    }

    public static String getMasteryResetPricePath() {
        return String.format("%s/mastery_reset_price.csv", MASTERY_PATH);
    }

    public static String getMasteryConfigPath() {
        return String.format("%s/mastery_config.csv", MASTERY_PATH);
    }

    public static String getTestMasteryPath() {
        return "test/user_specific/test_mastery_level.csv";
    }
}
