package com.zitga.idle.battleInfo.service.summoner;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.bean.annotation.BeanMethod;
import com.zitga.idle.base.constant.BasicTag;
import com.zitga.idle.battleInfo.utils.SummonerCsvPathUtils;
import com.zitga.idle.resource.model.Reward;
import com.zitga.idle.utils.FileService;
import com.zitga.support.CsvService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@BeanComponent
public class SummonerDataService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeanField
    private FileService fileService;

    @BeanField
    private CsvService csvService;

    // Key: summoner level, value exp needed to level up
    private Map<Integer, Long> summonerExpRequirements;

    // Key: summoner level, reward when level up
    private Map<Integer, List<Reward>> summonerLevelRewards;

    private int summonerLevelCap;

    @BeanMethod
    private void init() {
        logger.info("Initializing SummonerDataService ...");

        createSummonerExp();
    }

    // ---------------------------------------- Load data ----------------------------------------
    private void createSummonerExp() {
        String csvString = fileService.readCsvFile(SummonerCsvPathUtils.getSummonerExpPath());
        List<Map<String, String>> csvData = csvService.read(csvString);

        List<Reward> rewards = null;

        summonerExpRequirements = new ConcurrentHashMap<>();
        summonerLevelRewards = new ConcurrentHashMap<>();

        for (Map<String, String> data : csvData) {
            if (data.containsKey(BasicTag.LEVEL_TAG)) {
                int level = Integer.parseInt(data.get(BasicTag.LEVEL_TAG));
                if (level > summonerLevelCap) {
                    summonerLevelCap = level;
                }

                rewards = new ArrayList<>();
                summonerLevelRewards.put(level, rewards);

                long exp = Long.parseLong(data.get(BasicTag.EXP_TAG));
                summonerExpRequirements.put(level, exp);
            }

            if (rewards != null) {
                Reward reward = new Reward(data);
                rewards.add(reward);
            } else {
                throw new RuntimeException("Summoner level rewards is null");
            }
        }
    }

    // ---------------------------------------- Getters ----------------------------------------
    public long getSummonerExp(int summonerLevel) {
        return summonerExpRequirements.getOrDefault(summonerLevel, Long.MAX_VALUE);
    }

    public List<Reward> getLevelReward(int summonerLevel) {
        return summonerLevelRewards.get(summonerLevel);
    }

    public int getSummonerLevelCap() {
        return summonerLevelCap;
    }
}