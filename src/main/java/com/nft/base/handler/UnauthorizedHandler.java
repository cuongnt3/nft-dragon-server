package com.nft.base.handler;

import com.zitga.core.handler.socket.AbstractSocketHandler;
import com.zitga.core.handler.socket.support.context.HandlerContext;
import com.nft.base.constant.LogicCode;
import io.netty.buffer.ByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class UnauthorizedHandler extends AbstractSocketHandler {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void handle(HandlerContext context, int opCode, ByteBuf in, boolean isTcp) {
        try {
            // Ignore isTcp param for now, as this game will be TCP-only
            handle(context, opCode, in);
        } catch (Throwable e) {
            context.getPeer().send(opCode, LogicCode.SERVER_ERROR);

            logger.error(e.getMessage(), e);
            logger.error("[UNAUTHORIZED] FATAL error with peer={}, opCode={}",
                    context.getPeer().getRemoteAddress(), opCode);
        }
    }

    protected abstract void handle(HandlerContext context, int opCode, ByteBuf in);
}
