package com.zitga.idle.player.handler;

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
import com.zitga.idle.authentication.handler.LoginHttpHandler;
import com.zitga.idle.base.constant.HttpResponseCode;
import com.zitga.idle.base.constant.LogicCode;
import com.zitga.idle.base.utils.HttpResponseUtils;
import com.zitga.idle.config.game.GameConfig;
import com.zitga.idle.player.constant.PlayerConstant;
import com.zitga.idle.player.constant.PlayerRoute;
import com.zitga.idle.player.model.Player;
import com.zitga.idle.player.model.authorized.ErrorAuthorizedResult;
import com.zitga.idle.player.model.message.PlayerDataGetResult;
import com.zitga.idle.player.service.PlayerAuthorizedService;
import com.zitga.idle.player.service.PlayerDataGetService;
import com.zitga.support.JsonService;

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