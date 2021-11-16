package com.zitga.hatch.utils;

import com.zitga.idle.enumeration.hero.HeroFactionType;
import com.zitga.idle.enumeration.summon.SummonProductionType;

public class SummonFragmentUtils {

    public static SummonProductionType toSummonProductionType(HeroFactionType type) {
        switch (type) {
            case WATER:
                return SummonProductionType.FRAGMENT_WATER_SUMMON;

            case FIRE:
                return SummonProductionType.FRAGMENT_FIRE_SUMMON;

            case ABYSS:
                return SummonProductionType.FRAGMENT_ABYSS_SUMMON;

            case NATURE:
                return SummonProductionType.FRAGMENT_NATURE_SUMMON;

            case LIGHT:
                return SummonProductionType.FRAGMENT_LIGHT_SUMMON;

            case DARK:
                return SummonProductionType.FRAGMENT_DARK_SUMMON;
        }

        return SummonProductionType.FRAGMENT_WATER_SUMMON;
    }

    public static HeroFactionType toHeroFactionType(SummonProductionType type) {
        switch (type) {
            case FRAGMENT_WATER_SUMMON:
                return HeroFactionType.WATER;

            case FRAGMENT_FIRE_SUMMON:
                return HeroFactionType.FIRE;

            case FRAGMENT_ABYSS_SUMMON:
                return HeroFactionType.ABYSS;

            case FRAGMENT_NATURE_SUMMON:
                return HeroFactionType.NATURE;

            case FRAGMENT_LIGHT_SUMMON:
                return HeroFactionType.LIGHT;

            case FRAGMENT_DARK_SUMMON:
                return HeroFactionType.DARK;
        }

        return HeroFactionType.WATER;
    }
}
