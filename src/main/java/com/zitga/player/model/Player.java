package com.zitga.player.model;

import com.zitga.authentication.basicInfo.model.PlayerBasicInfo;
import com.zitga.authentication.model.PlayerAuthentication;
import com.zitga.base.service.LazyLoadService;
import com.zitga.dragon.model.PlayerDragonCollection;
import com.zitga.statistics.model.PlayerStatistics;
import com.zitga.summon.model.PlayerDragonSummon;

public class Player {

    private long playerId;

    private PlayerAuthentication authentication;

    private PlayerBasicInfo basicInfo;

    private PlayerDragonSummon dragonSummon;
    private PlayerDragonCollection dragonCollection;

    private PlayerStatistics statistics;

    private LazyLoadService lazyLoadService;

    public Player(PlayerAuthentication authentication) {
        this.authentication = authentication;
        this.playerId = authentication.getId();
    }

    // ---------------------------------------- Getters ----------------------------------------
    public long getPlayerId() {
        return playerId;
    }

    public PlayerAuthentication getAuthentication() {
        return authentication;
    }

    public PlayerBasicInfo getBasicInfo() {
        return basicInfo;
    }

    public PlayerDragonSummon getOrLoadDragonSummon() {
        if (dragonSummon == null) {
            lazyLoadService.loadDragonSummon(this);
        }
        return dragonSummon;
    }

    public PlayerDragonCollection getOrLoadDragon() {
        return dragonCollection;
    }

    public PlayerStatistics getStatistics() {
        return statistics;
    }

    // ---------------------------------------- Setters ----------------------------------------\
    public void setBasicInfo(PlayerBasicInfo basicInfo) {
        if (basicInfo == null) {
            this.basicInfo = basicInfo;
        }
    }

    public void replaceAuth(PlayerAuthentication authentication) {
        this.authentication = authentication;
    }

    public void setDragonSummon(PlayerDragonSummon dragonHatch) {
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