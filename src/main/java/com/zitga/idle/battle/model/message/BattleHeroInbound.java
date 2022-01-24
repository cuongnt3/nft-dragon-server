package com.zitga.idle.battle.model.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BattleHeroInbound {

    @JsonProperty("0")
    private long heroInventoryId;

    @JsonProperty("1")
    private int position;

    @JsonProperty("2")
    private boolean isFrontLine;

    // ---------------------------------------- Getters ----------------------------------------
    @JsonIgnore
    public long getHeroInventoryId() {
        return heroInventoryId;
    }

    @JsonIgnore
    public boolean isFrontLine() {
        return isFrontLine;
    }

    @JsonIgnore
    public int getPosition() {
        return position;
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
}
