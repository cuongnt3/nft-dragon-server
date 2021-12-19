package com.zitga.idle.pve.model.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HeroBattleInbound {

    @JsonProperty("id")
    private long inventoryHeroId;

    @JsonProperty("position")
    private int position;

    @JsonProperty("is_front_line")
    private boolean isFrontLine;

    @JsonIgnore
    public long getInventoryHeroId() {
        return inventoryHeroId;
    }

    @JsonIgnore
    public int getPosition() {
        return position;
    }

    @JsonIgnore
    public boolean isFrontLine() {
        return isFrontLine;
    }
}
