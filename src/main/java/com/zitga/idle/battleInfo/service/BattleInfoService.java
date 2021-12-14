package com.zitga.idle.battleInfo.service;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.idle.battleInfo.dao.PlayerBattleInfoDAO;
import com.zitga.idle.battleInfo.model.PlayerBattleInfo;
import com.zitga.idle.player.model.IAsyncPlayerDataManageable;
import com.zitga.idle.player.model.Player;
import com.zitga.idle.player.service.CachedPlayerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@BeanComponent
public class BattleInfoService implements IAsyncPlayerDataManageable {

    @BeanField
    private CachedPlayerService cachedPlayerService;

    @BeanField
    private PlayerBattleInfoDAO battleInfoDAO;

    @Override
    public void createPlayerData(Player player) {
        PlayerBattleInfo battleInfo = battleInfoDAO.create(player);
        player.setBattleInfo(battleInfo);
    }

    @Override
    public void loadPlayerData(Player player) {
        PlayerBattleInfo battleInfo = battleInfoDAO.findOrCreate(player);
        player.setBattleInfo(battleInfo);
    }

    public void saveBattleInfo(Player player) {
        battleInfoDAO.save(player.getBattleInfo());
    }

    public Map<Long, PlayerBattleInfo> findBattleInfo(List<Long> playerIds) {
        Map<Long, PlayerBattleInfo> result = new ConcurrentHashMap<>();

        List<Long> playerIdListToFind = new ArrayList<>();
        for (long playerId : playerIds) {
            Player player = cachedPlayerService.getPlayer(playerId);
            if (player != null) {
                result.put(playerId, player.getBattleInfo());
            } else {
                playerIdListToFind.add(playerId);
            }
        }

        if (!playerIdListToFind.isEmpty()) {
            List<PlayerBattleInfo> battleInfoList = battleInfoDAO.findMany(playerIdListToFind);
            for (PlayerBattleInfo battleInfo : battleInfoList) {
                result.put(battleInfo.getPlayerId(), battleInfo);
            }
        }

        return result;
    }
}
