package com.zitga.summon.utils;

public class SummonCsvPathUtils {

    private static final String SUMMON_PATH = "summon";

    // ---------------------------------------- Summon ----------------------------------------
    public static String getSummonPath() {
        return SUMMON_PATH;
    }

    public static String getHatchDurationPath() {
        return String.format("%s/hatch_duration.csv", SUMMON_PATH);
    }

    public static String getEggSummonRatePath() {
        return String.format("%s/egg_summon_rate.csv", SUMMON_PATH);
    }

    public static String getDragonSummonRatePath() {
        return String.format("%s/dragon_summon_rate.csv", SUMMON_PATH);
    }
}
