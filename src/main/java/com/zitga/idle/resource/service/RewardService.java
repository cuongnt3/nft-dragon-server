package com.zitga.idle.resource.service;

import com.zitga.bean.BeanContainer;
import com.zitga.bean.ReflectionScanner;
import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.bean.annotation.BeanMethod;
import com.zitga.idle.enumeration.resource.ResourceType;
import com.zitga.idle.player.model.Player;
import com.zitga.idle.resource.annotation.RewardController;
import com.zitga.idle.resource.model.IRewardController;
import com.zitga.idle.resource.model.Reward;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@BeanComponent
public class RewardService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeanField
    private BeanContainer beanContainer;

    private Map<ResourceType, IRewardController> rewardManagerMap;

    @BeanMethod
    private void init() {
        logger.info("Initializing RewardService ...");

        ReflectionScanner scanner = beanContainer.getScanner();
        Set<Class<?>> classes = scanner.scanAnnotatedType(RewardController.class);

        rewardManagerMap = new ConcurrentHashMap<>();

        for (Class<?> managerClass : classes) {
            RewardController annotation = managerClass.getAnnotation(RewardController.class);
            for (ResourceType type : annotation.value()) {
                if (rewardManagerMap.containsKey(type)) {
                    throw new RuntimeException(String.format("Duplicated type %s for %s", type, managerClass));
                }

                IRewardController manager = (IRewardController) beanContainer.getComponent(managerClass);
                if (manager == null) {
                    throw new RuntimeException(String.format("Manager is not found with type %s for %s", type, managerClass));
                }

                rewardManagerMap.put(type, manager);
            }
        }
    }

    // ---------------------------------------- Add resource ----------------------------------------
    public void saveReward(Player player, Reward reward) {
        if (reward == null || !reward.isValid()) {
            logger.error("Reward is invalid: reward = {}, stackTrace = {}", reward);
            return;
        }

        IRewardController manager = rewardManagerMap.get(reward.getType());
        manager.addReward(player, reward);

        manager.saveReward(player);
    }

    public void saveReward(Player player, List<Reward> rewards) {
        if (rewards.isEmpty()) {
            return;
        }

        Set<IRewardController> pendingRewardManagers = new HashSet<>();

        for (Reward reward : rewards) {
            if (reward == null || !reward.isValid()) {
                logger.error("Reward is invalid: reward = {}", reward);
                continue;
            }

            IRewardController manager = rewardManagerMap.get(reward.getType());
            if (manager != null) {
                manager.addReward(player, reward);
                pendingRewardManagers.add(manager);
            } else {
                logger.error("Reward type isn't supported: type = {}", reward.getType());
            }
        }

        for (IRewardController manager : pendingRewardManagers) {
            manager.saveReward(player);
        }
    }
}