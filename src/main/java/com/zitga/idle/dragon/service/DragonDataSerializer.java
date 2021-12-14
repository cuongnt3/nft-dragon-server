package com.zitga.idle.dragon.service;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.idle.dragon.model.PlayerDragonCollection;
import com.zitga.idle.enumeration.player.PlayerDataType;
import com.zitga.idle.player.annotation.PlayerDataSerializer;
import com.zitga.idle.player.model.Player;
import com.zitga.idle.player.model.message.PlayerDataGetResult;
import com.zitga.idle.player.service.serializer.BasePlayerData;
import com.zitga.support.JsonService;

@BeanComponent
@PlayerDataSerializer(PlayerDataType.DRAGON_COLLECTION)
public class DragonDataSerializer extends BasePlayerData {

    @BeanField
    private JsonService jsonService;

    @Override
    public void serializeData(Player player, PlayerDataType dataType, PlayerDataGetResult result) throws Exception {
        PlayerDragonCollection dragonCollection = player.getOrLoadDragon();

        String playerData = jsonService.writeValueAsString(dragonCollection);
        result.appendPlayerData(dataType, playerData);
    }
}
