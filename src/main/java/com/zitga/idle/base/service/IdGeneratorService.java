package com.zitga.idle.base.service;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.idle.authentication.basicInfo.dao.PlayerBasicInfoDAO;
import com.zitga.idle.base.constant.DatabaseConstant;
import com.zitga.utils.RandomUtils;
import com.zitga.utils.TimeUtils;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicLong;

@BeanComponent
public class IdGeneratorService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeanField
    private PlayerBasicInfoDAO basicInfoDAO;

    private final AtomicLong startHeroId = new AtomicLong(0);

    private final AtomicLong startDragonId = new AtomicLong(0);
    private final AtomicLong startEggId = new AtomicLong(0);

    // ---------------------------------------- Setters ----------------------------------------
    public long generatePlayerId() {
        for (int i = 0; i < DatabaseConstant.NUMBER_ID_GENERATE_ATTEMPT; i++) {
            long now = TimeUtils.getCurrentTimeInMilisecond();
            int offset = RandomUtils.nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE);

            long generatedPlayerId = now + offset;
            if (!basicInfoDAO.exists(generatedPlayerId)) {
                return generatedPlayerId;
            } else {
                logger.error("Retry generating player id");
            }
        }

        return TimeUtils.getCurrentTimeInMilisecond();
    }

    public long generateHeroId() {
        return startHeroId.getAndIncrement() + TimeUtils.getCurrentTimeInMilisecond();
    }

    public String generateBattleId() {
        return (new ObjectId()).toString();
    }

    public long generateDragonId() {
        return startDragonId.getAndIncrement() + TimeUtils.getCurrentTimeInMilisecond();
    }

    public long generateEggId() {
        return startEggId.getAndIncrement() + TimeUtils.getCurrentTimeInMilisecond();
    }
}