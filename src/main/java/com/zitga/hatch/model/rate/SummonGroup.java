package com.zitga.hatch.model.rate;

import com.zitga.idle.enumeration.random.RandomType;
import com.zitga.idle.hero.constant.HeroConstant;
import com.zitga.idle.hero.service.HeroService;
import com.zitga.idle.player.model.Player;
import com.zitga.idle.random.model.GroupRate;
import com.zitga.idle.random.service.RandomService;
import com.zitga.idle.summon.model.summonProduction.SummonProductionData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SummonGroup extends GroupRate {

    // key: heroId
    private final Map<Integer, SummonHero> heroes = new ConcurrentHashMap<>();

    private final RandomType rateRandomType;

    public SummonGroup(Map<String, String> data, RandomType rateRandomType) {
        super(data);
        this.rateRandomType = rateRandomType;
    }

    // ---------------------------------------- Getters ----------------------------------------
    public Map<Integer, SummonHero> getHeroes() {
        return heroes;
    }

    public SummonHero randomHero(Player player, List<Integer> excludedHeroIds) {
        List<SummonHero> filteredHeroes = filterExcludedHero(excludedHeroIds);
        SummonHero summonHero = RandomService.randomRawBucket(player, rateRandomType, getId(), filteredHeroes);

        return heroes.get(summonHero.getHeroId());
    }

    private List<SummonHero> filterExcludedHero(List<Integer> excludedHeroIds) {
        List<SummonHero> result = new ArrayList<>();
        for (SummonHero hero : heroes.values()) {
            if (!excludedHeroIds.contains(hero.getHeroId())) {
                result.add(hero);
            }
        }

        return result;
    }

    public void validate(Map<Integer, SummonProductionData> summonProductions, HeroService heroService) {
        boolean isContainValidGroup = false;
        for (SummonHero hero : heroes.values()) {
            if (!heroService.isValidHeroId(hero.getHeroId())) {
                throw new RuntimeException("SummonGroup invalid heroId: " + hero.getHeroId());
            }

            if (!summonProductions.containsKey(hero.getHeroId())) {
                // only 1 hero is unlimited is enough
                isContainValidGroup = true;
            }

            if (hero.getStar() < HeroConstant.MIN_STAR || hero.getStar() > HeroConstant.MAX_STAR) {
                throw new RuntimeException("SummonGroup invalid star: " + hero.getStar());
            }
        }

        if (!isContainValidGroup) {
            throw new RuntimeException("SummonGroup must have at least 1 hero that isn't in SummonProduction");
        }
    }

    // ---------------------------------------- Setters ----------------------------------------
    public void addHero(SummonHero hero) {
        heroes.put(hero.getHeroId(), hero);
    }
}
