package com.zitga.idle.pve.model.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class DefenderTeamGetResult {

    private int resultCode;

    @JsonProperty("defender_formation")
    private List<DragonBotData> dragonBotDataList = new ArrayList<>();

    // ---------------------------------------- Getters ----------------------------------------
    @JsonIgnore
    public int getResultCode() {
        return resultCode;
    }

    // ---------------------------------------- Setters ----------------------------------------
    public DefenderTeamGetResult withCode(int resultCode) {
        this.resultCode = resultCode;
        return this;
    }

    public void addDragonBot(DragonBotData dragonBotData) {
        this.dragonBotDataList.add(dragonBotData);
    }
}
