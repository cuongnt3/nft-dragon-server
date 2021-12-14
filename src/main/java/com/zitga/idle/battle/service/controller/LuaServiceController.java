package com.zitga.idle.battle.service.controller;

import com.zitga.idle.battle.utils.LuaPathUtils;
import com.zitga.idle.config.game.GameConfig;
import com.zitga.idle.hero.service.HeroDataService;
import com.zitga.idle.lua.model.LuaObject;
import com.zitga.idle.utils.FileService;
import org.luaj.vm2.LuaValue;

public class LuaServiceController extends LuaObject {

    public LuaServiceController() {
        super(LuaPathUtils.getServicePath(), "ServiceController");
    }

    // Setters
    public void bindDependencies() {
        invoke("BindDependencies");
    }

    public LuaHeroDataService createHeroDataService(FileService fileService, HeroDataService heroDataService) {
        LuaHeroDataService service = new LuaHeroDataService(fileService, heroDataService);
        invoke("SetHeroDataService", service.toLua());

        return service;
    }

    public LuaBattleService createBattleService(GameConfig gameConfig) {
        LuaBattleService service = new LuaBattleService(gameConfig);
        invoke("SetBattleService", service.toLua());

        return service;
    }

    public LuaItemDataService createItemDataService(FileService fileService) {
        LuaItemDataService service = new LuaItemDataService(fileService);
        invoke("SetItemDataService", service.toLua());

        return service;
    }

    public LuaPredefineTeamDataService createPredefineDataService(FileService fileService) {
        LuaPredefineTeamDataService service = new LuaPredefineTeamDataService(fileService);
        invoke("SetPredefineDataService", service.toLua());

        return service;
    }

    public void loadDynamicRequire(String luaPath) {
        invoke("LoadDynamicRequire", LuaValue.valueOf(luaPath));
    }
}
