package com.zitga.authentication.service;

import com.zitga.authentication.model.AdminAuthentication;
import com.zitga.bean.annotation.BeanComponent;
import com.zitga.core.constants.socket.BaseDisconnectReason;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@BeanComponent
public class CachedAuthService {

    // key: id of admin
    private Map<Long, AdminAuthentication> cachedAuthMap = new ConcurrentHashMap<>();

    public AdminAuthentication getAdminAuthentication(long id) {
        return cachedAuthMap.get(id);
    }

    public AdminAuthentication getAdminAuthentication(String username) {
        for (AdminAuthentication authentication : cachedAuthMap.values()) {
            if (authentication.getUserName().equals(username)) {
                return authentication;
            }
        }

        return null;
    }

    public void addToCache(AdminAuthentication adminAuth) {
        cachedAuthMap.put(adminAuth.getId(), adminAuth);
    }

    public void onAdminLogin(AdminAuthentication adminAuth) {
        AdminAuthentication currentAdminLogin = cachedAuthMap.get(adminAuth.getId());
        if (currentAdminLogin != null) {
            currentAdminLogin.getEndpoint().disconnect(BaseDisconnectReason.ANOTHER_DEVICE_LOGIN);
        }

        addToCache(adminAuth);
    }

    public void removeFromCache(AdminAuthentication adminAuth) {
        cachedAuthMap.remove(adminAuth.getId());
    }
}
