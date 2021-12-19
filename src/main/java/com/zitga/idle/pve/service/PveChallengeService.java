package com.zitga.idle.pve.service;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.idle.base.constant.LogicCode;
import com.zitga.idle.battle.model.battle.LuaBattle;
import com.zitga.idle.battle.model.data.PredefineTeamData;
import com.zitga.idle.battle.service.BattleService;
import com.zitga.idle.battleInfo.service.formation.FormationService;
import com.zitga.idle.enumeration.common.GameMode;
import com.zitga.idle.enumeration.resource.ResourceSource;
import com.zitga.idle.player.model.Player;
import com.zitga.idle.pve.model.message.ChallengeResult;
import com.zitga.idle.pve.model.message.PveChallengeInbound;
import com.zitga.idle.resource.model.Reward;

import java.util.List;

@BeanComponent
public class PveChallengeService {

    @BeanField
    private FormationService formationService;

    @BeanField
    private BattleService battleService;

    public ChallengeResult challenge(Player player, PveChallengeInbound inbound) {
        ChallengeResult result = new ChallengeResult();

        // TODO
        // need to validate inbound

        if (!formationService.saveFormation(player, GameMode.PVE, inbound)) {
            return result.withCode(LogicCode.FORMATION_INVALID);
        }

//        PredefineTeamData predefineTeamData = campaignDataService.getDefenderData(stageChallenge, dataId);
//
//        LuaBattle luaBattle = battleService.createBattle(GameMode.PVE, player, inbound, predefineTeamData);

//        battleService.calculateBattleResult(luaBattle, result);

        return result;
    }
}
