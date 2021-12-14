package com.zitga.idle.authentication.service;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.idle.authentication.model.PlayerAuthentication;
import com.zitga.idle.enumeration.auth.AuthMethod;
import com.zitga.idle.player.model.Player;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@BeanComponent
public class CachedAuthService {

    // key: AuthMethod + userName + password
    private final Map<String, PlayerAuthentication> cachedAuthMap = new ConcurrentHashMap<>();

    // ---------------------------------------- Getters ----------------------------------------
    public PlayerAuthentication getFromCacheByUserName(String userName, String password) {
        String cachedKey = getCachedKey(userName);
        return cachedAuthMap.get(cachedKey);
    }

    private String getCachedKey(String userName) {
        return String.format("%s_%s", AuthMethod.LOGIN_BY_USER_NAME, userName);
    }

    // ---------------------------------------- Setters ----------------------------------------
    public void addToCache(Player player) {
        PlayerAuthentication auth = player.getAuthentication();
        if (auth != null) {
            if (auth.isHaveUserName()) {

                String key = getCachedKey(auth.getUserName());
                cachedAuthMap.put(key, auth);
            }
        }
    }

    public void removeFromCache(Player player) {
        PlayerAuthentication auth = player.getAuthentication();
        if (auth != null) {
            if (auth.isHaveUserName()) {
                String key = getCachedKey(auth.getUserName());
                cachedAuthMap.remove(key, auth);
            }
        }
    }
}
