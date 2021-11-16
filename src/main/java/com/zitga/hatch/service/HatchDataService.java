package com.zitga.hatch.service;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanDelayedMethod;
import com.zitga.bean.annotation.BeanField;
import com.zitga.bean.annotation.BeanMethod;
import com.zitga.enumeration.hatch.HatchType;
import com.zitga.idle.authentication.utils.FileComparator;
import com.zitga.idle.config.game.GameConfig;
import com.zitga.idle.enumeration.random.RandomType;
import com.zitga.idle.enumeration.summon.SummonType;
import com.zitga.idle.hero.service.HeroService;
import com.zitga.idle.summon.constant.SummonConstant;
import com.zitga.idle.summon.model.config.SummonFreeInterval;
import com.zitga.idle.summon.model.config.SummonPrice;
import com.zitga.idle.summon.model.config.SummonProductionConfig;
import com.zitga.idle.summon.model.rate.SummonRate;
import com.zitga.idle.summon.model.summonProduction.SummonProductionData;
import com.zitga.idle.summon.utils.SummonCsvPathUtils;
import com.zitga.idle.utils.FileService;
import com.zitga.support.CsvService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@BeanComponent
public class HatchDataService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeanField
    private FileService fileService;

    @BeanField
    private CsvService csvService;

    @BeanField
    private HeroService heroService;

    @BeanField
    private GameConfig gameConfig;

    // Key: heroId
    private Map<Integer, SummonProductionData> summonProductions;
    private SummonProductionConfig summonProductionConfig;
    private Map<SummonType, SummonRate> summonRates;

    private List<SummonPrice> summonPrices;
    private Map<SummonType, SummonFreeInterval> summonFreeIntervals;

    // key: number summon
    private Map<Integer, SummonRate> fixedPremiumSummonRate;

    @BeanMethod
    private void init() {
        logger.info("Initializing SummonDataService ...");

        createSummonProductions();
        createSummonProductionConfig();

        createSummonRates();
        createFixedPremiumSummonRates();

        createSummonPrices();
        createSummonFreeIntervals();
    }

    @BeanDelayedMethod
    private void validate() {
        for (SummonRate summonRate : summonRates.values()) {
            summonRate.validate(summonProductions, heroService);
        }
    }

    // ---------------------------------------- Setters ----------------------------------------
    private void createSummonProductionConfig() {
        String csvString = fileService.readCsvFile(SummonCsvPathUtils.getSummonProductionConfigPath());
        List<Map<String, String>> csvData = csvService.read(csvString);

        summonProductionConfig = new SummonProductionConfig(csvData);
    }

    private void createSummonProductions() {
        String csvString = fileService.readCsvFile(SummonCsvPathUtils.getSummonHeroProductionPath());
        List<Map<String, String>> csvData = csvService.read(csvString);

        summonProductions = new ConcurrentHashMap<>();

        for (Map<String, String> entry : csvData) {
            SummonProductionData summonProductionData = new SummonProductionData(entry);
            summonProductions.put(summonProductionData.getHeroId(), summonProductionData);
        }
    }

    private void createSummonRates() {
        String[] summonRatesPath = SummonCsvPathUtils.getSummonRatesPath();

        summonRates = new ConcurrentHashMap<>();

        for (int i = 0; i < summonRatesPath.length; i++) {
            String path = String.format("%s/%s", SummonCsvPathUtils.getSummonPath(), summonRatesPath[i]);
            String summonGroupString = fileService.readCsvFile(path);

            List<Map<String, String>> csvData = csvService.read(summonGroupString);

            SummonType summonType = SummonType.toSummonType(i);
            RandomType groupRandomType = getGroupRandomType(summonType);
            RandomType rateRandomType = getRateRandomType(summonType);

            SummonRate summonRate = new SummonRate(csvData, groupRandomType, rateRandomType);

            summonRates.put(SummonType.toSummonType(i), summonRate);
        }
    }

    private void createFixedPremiumSummonRates() {
        List<File> files = fileService.listFile(gameConfig.getCsvPath() + "/" + SummonCsvPathUtils.getFixedPremiumSummonRatePath());
        files.sort(new FileComparator());

        fixedPremiumSummonRate = new ConcurrentHashMap<>();
        for (int i = 0; i < files.size(); i++) {
            File file = files.get(i);
            if (!file.getName().equalsIgnoreCase(String.format(SummonConstant.FIXED_SUMMON_RATE_NAME, i))) {
                throw new RuntimeException("Wrong fixed summon file name " + file.getName());
            }

            String csvString = fileService.readFileContent(file);
            List<Map<String, String>> csvData = csvService.read(csvString);

            SummonRate summonRate = new SummonRate(csvData, RandomType.SUMMON_PREMIUM_GROUP, RandomType.SUMMON_PREMIUM_GROUP);
            fixedPremiumSummonRate.put(i, summonRate);
        }
    }

    private void createSummonPrices() {
        String csvDataString = fileService.readCsvFile(SummonCsvPathUtils.getSummonPricePath());
        List<Map<String, String>> csvData = csvService.read(csvDataString);

        summonPrices = new ArrayList<>();

        for (Map<String, String> data : csvData) {
            SummonPrice summonPrice = new SummonPrice(data);
            summonPrices.add(summonPrice);
        }
    }

    private void createSummonFreeIntervals() {
        String csvDataString = fileService.readCsvFile(SummonCsvPathUtils.getSummonFreeIntervalPath());
        List<Map<String, String>> csvData = csvService.read(csvDataString);

        summonFreeIntervals = new ConcurrentHashMap<>();

        for (Map<String, String> data : csvData) {
            SummonFreeInterval freeInterval = new SummonFreeInterval(data);
            summonFreeIntervals.put(freeInterval.getSummonType(), freeInterval);
        }
    }

    // ---------------------------------------- Getters ----------------------------------------
    public SummonRate getSummonRate(SummonType summonType) {
        return summonRates.get(summonType);
    }

    public Map<Integer, SummonProductionData> getSummonProductions() {
        return summonProductions;
    }

    public SummonProductionConfig getSummonProductionConfig() {
        return summonProductionConfig;
    }

    public SummonPrice getSummonPrice(SummonType summonType, int numberSummon) {
        for (SummonPrice summonPrice : summonPrices) {
            if (summonPrice.getSummonType() == summonType && summonPrice.getSummonNumber() == numberSummon) {
                return summonPrice;
            }
        }

        return null;
    }

    public float getSummonFreeInterval(HatchType hatchType) {
        return summonFreeIntervals.get(hatchType).getFreeInterval();
    }

    public int getNumberFixedPremiumSummon() {
        return fixedPremiumSummonRate.size();
    }

    public SummonRate getFixedPremiumSummonRate(int numberSummon) {
        return fixedPremiumSummonRate.get(numberSummon);
    }

    private RandomType getGroupRandomType(SummonType summonType) {
        switch (summonType) {
            case SUMMON_BASIC:
                return RandomType.SUMMON_BASIC_GROUP;

            case SUMMON_PREMIUM:
                return RandomType.SUMMON_PREMIUM_GROUP;

            case SUMMON_FRIEND:
                return RandomType.SUMMON_FRIEND_GROUP;

            case SUMMON_POINT:
                return RandomType.SUMMON_POINT_GROUP;
        }

        return RandomType.SUMMON_BASIC_GROUP;
    }

    private RandomType getRateRandomType(SummonType summonType) {
        switch (summonType) {
            case SUMMON_BASIC:
                return RandomType.SUMMON_BASIC;

            case SUMMON_PREMIUM:
                return RandomType.SUMMON_PREMIUM;

            case SUMMON_FRIEND:
                return RandomType.SUMMON_FRIEND;

            case SUMMON_POINT:
                return RandomType.SUMMON_POINT;
        }

        return RandomType.SUMMON_BASIC;
    }
}