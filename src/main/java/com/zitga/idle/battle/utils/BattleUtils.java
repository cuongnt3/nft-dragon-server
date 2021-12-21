package com.zitga.idle.battle.utils;

import com.zitga.idle.battle.model.battle.input.LuaBattleTeamInfo;
import com.zitga.idle.battle.model.battle.input.LuaHeroBattleInfo;
import com.zitga.idle.battle.model.battle.input.LuaSummonerBattleInfo;
import com.zitga.idle.battle.model.message.BattleHeroInbound;
import com.zitga.idle.battle.model.message.BattleTeamInbound;
import com.zitga.idle.dragon.model.InventoryDragon;
import com.zitga.idle.dragon.model.PlayerDragonCollection;
import com.zitga.idle.player.model.Player;
import com.zitga.idle.pve.constant.PveConstant;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BattleUtils {

    public static Map<BattleHeroInbound, InventoryDragon> convertToInventoryHero(Player player, BattleTeamInbound attacker) {
        Map<BattleHeroInbound, InventoryDragon> result = new ConcurrentHashMap<>();

        PlayerDragonCollection dragonCollection = player.getOrLoadDragon();
        List<BattleHeroInbound> heroBattleInfoList = attacker.getBattleHeroes();

        for (BattleHeroInbound heroBattleInfo : heroBattleInfoList) {
            InventoryDragon hero = dragonCollection.getDragon(heroBattleInfo.getHeroInventoryId());
            if (hero == null) {
                return null;
            }

            result.put(heroBattleInfo, hero);
        }

        return result;
    }

    public static LuaBattleTeamInfo createTeamPve(LuaSummonerBattleInfo attackerSummoner) {
        LuaBattleTeamInfo team = new LuaBattleTeamInfo();

        team.setFormationId(PveConstant.FORMATION_DEFAULT);
        team.setSummonerBattleInfo(attackerSummoner);

        return team;
    }

    public static void addHeroToTeam(int teamType, LuaBattleTeamInfo team, Map<BattleHeroInbound, InventoryDragon> heroMap) {
        for (Map.Entry<BattleHeroInbound, InventoryDragon> entry : heroMap.entrySet()) {
            BattleHeroInbound heroInbound = entry.getKey();
            InventoryDragon inventoryDragon = entry.getValue();

            LuaHeroBattleInfo heroBattleInfo = convertToHeroBattleInfo(inventoryDragon,
                    teamType, heroInbound.isFrontLine(), heroInbound.getPosition());
            team.addHero(heroBattleInfo);
        }
    }

    private static LuaHeroBattleInfo convertToHeroBattleInfo(InventoryDragon dragon, int teamId, boolean isFrontLine, int position) {
        LuaHeroBattleInfo heroBattleInfo = new LuaHeroBattleInfo();

        heroBattleInfo.setInfo(teamId, dragon);
        heroBattleInfo.setPosition(isFrontLine, position);
        heroBattleInfo.setItems(dragon);

        return heroBattleInfo;
    }
}
