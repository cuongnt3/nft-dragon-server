package com.zitga.idle.pve.service;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.idle.base.constant.LogicCode;
import com.zitga.idle.battle.model.data.PredefineTeamData;
import com.zitga.idle.battle.model.hero.data.TeamLevelConfig;
import com.zitga.idle.battle.model.hero.data.TeamStarConfig;
import com.zitga.idle.battle.service.BattleDataService;
import com.zitga.idle.hero.model.FormationData;
import com.zitga.idle.hero.service.HeroDataService;
import com.zitga.idle.player.model.IAsyncPlayerDataManageable;
import com.zitga.idle.player.model.Player;
import com.zitga.idle.pve.constant.PveConstant;
import com.zitga.idle.pve.dao.PlayerPveDAO;
import com.zitga.idle.pve.model.PlayerPve;
import com.zitga.idle.pve.model.message.DefenderTeamGetResult;
import com.zitga.idle.pve.model.message.DragonBotData;

import java.util.List;

@BeanComponent
public class PveService implements IAsyncPlayerDataManageable {

    @BeanField
    private PlayerPveDAO playerPveDAO;

    @BeanField
    private PveDataService pveDataService;

    @BeanField
    private BattleDataService battleDataService;

    @BeanField
    private HeroDataService heroDataService;

    @Override
    public void createPlayerData(Player player) {
        PlayerPve playerPve = playerPveDAO.create(player);
        player.setPlayerPve(playerPve);
    }

    @Override
    public void loadPlayerData(Player player) {
        PlayerPve playerPve = playerPveDAO.findOrCreate(player);
        player.setPlayerPve(playerPve);
    }

    // ---------------------------------------- Handlers ----------------------------------------
    public DefenderTeamGetResult getDefenderFormation(Player player, int stage) {
        DefenderTeamGetResult result = new DefenderTeamGetResult();

        PredefineTeamData predefineTeamData = pveDataService.getDefenderData(stage);
        if (predefineTeamData == null) {
            return result.withCode(LogicCode.CAMPAIGN_STAGE_INVALID);
        }

        TeamLevelConfig levelConfig = battleDataService.getTeamLevelConfig(predefineTeamData.getTeamLevelId());
        if (levelConfig == null) {
            return result.withCode(LogicCode.INVALID_INPUT_DATA);
        }

        TeamStarConfig starConfig = battleDataService.getTeamStarConfig(predefineTeamData.getTeamStarId());
        if (starConfig == null) {
            return result.withCode(LogicCode.INVALID_INPUT_DATA);
        }

        FormationData formationData = heroDataService.getFormationData(PveConstant.FORMATION_DEFAULT);

        List<Integer> heroIds = predefineTeamData.getHeroList();
        for (int i = 0; i < heroIds.size(); i++) {
            DragonBotData dragonBotData = new DragonBotData();
            dragonBotData.setDragonId(heroIds.get(i));

            dragonBotData.setLevel(levelConfig.getLevel(i + 1));
            dragonBotData.setStar(starConfig.getStar(i + 1));

            if (i < formationData.getFrontLine()) {
                dragonBotData.setFrontLine(true);
                dragonBotData.setPosition(i + 1);
            } else {
                dragonBotData.setFrontLine(false);
                dragonBotData.setPosition(i + 1 - formationData.getFrontLine());
            }

            result.addDragonBot(dragonBotData);
        }

        return result.withCode(LogicCode.SUCCESS);
    }
}
