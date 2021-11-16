package com.zitga.base.service;

import com.zitga.authentication.dao.PlayerAuthenticationDAO;
import com.zitga.base.constant.DatabaseConstant;
import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.utils.RandomUtils;
import com.zitga.utils.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicLong;

@BeanComponent
public class IdGeneratorService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeanField
    private PlayerAuthenticationDAO authenticationDAO;

    private final AtomicLong startHeroId = new AtomicLong(0);
    private final AtomicLong startTavernQuestId = new AtomicLong(0);

    // ---------------------------------------- Setters ----------------------------------------
    public long generatePlayerId() {
        for (int i = 0; i < DatabaseConstant.NUMBER_ID_GENERATE_ATTEMPT; i++) {
            long now = TimeUtils.getCurrentTimeInMilisecond();
            int offset = RandomUtils.nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE);

            long generatedPlayerId = now + offset;
            if (!authenticationDAO.exists(generatedPlayerId)) {
                return generatedPlayerId;
            } else {
                logger.error("Retry generating player id");
            }
        }

        return TimeUtils.getCurrentTimeInMilisecond();
    }

    public long generateDragonId() {
        return startHeroId.getAndIncrement() + TimeUtils.getCurrentTimeInMilisecond();
    }
}