package com.zitga.fake.handler;

import com.zitga.authentication.handler.LoginHttpHandler;
import com.zitga.base.constant.HttpResponseCode;
import com.zitga.base.constant.LogicCode;
import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.config.GameConfig;
import com.zitga.core.annotation.http.HttpAuthorizeHandler;
import com.zitga.core.annotation.http.HttpController;
import com.zitga.core.annotation.http.HttpFilter;
import com.zitga.core.annotation.http.HttpRoute;
import com.zitga.core.annotation.http.param.HttpAuthorizedEntity;
import com.zitga.core.authentication.IAuthorizedEntity;
import com.zitga.core.constants.http.HttpMethod;
import com.zitga.core.message.http.HttpResponse;
import com.zitga.enumeration.fake.FakePlayerDataType;
import com.zitga.fake.constant.FakeRoute;
import com.zitga.fake.service.FakePlayerDataService;
import com.zitga.player.constant.PlayerConstant;
import com.zitga.player.model.Player;
import com.zitga.player.model.authorized.ErrorAuthorizedResult;
import com.zitga.player.service.PlayerAuthorizedService;
import com.zitga.resource.model.Reward;
import com.zitga.support.JsonService;

@HttpController(FakeRoute.HTTP_FAKE_ROUTE)
@BeanComponent
public class FakeResourceHandler {

    @BeanField
    private GameConfig gameConfig;

    @BeanField
    private FakePlayerDataService fakeDataService;

    @BeanField
    private JsonService jsonService;

    @HttpRoute(value = FakeRoute.HTTP_RESOURCE_ROUTE, method = HttpMethod.POST)
    @HttpFilter(LoginHttpHandler.class)
    @HttpAuthorizeHandler(value = PlayerAuthorizedService.class, isCreateNewUser = false)
    public HttpResponse handle(@HttpAuthorizedEntity IAuthorizedEntity authorizedEntity) {
        if (gameConfig.getAllowFakeData()) {
            if (authorizedEntity instanceof Player) {

                try {
                    Player player = (Player) authorizedEntity;
                    String data = player.getAuthToken(PlayerConstant.PLAYER_DATA);
                    int resultCode = fakeDataService.fakeData(player, FakePlayerDataType.RESOURCE, data);
                    if (resultCode == LogicCode.SUCCESS) {
                        return HttpResponse.ok();
                    } else {
                        return HttpResponse.error(HttpResponseCode.UNAUTHORIZED, resultCode);
                    }

                } catch (Exception e) {
                    return HttpResponse.error(HttpResponseCode.BAD_REQUEST, LogicCode.INVALID_INPUT_DATA);
                }
            } else {
                ErrorAuthorizedResult error = (ErrorAuthorizedResult) authorizedEntity;
                return HttpResponse.error(HttpResponseCode.UNAUTHORIZED, error.getResultCode());
            }
        } else {
            return HttpResponse.error(HttpResponseCode.FORBIDDEN, LogicCode.FAKE_FORBIDDEN);
        }
    }
}