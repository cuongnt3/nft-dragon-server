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
import com.zitga.support.JsonService;

@BeanComponent
public class PveChallengeService {

    @BeanField
    private FormationService formationService;

    @BeanField
    private BattleService battleService;

    @BeanField
    private PveDataService pveDataService;

    @BeanField
    private JsonService jsonService;

    public ChallengeResult challenge(Player player, BattleTeamInbound inbound) {
        ChallengeResult result = new ChallengeResult();

        // TODO
        // need to validate inbound

        if (!formationService.saveFormation(player, GameMode.PVE, inbound)) {
            return result.withCode(LogicCode.FORMATION_INVALID);
        }

        PredefineTeamData predefineTeamData = pveDataService.getDefenderData(101001);

        LuaBattle luaBattle = battleService.createBattle(GameMode.PVE, player, inbound, predefineTeamData);

        BattleResultInfo resultInfo = new BattleResultInfo();
        battleService.calculateBattleResult(luaBattle, resultInfo);

        result.setResultLog(BattleLogUtils.createBattleLog(resultInfo.getLuaBattleResult()));

        return result;
    }
}
