package com.zitga.fake.controller;

import com.zitga.base.constant.LogicCode;
import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.dragon.model.InventoryDragonEgg;
import com.zitga.dragon.model.PlayerDragonCollection;
import com.zitga.dragon.service.DragonService;
import com.zitga.enumeration.dragon.EggType;
import com.zitga.enumeration.fake.FakePlayerDataType;
import com.zitga.fake.annotation.FakeDataController;
import com.zitga.player.model.Player;
import com.zitga.resource.model.Reward;
import com.zitga.support.JsonService;

@BeanComponent
@FakeDataController({
        FakePlayerDataType.RESOURCE
})
public class FakeResourceController extends BaseFakeDataController {

    @BeanField
    private JsonService jsonService;

    @BeanField
    private DragonService dragonService;

    @Override
    public int fakeData(Player player, FakePlayerDataType fakePlayerDataType, String data) {
        try {
            Reward reward = jsonService.readValue(data, Reward.class);
            return addReward(player, reward);
        } catch (Exception e) {
            return LogicCode.INVALID_INPUT_DATA;
        }
    }

    public int addReward(Player player, Reward reward) {
        if (reward.getNumber() <= 0) {
            return LogicCode.INVALID_INPUT_DATA;
        }

        switch (reward.getType()) {
            case EGG:
                int result = addEgg(player, reward);
                if (result != LogicCode.SUCCESS){
                    return result;
                }
                break;
        }

        logger.info("[FAKE player = {}] add reward type = {}, reward number = {}",
                player, reward.getId(), reward.getNumber());
        return LogicCode.SUCCESS;
    }

    private int addEgg(Player player, Reward reward) {
        if (reward.getId() < 1 || reward.getId() > 3) {
            return LogicCode.INVALID_INPUT_DATA;
        }

        if (player.getOrLoadDragon() == null) {
            dragonService.loadPlayerData(player);
        }

        if (dragonService.isFullEggCollection(player, reward.getNumber())) {
            return LogicCode.DRAGON_COLLECTION_FULL;
        }

        PlayerDragonCollection dragonCollection = player.getOrLoadDragon();
        for (int i = 0; i < reward.getNumber(); i++) {
            InventoryDragonEgg dragonEgg = new InventoryDragonEgg(EggType.toType(reward.getId()));
            dragonCollection.addDragonEgg(dragonEgg);

        }

        dragonService.saveDragonCollection(player);
        return LogicCode.SUCCESS;
    }
}
