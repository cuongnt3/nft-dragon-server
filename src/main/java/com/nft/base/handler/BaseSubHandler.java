package com.nft.base.handler;

import com.zitga.core.handler.socket.support.context.Peer;
import io.netty.buffer.ByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseSubHandler {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public abstract void handle(Peer peer, ByteBuf in) throws Exception;
}
