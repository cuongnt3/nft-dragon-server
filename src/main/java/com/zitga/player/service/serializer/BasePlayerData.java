package com.zitga.player.service.serializer;

import com.zitga.enumeration.player.PlayerDataType;
import com.zitga.player.model.Player;
import com.zitga.player.model.message.PlayerDataGetResult;

public abstract class BasePlayerData {

    public abstract void serializeData(Player player, PlayerDataType dataType, PlayerDataGetResult result) throws Exception;
}
