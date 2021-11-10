package com.nft.base.service;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.nft.base.constant.DatabaseConstant;
import com.nft.battle.service.cache.CachedBattleService;
import com.zitga.utils.RandomUtils;
import com.zitga.utils.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicLong;

@BeanComponent
public class IdManagerService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private AtomicLong startHeroId = new AtomicLong(0);
    private AtomicLong startTavernQuestId = new AtomicLong(0);

    @BeanField
    private CachedBattleService cachedBattleService;

    // ---------------------------------------- Setters ----------------------------------------
    public long generateHeroId() {
        return startHeroId.getAndIncrement() + TimeUtils.getCurrentTimeInMilisecond();
    }

    public int generateBattleId() {
        for (int i = 0; i < DatabaseConstant.NUMBER_ID_GENERATE_ATTEMPT; i++) {
            int generatedBattleId = RandomUtils.nextInt(DatabaseConstant.MIN_BATTLE_ID, DatabaseConstant.MAX_BATTLE_ID);
            if (!cachedBattleService.isExistedBattleInCached(generatedBattleId)) {
                return generatedBattleId;
            } else {
                logger.error("Retry generating guild id");
            }
        }

        return RandomUtils.nextInt(DatabaseConstant.MIN_BATTLE_ID, DatabaseConstant.MAX_BATTLE_ID);
    }
}