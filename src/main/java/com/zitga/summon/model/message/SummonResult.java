package com.zitga.summon.model.message;

import com.zitga.dragon.model.InventoryDragon;
import com.zitga.enumeration.summon.SummonType;

public class SummonResult {

    private int resultCode;
    private SummonType summonType;

    private InventoryDragon dragon;

    // ---------------------------------------- Getters ----------------------------------------
    public int getResultCode() {
        return resultCode;
    }

    // ---------------------------------------- Setters ----------------------------------------
    public SummonResult withCode(int resultCode) {
        this.resultCode = resultCode;
        return this;
    }
}
