package com.nft.authentication.service;

import com.nft.authentication.constant.AdminAuthConstant;
import com.nft.authentication.dao.AdminOpCodeActionDAO;
import com.nft.authentication.model.data.AdminOpCodeAction;
import com.nft.enumeration.admin.AdminAction;
import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.bean.annotation.BeanMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@BeanComponent
public class AdminOpcodeService {

    @BeanField
    private AdminOpCodeActionDAO adminOpCodeActionDAO;

    private Map<Integer, Integer> opcodeActionRoleMap;

    @BeanMethod
    private void init() {
        List<AdminOpCodeAction> adminOpCodeActionList = adminOpCodeActionDAO.findAll();
        if (adminOpCodeActionList.isEmpty()) {
            throw new RuntimeException("Opcode role is empty!");
        }

        opcodeActionRoleMap = new HashMap<>();

        for (AdminOpCodeAction adminOpCodeAction : adminOpCodeActionList) {
            if (adminOpCodeAction == null) {
                throw new RuntimeException("Opcode role is null!");
            }

            if (AdminAction.toAdminAction(adminOpCodeAction.getActionType()) == null) {
                throw new RuntimeException(String.format("Unrecognized adminAction: %d at opcode: %d!", adminOpCodeAction.getActionType(), adminOpCodeAction.getOpcode()));
            }
            opcodeActionRoleMap.put(adminOpCodeAction.getOpcode(), adminOpCodeAction.getActionType());
        }
    }

    public int getAdminActionFromOpcode(int opcode) {
        return opcodeActionRoleMap.getOrDefault(opcode, AdminAuthConstant.NO_ADMIN_ACTION);
    }
}