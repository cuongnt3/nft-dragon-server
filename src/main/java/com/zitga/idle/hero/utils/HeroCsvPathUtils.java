package com.zitga.idle.hero.utils;

public class HeroCsvPathUtils {

    private static final String HERO_PATH = "hero";
    private static final String HERO_LEVEL_PATH = "hero_level";
    private static final String HERO_FRAGMENT_PATH = "hero_fragment";

    private static final String CLIENT_DATA_PATH = "client";

    // ---------------------------------------- Hero ----------------------------------------
    public static String getHeroPath() {
        return HERO_PATH;
    }

    public static String getHeroLinkingPath() {
        return String.format("%s/hero_linking.csv", HERO_PATH);
    }

    public static String getHeroTierPath() {
        return String.format("%s/hero_tier.csv", HERO_PATH);
    }

    public static String getAvailableHeroesPath() {
        return String.format("%s/hero_collection.csv", CLIENT_DATA_PATH);
    }

    public static String getHeroBaseStarPath() {
        return String.format("%s/hero_base_star.csv", CLIENT_DATA_PATH);
    }

    // ---------------------------------------- Hero Level ----------------------------------------
    public static String getHeroEvolveConfigPath() {
        return String.format("%s/hero_evolve_config.csv", HERO_LEVEL_PATH);
    }

    public static String getHeroLevelPricePath() {
        return String.format("%s/hero_level_price.csv", HERO_LEVEL_PATH);
    }

    public static String getHeroLevelCapPath() {
        return String.format("%s/hero_level_cap.csv", HERO_LEVEL_PATH);
    }

    public static String getHeroSkillLevelPath() {
        return String.format("%s/hero_skill_level.csv", HERO_LEVEL_PATH);
    }

    public static String getHeroEvolvePricePath() {
        return String.format("%s/hero_evolve_price.csv", HERO_LEVEL_PATH);
    }

    public static String getHeroEvolveSpecificPriceFolder() {
        return String.format("%s/specific", HERO_LEVEL_PATH);
    }

    // ---------------------------------------- Hero Fragment ----------------------------------------
    public static String getHeroFragmentNumberPath() {
        return String.format("%s/hero_fragment_number.csv", HERO_FRAGMENT_PATH);
    }

    public static String getHeroFragmentSummonPath() {
        return String.format("%s/summon_rate", HERO_FRAGMENT_PATH);
    }
}
