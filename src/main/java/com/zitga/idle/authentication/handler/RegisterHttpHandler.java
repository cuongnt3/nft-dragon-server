package com.zitga.idle.authentication.handler;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.core.annotation.http.HttpController;
import com.zitga.core.annotation.http.HttpFilter;
import com.zitga.core.annotation.http.HttpRoute;
import com.zitga.core.annotation.http.param.HttpParam;
import com.zitga.core.annotation.http.param.HttpSenderAddress;
import com.zitga.core.constants.http.HttpCode;
import com.zitga.core.constants.http.HttpMethod;
import com.zitga.core.message.http.HttpResponse;
import com.zitga.idle.authentication.constant.AuthRoute;
import com.zitga.idle.authentication.constant.AuthTag;
import com.zitga.idle.authentication.model.message.RegisterResult;
import com.zitga.idle.authentication.service.RegisterService;
import com.zitga.idle.base.constant.LogicCode;
import com.zitga.idle.base.handler.BaseHttpHandler;
import com.zitga.idle.utils.GZipUtils;
import com.zitga.support.JsonService;

import java.net.SocketAddress;

@HttpController(AuthRoute.HTTP_AUTH_ROUTE)
@BeanComponent
public class RegisterHttpHandler extends BaseHttpHandler {

    @BeanField
    private RegisterService registerService;

    @BeanField
    private JsonService jsonService;

    @HttpRoute(value = AuthRoute.HTTP_REGISTER_ROUTE, method = HttpMethod.POST)
    @HttpFilter(RegisterHttpHandler.class)
    public HttpResponse register(@HttpSenderAddress SocketAddress senderAddress,
                                 @HttpParam(AuthTag.USER_NAME_TAG) String username,
                                 @HttpParam(AuthTag.PASSWORD_TAG) String password,
                                 @HttpParam(AuthTag.SIGN_TAG) String sign) {
        try {
            logger.debug("[REGISTER] register data = {} - {}", username, password);

            RegisterResult result = registerService.register(senderAddress, username, password, sign);
            if (result.getResultCode() != LogicCode.SUCCESS) {
                logger.debug("[REGISTER] [FAIL] register data = {} - {}", username, password);
                return HttpResponse.error(HttpCode.BAD_REQUEST, result.getResultCode());
            }

            String responseData = jsonService.writeValueAsString(result);
            return HttpResponse.ok(GZipUtils.compress(responseData));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResponse.error(HttpCode.INTERNAL_SERVER_ERROR, LogicCode.SERVER_ERROR);
        }
    }
}
