package com.zitga.idle.battle.service.controller;

import com.zitga.idle.battle.constant.BattleConstant;
import com.zitga.idle.battle.model.battle.input.LuaBattleTeamInfo;
import com.zitga.idle.battle.model.data.LuaPredefineTeamData;
import com.zitga.idle.battle.model.data.PredefineTeamData;
import com.zitga.idle.battle.utils.BattleCsvPathUtils;
import com.zitga.idle.battle.utils.LuaPathUtils;
import com.zitga.idle.lua.model.LuaObject;
import com.zitga.idle.utils.FileService;
import org.luaj.vm2.LuaValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LuaPredefineTeamDataService extends LuaObject {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final FileService fileService;

    public LuaPredefineTeamDataService(FileService fileService) {
        super(LuaPathUtils.getServicePath(), "PredefineTeamDataService");
        this.fileService = fileService;
    }

    public void load() {
        loadTeamSummonerData();

        loadTeamLevelData();
        loadTeamStarData();

        loadTeamItemData();
        loadTeamMasteryData();

        loadBossStatData();
        loadBossSlotData();
    }

    // ---------------------------------------- Load ----------------------------------------
    private void loadTeamSummonerData() {
        logger.info("[LUA] Loading team summoner ...");
        String csvTeam = fileService.readCsvFile(BattleCsvPathUtils.getPredefineTeamSummonerPath());
        invoke("SetSummonerPredefine", LuaValue.valueOf(csvTeam));
    }

    private void loadTeamLevelData() {
        logger.info("[LUA] Loading team level ...");

        String csvTeam = fileService.readCsvFile(BattleCsvPathUtils.getPredefineTeamLevelPath());
        invoke("SetTeamLevelData", LuaValue.valueOf(csvTeam));

        csvTeam = fileService.readCsvFile(BattleCsvPathUtils.getPredefineTeamLevelSpecialPath());
        invoke("SetTeamLevelData", LuaValue.valueOf(csvTeam));
    }

    private void loadTeamStarData() {
        logger.info("[LUA] Loading team star ...");
        String csvTeam = fileService.readCsvFile(BattleCsvPathUtils.getPredefineTeamStarPath());
        invoke("SetTeamStarData", LuaValue.valueOf(csvTeam));
    }

    private void loadTeamItemData() {
        logger.info("[LUA] Loading team item ...");
        String csvTeam = fileService.readCsvFile(BattleCsvPathUtils.getPredefineTeamItemPath());
        invoke("SetTeamItemData", LuaValue.valueOf(csvTeam));
    }

    private void loadTeamMasteryData() {
        logger.info("[LUA] Loading team mastery ...");
        String csvTeam = fileService.readCsvFile(BattleCsvPathUtils.getPredefineTeamMasteryPath());
        invoke("SetTeamMasteryData", LuaValue.valueOf(csvTeam));
    }

    private void loadBossStatData() {
        logger.info("[LUA] Loading boss stat ...");
        String csvTeam = fileService.readCsvFile(BattleCsvPathUtils.getPredefineBossStatPath());
        invoke("SetBossStatMultiplierList", LuaValue.valueOf(csvTeam));
    }

    private void loadBossSlotData() {
        logger.info("[LUA] Loading boss slot ...");
        String csvTeam = fileService.readCsvFile(BattleCsvPathUtils.getPredefineBossSlotPath());
        invoke("SetBossSlot", LuaValue.valueOf(csvTeam));
    }

    // ---------------------------------------- Getters ----------------------------------------
    public LuaBattleTeamInfo getTeam(PredefineTeamData predefineTeamData, int teamId) {
        LuaPredefineTeamData team = new LuaPredefineTeamData(predefineTeamData, teamId);
        return new LuaBattleTeamInfo(invoke("GetBattleTeamInfo", team.toLua()));
    }

    public void validate(PredefineTeamData predefineTeamData) {
        LuaPredefineTeamData team = new LuaPredefineTeamData(predefineTeamData, BattleConstant.ATTACKER_TEAM);
        invoke("Validate", team.toLua());
    }
}
