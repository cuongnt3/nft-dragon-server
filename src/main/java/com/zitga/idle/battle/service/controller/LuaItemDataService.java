package com.zitga.idle.battle.service.controller;

import com.zitga.idle.battle.utils.LuaPathUtils;
import com.zitga.idle.item.utils.ItemCsvPathUtils;
import com.zitga.idle.lua.model.LuaObject;
import com.zitga.idle.utils.FileService;
import org.luaj.vm2.LuaValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LuaItemDataService extends LuaObject {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final FileService fileService;

    public LuaItemDataService(FileService fileService) {
        super(LuaPathUtils.getServicePath(), "ItemDataService");
        this.fileService = fileService;
    }

    public void load(LuaHeroDataService luaHeroDataService) {
        this.loadEquipmentData(luaHeroDataService);
        this.loadArtifactData(luaHeroDataService);
        this.loadStoneData(luaHeroDataService);
        this.loadSkinData(luaHeroDataService);
        this.loadIdleEffectData(luaHeroDataService);
        this.loadTalentData(luaHeroDataService);
        this.loadSetEquipmentData(luaHeroDataService);
    }

    // ---------------------------------------- Load ----------------------------------------
    private void loadEquipmentData(LuaHeroDataService luaHeroDataService) {
        logger.info("[LUA] Loading equipment data ...");
        String equipmentText = fileService.readCsvFile(ItemCsvPathUtils.getEquipmentPath());
        invoke("AddEquipmentData", LuaValue.valueOf(equipmentText), luaHeroDataService.toLua());
    }

    private void loadArtifactData(LuaHeroDataService luaHeroDataService) {
        logger.info("[LUA] Loading artifact data ...");
        String artifactText = fileService.readCsvFile(ItemCsvPathUtils.getArtifactPath());
        invoke("AddArtifactData", LuaValue.valueOf(artifactText), luaHeroDataService.toLua());
    }

    private void loadStoneData(LuaHeroDataService luaHeroDataService) {
        logger.info("[LUA] Loading stone data ...");
        String stoneText = fileService.readCsvFile(ItemCsvPathUtils.getStonePath());
        invoke("AddStoneData", LuaValue.valueOf(stoneText), luaHeroDataService.toLua());
    }

    private void loadSetEquipmentData(LuaHeroDataService luaHeroDataService) {
        logger.info("[LUA] Loading equipment set data ...");
        String setEquipmentText = fileService.readCsvFile(ItemCsvPathUtils.getEquipmentSetPath());
        invoke("AddEquipmentSetData", LuaValue.valueOf(setEquipmentText), luaHeroDataService.toLua());
    }

    private void loadSkinData(LuaHeroDataService luaHeroDataService) {
        logger.info("[LUA] Loading skin data ...");
        String skinText = fileService.readCsvFile(ItemCsvPathUtils.getSkinPath());
        invoke("AddSkinData", LuaValue.valueOf(skinText), luaHeroDataService.toLua());
    }

    private void loadIdleEffectData(LuaHeroDataService luaHeroDataService) {
        logger.info("[LUA] Loading idle effect data ...");
        String csvText = fileService.readCsvFile(ItemCsvPathUtils.getIdleEffectCsvPath());
        invoke("AddIdleEffectData", LuaValue.valueOf(csvText), luaHeroDataService.toLua());
    }

    private void loadTalentData(LuaHeroDataService luaHeroDataService) {
        logger.info("[LUA] Loading talent data ...");
        String csvText = fileService.readCsvFile(ItemCsvPathUtils.getTalentStatPath());
        invoke("AddTalentStatData", LuaValue.valueOf(csvText), luaHeroDataService.toLua());
    }
}
