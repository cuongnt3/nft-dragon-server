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
import com.zitga.fake.model.type.FakeEggData;
import com.zitga.player.model.Player;
import com.zitga.support.JsonService;

@BeanComponent
@FakeDataController({
        FakePlayerDataType.ADD_EGG,
        FakePlayerDataType.ADD_EGG_FRAGMENT
})
public class FakeAddEggController extends BaseFakeDataController {

    @BeanField
    private JsonService jsonService;

    @BeanField
    private DragonService dragonService;

    @Override
    public int fakeData(Player player, FakePlayerDataType fakePlayerDataType, String data) {
        switch (fakePlayerDataType) {
            case ADD_EGG: {
                try {
                    FakeEggData fakeEggData = jsonService.readValue(data, FakeEggData.class);
                    return addEgg(player, fakeEggData);
                } catch (Exception e) {
                    return LogicCode.INVALID_INPUT_DATA;
                }
            }
        }

        return LogicCode.FEATURE_NOT_IMPLEMENTED;
    }

    public int addEgg(Player player, FakeEggData fakeEggData) {
        if (fakeEggData.getNumberEgg() <= 0) {
            return LogicCode.INVALID_INPUT_DATA;
        }

        if (fakeEggData.getEggType() < 1 || fakeEggData.getEggType() > 3) {
            return LogicCode.INVALID_INPUT_DATA;
        }

        if (player.getOrLoadDragon() == null) {
            dragonService.loadPlayerData(player);
        }

        if (dragonService.isFullEggCollection(player, fakeEggData.getNumberEgg())) {
            return LogicCode.DRAGON_COLLECTION_FULL;
        }

        PlayerDragonCollection dragonCollection = player.getOrLoadDragon();
        for (int i = 0; i < fakeEggData.getNumberEgg(); i++) {
            InventoryDragonEgg dragonEgg = new InventoryDragonEgg(EggType.toType(fakeEggData.getEggType()));
            dragonCollection.addDragonEgg(dragonEgg);

        }

        dragonService.saveDragonCollection(player);
        logger.info("[FAKE player = {}] add egg type = {}, egg number = {}",
                player, fakeEggData.getEggType(), fakeEggData.getNumberEgg());
        return LogicCode.SUCCESS;
    }
}
