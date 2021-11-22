package com.zitga.base.service;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.core.authentication.socket.IPeerAuthentication;
import com.zitga.core.handler.socket.support.ISocketConnectionListener;
import com.zitga.core.handler.socket.support.context.HandlerContext;
import com.zitga.authentication.model.PlayerAuthentication;
import com.zitga.authentication.model.endPoint.PlayerEndpoint;
import com.zitga.authentication.service.CachedAuthService;
import com.zitga.authentication.service.HandshakeService;
import com.zitga.player.model.Player;
import com.zitga.publisher.model.battle.DisconnectListenerData;
import com.zitga.publisher.service.PublisherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@BeanComponent
public class ConnectionService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeanField
    private CachedAuthService cachedAuthService;

    @BeanField
    private PublisherService publisherService;

    // ---------------------------------------- Manage player connection ----------------------------------------
}
