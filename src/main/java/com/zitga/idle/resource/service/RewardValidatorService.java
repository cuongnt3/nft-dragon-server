package com.zitga.idle.resource.service;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanDelayedMethod;
import com.zitga.bean.annotation.BeanField;
import com.zitga.idle.enumeration.resource.ResourceType;
import com.zitga.idle.resource.model.Reward;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@BeanComponent
public class RewardValidatorService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeanField
    private RewardService rewardService;

    private List<Reward> rewards;

    public RewardValidatorService() {
        rewards = new ArrayList<>();
    }

    @BeanDelayedMethod
    private void validate() {
        logger.info("Validating reward ...");

        for (Reward reward : rewards) {
            validateReward(reward);
        }

        // let's GC collect these items
        rewards = null;
    }

    public void addRewardToValidate(Reward reward) {
        rewards.add(reward);
    }

    public void addRewardToValidate(ResourceType type, int resourceId, int number, int data) {
        rewards.add(new Reward(type, resourceId, number, data));
    }

    public void validateReward(Reward reward) {
        if (!reward.isValid()) {
            throw new RuntimeException("Invalid reward = " + reward);
        }

        switch (reward.getType()) {
            case DRAGON: {
                // TODO
//                if (!heroDataService.isValidHeroId(reward.getId())) {
//                    throw new RuntimeException("Invalid reward = " + reward);
//                }
//
//                int heroStar = reward.getData();
//                if (heroStar < HeroConstant.MIN_STAR || heroStar > HeroConstant.MAX_STAR) {
//                    throw new RuntimeException(String.format("Invalid star = %s, id = %s", heroStar, reward.getId()));
//                }
                break;
            }

            case EGG: {
                // TODO
//                if (!heroDataService.isValidHeroId(reward.getId())) {
//                    throw new RuntimeException("Invalid reward = " + reward);
//                }
//
//                int heroStar = reward.getData();
//                if (heroStar < HeroConstant.MIN_STAR || heroStar > HeroConstant.MAX_STAR) {
//                    throw new RuntimeException(String.format("Invalid star = %s, id = %s", heroStar, reward.getId()));
//                }
                break;
            }
        }
    }
}
