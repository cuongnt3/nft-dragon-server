package com.nft.authentication.service;

import com.nft.authentication.dao.AdminAuthenticationDAO;
import com.nft.authentication.dao.AdminRoleDAO;
import com.nft.authentication.model.AdminAuthentication;
import com.nft.authentication.model.role.AdminRole;
import com.nft.base.constant.LogicCode;
import com.nft.enumeration.admin.AdminAction;
import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.bean.annotation.BeanMethod;
import com.nft.authentication.constant.AdminAuthConstant;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@BeanComponent
public class AdminRoleService {

    @BeanField
    private AdminOpcodeService adminOpcodeService;

    @BeanField
    private CachedAuthService cachedAuthService;

    @BeanField
    private AdminAuthenticationDAO adminAuthDAO;

    @BeanField
    private AdminRoleDAO adminRoleDAO;

    //key: role assigned to admin; value: Set<enum AdminAssignedPermission>
    private Map<Integer, Set<Integer>> roleAdminActionMap;

    @BeanMethod
    private void init() {
        List<AdminRole> adminRoleList = adminRoleDAO.findAll();

        roleAdminActionMap = new ConcurrentHashMap<>();
        for (AdminRole adminRole : adminRoleList) {
            if (roleAdminActionMap.containsKey(adminRole.getRole())) {
                throw new RuntimeException(String.format("Duplicate role %d!", adminRole.getRole()));
            }

            Set<Integer> adminActionSet = adminRole.getAllowedActions();

            if (adminActionSet == null || adminActionSet.isEmpty()) {
                throw new RuntimeException(String.format("Role: %d has empty permission!", adminRole.getRole()));
            }

            for (int adminAction : adminActionSet) {
                if (AdminAction.toAdminAction(adminAction) == null) {
                    throw new RuntimeException(String.format("Role: %d has unrecognized adminAction: %d !", adminRole.getRole(), adminAction));
                }
            }

            roleAdminActionMap.put(adminRole.getRole(), adminActionSet);
        }
    }

    public boolean isAdminAuthorized(int opcode, AdminAuthentication adminAuth) {
        //key: enum adminAction; value: enum adminPermission
        Set<Integer> adminActionSet = roleAdminActionMap.get(adminAuth.getRole());
        if (adminActionSet == null) {
            return false;
        }

        int adminAction = adminOpcodeService.getAdminActionFromOpcode(opcode);
        if (adminAction == AdminAuthConstant.NO_ADMIN_ACTION || !adminActionSet.contains(adminAction)) {
            return false;
        }

        return true;
    }

    public int addRolePermission(AdminRole adminRole) {
        if (roleAdminActionMap.containsKey(adminRole.getRole())) {
            return LogicCode.ADMIN_DUPLICATE_ROLE;
        }

        Set<Integer> adminActionSet = adminRole.getAllowedActions();
        for (int adminAction : adminActionSet) {
            if (AdminAction.toAdminAction(adminAction) == null || adminAction == AdminAuthConstant.NO_ADMIN_ACTION) {
                return LogicCode.ADMIN_ACTION_NOT_FOUND;
            }
        }

        adminRoleDAO.save(adminRole);
        roleAdminActionMap.put(adminRole.getRole(), adminActionSet);

        return LogicCode.SUCCESS;
    }

    public int assignRole(long assigneeAdminId, int role) {
        if (!roleAdminActionMap.containsKey(role)) {
            return LogicCode.ADMIN_ROLE_NOT_FOUND;
        }

        AdminAuthentication assigneeAdminAuth = adminAuthDAO.findOne(assigneeAdminId);
        if (assigneeAdminAuth == null) {
            return LogicCode.PLAYER_NOT_FOUND;
        }

        if (assigneeAdminAuth.getRole() == role) {
            return LogicCode.ADMIN_DUPLICATE_ROLE;
        }

        assigneeAdminAuth.setRole(role);
        adminAuthDAO.updateAdminRole(assigneeAdminId, role);

        assigneeAdminAuth = cachedAuthService.getAdminAuthentication(assigneeAdminId);
        if (assigneeAdminAuth != null) {
            assigneeAdminAuth.setRole(role);
        }

        return LogicCode.SUCCESS;
    }

    public List<AdminAuthentication> getAllAdminAuths() {
        return adminAuthDAO.findAll();
    }

    public Map<Integer, Set<Integer>> getRoleAdminActionMap() {
        return roleAdminActionMap;
    }
}