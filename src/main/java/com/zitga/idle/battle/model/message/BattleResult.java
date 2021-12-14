package com.zitga.idle.battle.model.message;

import com.zitga.idle.base.constant.LogicCode;
import io.netty.buffer.ByteBuf;

public class BattleResult extends BattleResultInfo {

    protected int resultCode;

    // ---------------------------------------- Getters ----------------------------------------
    public int getResultCode() {
        return resultCode;
    }

    @Override
    public void serialize(ByteBuf out) {
        out.writeShortLE(resultCode);

        if (resultCode == LogicCode.SUCCESS) {
            super.serialize(out);
        }
    }

    // ---------------------------------------- Setters ----------------------------------------
    public BattleResult withCode(int logicCode) {
        this.resultCode = logicCode;
        return this;
    }

    @Override
    public void deserialize(ByteBuf in) {
        resultCode = in.readShortLE();

        if (resultCode == LogicCode.SUCCESS) {
            super.deserialize(in);
        }
    }
}