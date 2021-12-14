package com.zitga.idle.battleInfo.model.formation;

import com.zitga.core.message.socket.IDeserializable;
import com.zitga.core.message.socket.ISerializable;
import com.zitga.idle.battle.model.message.BattleHeroInbound;
import com.zitga.idle.battleInfo.constant.BattleInfoTag;
import io.netty.buffer.ByteBuf;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Entity(value = "", noClassnameStored = true)
public class TeamFormation implements ISerializable, IDeserializable {

    @Property(BattleInfoTag.SUMMONER_CLASS_TAG)
    private int summonerClass;

    @Property(BattleInfoTag.FORMATION_TAG)
    private int formation = 0;

    @Embedded(BattleInfoTag.FRONT_LINE_TAG)
    // Key: position, value: heroInventoryId
    private Map<Integer, Long> frontLine = new ConcurrentHashMap<>();

    @Embedded(BattleInfoTag.BACK_LINE_TAG)
    // Key: position, value: heroInventoryId
    private Map<Integer, Long> backLine = new ConcurrentHashMap<>();

    public TeamFormation() {
        // For serialize purpose
    }

    // ---------------------------------------- Getters ----------------------------------------
    public int getSummonerClass() {
        return summonerClass;
    }

    public int getFormation() {
        return formation;
    }

    public Map<Integer, Long> getFrontLine() {
        return frontLine;
    }

    public Map<Integer, Long> getBackLine() {
        return backLine;
    }

    public boolean isContainHero(long heroInventoryId) {
        if (frontLine.containsValue(heroInventoryId)) {
            return true;
        }

        if (backLine.containsValue(heroInventoryId)) {
            return true;
        }

        return false;
    }

    public int getNumberHeroes() {
        return frontLine.size() + backLine.size();
    }

    public Map<Integer, Long> getAllHero() {
        Map<Integer, Long> allHero = new ConcurrentHashMap<>(frontLine);
        allHero.putAll(backLine);
        return allHero;
    }

    public boolean isFrontLine(int position, long heroInventoryId) {
        long heroId = frontLine.getOrDefault(position, 0L);
        if (heroId == heroInventoryId) {
            return true;
        }
        return false;
    }

    @Override
    public void serialize(ByteBuf out) {
        out.writeByte(summonerClass);
        out.writeByte(formation);

        out.writeByte(frontLine.size());
        for (Map.Entry<Integer, Long> entry : frontLine.entrySet()) {
            out.writeByte(entry.getKey());
            out.writeLongLE(entry.getValue());
        }

        out.writeByte(backLine.size());
        for (Map.Entry<Integer, Long> entry : backLine.entrySet()) {
            out.writeByte(entry.getKey());
            out.writeLongLE(entry.getValue());
        }
    }

    // ---------------------------------------- Setters ----------------------------------------
    public void setSummonerClass(int summonerClass) {
        this.summonerClass = summonerClass;
    }

    public void setFormation(int formation) {
        this.formation = formation;
    }

    public void addHero(BattleHeroInbound heroInbound) {
        if (heroInbound.isFrontLine()) {
            frontLine.put(heroInbound.getPosition(), heroInbound.getHeroInventoryId());
        } else {
            backLine.put(heroInbound.getPosition(), heroInbound.getHeroInventoryId());
        }
    }

    @Override
    public void deserialize(ByteBuf in) {
        summonerClass = in.readUnsignedByte();
        formation = in.readUnsignedByte();

        frontLine = new ConcurrentHashMap<>();
        int size = in.readUnsignedByte();
        for (int i = 0; i < size; i++) {
            int positionId = in.readUnsignedByte();
            long heroInventoryId = in.readLongLE();

            frontLine.put(positionId, heroInventoryId);
        }

        backLine = new ConcurrentHashMap<>();
        size = in.readUnsignedByte();
        for (int i = 0; i < size; i++) {
            int positionId = in.readUnsignedByte();
            long heroInventoryId = in.readLongLE();

            backLine.put(positionId, heroInventoryId);
        }
    }
}
