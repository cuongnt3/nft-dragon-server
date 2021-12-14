package com.zitga.idle.resource.model;

import com.zitga.idle.player.model.Player;

public interface IRewardController {

    void addReward(Player player, Reward reward);

    void saveReward(Player player);
}