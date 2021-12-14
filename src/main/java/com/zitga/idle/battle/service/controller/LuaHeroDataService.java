package com.zitga.idle.battle.service.controller;

import com.zitga.idle.battle.service.controller.loader.HeroDataLoader;
import com.zitga.idle.battle.service.controller.loader.SummonerDataLoader;
import com.zitga.idle.battle.utils.BattleCsvPathUtils;
import com.zitga.idle.battle.utils.LuaPathUtils;
import com.zitga.idle.hero.service.HeroDataService;
import com.zitga.idle.hero.utils.HeroCsvPathUtils;
import com.zitga.idle.lua.model.LuaObject;
import com.zitga.idle.utils.FileService;
import org.luaj.vm2.LuaValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LuaHeroDataService extends LuaObject {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final FileService fileService;
    private final HeroDataService heroDataService;

    public LuaHeroDataService(FileService fileService, HeroDataService heroDataService) {
        super(LuaPathUtils.getServicePath(), "HeroDataService");
        this.fileService = fileService;
        this.heroDataService = heroDataService;
    }

    public void load() {
        this.loadHeroData();
        this.loadSummonerData();
        this.loadHeroLevelCapData();

        this.loadHeroSkillLevelData();
        this.loadFormationData();
        this.loadPowerGainData();
        this.loadCompanionBuffData();
    }

    // ---------------------------------------- Load ----------------------------------------
    private void loadHeroData() {
        logger.info("[LUA] Loading hero data ...");
        HeroDataLoader loader = new HeroDataLoader(fileService, heroDataService);
        loader.load(this);
    }

    private void loadSummonerData() {
        logger.info("[LUA] Loading summoner data ...");
        SummonerDataLoader summonerLoader = new SummonerDataLoader(fileService);
        summonerLoader.load(this);
    }

    private void loadHeroLevelCapData() {
        logger.info("[LUA] Loading hero level cap data ...");
        String csvText = fileService.readCsvFile(HeroCsvPathUtils.getHeroLevelCapPath());
        invoke("AddHeroLevelCap", LuaValue.valueOf(csvText));
    }

    private void loadHeroSkillLevelData() {
        logger.info("[LUA] Loading hero skill level data ...");
        String csvText = fileService.readCsvFile(HeroCsvPathUtils.getHeroSkillLevelPath());
        invoke("AddHeroSkillLevelData", LuaValue.valueOf(csvText));
    }

    private void loadFormationData() {
        logger.info("[LUA] Loading formation data ...");
        String csvText = fileService.readCsvFile(BattleCsvPathUtils.getBattleFormationPath());
        invoke("AddFormationData", LuaValue.valueOf(csvText));

        csvText = fileService.readCsvFile(BattleCsvPathUtils.getBattleFormationBuffPath());
        invoke("AddFormationBuffData", LuaValue.valueOf(csvText));
    }

    private void loadPowerGainData() {
        logger.info("[LUA] Loading power gain data ...");
        String csvText = fileService.readCsvFile(BattleCsvPathUtils.getBattleHeroPowerGainPath());
        invoke("AddPowerGainData", LuaValue.valueOf(csvText));
    }

    private void loadCompanionBuffData() {
        logger.info("[LUA] Loading companion buff data ...");
        String csvText = fileService.readCsvFile(BattleCsvPathUtils.getBattleCompanionBuffPath());
        invoke("AddHeroCompanionBuffData", LuaValue.valueOf(csvText));
    }

    // ---------------------------------------- Setters ----------------------------------------
    public void addHeroData(LuaValue luaValue) {
        invoke("AddHeroDataEntry", luaValue);
    }

    public void addSummonerData(LuaValue luaValue) {
        invoke("AddSummonerDataEntry", luaValue);
    }
}