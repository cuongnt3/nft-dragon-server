package com.zitga.idle.battle.service;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.bean.annotation.BeanMethod;
import com.zitga.idle.battle.constant.BattleTag;
import com.zitga.idle.battle.model.hero.data.TeamLevelConfig;
import com.zitga.idle.battle.model.hero.data.TeamStarConfig;
import com.zitga.idle.battle.utils.BattleCsvPathUtils;
import com.zitga.idle.utils.FileService;
import com.zitga.support.CsvService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@BeanComponent
public class BattleDataService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeanField
    private FileService fileService;

    @BeanField
    private CsvService csvService;

    private List<Integer> forbiddenHeroIds;
    private List<Integer> warningHeroIds;

    // key: teamId
    private Map<Integer, TeamLevelConfig> teamLevelConfigMap;

    // key: teamId
    private Map<Integer, TeamStarConfig> teamStarConfigMap;

    @BeanMethod
    private void init() {
        logger.info("Initializing BattleDataService ...");

        createHeroCheckConfig();
        createTeamLevelConfig();
        createTeamStarConfig();
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

    private void createTeamLevelConfig() {
        String csvString = fileService.readCsvFile(BattleCsvPathUtils.getTeamLevelConfigPath());
        List<Map<String, String>> csvData = csvService.read(csvString);

        teamLevelConfigMap = new ConcurrentHashMap<>();
        TeamLevelConfig levelConfig;
        for (Map<String, String> data : csvData) {
            if (data.containsKey(BattleTag.ID_TEAM_LEVEL_TAG)) {
                levelConfig = new TeamLevelConfig(data);
                teamLevelConfigMap.put(levelConfig.getId(), levelConfig);
            }
        }
    }

    private void createTeamStarConfig() {
        String csvString = fileService.readCsvFile(BattleCsvPathUtils.getTeamStarConfigPath());
        List<Map<String, String>> csvData = csvService.read(csvString);

        teamStarConfigMap = new ConcurrentHashMap<>();
        TeamStarConfig teamStarConfig;
        for (Map<String, String> data : csvData) {
            if (data.containsKey(BattleTag.ID_TEAM_STAR_TAG)) {
                teamStarConfig = new TeamStarConfig(data);
                teamStarConfigMap.put(teamStarConfig.getId(), teamStarConfig);
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

    public TeamLevelConfig getTeamLevelConfig(int teamId){
        return teamLevelConfigMap.get(teamId);
    }

    public TeamStarConfig getTeamStarConfig(int teamId){
        return teamStarConfigMap.get(teamId);
    }
}