package com.zitga.summon.service;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.enumeration.player.PlayerDataType;
import com.zitga.player.annotation.PlayerDataSerializer;
import com.zitga.player.model.Player;
import com.zitga.player.model.message.PlayerDataGetResult;
import com.zitga.player.service.serializer.BasePlayerData;
import com.zitga.summon.model.PlayerDragonSummon;
import com.zitga.support.JsonService;

@BeanComponent
@PlayerDataSerializer(PlayerDataType.SUMMON)
public class DragonSummonSerializer extends BasePlayerData {

    @BeanField
    private JsonService jsonService;

    @Override
    public void serializeData(Player player, PlayerDataType dataType, PlayerDataGetResult result) throws Exception {
        PlayerDragonSummon dragonSummon = player.getOrLoadDragonSummon();

        String playerData = jsonService.writeValueAsString(dragonSummon);
        result.appendPlayerData(dataType, playerData);
    }
}
