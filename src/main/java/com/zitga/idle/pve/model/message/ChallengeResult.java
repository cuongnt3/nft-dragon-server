package com.zitga.idle.pve.model.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zitga.idle.battle.model.message.log.BattleResultLog;

import java.util.ArrayList;
import java.util.List;

public class ChallengeResult {

    private int resultCode;

    @JsonProperty("0")
    private BattleResultLog resultLog;

    @JsonProperty("1")
    private List<DragonBotData> dragonBotDataList = new ArrayList<>();

    // ---------------------------------------- Getters ----------------------------------------
    @JsonIgnore
    public int getResultCode() {
        return resultCode;
    }

    // ---------------------------------------- Setters ----------------------------------------
    public ChallengeResult withCode(int resultCode) {
        this.resultCode = resultCode;
        return this;
    }

    public void setResultLog(BattleResultLog resultLog) {
        this.resultLog = resultLog;
    }

    public void setDragonBotDataList(List<DragonBotData> dragonBotDataList) {
        this.dragonBotDataList = dragonBotDataList;
    }
}
