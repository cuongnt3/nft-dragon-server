package com.zitga.hatch.service;

import com.zitga.base.constant.LogicCode;
import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.enumeration.hatch.HatchType;
import com.zitga.hatch.dao.PlayerDragonHatchDAO;
import com.zitga.hatch.model.PlayerDragonHatch;
import com.zitga.hatch.model.message.HatchResult;
import com.zitga.player.model.Player;
import com.zitga.utils.TimeUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@BeanComponent
public class HatchService {

    @BeanField
    private HatchDataService hatchDataService;

    @BeanField
    private PlayerDragonHatchDAO dragonHatchDAO;

    public void createHeroSummon(Player player) {
        PlayerDragonHatch dragonHatch = dragonHatchDAO.create(player);
        player.setDragonHatch(dragonHatch);
    }

    public void loadHeroSummon(Player player) {
        PlayerDragonHatch heroSummon = dragonHatchDAO.findOrCreate(player);
        player.setDragonHatch(heroSummon);
    }

    public void saveHeroSummon(Player player) {
        dragonHatchDAO.save(player.getOrLoadDragonHatch());
    }

    // --------------------------------------------------------------------------------
    public HatchResult hatchDragon(Player player, HatchType hatchType) {
        PlayerDragonHatch dragonHatch = player.getOrLoadDragonHatch();

        if (isCanHatch(dragonHatch, hatchType)) {
            dragonHatch.updateHatchTime(hatchType, TimeUtils.getCurrentTimeInGMT());
            return hatchDragon(player, summonType);
        }
    }

    // ---------------------------------------- Summon helpers ----------------------------------------
    private HatchResult hatchDragon(Player player, HatchType hatchType) {
        HatchResult result = new HatchResult();

//        if (player.getHeroCollection().isHeroCollectionFull(1)) {
//            return result.withCode(LogicCode.HERO_COLLECTION_FULL);
//        }

        summonAndSaveData(player, hatchType, 1, result);
        return result.withCode(LogicCode.SUCCESS);
    }


    public void summonAndSaveData(Player player, SummonType summonType, int numberSummon, HatchResult result) {
        List<SummonHero> summonHeroes = massSummon(player, summonType, numberSummon);
        for (SummonHero summonHero : summonHeroes) {
            InventoryHero inventoryHero = heroService.addHero(player, summonHero.getHeroId(), summonHero.getStar(),
                    TrackingUtils.toSource(summonType), null);

            result.addHero(inventoryHero);
        }

        // save data
        heroService.saveHeroCollection(player);
        saveHeroSummon(player);
    }

    public List<SummonHero> massSummon(Player player, SummonType summonType, int numberSummon) {
        List<SummonHero> result = new ArrayList<>();

        PlayerHeroSummon heroSummon = player.getOrLoadHeroSummon();

        if (summonType == SummonType.SUMMON_PREMIUM) {
            // fixed summon rate
            if (heroSummon.getNumberFixedSummon() < hatchDataService.getNumberFixedPremiumSummon()) {
                int numberFixedSummon = hatchDataService.getNumberFixedPremiumSummon() - heroSummon.getNumberFixedSummon();

                numberFixedSummon = Math.min(numberSummon, numberFixedSummon);
                numberSummon = numberSummon - numberFixedSummon;

                int startFixedNumberSummon = heroSummon.getNumberFixedSummon();
                int endFixedNumberSummon = heroSummon.getNumberFixedSummon() + numberFixedSummon;

                // summon
                for (int i = startFixedNumberSummon; i < endFixedNumberSummon; i++) {
                    SummonHero summonHero = null;
                    if (isUseAbtestHero(player, i)) {
                        String remoteData = remoteConfigService.getRemoteConfigAbTest(player, RemoteConfigConstant.SUMMON_REMOTE_KEY);
                        int heroId = Integer.parseInt(remoteData);
                        summonHero = new SummonHero(heroId, SummonConstant.RED_HERO_DEFAULT_STAR);
                    } else {
                        SummonRate summonRate = hatchDataService.getFixedPremiumSummonRate(i);

                        List<Integer> excludedHeroes = new ArrayList<>();
                        summonHero = summonRate.randomHero(player, excludedHeroes);
                    }
                    heroSummon.updateSummonProduction(summonHero.getHeroId(), getProductionType(summonType));

                    result.add(summonHero);
                }

                heroSummon.setNumberFixedSummon(heroSummon.getNumberFixedSummon() + numberFixedSummon);
            }
        }

        SummonRate summonRate = hatchDataService.getSummonRate(summonType);

        // summon
        for (int i = 0; i < numberSummon; i++) {
            SummonHero summonHero = summonRate.randomHero(player, heroSummon.getExcludedHeroes());
            heroSummon.updateSummonProduction(summonHero.getHeroId(), getProductionType(summonType));

            result.add(summonHero);
        }

        return result;
    }

    // ---------------------------------------- Helpers ----------------------------------------
    public boolean isCanHatch(PlayerDragonHatch dragonHatch, HatchType hatchType) {
        float summonFreeInterval = hatchDataService.getSummonFreeInterval(hatchType);

        Date now = TimeUtils.getCurrentTimeInGMT();
        Date lastHatchTime = dragonHatch.getLastHatchTime(hatchType);

        long delta = Duration.between(lastHatchTime.toInstant(), now.toInstant()).getSeconds();
        if (delta > summonFreeInterval) {
            return true;
        }

        return false;
    }
}