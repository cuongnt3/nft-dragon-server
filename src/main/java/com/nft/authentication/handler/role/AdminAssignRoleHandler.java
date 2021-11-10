package com.nft.authentication.handler.role;

import com.nft.authentication.model.AdminAuthentication;
import com.nft.authentication.service.AdminRoleService;
import com.nft.base.constant.OpCode;
import com.nft.base.handler.AuthorizedHandler;
import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.core.annotation.socket.SocketHandler;
import io.netty.buffer.ByteBuf;

@BeanComponent
@SocketHandler(OpCode.ADMIN_ASSIGN_ROLE)
public class AdminAssignRoleHandler extends AuthorizedHandler {

    @BeanField
    private AdminRoleService adminRoleService;

    @Override
    protected void handle(AdminAuthentication adminAuth, int opCode, ByteBuf in) throws Exception {
        long assigneeAdminId = in.readLongLE();
        int role = in.readUnsignedByte();

        int resultCode = adminRoleService.assignRole(assigneeAdminId, role);
        adminAuth.send(opCode, resultCode);
    }
}