package com.zitga.idle.battle.service;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.idle.battle.model.battle.LuaBattle;
import com.zitga.idle.battle.model.battle.output.LuaBattleResult;
import com.zitga.idle.battle.model.message.BattleResultInfo;
import com.zitga.idle.lua.service.LuaServiceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@BeanComponent
public class BattleService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeanField
    private LuaServiceManager luaServiceManager;

    // ---------------------------------------- Create battle ----------------------------------------

    // ---------------------------------------- Calculate battle result ----------------------------------------
    public LuaBattleResult calculateBattleResult(LuaBattle luaBattle, BattleResultInfo battleResult) {
        return calculateBattleResult(luaBattle, battleResult, true);
    }

    public LuaBattleResult calculateBattleResult(LuaBattle luaBattle, BattleResultInfo battleResult, boolean isAttacker) {
        LuaBattleResult luaBattleResult = luaServiceManager.calculateBattleResult(luaBattle);
        battleResult.setLuaBattleResult(luaBattleResult, isAttacker);
//        logger.info("BATTLE RESULT: {}", luaBattleResult.toString(LuaRunMode.NORMAL));
//        logger.info("BATTLE RESULT: {}", luaBattleResult.toShortString(LuaRunMode.NORMAL));

        return luaBattleResult;
    }
}