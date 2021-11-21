package com.zitga.player.model;

import com.zitga.authentication.model.PlayerAuthentication;
import com.zitga.base.service.LazyLoadService;
import com.zitga.dragon.model.PlayerDragonCollection;
import com.zitga.statistics.model.PlayerStatistics;
import com.zitga.summon.model.PlayerDragonSummon;

public class Player {

    private long playerId;

    private PlayerAuthentication authentication;

    private PlayerDragonSummon dragonSummon;
    private PlayerDragonCollection dragonCollection;

    private PlayerStatistics statistics;

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
            lazyLoadService.loadDragonSummon(this);
        }
        return dragonSummon;
    }

    public PlayerDragonCollection getOrLoadDragon(){
        if (dragonCollection == null) {
            lazyLoadService.loadDragonCollection(this);
        }
        return dragonCollection;
    }

    public PlayerStatistics getStatistics() {
        return statistics;
    }

    // ---------------------------------------- Setters ----------------------------------------
    public void setDragonSummon(PlayerDragonSummon dragonHatch){
        if (this.dragonSummon == null) {
            this.dragonSummon = dragonHatch;
        }
    }

    public void setDragonCollection(PlayerDragonCollection dragonCollection) {
        if (this.dragonCollection == null) {
            this.dragonCollection = dragonCollection;
        }
    }

    public void setStatistics(PlayerStatistics statistics) {
        if (this.statistics == null) {
            this.statistics = statistics;
        }
    }
}