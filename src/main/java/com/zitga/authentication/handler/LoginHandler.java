package com.zitga.authentication.handler;

import com.zitga.authentication.model.message.LoginResult;
import com.zitga.base.constant.OpCode;
import com.zitga.base.handler.UnauthorizedHandler;
import com.zitga.bean.annotation.BeanField;
import com.zitga.core.annotation.socket.SocketHandler;
import com.zitga.core.handler.socket.support.context.HandlerContext;
import com.zitga.core.utils.socket.SerializeHelper;
import com.zitga.core.utils.socket.SocketUtils;
import com.zitga.authentication.service.LoginService;
import io.netty.buffer.ByteBuf;

@SocketHandler(OpCode.LOGIN)
public class LoginHandler extends UnauthorizedHandler {

    @BeanField
    private LoginService loginService;

    @Override
    public void handle(HandlerContext context, int opCode, ByteBuf in) {
        String userName = SerializeHelper.readString(in).toLowerCase();
        String hashedPassword = SerializeHelper.readString(in, false);

        int version = in.readIntLE();

        LoginResult result = loginService.loginByUserName(context, userName, hashedPassword, version);
        ByteBuf out = SocketUtils.createByteBuf(opCode);
        result.serialize(out);
        context.getPeer().send(out);

        logger.info("[AUTH] [LOGIN] Login result {}", result.getResultCode());
    }
}