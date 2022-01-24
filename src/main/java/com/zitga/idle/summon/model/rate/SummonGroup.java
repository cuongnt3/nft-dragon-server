package com.zitga.idle.summon.model.rate;

import com.zitga.idle.enumeration.dragon.EggType;
import com.zitga.idle.enumeration.random.RandomType;
import com.zitga.idle.player.model.Player;
import com.zitga.idle.random.service.RandomService;
import com.zitga.idle.summon.constant.SummonTag;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SummonGroup {

    private EggType eggType;

    // key: item id
    private Map<Integer, SummonEgg> eggSummonRates;

    // key: dragon id
    private Map<Integer, SummonDragon> dragonSummonRates;

    private RandomType randomType;

    public SummonGroup(Map<String, String> data) {
        eggSummonRates = new ConcurrentHashMap<>();
        dragonSummonRates = new ConcurrentHashMap<>();

        eggType = EggType.toType(Integer.parseInt(data.get(SummonTag.EGG_TYPE_TAG)));
        switch (eggType) {
            case UNCOMMON:
                randomType = RandomType.SUMMON_BASIC_EGG;
                break;
            case COMMON:
                randomType = RandomType.SUMMON_NORMAL_EGG;
                break;
            case RARE:
                randomType = RandomType.SUMMON_RARE_EGG;
                break;
        }
    }

    public void validate() {
        if (randomType == null) {
            throw new RuntimeException("Random type is invalid with egg type = " + eggType);
        }

        for (SummonEgg summonEgg : eggSummonRates.values()) {
            summonEgg.validate();
        }

        for (SummonDragon summonDragon : dragonSummonRates.values()) {
            summonDragon.validate();
        }
    }

    // ---------------------------------------- Getters ----------------------------------------
    public EggType getEggType() {
        return eggType;
    }

    public SummonEgg randomItem(Player player, List<Integer> excludedItemIds) {
        List<SummonEgg> filteredItems = filterExcludedItem(excludedItemIds);
        SummonEgg summonEgg = RandomService.randomRawBucket(player, randomType, eggType.getValue(), filteredItems);

        return eggSummonRates.get(summonEgg.getId());
    }

    public SummonDragon randomDragon(Player player, List<Integer> excludedDragonIds) {
        List<SummonDragon> filteredItems = filterExcludedDragon(excludedDragonIds);
        SummonDragon summonDragon = RandomService.randomRawBucket(player, randomType, eggType.getValue(), filteredItems);

        return dragonSummonRates.get(summonDragon.getId());
    }

    private List<SummonEgg> filterExcludedItem(List<Integer> excludedIds) {
        List<SummonEgg> result = new ArrayList<>();
        for (SummonEgg summonItem : eggSummonRates.values()) {
            if (!excludedIds.contains(summonItem.getId())) {
                result.add(summonItem);
            }
        }

        return result;
    }

    private List<SummonDragon> filterExcludedDragon(List<Integer> excludedIds) {
        List<SummonDragon> result = new ArrayList<>();
        for (SummonDragon summonItem : dragonSummonRates.values()) {
            if (!excludedIds.contains(summonItem.getId())) {
                result.add(summonItem);
            }
        }

        return result;
    }

    // ---------------------------------------- Setters ----------------------------------------
    public void addEggSummonRate(SummonEgg summonEgg) {
        eggSummonRates.put(summonEgg.getId(), summonEgg);
    }

    public void addDragonSummonRate(SummonDragon summonDragon) {
        dragonSummonRates.put(summonDragon.getId(), summonDragon);
    }
}
