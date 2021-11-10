package com.nft.base.handler;

import com.zitga.bean.annotation.BeanField;
import com.zitga.core.authentication.socket.IPeerAuthentication;
import com.zitga.core.constants.socket.DisconnectReason;
import com.zitga.core.handler.socket.AbstractSocketHandler;
import com.zitga.core.handler.socket.support.context.HandlerContext;
import com.nft.authentication.model.AdminAuthentication;
import com.nft.authentication.model.endPoint.AdminEndpoint;
import com.nft.authentication.service.AdminRoleService;
import com.nft.base.constant.LogicCode;
import io.netty.buffer.ByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AuthorizedHandler extends AbstractSocketHandler {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeanField
    private AdminRoleService adminRoleService;

    @Override
    public void handle(HandlerContext context, int opCode, ByteBuf in, boolean isTcp) {
        IPeerAuthentication auth = context.getAuth();
        if (auth instanceof AdminEndpoint) {
            AdminAuthentication adminAuth = ((AdminEndpoint) auth).getAdminAuth();
            try {
                // Ignore isTcp param for now, as this game will be TCP-only
                handle(adminAuth, opCode, in);
            } catch (Throwable e) {
                context.getPeer().send(opCode, LogicCode.SERVER_ERROR);

                logger.error(e.getMessage(), e);
                logger.error("[AUTHORIZED] FATAL error with peer={}, opCode={}, userName={}",
                        context.getPeer().getRemoteAddress(), opCode, adminAuth.getUserName());
            }
        } else {
            context.getPeer().disconnect(DisconnectReason.AUTHORIZATION_REQUIRED);
        }
    }

    protected abstract void handle(AdminAuthentication adminAuth, int opCode, ByteBuf in) throws Exception;
}