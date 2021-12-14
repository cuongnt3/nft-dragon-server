package com.zitga.idle.battleInfo.service.formation;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.idle.battle.model.message.BattleHeroInbound;
import com.zitga.idle.battle.model.message.BattleTeamInbound;
import com.zitga.idle.battleInfo.model.PlayerBattleInfo;
import com.zitga.idle.battleInfo.model.formation.TeamFormation;
import com.zitga.idle.battleInfo.service.BattleInfoService;
import com.zitga.idle.dragon.model.InventoryDragon;
import com.zitga.idle.dragon.model.PlayerDragonCollection;
import com.zitga.idle.enumeration.common.GameMode;
import com.zitga.idle.hero.model.FormationData;
import com.zitga.idle.hero.service.HeroDataService;
import com.zitga.idle.player.model.Player;
import com.zitga.idle.publisher.service.PublisherService;

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

    public TeamFormation getTeamFormation(Player player, BattleTeamInbound inbound) {
        PlayerDragonCollection heroCollection = player.getOrLoadDragon();

        Set<Long> heroInventoryIds = new HashSet<>();
        List<BattleHeroInbound> heroInbounds = inbound.getBattleHeroes();
        for (BattleHeroInbound heroInbound : heroInbounds) {
            InventoryDragon hero = heroCollection.getDragon(heroInbound.getHeroInventoryId());
            if (hero == null) {
                // hero is not found
                return null;
            }

            heroInventoryIds.add(heroInbound.getHeroInventoryId());
        }

        if (heroInventoryIds.size() != heroInbounds.size()) {
            // contain duplicated hero
            return null;
        }

        FormationData formationData = heroDataService.getFormationData(inbound.getFormation());
        if (formationData == null) {
            // formation is not found
            return null;
        }

        for (BattleHeroInbound heroInbound : heroInbounds) {
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

    public TeamFormation convertToTeamFormation(BattleTeamInbound inbound) {
        TeamFormation teamFormation = new TeamFormation();
        teamFormation.setSummonerClass(inbound.getSummonerClass());
        teamFormation.setFormation(inbound.getFormation());

        List<BattleHeroInbound> heroInbounds = inbound.getBattleHeroes();
        for (BattleHeroInbound heroInbound : heroInbounds) {
            teamFormation.addHero(heroInbound);
        }

        return teamFormation;
    }
}
