package com.zitga.idle.battle.model.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class BattleTeamInbound {

    @JsonProperty("0")
    private List<BattleHeroInbound> battleHeroes = new ArrayList<>();

    @JsonProperty("1")
    private int stage;

    // ---------------------------------------- Getters ----------------------------------------
    @JsonIgnore
    public List<BattleHeroInbound> getBattleHeroes() {
        return battleHeroes;
    }

    @JsonIgnore
    public int getStage() {
        return stage;
    }
}