package com.nft.authentication.service;

import com.nft.authentication.dao.AdminAuthenticationDAO;
import com.nft.authentication.model.AdminAuthentication;
import com.nft.authentication.model.message.LoginResult;
import com.nft.base.constant.LogicCode;
import com.nft.config.GameConfig;
import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.core.handler.socket.support.context.HandlerContext;
import com.zitga.utils.StringUtils;

@BeanComponent
public class LoginService {

    @BeanField
    private CachedAuthService cachedAuthService;

    @BeanField
    private GameConfig gameConfig;

    @BeanField
    private AdminAuthenticationDAO authDAO;

    public LoginResult loginByUserName(HandlerContext context, String userName, String hashedPassword, int version) {
        LoginResult result = new LoginResult();
        userName = StringUtils.trim(userName);
        if (StringUtils.isNullOrEmpty(userName)) {
            return result.withCode(LogicCode.INVALID_INPUT_DATA);
        }

        hashedPassword = StringUtils.trim(hashedPassword);
        if (StringUtils.isNullOrEmpty(hashedPassword)) {
            return result.withCode(LogicCode.INVALID_INPUT_DATA);
        }

        if (version != gameConfig.getVersion()) {
            return result.withCode(LogicCode.AUTH_VERSION_MISMATCH);
        }

        AdminAuthentication adminAuth = authDAO.findWithUserName(userName);
        if (adminAuth == null) {
            return result.withCode(LogicCode.PLAYER_NOT_FOUND);
        }

        if (!adminAuth.comparePassword(hashedPassword)) {
            return result.withCode(LogicCode.AUTH_PASSWORD_NOT_MATCH);
        }

        onLoginSuccess(context, adminAuth);
        result.setPlayerId(adminAuth.getId());
        return result.withCode(LogicCode.SUCCESS);
    }

    private void onLoginSuccess(HandlerContext context, AdminAuthentication adminAuth) {
        context.setAuth(adminAuth);

        adminAuth.setPeer(context.getPeer());
        adminAuth.setPeerAuthorized(true);
        adminAuth.setAdminEndpoint(context);

        cachedAuthService.onAdminLogin(adminAuth);
    }
}