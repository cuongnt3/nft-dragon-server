package com.nft.authentication.handler;

import com.nft.authentication.model.AdminAuthentication;
import com.nft.base.constant.OpCode;
import com.nft.base.handler.AuthorizedHandler;
import com.zitga.bean.annotation.BeanField;
import com.zitga.core.annotation.socket.SocketHandler;
import com.zitga.core.utils.socket.SerializeHelper;
import com.nft.authentication.service.ChangePasswordService;
import io.netty.buffer.ByteBuf;

@SocketHandler(OpCode.ADMIN_PASSWORD_CHANGE)
public class PasswordChangeHandler extends AuthorizedHandler {

    @BeanField
    private ChangePasswordService changePasswordService;

    @Override
    protected void handle(AdminAuthentication adminAuth, int opCode, ByteBuf in) throws Exception {
        String oldHashedPassword = SerializeHelper.readString(in, false);
        String newHashedPassword = SerializeHelper.readString(in, false);

        int resultCode  = changePasswordService.changePassword(adminAuth, oldHashedPassword, newHashedPassword);
        adminAuth.send(opCode, resultCode);
    }
}