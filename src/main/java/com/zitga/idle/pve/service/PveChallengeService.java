package com.zitga.idle.pve.service;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.idle.base.constant.LogicCode;
import com.zitga.idle.battle.model.battle.LuaBattle;
import com.zitga.idle.battle.model.data.PredefineTeamData;
import com.zitga.idle.battle.model.message.BattleResultInfo;
import com.zitga.idle.battle.model.message.BattleTeamInbound;
import com.zitga.idle.battle.service.BattleService;
import com.zitga.idle.battle.utils.BattleLogUtils;
import com.zitga.idle.battleInfo.service.formation.FormationService;
import com.zitga.idle.enumeration.common.GameMode;
import com.zitga.idle.player.model.Player;
import com.zitga.idle.pve.model.message.ChallengeResult;
import com.zitga.idle.pve.model.message.DragonBotData;
import com.zitga.support.JsonService;

import java.util.List;

@BeanComponent
public class PveChallengeService {

    @BeanField
    private FormationService formationService;

    @BeanField
    private BattleService battleService;

    @BeanField
    private PveDataService pveDataService;

    @BeanField
    private PveService pveService;

    @BeanField
    private JsonService jsonService;

    public ChallengeResult challenge(Player player, BattleTeamInbound inbound) {
        ChallengeResult result = new ChallengeResult();

        PredefineTeamData predefineTeamData = pveDataService.getDefenderData(inbound.getStage());
        if (predefineTeamData == null) {
            return result.withCode(LogicCode.CAMPAIGN_STAGE_INVALID);
        }

        if (!formationService.saveFormation(player, GameMode.PVE, inbound)) {
            return result.withCode(LogicCode.FORMATION_INVALID);
        }

        LuaBattle luaBattle = battleService.createBattle(GameMode.PVE, player, inbound, predefineTeamData);

        BattleResultInfo resultInfo = new BattleResultInfo();
        battleService.calculateBattleResult(luaBattle, resultInfo);

        result.setResultLog(BattleLogUtils.createBattleLog(resultInfo.getLuaBattleResult()));

        List<DragonBotData> dragonBotDataList = pveService.getDefenderFormation(player, inbound.getStage());
        result.setDragonBotDataList(dragonBotDataList);
        return result;
    }
}
