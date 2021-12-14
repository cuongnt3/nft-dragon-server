package com.zitga.idle.hero.service;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanDelayedMethod;
import com.zitga.bean.annotation.BeanField;
import com.zitga.bean.annotation.BeanMethod;
import com.zitga.idle.base.constant.BasicTag;
import com.zitga.idle.battle.utils.BattleCsvPathUtils;
import com.zitga.idle.dragon.utils.DragonUtils;
import com.zitga.idle.enumeration.hero.HeroClassType;
import com.zitga.idle.enumeration.hero.HeroEvolveFoodMaterialType;
import com.zitga.idle.enumeration.hero.HeroFactionType;
import com.zitga.idle.hero.constant.HeroConstant;
import com.zitga.idle.hero.constant.HeroTag;
import com.zitga.idle.hero.model.FormationData;
import com.zitga.idle.hero.model.HeroCompanionBuffData;
import com.zitga.idle.hero.model.InventoryHero;
import com.zitga.idle.hero.utils.HeroCsvPathUtils;
import com.zitga.idle.utils.FileService;
import com.zitga.support.CsvService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@BeanComponent
public class HeroDataService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeanField
    private FileService fileService;

    @BeanField
    private CsvService csvService;

    // Key: heroId, value: hero tier
    private Map<Integer, Integer> heroTiers;

    // Key: heroId, value: HeroClassType
    private final Map<Integer, HeroClassType> heroClassTypes;

    // Key: heroId, value: hero base star
    private Map<Integer, Integer> heroBaseStars;

    // Key: heroStar_heroFaction, value: number of heroes
    private Map<String, Integer> heroBaseStarCountMap;

    // Key: formationId
    private Map<Integer, FormationData> formationDataMap;

    private final Set<Integer> allHeroIds;
    private Set<Integer> availableHeroIds;

    private List<HeroCompanionBuffData> heroCompanionBuffDataList;

    public HeroDataService() {
        heroClassTypes = new ConcurrentHashMap<>();
        allHeroIds = new HashSet<>();
    }

    @BeanMethod
    private void init() {
        logger.info("Initializing HeroDataService ...");

        createHeroTiers();
        createAvailableHeroMap();
        createHeroBaseStars();
        createHeroCompanionBuffData();
        createFormationData();
    }

    @BeanDelayedMethod
    private void validate() {
        if (allHeroIds.size() != heroTiers.size()) {
            throw new RuntimeException("Hero list and hero tier list is not the same");
        }

        for (int heroId : allHeroIds) {
            if (!heroTiers.containsKey(heroId)) {
                throw new RuntimeException("Hero tier list doesn't contain heroId = " + heroId);
            }
        }
    }

    // ---------------------------------------- Setters ----------------------------------------
    private void createHeroTiers() {
        String csvString = fileService.readCsvFile(HeroCsvPathUtils.getHeroTierPath());
        List<Map<String, String>> csvData = csvService.read(csvString);

        heroTiers = new ConcurrentHashMap<>();
        for (Map<String, String> data : csvData) {
            int heroId = Integer.parseInt(data.get(HeroTag.HERO_ID_TAG));
            int tier = Integer.parseInt(data.get(BasicTag.TIER_TAG));

            if (!heroTiers.containsKey(heroId)) {
                heroTiers.put(heroId, tier);
            } else {
                throw new RuntimeException("Hero tier is already existed: heroId = " + heroId);
            }
        }
    }

    private void createAvailableHeroMap() {
        String csvString = fileService.readCsvFile(HeroCsvPathUtils.getAvailableHeroesPath());
        List<Map<String, String>> csvData = csvService.read(csvString);

        availableHeroIds = new HashSet<>();
        for (Map<String, String> data : csvData) {
            int heroId = Integer.parseInt(data.get(BasicTag.ID_TAG));
            if (heroId > HeroConstant.HERO_ID_DELTA) {
                availableHeroIds.add(heroId);
            }
        }
    }

    private void createHeroBaseStars() {
        String csvString = fileService.readCsvFile(HeroCsvPathUtils.getHeroBaseStarPath());
        List<Map<String, String>> csvData = csvService.read(csvString);

        heroBaseStars = new ConcurrentHashMap<>();
        heroBaseStarCountMap = new ConcurrentHashMap<>();

        for (Map<String, String> data : csvData) {
            int heroId = Integer.parseInt(data.get(BasicTag.ID_TAG));
            if (heroId > HeroConstant.HERO_ID_DELTA) {
                int baseStar = Integer.parseInt(data.get(BasicTag.STAR_TAG));
                HeroFactionType factionType = DragonUtils.convertToHeroFactionType(heroId);

                heroBaseStars.put(heroId, baseStar);

                String tag = String.format("%s_%s", baseStar, factionType.getValue());

                int heroCount = heroBaseStarCountMap.getOrDefault(tag, 0);
                heroBaseStarCountMap.put(tag, heroCount + 1);
            }
        }
    }

    private void createHeroCompanionBuffData() {
        String csvString = fileService.readCsvFile(BattleCsvPathUtils.getBattleCompanionBuffPath());
        List<Map<String, String>> csvData = csvService.read(csvString);

        heroCompanionBuffDataList = new ArrayList<>();

        HeroCompanionBuffData companionBuffData = null;
        for (Map<String, String> data : csvData) {
            if (data.containsKey(BasicTag.ID_TAG)) {
                int id = Integer.parseInt(data.get(BasicTag.ID_TAG));

                companionBuffData = new HeroCompanionBuffData(id);
                heroCompanionBuffDataList.add(companionBuffData);
            }

            if (companionBuffData != null) {
                companionBuffData.addCondition(data);
            } else {
                throw new RuntimeException("Companion buff doesn't have id");
            }
        }
    }

    private void createFormationData() {
        String csvString = fileService.readCsvFile(BattleCsvPathUtils.getBattleFormationPath());
        List<Map<String, String>> csvData = csvService.read(csvString);

        formationDataMap = new ConcurrentHashMap<>();

        for (Map<String, String> data : csvData) {
            FormationData formationData = new FormationData(data);
            formationDataMap.put(formationData.getId(), formationData);
        }
    }

    public void addHeroId(int heroId) {
        allHeroIds.add(heroId);
    }

    public void addHeroClass(int heroId, HeroClassType heroClassType) {
        heroClassTypes.put(heroId, heroClassType);
    }

    // ---------------------------------------- Getters ----------------------------------------
    public int getHeroTier(int heroId) {
        return heroTiers.getOrDefault(heroId, 0);
    }

    public int getHeroBaseStar(int heroId) {
        return heroBaseStars.getOrDefault(heroId, 0);
    }

    public int getHeroBaseStarCount(int star, HeroFactionType factionType) {
        String tag = String.format("%s_%s", star, factionType.getValue());
        return heroBaseStarCountMap.getOrDefault(tag, 0);
    }

    public int getHeroBaseStarCount(int star) {
        int result = 0;
        for (int i = HeroFactionType.WATER.getValue(); i <= HeroFactionType.DARK.getValue(); i++) {
            String tag = String.format("%s_%s", star, i);
            result += heroBaseStarCountMap.getOrDefault(tag, 0);
        }

        return result;
    }

    public boolean isValidHeroId(int heroId) {
        return allHeroIds.contains(heroId);
    }

    public boolean isValidHeroFoodId(int heroFoodId) {
        for (HeroEvolveFoodMaterialType type : HeroEvolveFoodMaterialType.values()) {
            if (type.getValue() == heroFoodId) {
                return true;
            }
        }

        return false;
    }

    public HeroClassType getHeroClassType(int heroId) {
        return heroClassTypes.get(heroId);
    }

    public Set<Integer> getAllHeroIds() {
        return allHeroIds;
    }

    public Set<Integer> getAvailableHeroIds() {
        return availableHeroIds;
    }

    public HeroCompanionBuffData getBestMatchCompanionBuff(List<InventoryHero> inventoryHeroes) {
        for (HeroCompanionBuffData companionBuffData : heroCompanionBuffDataList) {
            if (companionBuffData.isMatch(inventoryHeroes)) {
                // closest match to this companion buff
                return companionBuffData;
            }
        }

        return null;
    }

    public FormationData getFormationData(int formationId) {
        return formationDataMap.get(formationId);
    }
}