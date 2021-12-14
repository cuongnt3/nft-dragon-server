package com.zitga.idle.player.service;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.core.config.ServerConfig;
import com.zitga.idle.authentication.service.CachedAuthService;
import com.zitga.idle.player.model.Player;
import com.zitga.utils.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@BeanComponent
public class CachedPlayerService implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeanField
    private CachedAuthService cachedAuthService;

    @BeanField
    private ServerConfig serverConfig;

    // key: playerId
    private final Map<Long, Player> cachedPlayers = new ConcurrentHashMap<>();

    @Override
    public void run() {
        try {
            long pendingTimeOut = serverConfig.getNetwork().getIdleTimeOut();

            long currentTime = TimeUtils.getCurrentTimeInSecond();

            List<Player> removePlayers = new ArrayList<>();
            for (Player player : cachedPlayers.values()) {
                if (currentTime - TimeUtils.toSecond(player.getAuthentication().getLastOnlineTime()) > pendingTimeOut) {
                    removePlayers.add(player);
                }
            }

            for (Player player : removePlayers) {
                removeFromCache(player);
                logger.debug("Remove player {} because of pending timeout", player.getPlayerId());
            }

            logger.info("[CACHED] Cached players = {}", cachedPlayers.size());
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
        }
    }

    public void addToCache(Player player) {
        cachedPlayers.put(player.getPlayerId(), player);
        cachedAuthService.addToCache(player);
    }


    public void removeFromCache(Player player) {
        if (player != null) {
            cachedAuthService.removeFromCache(player);

            cachedPlayers.remove(player.getPlayerId());
        }
    }

    public Player getPlayer(long playerId) {
        return cachedPlayers.get(playerId);
    }
}
