package com.zitga.hatch.utils;

import com.zitga.idle.enumeration.summon.SummonProductionType;

public class SummonUtils {

    public static int getNumberSummon(boolean isSingleSummon) {
        if (isSingleSummon) {
            return 1;
        } else {
            return 10;
        }
    }

    public static boolean isProphetTreeSummon(SummonProductionType type) {
        switch (type) {
            case PROPHET_TREE_WATER_SUMMON:
            case PROPHET_TREE_FIRE_SUMMON:
            case PROPHET_TREE_ABYSS_SUMMON:
            case PROPHET_TREE_NATURE_SUMMON:
            case PROPHET_TREE_LIGHT_SUMMON:
            case PROPHET_TREE_DARK_SUMMON:

            case PROPHET_TREE_WATER_CONVERT:
            case PROPHET_TREE_FIRE_CONVERT:
            case PROPHET_TREE_ABYSS_CONVERT:
            case PROPHET_TREE_NATURE_CONVERT:
            case PROPHET_TREE_LIGHT_CONVERT:
            case PROPHET_TREE_DARK_CONVERT:
                return true;
        }

        return false;
    }

    public static boolean isFragmentSummon(SummonProductionType type) {
        switch (type) {
            case FRAGMENT_WATER_SUMMON:
            case FRAGMENT_FIRE_SUMMON:
            case FRAGMENT_ABYSS_SUMMON:
            case FRAGMENT_NATURE_SUMMON:
            case FRAGMENT_LIGHT_SUMMON:
            case FRAGMENT_DARK_SUMMON:
                return true;
        }

        return false;
    }
}