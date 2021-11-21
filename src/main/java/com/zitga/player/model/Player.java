package com.zitga.player.model;

import com.zitga.authentication.model.PlayerAuthentication;
import com.zitga.base.service.LazyLoadService;
import com.zitga.dragon.model.PlayerDragonCollection;
import com.zitga.summon.model.PlayerDragonSummon;

public class Player {

    private long playerId;

    private PlayerAuthentication authentication;

    private PlayerDragonSummon dragonSummon;
    private PlayerDragonCollection dragonCollection;

    private LazyLoadService lazyLoadService;

    public Player(PlayerAuthentication authentication, long playerId) {
        this.authentication = authentication;
        this.playerId = playerId;

    }

    // ---------------------------------------- Getters ----------------------------------------
    public long getPlayerId() {
        return playerId;
    }

    public PlayerDragonSummon getOrLoadDragonSummon() {
        if (dragonSummon == null) {
            lazyLoadService.loadHeroSummon(this);
        }
        return dragonSummon;
    }

    public PlayerDragonCollection getOrLoadDragon(){
        if (dragonCollection == null) {
            lazyLoadService.loadDragonCollection(this);
        }
        return dragonCollection;
    }

    // ---------------------------------------- Setters ----------------------------------------
    public void setDragonSummon(PlayerDragonSummon dragonHatch){
        if (this.dragonSummon == null) {
            this.dragonSummon = dragonHatch;
        }
    }

    public void setHeroCollection(PlayerDragonCollection dragonCollection) {
        if (this.dragonCollection == null) {
            this.dragonCollection = dragonCollection;
        }
    }
}