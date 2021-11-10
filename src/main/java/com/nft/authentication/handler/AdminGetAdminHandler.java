package com.nft.authentication.handler;

import com.nft.authentication.model.AdminAuthentication;
import com.nft.base.constant.LogicCode;
import com.nft.base.constant.OpCode;
import com.nft.base.handler.AuthorizedHandler;
import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.core.annotation.socket.SocketHandler;
import com.zitga.core.utils.socket.SocketUtils;
import com.nft.authentication.service.AdminRoleService;
import io.netty.buffer.ByteBuf;

import java.util.List;

@BeanComponent
@SocketHandler(OpCode.ADMIN_GET_ADMIN)
public class AdminGetAdminHandler extends AuthorizedHandler {

    @BeanField
    private AdminRoleService adminRoleService;

    @Override
    protected void handle(AdminAuthentication adminAuth, int opCode, ByteBuf in) throws Exception {
        ByteBuf out = SocketUtils.createByteBuf(opCode);
        List<AdminAuthentication> adminAuthList = adminRoleService.getAllAdminAuths();

        out.writeShortLE(LogicCode.SUCCESS);
        out.writeByte(adminAuthList.size());
        for (AdminAuthentication adminAuthentication : adminAuthList) {
            adminAuthentication.serialize(out);
        }

        adminAuth.send(out);
    }
}