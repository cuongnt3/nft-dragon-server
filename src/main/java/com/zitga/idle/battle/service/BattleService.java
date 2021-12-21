package com.zitga.idle.battle.service;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.idle.battle.constant.BattleConstant;
import com.zitga.idle.battle.model.battle.LuaBattle;
import com.zitga.idle.battle.model.battle.input.LuaBattleTeamInfo;
import com.zitga.idle.battle.model.battle.input.LuaDummySummonerBattleInfo;
import com.zitga.idle.battle.model.battle.input.LuaSummonerBattleInfo;
import com.zitga.idle.battle.model.battle.output.LuaBattleResult;
import com.zitga.idle.battle.model.data.PredefineTeamData;
import com.zitga.idle.battle.model.message.BattleHeroInbound;
import com.zitga.idle.battle.model.message.BattleResultInfo;
import com.zitga.idle.battle.model.message.BattleTeamInbound;
import com.zitga.idle.battle.utils.BattleUtils;
import com.zitga.idle.dragon.model.InventoryDragon;
import com.zitga.idle.enumeration.common.GameMode;
import com.zitga.idle.hero.model.InventoryHero;
import com.zitga.idle.lua.service.LuaServiceManager;
import com.zitga.idle.player.model.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;

@BeanComponent
public class BattleService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeanField
    private LuaServiceManager luaServiceManager;

    // ---------------------------------------- Create battle ----------------------------------------
    public LuaBattle createBattle(GameMode gameMode, Player player,
                                  BattleTeamInbound attacker, PredefineTeamData predefineTeamData) {
        Map<BattleHeroInbound, InventoryDragon> heroMap = BattleUtils.convertToInventoryHero(player, attacker);
        if (heroMap == null) {
            return null;
        }

        // ----- ATTACKER -----
        LuaDummySummonerBattleInfo attackerSummoner = new LuaDummySummonerBattleInfo();
        attackerSummoner.setInfo(BattleConstant.ATTACKER_TEAM);

        LuaBattleTeamInfo attackerTeam = BattleUtils.createTeamPve(attackerSummoner);
        BattleUtils.addHeroToTeam(BattleConstant.ATTACKER_TEAM, attackerTeam, heroMap);

        // ----- DEFENDER -----
        LuaBattleTeamInfo defenderTeam = luaServiceManager.getPredefineTeam(predefineTeamData);

        // ----- BATTLE -----
        LuaBattle luaBattle = new LuaBattle(gameMode);
        luaBattle.setTeamInfo(attackerTeam, defenderTeam);

        return luaBattle;
    }

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