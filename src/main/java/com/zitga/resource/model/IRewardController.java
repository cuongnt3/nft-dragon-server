package com.zitga.resource.model;


import com.zitga.player.model.Player;

public interface IRewardController {

    void addReward(Player player, Reward reward);

    void saveReward(Player player);
}