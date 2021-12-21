package com.zitga.idle.battle.model.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class BattleTeamInbound {

    @JsonProperty("hero_list")
    private List<BattleHeroInbound> battleHeroes = new ArrayList<>();

    // ---------------------------------------- Getters ----------------------------------------
    @JsonIgnore
    public List<BattleHeroInbound> getBattleHeroes() {
        return battleHeroes;
    }

    // ---------------------------------------- Setters ----------------------------------------
    public void addBattleHero(BattleHeroInbound battleHeroInbound) {
        this.battleHeroes.add(battleHeroInbound);
    }
}