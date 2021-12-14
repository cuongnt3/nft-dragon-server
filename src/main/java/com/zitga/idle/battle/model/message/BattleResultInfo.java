package com.zitga.idle.battle.model.message;

import com.zitga.core.message.socket.IDeserializable;
import com.zitga.core.message.socket.ISerializable;
import com.zitga.idle.battle.model.battle.output.LuaBattleResult;
import io.netty.buffer.ByteBuf;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BattleResultInfo implements ISerializable, IDeserializable {

    protected boolean isWin = false;

    protected int seed;
    protected int numberRandom;

    private Map<Integer, Integer> activeLinkingGroups = new ConcurrentHashMap<>();

    private LuaBattleResult luaBattleResult;

    public BattleResultInfo() {
        // For serialize purpose
    }

    // ---------------------------------------- Getters ----------------------------------------
    public boolean isWin() {
        return isWin;
    }

    public int getSeed() {
        return seed;
    }

    public int getNumberRandom() {
        return numberRandom;
    }

    public Map<Integer, Integer> getActiveLinkingGroups() {
        return activeLinkingGroups;
    }

    public LuaBattleResult getLuaBattleResult() {
        return luaBattleResult;
    }

    @Override
    public void serialize(ByteBuf out) {
        out.writeBoolean(isWin);

        out.writeIntLE(seed);
        out.writeIntLE(numberRandom);

        out.writeByte(activeLinkingGroups.size());
        for (Map.Entry<Integer, Integer> entry : activeLinkingGroups.entrySet()) {
            out.writeIntLE(entry.getKey());
            out.writeIntLE(entry.getValue());
        }
    }

    // ---------------------------------------- Setters ----------------------------------------
    public void setLuaBattleResult(LuaBattleResult luaBattleResult, boolean isAttacker) {
        if (isAttacker) {
            this.isWin = luaBattleResult.isAttackerWin();
        } else {
            this.isWin = luaBattleResult.isDefenderWin();
        }

        this.seed = luaBattleResult.getRandomHelper().getSeed();
        this.numberRandom = luaBattleResult.getRandomHelper().getNumberRandom();
        this.luaBattleResult = luaBattleResult;
    }

    public void setActiveLinkingGroups(Map<Integer, Integer> activeLinkingGroups) {
        this.activeLinkingGroups = activeLinkingGroups;
    }

    @Override
    public void deserialize(ByteBuf in) {
        isWin = in.readBoolean();

        seed = in.readIntLE();
        numberRandom = in.readIntLE();

        activeLinkingGroups = new ConcurrentHashMap<>();
        int sizeLinking = in.readUnsignedByte();
        for (int i = 0; i < sizeLinking; i++) {
            int group = in.readIntLE();
            int level = in.readIntLE();
            activeLinkingGroups.put(group, level);
        }
    }
}
