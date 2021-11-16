package com.zitga.hatch.utils;

public class SummonCsvPathUtils {

    private static final String SUMMON_PATH = "summon";
    private static final String[] SUMMON_RATE_PATH = {
            "basic_summon_rate.csv",
            "premium_summon_rate.csv",
            "friend_summon_rate.csv",
            "cumulative_summon_rate.csv"
    };

    // ---------------------------------------- Summon ----------------------------------------
    public static String getSummonPath() {
        return SUMMON_PATH;
    }

    public static String getSummonHeroProductionPath() {
        return String.format("%s/summon_production/hero_production.csv", SUMMON_PATH);
    }

    public static String getSummonProductionConfigPath() {
        return String.format("%s/summon_production/summon_production_config.csv", SUMMON_PATH);
    }

    public static String getSummonPricePath() {
        return String.format("%s/summon_price.csv", SUMMON_PATH);
    }

    public static String getSummonFreeIntervalPath() {
        return String.format("%s/summon_free_interval.csv", SUMMON_PATH);
    }

    public static String getFixedPremiumSummonRatePath() {
        return String.format("%s/fixed_premium_summon_rate", SUMMON_PATH);
    }

    public static String[] getSummonRatesPath() {
        return SUMMON_RATE_PATH;
    }
}
