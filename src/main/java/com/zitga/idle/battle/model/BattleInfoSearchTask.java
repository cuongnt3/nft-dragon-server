package com.zitga.idle.battle.model;

import com.zitga.idle.battleInfo.model.PlayerBattleInfo;
import com.zitga.idle.battleInfo.service.BattleInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class BattleInfoSearchTask implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Map<Long, PlayerBattleInfo> battleInfoMap;

    private final BattleInfoService battleInfoService;
    private final List<Long> playerIds;
    private CountDownLatch latch;

    public BattleInfoSearchTask(BattleInfoService battleInfoService, List<Long> playerIds) {
        this.battleInfoService = battleInfoService;
        this.playerIds = playerIds;
    }

    @Override
    public void run() {
        try {
            battleInfoMap = battleInfoService.findBattleInfo(playerIds);
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
            battleInfoMap = new ConcurrentHashMap<>();
        } finally {
            latch.countDown();
        }
    }

    public PlayerBattleInfo getBattleInfo(long playerId) {
        return battleInfoMap.get(playerId);
    }

    public void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }
}