package com.nft.base.model;

import com.zitga.core.message.socket.IDeserializable;
import com.zitga.core.message.socket.ISerializable;
import com.nft.battle.utils.BattleUtils;
import io.netty.buffer.ByteBuf;

public class Vector3 implements IDeserializable, ISerializable {

    private float x;
    private float y;
    private float z;

    public Vector3() {
    }

    public Vector3(Vector3 vector3) {
        this.x = vector3.x;
        this.y = vector3.y;
        this.z = vector3.z;
    }

    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // ---------------------------------------- Getters ----------------------------------------
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public Vector3 clone() {
        Vector3 clone = new Vector3();
        clone.x = x;
        clone.y = y;
        clone.z = z;
        return clone;
    }

    @Override
    public void serialize(ByteBuf out) {
        out.writeIntLE(BattleUtils.floatToInt(x));
        out.writeIntLE(BattleUtils.floatToInt(y));
        out.writeIntLE(BattleUtils.floatToInt(z));
    }

    // ---------------------------------------- Setters ----------------------------------------
    public Vector3 add(Vector3 vector) {
        x += vector.x;
        y += vector.y;
        z += vector.z;
        return this;
    }

    public Vector3 minus(Vector3 vector) {
        Vector3 minusVector = new Vector3();
        minusVector.x = x - vector.x;
        minusVector.y = y - vector.y;
        minusVector.z = z - vector.z;
        return minusVector;
    }

    public Vector3 multiply(float multiplier) {
        x *= multiplier;
        y *= multiplier;
        z *= multiplier;
        return this;
    }

    public void normalizeRotation() {
        x = (x + 7200) % 360;
        y = (y + 7200) % 360;
        z = (z + 7200) % 360;
    }

    @Override
    public void deserialize(ByteBuf in) {
        int xLong = in.readIntLE();
        x = BattleUtils.intToFloat(xLong);

        int yLong = in.readIntLE();
        y = BattleUtils.intToFloat(yLong);

        int zLong = in.readIntLE();
        z = BattleUtils.intToFloat(zLong);
    }
}
