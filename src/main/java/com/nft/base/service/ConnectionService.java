package com.nft.base.service;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.core.authentication.socket.IPeerAuthentication;
import com.zitga.core.handler.socket.support.ISocketConnectionListener;
import com.zitga.core.handler.socket.support.context.HandlerContext;
import com.nft.authentication.model.AdminAuthentication;
import com.nft.authentication.model.endPoint.AdminEndpoint;
import com.nft.authentication.service.CachedAuthService;
import com.nft.authentication.service.HandshakeService;
import com.nft.publisher.model.battle.DisconnectListenerData;
import com.nft.publisher.service.PublisherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@BeanComponent
public class ConnectionService implements ISocketConnectionListener {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeanField
    private CachedAuthService cachedAuthService;

    @BeanField
    private HandshakeService handshakeService;

    @BeanField
    private PublisherService publisherService;

    @Override
    public void onConnected(HandlerContext context) {
        logger.debug("[{}] Peer = {} CONNECTED", context.getPeer().getRemoteAddress(), context.getPeer().getId());
        handshakeService.addHandshake(context);
    }

    @Override
    public void onDisconnected(HandlerContext context) {
        logger.debug("[{}] Peer = {} DISCONNECTED", context.getPeer().getRemoteAddress(), context.getPeer().getId());

        handshakeService.removeHandShake(context);
        IPeerAuthentication auth = context.getAuth();
        AdminEndpoint adminEndpoint = (AdminEndpoint) auth;
        AdminAuthentication adminAuth = adminEndpoint.getAdminAuth();
        logger.debug("Admin = {} DISCONNECTED", adminAuth.getUserName());
        cachedAuthService.removeFromCache(adminAuth);

        publisherService.notifyListener(adminAuth, new DisconnectListenerData());
    }
}
