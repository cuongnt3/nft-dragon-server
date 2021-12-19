package com.zitga.idle.battleInfo.service.formation;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.idle.battle.model.message.BattleHeroInbound;
import com.zitga.idle.battle.model.message.BattleTeamInbound;
import com.zitga.idle.battleInfo.model.PlayerBattleInfo;
import com.zitga.idle.battleInfo.model.formation.DetailTeamFormation;
import com.zitga.idle.battleInfo.model.formation.TeamFormation;
import com.zitga.idle.battleInfo.service.BattleInfoService;
import com.zitga.idle.dragon.model.InventoryDragon;
import com.zitga.idle.dragon.model.PlayerDragonCollection;
import com.zitga.idle.enumeration.common.GameMode;
import com.zitga.idle.hero.model.FormationData;
import com.zitga.idle.hero.service.HeroDataService;
import com.zitga.idle.player.model.Player;
import com.zitga.idle.publisher.service.PublisherService;
import com.zitga.idle.pve.constant.PveConstant;
import com.zitga.idle.pve.model.message.HeroBattleInbound;
import com.zitga.idle.pve.model.message.PveChallengeInbound;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@BeanComponent
public class FormationService {

    @BeanField
    private BattleInfoService battleInfoService;

    @BeanField
    private HeroDataService heroDataService;

    @BeanField
    private PublisherService publisherService;

    // --------------------------------------------------------------------------------
    public boolean saveFormation(Player player, GameMode gameMode, PveChallengeInbound inbound){
        PlayerBattleInfo battleInfo = player.getBattleInfo();

        TeamFormation teamFormation = getTeamFormation(player, inbound);
        if (teamFormation == null) {
            return false;
        }

        battleInfo.addFormation(gameMode, teamFormation);

        battleInfoService.saveBattleInfo(player);
        return true;
    }

    public TeamFormation getTeamFormation(Player player, PveChallengeInbound inbound) {
        PlayerDragonCollection heroCollection = player.getOrLoadDragon();

        Set<Long> heroInventoryIds = new HashSet<>();
        List<HeroBattleInbound> heroInbounds = inbound.getHeroList();
        for (HeroBattleInbound heroInbound : heroInbounds) {
            InventoryDragon hero = heroCollection.getDragon(heroInbound.getInventoryHeroId());
            if (hero == null) {
                // hero is not found
                return null;
            }

            heroInventoryIds.add(heroInbound.getInventoryHeroId());
        }

        if (heroInventoryIds.size() != heroInbounds.size()) {
            // contain duplicated hero
            return null;
        }

        FormationData formationData = heroDataService.getFormationData(PveConstant.FORMATION_DEFAULT);
        if (formationData == null) {
            // formation is not found
            return null;
        }

        for (HeroBattleInbound heroInbound : heroInbounds) {
            if (heroInbound.isFrontLine()) {
                if (heroInbound.getPosition() <= 0 || heroInbound.getPosition() > formationData.getFrontLine()) {
                    // invalid front line position
                    return null;
                }
            } else {
                if (heroInbound.getPosition() <= 0 || heroInbound.getPosition() > formationData.getBackLine()) {
                    // invalid back line position
                    return null;
                }
            }
        }

        TeamFormation teamFormation = convertToTeamFormation(inbound);
        if (teamFormation.getFrontLine().isEmpty() && teamFormation.getBackLine().isEmpty()) {
            // doesn't contain any heroes
            return null;
        }

        if (teamFormation.getNumberHeroes() != heroInventoryIds.size()) {
            // contain hero in duplicated position
            return null;
        }
        return teamFormation;
    }

    public TeamFormation convertToTeamFormation(PveChallengeInbound inbound) {
        TeamFormation teamFormation = new TeamFormation();

        List<HeroBattleInbound> heroInbounds = inbound.getHeroList();
        for (HeroBattleInbound heroInbound : heroInbounds) {
            teamFormation.addHero(heroInbound);
        }

        return teamFormation;
    }
}
