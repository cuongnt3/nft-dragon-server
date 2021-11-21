package com.zitga.resource.utils;

import com.zitga.resource.model.Reward;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ResourceUtils {

    public static List<Reward> combineRewards(List<Reward> rewards) {
        Map<String, Reward> result = new ConcurrentHashMap<>();

        for (Reward reward : rewards) {
            if (reward.getNumber() <= 0) {
                continue;
            }

            String key = String.format("%s_%s_%s", reward.getType(), reward.getId(), reward.getData());

            Reward uniqueReward = result.get(key);
            if (uniqueReward != null) {
                uniqueReward.addNumber(reward.getNumber());
            } else {
                uniqueReward = reward.clone();
                result.put(key, uniqueReward);
            }
        }

        return new ArrayList<>(result.values());
    }
}