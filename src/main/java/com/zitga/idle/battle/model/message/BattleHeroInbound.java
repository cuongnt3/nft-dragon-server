package com.zitga.idle.battle.model.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BattleHeroInbound {

    @JsonProperty("id")
    private long heroInventoryId;

    @JsonProperty("position")
    private int position;

    @JsonProperty("is_front_line")
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
