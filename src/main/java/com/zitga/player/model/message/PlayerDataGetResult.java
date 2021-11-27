package com.zitga.player.model.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zitga.enumeration.player.PlayerDataType;
import org.mongodb.morphia.annotations.Property;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerDataGetResult {

    // key: player data
    @JsonProperty("0")
    private Map<Integer, String> playerDataMap = new ConcurrentHashMap<>();

    // ---------------------------------------- Getters ----------------------------------------
    public Map<Integer, String> getPlayerDataMap() {
        return playerDataMap;
    }

    // ---------------------------------------- Setters ----------------------------------------
    public void appendPlayerData(PlayerDataType type, String data) {
        playerDataMap.put(type.getValue(), data);
    }
}
