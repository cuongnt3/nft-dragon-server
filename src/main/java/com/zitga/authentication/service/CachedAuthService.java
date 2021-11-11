package com.zitga.authentication.service;

import com.zitga.authentication.model.PlayerAuthentication;
import com.zitga.bean.annotation.BeanComponent;
import com.zitga.core.constants.socket.BaseDisconnectReason;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@BeanComponent
public class CachedAuthService {

    // key: id of admin
    private Map<Long, PlayerAuthentication> cachedAuthMap = new ConcurrentHashMap<>();

    public PlayerAuthentication getAdminAuthentication(long id) {
        return cachedAuthMap.get(id);
    }

    public PlayerAuthentication getAdminAuthentication(String username) {
        for (PlayerAuthentication authentication : cachedAuthMap.values()) {
            if (authentication.getUserName().equals(username)) {
                return authentication;
            }
        }

        return null;
    }

    public PlayerAuthentication getFromCacheByUserName(String userName, String password) {
        String cachedKey = getCachedKey(userName, password);
        return cachedAuthMap.get(cachedKey);
    }

    private String getCachedKey(String userName, String password) {
        return String.format("%s_%s", userName, password);
    }

    public void addToCache(PlayerAuthentication adminAuth) {
        cachedAuthMap.put(adminAuth.getId(), adminAuth);
    }

    public void onAdminLogin(PlayerAuthentication adminAuth) {
        PlayerAuthentication currentAdminLogin = cachedAuthMap.get(adminAuth.getId());
        if (currentAdminLogin != null) {
            currentAdminLogin.getEndpoint().disconnect(BaseDisconnectReason.ANOTHER_DEVICE_LOGIN);
        }

        addToCache(adminAuth);
    }

    public void removeFromCache(PlayerAuthentication adminAuth) {
        cachedAuthMap.remove(adminAuth.getId());
    }
}
