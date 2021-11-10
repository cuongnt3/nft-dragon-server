package com.nft.authentication.service;

import com.nft.authentication.constant.AdminAuthConstant;
import com.nft.authentication.dao.AdminAuthenticationDAO;
import com.nft.authentication.model.AdminAuthentication;
import com.nft.base.constant.LogicCode;
import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.utils.StringUtils;

@BeanComponent
public class ChangePasswordService {

    @BeanField
    private HandshakeService handshakeService;

    @BeanField
    private AdminAuthenticationDAO authDAO;

    public int changePassword(AdminAuthentication adminAuth, String oldHashedPassword, String newHashedPassword) {
        oldHashedPassword = StringUtils.trim(oldHashedPassword);
        if (StringUtils.isNullOrEmpty(oldHashedPassword)) {
            return LogicCode.INVALID_INPUT_DATA;
        }

        newHashedPassword = StringUtils.trim(newHashedPassword);
        if (StringUtils.isNullOrEmpty(newHashedPassword)) {
            return LogicCode.INVALID_INPUT_DATA;
        }

        int passwordWrongCount = handshakeService.getPasswordWrongCount(adminAuth.getEndpoint().getContext());
        if (passwordWrongCount >= AdminAuthConstant.PASSWORD_WRONG_LIMIT) {
            return LogicCode.AUTH_PASSWORD_MISMATCHED_LIMIT_REACHED;
        }

        if (!adminAuth.comparePassword(oldHashedPassword)) {
            handshakeService.incrementPasswordWrongCount(adminAuth.getEndpoint().getContext());
            return LogicCode.AUTH_PASSWORD_MISMATCHED;
        }

        if (newHashedPassword.equals(oldHashedPassword)) {
            return LogicCode.AUTH_PASSWORD_ALREADY_USED;
        }

        adminAuth.setPassword(newHashedPassword);
        authDAO.save(adminAuth);

        return LogicCode.SUCCESS;
    }
}