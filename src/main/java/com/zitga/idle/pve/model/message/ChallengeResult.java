package com.zitga.idle.pve.model.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zitga.idle.battle.model.message.log.BattleResultLog;

public class ChallengeResult {

    private int resultCode;

    @JsonProperty("resultLog")
    private BattleResultLog resultLog;

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
}
