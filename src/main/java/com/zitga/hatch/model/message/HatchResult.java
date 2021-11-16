package com.zitga.hatch.model.message;

import com.zitga.dragon.model.InventoryDragon;
import com.zitga.enumeration.hatch.HatchType;

public class HatchResult {

    private int resultCode;
    private HatchType summonType;

    private InventoryDragon dragon;

    // ---------------------------------------- Getters ----------------------------------------
    public int getResultCode() {
        return resultCode;
    }

    // ---------------------------------------- Setters ----------------------------------------
    public HatchResult withCode(int resultCode) {
        this.resultCode = resultCode;
        return this;
    }
}
