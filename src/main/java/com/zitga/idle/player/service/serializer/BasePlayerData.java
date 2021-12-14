package com.zitga.idle.player.service.serializer;

import com.zitga.idle.enumeration.player.PlayerDataType;
import com.zitga.idle.player.model.Player;
import com.zitga.idle.player.model.message.PlayerDataGetResult;

public abstract class BasePlayerData {

    public abstract void serializeData(Player player, PlayerDataType dataType, PlayerDataGetResult result) throws Exception;
}
