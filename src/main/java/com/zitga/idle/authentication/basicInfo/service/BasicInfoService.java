package com.zitga.idle.authentication.basicInfo.service;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.idle.authentication.basicInfo.dao.PlayerBasicInfoDAO;
import com.zitga.idle.authentication.basicInfo.model.PlayerBasicInfo;
import com.zitga.idle.enumeration.resource.ResourceType;
import com.zitga.idle.player.model.Player;
import com.zitga.idle.player.service.CachedPlayerService;
import com.zitga.idle.publisher.service.PublisherService;
import com.zitga.idle.resource.annotation.RewardController;
import com.zitga.idle.resource.model.IRewardController;
import com.zitga.idle.resource.model.Reward;

@BeanComponent
@RewardController(ResourceType.MONEY)
public class BasicInfoService implements IRewardController {

    @BeanField
    private CachedPlayerService cachedPlayerService;

    @BeanField
    private PublisherService publisherService;

    @BeanField
    private PlayerBasicInfoDAO basicInfoDAO;

    public void createBasicInfo(Player player) {
        PlayerBasicInfo basicInfo = basicInfoDAO.create(player);
        player.setBasicInfo(basicInfo);
    }

    public void loadBasicInfo(Player player) {
        PlayerBasicInfo basicInfo = basicInfoDAO.findOrCreate(player);
        player.setBasicInfo(basicInfo);
    }

    public void saveBasicInfo(Player player) {
        basicInfoDAO.save(player.getBasicInfo());
    }

    // ---------------------------------------- Reward ----------------------------------------
    @Override
    public void addReward(Player player, Reward reward) {

    }

    @Override
    public void saveReward(Player player) {
        saveBasicInfo(player);
    }
}