package com.nft.authentication.handler.role;

import com.nft.authentication.model.AdminAuthentication;
import com.nft.authentication.model.role.AdminRole;
import com.nft.base.constant.OpCode;
import com.nft.base.handler.AuthorizedHandler;
import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.core.annotation.socket.SocketHandler;
import com.nft.authentication.service.AdminRoleService;
import io.netty.buffer.ByteBuf;

@BeanComponent
@SocketHandler(OpCode.ADMIN_ADD_ROLE_PERMISSION)
public class AdminAddRolePermissionHandler extends AuthorizedHandler {

    @BeanField
    private AdminRoleService adminRoleService;

    @Override
    protected void handle(AdminAuthentication adminAuth, int opCode, ByteBuf in) throws Exception {
        AdminRole adminRole = new AdminRole();
        adminRole.deserialize(in);

        int resultCode = adminRoleService.addRolePermission(adminRole);

        adminAuth.send(opCode, resultCode);
    }
}