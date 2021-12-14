package com.zitga.idle.battle.model.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zitga.idle.battle.model.message.log.BattleResultLog;

public class BattleLogResult {

    @JsonProperty("0")
    private int resultCode;

    @JsonProperty("1")
    private BattleResultLog battleResultLog;

    @JsonIgnore
    public BattleLogResult withCode(int code){
        this.resultCode = code;
        return this;
    }

    public void setBattleLog(BattleResultLog battleResultLog) {
        this.battleResultLog = battleResultLog;
    }
}
