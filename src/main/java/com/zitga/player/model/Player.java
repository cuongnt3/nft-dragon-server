package com.zitga.player.model;

import com.zitga.authentication.model.PlayerAuthentication;
import com.zitga.base.service.LazyLoadService;
import com.zitga.hatch.model.PlayerDragonHatch;

public class Player {

    private long playerId;

    private PlayerAuthentication authentication;

    private PlayerDragonHatch dragonHatch;


    private LazyLoadService lazyLoadService;

    public Player(PlayerAuthentication authentication, long playerId) {
        this.authentication = authentication;
        this.playerId = playerId;

    }

    // ---------------------------------------- Getters ----------------------------------------
    public long getPlayerId() {
        return playerId;
    }

    public PlayerDragonHatch getOrLoadDragonHatch() {
        if (dragonHatch == null) {
            lazyLoadService.loadHeroSummon(this);
        }
        return dragonHatch;
    }

    // ---------------------------------------- Setters ----------------------------------------
    public void setDragonHatch(PlayerDragonHatch dragonHatch){
        if (this.dragonHatch == null) {
            this.dragonHatch = dragonHatch;
        }
    }
}