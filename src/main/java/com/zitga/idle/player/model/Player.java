package com.zitga.idle.player.model;


import com.zitga.core.authentication.IAuthorizedEntity;
import com.zitga.idle.ServiceController;
import com.zitga.idle.authentication.basicInfo.model.PlayerBasicInfo;
import com.zitga.idle.authentication.model.PlayerAuthentication;
import com.zitga.idle.base.service.LazyLoadService;
import com.zitga.idle.battleInfo.model.PlayerBattleInfo;
import com.zitga.idle.dragon.model.PlayerDragonCollection;
import com.zitga.idle.pve.model.PlayerPve;
import com.zitga.idle.statistics.model.PlayerStatistics;
import com.zitga.idle.summon.model.PlayerDragonSummon;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Player implements IAuthorizedEntity {

    private long playerId;

    private PlayerAuthentication authentication;

    private PlayerBasicInfo basicInfo;
    private PlayerBattleInfo battleInfo;

    private PlayerDragonSummon dragonSummon;
    private PlayerDragonCollection dragonCollection;
    private PlayerPve playerPve;

    private PlayerStatistics statistics;

    private LazyLoadService lazyLoadService;

    // key: data type
    private Map<Integer, String> playerAuthorizedDataMap = new ConcurrentHashMap<>();

    public Player(PlayerAuthentication authentication) {
        this.authentication = authentication;
        this.playerId = authentication.getId();

        this.lazyLoadService = ServiceController.instance().getLazyLoadService();
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

    public PlayerBattleInfo getBattleInfo() {
        return battleInfo;
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

    public PlayerPve getOrLoadPlayerPve() {
        return playerPve;
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

    public void setBattleInfo(PlayerBattleInfo battleInfo) {
        if (battleInfo == null) {
            this.battleInfo = battleInfo;
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

    public void setPlayerPve(PlayerPve playerPve) {
        if (this.playerPve == null) {
            this.playerPve = playerPve;
        }
    }

    public void setStatistics(PlayerStatistics statistics) {
        if (this.statistics == null) {
            this.statistics = statistics;
        }
    }

    @Override
    public String getAuthToken(int key) {
        return playerAuthorizedDataMap.remove(key);
    }

    @Override
    public void setAuthToken(int key, String data) {
        playerAuthorizedDataMap.put(key, data);
    }
}