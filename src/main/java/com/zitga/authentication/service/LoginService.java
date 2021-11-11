package com.zitga.authentication.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zitga.authentication.dao.PlayerAuthenticationDAO;
import com.zitga.authentication.model.PlayerAuthentication;
import com.zitga.authentication.model.message.LoginResult;
import com.zitga.base.constant.LogicCode;
import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.config.GameConfig;
import com.zitga.core.constants.http.HttpCode;
import com.zitga.core.message.http.HttpResponse;
import com.zitga.support.JsonService;
import com.zitga.utils.GZipUtils;
import com.zitga.utils.StringUtils;

@BeanComponent
public class LoginService {

    @BeanField
    private CachedAuthService cachedAuthService;

    @BeanField
    private GameConfig gameConfig;

    @BeanField
    private JsonService jsonService;

    @BeanField
    private PlayerAuthenticationDAO authDAO;

    public HttpResponse loginByUserName(PlayerAuthentication playerAuth) throws Exception {
        LoginResult result = new LoginResult();

        String password = playerAuth.getCachedHashPassword();
        if (StringUtils.isNullOrEmpty(password)){
            return HttpResponse.error(HttpCode.FORBIDDEN, LogicCode.INVALID_INPUT_DATA);
        }

        if (!playerAuth.checkPassword()) {
            return HttpResponse.error(HttpCode.FORBIDDEN, LogicCode.AUTH_PASSWORD_NOT_MATCH);
        }

        onLoginSuccess(playerAuth);

        result.setPlayerId(playerAuth.getId());
        String data = jsonService.writeValueAsString(result);
        String zipData = GZipUtils.compress(data);
        return HttpResponse.ok(zipData);
    }

    private void onLoginSuccess(PlayerAuthentication adminAuth) {
        cachedAuthService.onAdminLogin(adminAuth);
    }
}