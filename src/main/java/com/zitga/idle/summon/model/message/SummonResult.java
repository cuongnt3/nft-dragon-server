package com.zitga.idle.summon.model.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zitga.idle.dragon.model.InventoryDragon;
import com.zitga.idle.resource.model.Reward;

public class SummonResult {

    private int resultCode;

    private InventoryDragon dragon;

    private Reward reward;

    // ---------------------------------------- Getters ----------------------------------------
    @JsonIgnore
    public int getResultCode() {
        return resultCode;
    }

    public InventoryDragon getDragon() {
        return dragon;
    }

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
