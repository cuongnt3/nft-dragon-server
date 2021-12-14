package com.zitga.idle.lua.service;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.idle.battle.constant.BattleConstant;
import com.zitga.idle.battle.model.battle.LuaBattle;
import com.zitga.idle.battle.model.battle.input.LuaBattleTeamInfo;
import com.zitga.idle.battle.model.battle.output.LuaBattleResult;
import com.zitga.idle.battle.model.data.PredefineTeamData;
import com.zitga.idle.battle.model.message.log.BattleResultLog;
import com.zitga.idle.battle.service.controller.*;
import com.zitga.idle.battle.service.controller.loader.LuaInit;
import com.zitga.idle.config.game.GameConfig;
import com.zitga.idle.hero.service.HeroDataService;
import com.zitga.idle.utils.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@BeanComponent
public class LuaServiceManager {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final FileService fileService;
    private final HeroDataService heroDataService;

    private final GameConfig gameConfig;

    private LuaServiceController serviceController;

    private LuaBattleService luaBattleService;

    private LuaHeroDataService luaHeroDataService;
    private LuaItemDataService luaItemDataService;

    private LuaPredefineTeamDataService predefineTeamDataService;

    public LuaServiceManager(FileService fileService, HeroDataService heroDataService, GameConfig gameConfig) {
        this.fileService = fileService;
        this.heroDataService = heroDataService;

        this.gameConfig = gameConfig;
    }

    public void init() {
        logger.info("[LUA] Importing requirements ...");
        LuaInit luaInit = new LuaInit();

        logger.info("[LUA] Initializing services ...");
        serviceController = new LuaServiceController();

        luaHeroDataService = serviceController.createHeroDataService(fileService, heroDataService);
        luaItemDataService = serviceController.createItemDataService(fileService);

        luaBattleService = serviceController.createBattleService(gameConfig);
        predefineTeamDataService = serviceController.createPredefineDataService(fileService);

        logger.info("[LUA] Loading data  ...");
        predefineTeamDataService.load();
        luaHeroDataService.load();
        luaItemDataService.load(luaHeroDataService);

        serviceController.bindDependencies();
    }

    public void loadDynamicRequire(String path) {
        logger.trace("[LUA] Loading dynamic require at {}", path);
        serviceController.loadDynamicRequire(path);
    }

    public LuaBattleTeamInfo getPredefineTeam(PredefineTeamData predefineTeamData) {
        return predefineTeamDataService.getTeam(predefineTeamData, BattleConstant.DEFENDER_TEAM);
    }

    public LuaBattleTeamInfo getPredefineTeam(PredefineTeamData predefineTeamData, int teamId) {
        return predefineTeamDataService.getTeam(predefineTeamData, teamId);
    }

    public LuaBattleResult calculateBattleResult(LuaBattle luaBattle) {
        return luaBattleService.calculateBattleResult(luaBattle);
    }

    public LuaPredefineTeamDataService getPredefineTeamDataService() {
        return predefineTeamDataService;
    }

    public LuaBattleService getLuaBattleService() {
        return luaBattleService;
    }
}
