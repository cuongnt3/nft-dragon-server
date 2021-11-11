package com.zitga.authentication.handler;

import com.zitga.authentication.constant.AuthRoute;
import com.zitga.authentication.model.PlayerAuthentication;
import com.zitga.authentication.service.AuthenticationService;
import com.zitga.authentication.service.LoginService;
import com.zitga.base.constant.LogicCode;
import com.zitga.base.handler.BaseHttpHandler;
import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.core.annotation.http.HttpAuthorizeHandler;
import com.zitga.core.annotation.http.HttpController;
import com.zitga.core.annotation.http.HttpFilter;
import com.zitga.core.annotation.http.HttpRoute;
import com.zitga.core.annotation.http.param.HttpAuthorizedEntity;
import com.zitga.core.authentication.IAuthorizedEntity;
import com.zitga.core.constants.http.HttpCode;
import com.zitga.core.constants.http.HttpMethod;
import com.zitga.core.message.http.HttpResponse;

@HttpController(AuthRoute.HTTP_AUTH_ROUTE)
@BeanComponent
public class LoginHttpHandler extends BaseHttpHandler {

    @BeanField
    private LoginService loginService;

    @HttpRoute(value = AuthRoute.HTTP_LOGIN_ROUTE, method = HttpMethod.POST)
    @HttpFilter(LoginHttpHandler.class)
    @HttpAuthorizeHandler(value = AuthenticationService.class, isCreateNewUser = true)
    public HttpResponse login(@HttpAuthorizedEntity IAuthorizedEntity authorizedEntity) {
        try {
            if (authorizedEntity == null) {
                return HttpResponse.error(HttpCode.FORBIDDEN, LogicCode.PLAYER_NOT_FOUND);
            }

            PlayerAuthentication playerAuthentication = (PlayerAuthentication) authorizedEntity;
            return loginService.loginByUserName(playerAuthentication);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResponse.error(HttpCode.INTERNAL_SERVER_ERROR, LogicCode.SERVER_ERROR);
        }
    }
}
