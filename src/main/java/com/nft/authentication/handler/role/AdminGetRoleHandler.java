package com.nft.authentication.handler.role;

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

import java.util.Map;
import java.util.Set;

@BeanComponent
@SocketHandler(OpCode.ADMIN_GET_ROLE)
public class AdminGetRoleHandler extends AuthorizedHandler {

    @BeanField
    private AdminRoleService adminRoleService;

    @Override
    protected void handle(AdminAuthentication adminAuth, int opCode, ByteBuf in) throws Exception {
        Map<Integer, Set<Integer>> roleAdminActionMap = adminRoleService.getRoleAdminActionMap();

        ByteBuf out = SocketUtils.createByteBuf(opCode);
        out.writeShortLE(LogicCode.SUCCESS);
        out.writeByte(roleAdminActionMap.size());
        for (Map.Entry<Integer, Set<Integer>> roleAdminActionEntry : roleAdminActionMap.entrySet()) {
            out.writeByte(roleAdminActionEntry.getKey());

            out.writeByte(roleAdminActionEntry.getValue().size());
            for (int adminAction : roleAdminActionEntry.getValue()) {
                out.writeShortLE(adminAction);
            }
        }

        adminAuth.send(out);
    }
}
