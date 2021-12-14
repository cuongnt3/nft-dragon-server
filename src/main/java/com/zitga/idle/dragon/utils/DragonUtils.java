package com.zitga.idle.dragon.utils;

import com.zitga.idle.ServiceController;
import com.zitga.idle.battle.model.message.BattleHeroInbound;
import com.zitga.idle.enumeration.hero.HeroClassType;
import com.zitga.idle.enumeration.hero.HeroFactionType;
import com.zitga.idle.hero.constant.HeroConstant;
import com.zitga.idle.hero.model.InventoryHero;
import com.zitga.idle.hero.service.HeroDataService;
import com.zitga.utils.RandomUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DragonUtils {

    public static int convertToHeroFragment(int heroId, int star) {
        return heroId * 100 + star;
    }

    public static HeroFactionType convertToHeroFactionType(int heroId) {
        return HeroFactionType.toFactionType(heroId / HeroConstant.HERO_ID_DELTA);
    }

    public static int convertToHeroStarFromFragment(int heroFragmentId) {
        return heroFragmentId % 100;
    }

    public static int convertToHeroIdFromFragment(int heroFragmentId) {
        return heroFragmentId / 100;
    }

    public static int convertToHeroFoodId(int foodType, int star) {
        return foodType * HeroConstant.HERO_FOOD_ID_DELTA + star;
    }

    public static int convertToHeroStarFromFoodId(int foodId) {
        return foodId % HeroConstant.HERO_FOOD_ID_DELTA;
    }

    public static int convertToHeroFoodTypeFromFoodId(int foodId) {
        return foodId / HeroConstant.HERO_FOOD_ID_DELTA;
    }

    public static boolean isContainHeroClassType(List<InventoryHero> inventoryHeroes, HeroClassType heroClassType) {
        HeroDataService heroDataService = ServiceController.instance().getHeroDataService();
        for (InventoryHero inventoryHero : inventoryHeroes) {
            if (heroDataService.getHeroClassType(inventoryHero.getHeroId()) == heroClassType) {
                return true;
            }
        }

        return false;
    }

    public static boolean isContainHeroFactionType(List<InventoryHero> inventoryHeroes, HeroFactionType heroFactionType) {
        for (InventoryHero inventoryHero : inventoryHeroes) {
            if (inventoryHero.getFactionType() == heroFactionType) {
                return true;
            }
        }

        return false;
    }

    public static boolean isContainHeroWithStar(List<InventoryHero> inventoryHeroes, int star) {
        for (InventoryHero inventoryHero : inventoryHeroes) {
            if (inventoryHero.getStar() >= star) {
                return true;
            }
        }

        return false;
    }

    public static int randomHeroClass() {
        return RandomUtils.nextInt(HeroClassType.MAGE.getValue(), HeroClassType.RANGER.getValue() + 1);
    }

    public static int randomHeroFaction() {
        return RandomUtils.nextInt(HeroFactionType.WATER.getValue(), HeroFactionType.DARK.getValue() + 1);
    }

    public static boolean isContainDuplicatedHeroInventoryId(List<Long> inventoryIdList) {
        Set<Long> heroInventoryIdSet = new HashSet<>(inventoryIdList);
        if (heroInventoryIdSet.size() < inventoryIdList.size()) {
            return true;
        }

        return false;
    }

    public static boolean isContainDuplicatedBattleHeroInbound(List<BattleHeroInbound> inbounds) {
        Set<Long> heroSet = new HashSet<>();

        for (BattleHeroInbound heroInbound : inbounds) {
            heroSet.add(heroInbound.getHeroInventoryId());
        }
        if (heroSet.size() < inbounds.size()) {
            return true;
        }

        return false;
    }
}
