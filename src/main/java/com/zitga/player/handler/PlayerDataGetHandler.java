package com.zitga.player.handler;

import com.zitga.authentication.handler.LoginHttpHandler;
import com.zitga.base.constant.HttpResponseCode;
import com.zitga.base.constant.LogicCode;
import com.zitga.base.utils.HttpResponseUtils;
import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanDelayedMethod;
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
import com.zitga.player.constant.PlayerConstant;
import com.zitga.player.constant.PlayerRoute;
import com.zitga.player.model.Player;
import com.zitga.player.model.authorized.ErrorAuthorizedResult;
import com.zitga.player.model.message.PlayerDataGetResult;
import com.zitga.player.service.PlayerAuthorizedService;
import com.zitga.player.service.PlayerDataGetService;
import com.zitga.support.JsonService;
import com.zitga.utils.BitUtils;
import com.zitga.utils.GZipUtils;

@HttpController(PlayerRoute.HTTP_PLAYER_ROUTE)
@BeanComponent
public class PlayerDataGetHandler {

    @BeanField
    private GameConfig gameConfig;

    @BeanField
    private PlayerDataGetService playerDataGetService;

    @BeanField
    private JsonService jsonService;

    @HttpRoute(value = PlayerRoute.HTTP_PLAYER_DATA_GET, method = HttpMethod.GET)
    @HttpFilter(LoginHttpHandler.class)
    @HttpAuthorizeHandler(value = PlayerAuthorizedService.class, isCreateNewUser = false)
    public HttpResponse handle(@HttpAuthorizedEntity IAuthorizedEntity authorizedEntity) {
        if (authorizedEntity instanceof Player) {
            try {
                Player player = (Player) authorizedEntity;
                String data = player.getAuthToken(PlayerConstant.PLAYER_DATA);
                long bitmask = Long.parseLong(data);
                PlayerDataGetResult result = playerDataGetService.serializeData(player, bitmask);
                return HttpResponseUtils.success(jsonService.writeValueAsString(result));

            } catch (Exception e) {
                return HttpResponse.error(HttpResponseCode.BAD_REQUEST, LogicCode.INVALID_INPUT_DATA);
            }
        } else {
            ErrorAuthorizedResult error = (ErrorAuthorizedResult) authorizedEntity;
            return HttpResponse.error(HttpResponseCode.UNAUTHORIZED, error.getResultCode());
        }
    }
}