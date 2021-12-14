package com.zitga.idle.summon.service;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanDelayedMethod;
import com.zitga.bean.annotation.BeanField;
import com.zitga.bean.annotation.BeanMethod;
import com.zitga.idle.base.constant.BasicTag;
import com.zitga.idle.dragon.model.InventoryDragonEgg;
import com.zitga.idle.enumeration.dragon.EggFragmentType;
import com.zitga.idle.enumeration.dragon.EggType;
import com.zitga.idle.enumeration.resource.ResourceType;
import com.zitga.idle.player.model.Player;
import com.zitga.idle.resource.model.Reward;
import com.zitga.idle.summon.constant.SummonTag;
import com.zitga.idle.summon.model.rate.SummonDragon;
import com.zitga.idle.summon.model.rate.SummonEgg;
import com.zitga.idle.summon.model.rate.SummonGroup;
import com.zitga.idle.summon.utils.SummonCsvPathUtils;
import com.zitga.idle.utils.FileService;
import com.zitga.support.CsvService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@BeanComponent
public class SummonDataService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeanField
    private FileService fileService;

    @BeanField
    private CsvService csvService;

    // key: egg type
    private Map<EggType, Integer> eggHatchDurationMap = new ConcurrentHashMap<>();

    // key: egg type
    private Map<EggType, SummonGroup> eggSummonRateMap = new ConcurrentHashMap<>();

    @BeanMethod
    private void init() {
        logger.info("Initializing SummonDataService ...");

        createHatchDuration();
        createEggSummonRate();
        createDragonSummonRate();
    }

    @BeanDelayedMethod
    private void validate() {
        for (EggType eggType : EggType.values()) {
            if (!eggHatchDurationMap.containsKey(eggType)) {
                throw new RuntimeException(String.format("No duration config for egg type %s ", eggType));
            }

            if (!eggSummonRateMap.containsKey(eggType)) {
                throw new RuntimeException(String.format("No duration config for egg type %s ", eggType));
            } else {
                SummonGroup summonGroup = eggSummonRateMap.get(eggType);
                summonGroup.validate();
            }
        }
    }

    // ---------------------------------------- Getters ----------------------------------------
    public Reward randomHatchEgg(Player player, InventoryDragonEgg dragonEgg) {
        EggType eggType = EggType.toType(dragonEgg.getEggType());

        SummonGroup summonGroup = eggSummonRateMap.get(eggType);
        SummonEgg summonEgg = summonGroup.randomItem(player, new ArrayList<>());

        Reward result = null;
        switch (summonEgg.getType()) {
            case EGG_FRAGMENT:
                result = new Reward(ResourceType.EGG_FRAGMENT, EggFragmentType.COMMON.getValue(), 1);
                break;
            case DRAGON:
                SummonDragon summonDragon = summonGroup.randomDragon(player, new ArrayList<>());
                result = new Reward(ResourceType.DRAGON, summonDragon.getId(), 1, summonEgg.getStar());
                break;
        }

        return result;
    }

    public int getHatchDuration(int eggType) {
        return eggHatchDurationMap.get(EggType.toType(eggType));
    }

    // ---------------------------------------- Setters ----------------------------------------
    public void createHatchDuration() {
        String csvString = fileService.readCsvFile(SummonCsvPathUtils.getHatchDurationPath());
        List<Map<String, String>> csvData = csvService.read(csvString);

        eggHatchDurationMap = new ConcurrentHashMap<>();
        for (Map<String, String> data : csvData) {
            int eggType = Integer.parseInt(data.get(SummonTag.EGG_TYPE_TAG));
            EggType type = EggType.toType(eggType);

            int duration = Integer.parseInt(data.get(BasicTag.DURATION_TAG));
            eggHatchDurationMap.put(type, duration);
        }
    }

    public void createEggSummonRate() {
        String csvString = fileService.readCsvFile(SummonCsvPathUtils.getEggSummonRatePath());
        List<Map<String, String>> csvData = csvService.read(csvString);

        eggSummonRateMap = new ConcurrentHashMap<>();
        SummonGroup summonGroup = null;
        SummonEgg summonEgg = null;
        for (Map<String, String> data : csvData) {
            if (data.containsKey(SummonTag.EGG_TYPE_TAG)) {
                summonGroup = new SummonGroup(data);

                eggSummonRateMap.put(summonGroup.getEggType(), summonGroup);
            }

            if (summonGroup != null) {
                summonEgg = new SummonEgg(data);
                summonGroup.addEggSummonRate(summonEgg);
            } else {
                throw new RuntimeException("Egg summon pool is invalid");
            }
        }
    }

    public void createDragonSummonRate() {
        String csvString = fileService.readCsvFile(SummonCsvPathUtils.getDragonSummonRatePath());
        List<Map<String, String>> csvData = csvService.read(csvString);

        SummonDragon summonDragon = null;
        SummonGroup summonGroup = null;
        EggType eggType = null;
        for (Map<String, String> data : csvData) {
            if (data.containsKey(SummonTag.EGG_TYPE_TAG)) {
                int type = Integer.parseInt(data.get(SummonTag.EGG_TYPE_TAG));
                eggType = EggType.toType(type);
                summonGroup = eggSummonRateMap.get(eggType);
            }

            if (summonGroup != null) {
                summonDragon = new SummonDragon(data);
                summonGroup.addDragonSummonRate(eggType, summonDragon);
            } else {
                throw new RuntimeException("Dragon summon pool is invalid");
            }
        }
    }
}