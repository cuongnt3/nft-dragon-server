package com.zitga.idle.battle.model.message;

import com.zitga.core.message.socket.IDeserializable;
import com.zitga.core.message.socket.ISerializable;
import io.netty.buffer.ByteBuf;

public class BattleHeroInbound implements ISerializable, IDeserializable {

    private long heroInventoryId;
    private boolean isFrontLine;
    private int position;

    // ---------------------------------------- Getters ----------------------------------------
    public long getHeroInventoryId() {
        return heroInventoryId;
    }

    public boolean isFrontLine() {
        return isFrontLine;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public void serialize(ByteBuf out) {
        out.writeLongLE(heroInventoryId);
        out.writeBoolean(isFrontLine);
        out.writeByte(position);
    }

    // ---------------------------------------- Setters ----------------------------------------
    public void setHeroInventoryId(long heroInventoryId) {
        this.heroInventoryId = heroInventoryId;
    }

    public void setFrontLine(boolean frontLine) {
        isFrontLine = frontLine;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public void deserialize(ByteBuf in) {
        heroInventoryId = in.readLongLE();
        isFrontLine = in.readBoolean();
        position = in.readUnsignedByte();
    }
}
