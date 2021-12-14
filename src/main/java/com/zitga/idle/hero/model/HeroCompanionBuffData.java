package com.zitga.idle.hero.model;

import com.zitga.idle.battle.constant.BattleConstant;
import com.zitga.idle.dragon.utils.DragonUtils;
import com.zitga.idle.enumeration.hero.HeroCompanionBuffConditionType;
import com.zitga.idle.enumeration.hero.HeroFactionType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HeroCompanionBuffData {

    private final int id;

    private int totalSameFactionConditionNumber;
    private final List<HeroCompanionBuffCondition> sameFactionConditions = new ArrayList<>();

    public HeroCompanionBuffData(int id) {
        this.id = id;
    }

    // ---------------------------------------- Getters ----------------------------------------
    public int getId() {
        return id;
    }

    public boolean isMatch(List<InventoryHero> inventoryHeroes) {
        if (inventoryHeroes.size() < BattleConstant.NUMBER_SLOT) {
            return false;
        }

        Map<HeroFactionType, Integer> heroCountMap = new ConcurrentHashMap<>();
        for (InventoryHero hero : inventoryHeroes) {
            HeroFactionType heroFactionType = DragonUtils.convertToHeroFactionType(hero.getHeroId());
            heroCountMap.put(heroFactionType, heroCountMap.getOrDefault(heroFactionType, 0) + 1);
        }

        List<Integer> heroSets = getAllHeroSets(heroCountMap);
        if (heroSets.size() != sameFactionConditions.size()) {
            return false;
        }

        int delta = totalSameFactionConditionNumber;
        for (int i = 0; i < heroSets.size(); i++) {
            int numberHeroes = heroSets.get(i);
            HeroCompanionBuffCondition condition = sameFactionConditions.get(i);

            if (numberHeroes == condition.getConditionNumber()) {
                delta -= numberHeroes;
            }
        }

        return delta == 0;
    }

    // ---------------------------------------- Setters ----------------------------------------
    public void addCondition(Map<String, String> data) {
        HeroCompanionBuffCondition condition = new HeroCompanionBuffCondition(data);

        if (condition.getConditionType() == HeroCompanionBuffConditionType.SAME_FACTION) {
            sameFactionConditions.add(condition);
            sameFactionConditions.sort(null);

            this.totalSameFactionConditionNumber += condition.getConditionNumber();
        }
    }

    public List<Integer> getAllHeroSets(Map<HeroFactionType, Integer> heroCountMap) {
        List<Integer> result = new ArrayList<>();

        for (int numberHeroes : heroCountMap.values()) {
            if (numberHeroes > 1) {
                result.add(numberHeroes);
            }
        }

        result.sort(null);
        return result;
    }
}