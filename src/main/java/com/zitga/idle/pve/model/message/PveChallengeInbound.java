package com.zitga.idle.pve.model.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class PveChallengeInbound {

    @JsonProperty("stage")
    private int stage;

    @JsonProperty("heroList")
    private List<HeroBattleInbound> heroList = new ArrayList<>();

    @JsonIgnore
    public int getStage() {
        return stage;
    }

    @JsonIgnore
    public List<HeroBattleInbound> getHeroList() {
        return heroList;
    }
}
