package com.zitga.authentication.model.message;

import com.zitga.base.constant.LogicCode;
import com.zitga.core.message.socket.ISerializable;
import io.netty.buffer.ByteBuf;

public class LoginResult implements ISerializable {

    private int resultCode;

    private long playerId;

    public LoginResult withCode(int logicCode) {
        this.resultCode = logicCode;

        return this;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    @Override
    public void serialize(ByteBuf out) {
        out.writeShortLE(resultCode);
        if (resultCode == LogicCode.SUCCESS) {
            out.writeLongLE(playerId);
        }
    }
}
