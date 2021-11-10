package com.nft.authentication.service;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanMethod;
import com.zitga.core.handler.socket.support.context.HandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@BeanComponent
public class HandshakeService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // value: number wrong password
    private Map<HandlerContext, Integer> passwordWrongCountMap;

    @BeanMethod
    private void init() {
        logger.info("Initializing HandshakeService...");

        passwordWrongCountMap = new ConcurrentHashMap<>();
    }

    public int getPasswordWrongCount(HandlerContext handlerContext) {
        return passwordWrongCountMap.get(handlerContext);
    }

    public void addHandshake(HandlerContext context) {
        passwordWrongCountMap.put(context, 0);
    }

    public void removeHandShake(HandlerContext context) {
        passwordWrongCountMap.remove(context);
    }

    public void incrementPasswordWrongCount(HandlerContext handlerContext) {
        int count = passwordWrongCountMap.get(handlerContext);
        count++;
        passwordWrongCountMap.put(handlerContext, count);
    }
}