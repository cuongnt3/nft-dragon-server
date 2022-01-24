package com.zitga.idle.summon.model.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zitga.idle.dragon.model.InventoryDragon;
import com.zitga.idle.resource.model.Reward;

public class SummonResult {

    @JsonProperty("0")
    private int resultCode;

    @JsonProperty("1")
    private InventoryDragon dragon;

    @JsonProperty("2")
    private Reward reward;

    // ---------------------------------------- Getters ----------------------------------------
    @JsonIgnore
    public int getResultCode() {
        return resultCode;
    }

    @JsonIgnore
    public InventoryDragon getDragon() {
        return dragon;
    }

    @JsonIgnore
    public Reward getReward() {
        return reward;
    }

    // ---------------------------------------- Setters ----------------------------------------
    public SummonResult withCode(int resultCode) {
        this.resultCode = resultCode;
        return this;
    }

    public void setDragon(InventoryDragon dragon) {
        this.dragon = dragon;
    }

    public void setReward(Reward reward) {
        this.reward = reward;
    }
}
