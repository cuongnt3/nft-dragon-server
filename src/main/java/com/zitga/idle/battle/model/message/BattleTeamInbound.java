package com.zitga.idle.battle.model.message;

import com.zitga.core.message.socket.IDeserializable;
import com.zitga.core.message.socket.ISerializable;
import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.List;

public class BattleTeamInbound implements ISerializable, IDeserializable {

    private int summonerClass;
    private int formation;
    private final List<BattleHeroInbound> battleHeroes = new ArrayList<>();

    // ---------------------------------------- Getters ----------------------------------------
    public int getSummonerClass() {
        return summonerClass;
    }

    public int getFormation() {
        return formation;
    }

    public List<BattleHeroInbound> getBattleHeroes() {
        return battleHeroes;
    }

    @Override
    public void serialize(ByteBuf out) {
        out.writeByte(summonerClass);
        out.writeByte(formation);

        out.writeByte(battleHeroes.size());
        for (BattleHeroInbound battleHero : battleHeroes) {
            battleHero.serialize(out);
        }
    }

    // ---------------------------------------- Setters ----------------------------------------
    public void setSummonerClass(int summonerClass) {
        this.summonerClass = summonerClass;
    }

    public void setFormation(int formation) {
        this.formation = formation;
    }

    public void addBattleHero(BattleHeroInbound battleHeroInbound) {
        this.battleHeroes.add(battleHeroInbound);
    }

    @Override
    public void deserialize(ByteBuf in) {
        summonerClass = in.readUnsignedByte();
        formation = in.readUnsignedByte();

        int number = in.readUnsignedByte();
        for (int i = 0; i < number; i++) {
            BattleHeroInbound battleHero = new BattleHeroInbound();
            battleHero.deserialize(in);

            battleHeroes.add(battleHero);
        }
    }
}