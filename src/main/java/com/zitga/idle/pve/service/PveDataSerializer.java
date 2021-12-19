package com.zitga.idle.pve.service;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.idle.enumeration.player.PlayerDataType;
import com.zitga.idle.player.annotation.PlayerDataSerializer;
import com.zitga.idle.player.model.Player;
import com.zitga.idle.player.model.message.PlayerDataGetResult;
import com.zitga.idle.player.service.serializer.BasePlayerData;
import com.zitga.idle.pve.model.PlayerPve;
import com.zitga.support.JsonService;

@BeanComponent
@PlayerDataSerializer({
        PlayerDataType.PVE
})
public class PveDataSerializer extends BasePlayerData {

    @BeanField
    private JsonService jsonService;

    @Override
    public void serializeData(Player player, PlayerDataType dataType, PlayerDataGetResult result) throws Exception {
        PlayerPve playerPve = player.getOrLoadPlayerPve();

        String playerData = jsonService.writeValueAsString(playerPve);
        result.appendPlayerData(dataType, playerData);
    }
}
