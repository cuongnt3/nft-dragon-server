package com.zitga.idle.fake.handler;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.core.annotation.http.HttpAuthorizeHandler;
import com.zitga.core.annotation.http.HttpController;
import com.zitga.core.annotation.http.HttpFilter;
import com.zitga.core.annotation.http.HttpRoute;
import com.zitga.core.annotation.http.param.HttpAuthorizedEntity;
import com.zitga.core.authentication.IAuthorizedEntity;
import com.zitga.core.constants.http.HttpMethod;
import com.zitga.core.message.http.HttpResponse;
import com.zitga.idle.base.constant.HttpResponseCode;
import com.zitga.idle.base.constant.LogicCode;
import com.zitga.idle.base.handler.BaseHttpHandler;
import com.zitga.idle.base.utils.HttpResponseUtils;
import com.zitga.idle.config.game.GameConfig;
import com.zitga.idle.enumeration.fake.FakePlayerDataType;
import com.zitga.idle.fake.constant.FakeRoute;
import com.zitga.idle.fake.service.FakePlayerDataService;
import com.zitga.idle.player.constant.PlayerConstant;
import com.zitga.idle.player.model.Player;
import com.zitga.idle.player.model.authorized.ErrorAuthorizedResult;
import com.zitga.idle.player.service.PlayerAuthorizedService;
import com.zitga.support.JsonService;

@HttpController(FakeRoute.HTTP_FAKE_ROUTE)
@BeanComponent
public class FakeResourceHandler extends BaseHttpHandler {

    @BeanField
    private GameConfig gameConfig;

    @BeanField
    private FakePlayerDataService fakeDataService;

    @BeanField
    private JsonService jsonService;

    @HttpRoute(value = FakeRoute.HTTP_RESOURCE_ROUTE, method = HttpMethod.POST)
    @HttpFilter(FakeResourceHandler.class)
    @HttpAuthorizeHandler(value = PlayerAuthorizedService.class, isCreateNewUser = false)
    public HttpResponse handle(@HttpAuthorizedEntity IAuthorizedEntity authorizedEntity) {
        if (gameConfig.getAllowFakeData()) {
            if (authorizedEntity instanceof Player) {
                try {
                    Player player = (Player) authorizedEntity;
                    String data = player.getAuthToken(PlayerConstant.PLAYER_DATA);
                    int resultCode = fakeDataService.fakeData(player, FakePlayerDataType.RESOURCE, data);
                    if (resultCode == LogicCode.SUCCESS) {
                        return HttpResponseUtils.success("");
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