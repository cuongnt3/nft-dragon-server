package com.zitga.idle.pve.model.message;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DragonBotData {

    @JsonProperty("0")
    private int dragonId;

    @JsonProperty("1")
    private int level;

    @JsonProperty("2")
    private int star;

    @JsonProperty("3")
    private int position;

    @JsonProperty("4")
    private boolean isFrontLine;

    public void setDragonId(int dragonId) {
        this.dragonId = dragonId;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setFrontLine(boolean frontLine) {
        isFrontLine = frontLine;
    }
}
