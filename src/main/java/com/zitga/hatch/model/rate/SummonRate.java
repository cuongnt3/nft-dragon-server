package com.zitga.hatch.model.rate;

import com.zitga.idle.base.constant.BasicTag;
import com.zitga.idle.enumeration.random.RandomType;
import com.zitga.idle.hero.service.HeroService;
import com.zitga.idle.player.model.Player;
import com.zitga.idle.random.model.BucketRate;
import com.zitga.idle.summon.model.summonProduction.SummonProductionData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SummonRate {

    // key: groupId
    private final Map<Integer, SummonGroup> groups = new ConcurrentHashMap<>();

    private BucketRate<SummonGroup> bucketRate;

    private RandomType groupRandomType;

    public SummonRate(List<Map<String, String>> csvData, RandomType groupRandomType, RandomType rateRandomType) {
        SummonGroup group = null;
        for (Map<String, String> data : csvData) {
            if (data.containsKey(BasicTag.GROUP_TAG)) {
                group = new SummonGroup(data, rateRandomType);
                groups.put(group.getId(), group);
            }

            if (group != null) {
                SummonHero hero = new SummonHero(data);
                group.addHero(hero);
            } else {
                throw new RuntimeException("Summon group is null");
            }
        }

        this.bucketRate = new BucketRate<>(groups.values());
        this.groupRandomType = groupRandomType;
    }

    public SummonRate(List<Map<String, String>> csvData, int star, RandomType groupRandomType, RandomType rateRandomType) {
        SummonGroup group = null;
        for (Map<String, String> data : csvData) {
            if (data.containsKey(BasicTag.GROUP_TAG)) {
                group = new SummonGroup(data, rateRandomType);
                groups.put(group.getId(), group);
            }

            if (group != null) {
                SummonHero hero = new SummonHero(data, star);
                group.addHero(hero);
            } else {
                throw new RuntimeException("Summon group is null");
            }
        }

        this.bucketRate = new BucketRate<>(groups.values());
        this.groupRandomType = groupRandomType;
    }

    // ---------------------------------------- Getters ----------------------------------------
    public SummonHero randomHero(Player player, List<Integer> excludedHeroes) {
        SummonGroup group = bucketRate.randomBucket(player, groupRandomType);
        return group.randomHero(player, excludedHeroes);
    }

    public List<SummonHero> getAllHero(){
        List<SummonHero> allHeroList = new ArrayList<>();
        for (SummonGroup group: groups.values()){
            Map<Integer, SummonHero> heroGroup = group.getHeroes();
            allHeroList.addAll(heroGroup.values());
        }
        return allHeroList;
    }

    public void validate(Map<Integer, SummonProductionData> summonProductions, HeroService heroService) {
        for (SummonGroup group : groups.values()) {
            group.validate(summonProductions, heroService);
        }
    }
}