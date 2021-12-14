package com.zitga.idle.battle.service;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.bean.annotation.BeanMethod;
import com.zitga.idle.battle.constant.BattleTag;
import com.zitga.idle.battle.utils.BattleCsvPathUtils;
import com.zitga.idle.utils.FileService;
import com.zitga.support.CsvService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@BeanComponent
public class BattleDataService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeanField
    private FileService fileService;

    @BeanField
    private CsvService csvService;

    private List<Integer> forbiddenHeroIds;
    private List<Integer> warningHeroIds;

    @BeanMethod
    private void init() {
        logger.info("Initializing BattleDataService ...");

        createHeroCheckConfig();
    }

    // ---------------------------------------- Setters ----------------------------------------
    private void createHeroCheckConfig() {
        String csvString = fileService.readCsvFile(BattleCsvPathUtils.getBossHeroCheckPath());
        List<Map<String, String>> csvData = csvService.read(csvString);

        forbiddenHeroIds = new ArrayList<>();
        warningHeroIds = new ArrayList<>();

        for (Map<String, String> data : csvData) {
            if (data.containsKey(BattleTag.HERO_FORBIDDEN_ID_TAG)) {
                forbiddenHeroIds.add(Integer.parseInt(data.get(BattleTag.HERO_FORBIDDEN_ID_TAG)));
            }

            if (data.containsKey(BattleTag.HERO_WARNING_ID_TAG)) {
                warningHeroIds.add(Integer.parseInt(data.get(BattleTag.HERO_WARNING_ID_TAG)));
            }
        }
    }

    // ---------------------------------------- Getters ----------------------------------------
    public void checkHero(int heroId) {
        for (int forbiddenHeroId : forbiddenHeroIds) {
            if (forbiddenHeroId == heroId) {
                throw new RuntimeException(String.format("%s is in forbidden list", heroId));
            }
        }

        for (int warningHeroId : warningHeroIds) {
            if (warningHeroId == heroId) {
                logger.trace("[HeroId = {}] is in warning list", heroId);
            }
        }
    }
}