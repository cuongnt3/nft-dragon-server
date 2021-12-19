package com.zitga.idle.summon.handler;

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
import com.zitga.idle.player.model.Player;
import com.zitga.idle.player.model.authorized.ErrorAuthorizedResult;
import com.zitga.idle.player.service.PlayerAuthorizedService;
import com.zitga.idle.summon.constant.SummonRoute;
import com.zitga.idle.summon.model.message.SummonResult;
import com.zitga.idle.summon.service.SummonService;
import com.zitga.support.JsonService;

@HttpController(SummonRoute.HTTP_DRAGON_ROUTE)
@BeanComponent
public class DragonSummonHandler {

    @BeanField
    private GameConfig gameConfig;

    @BeanField
    private SummonService summonService;

    @BeanField
    private JsonService jsonService;

    @HttpRoute(value = SummonRoute.HTTP_DRAGON_SUMMON_ROUTE, method = HttpMethod.POST)
    @HttpFilter(LoginHttpHandler.class)
    @HttpAuthorizeHandler(value = PlayerAuthorizedService.class, isCreateNewUser = false)
    public HttpResponse handle(@HttpAuthorizedEntity IAuthorizedEntity authorizedEntity) {
        if (authorizedEntity instanceof Player) {
            try {
                Player player = (Player) authorizedEntity;
                String data = player.getAuthToken(PlayerConstant.PLAYER_DATA);
                long eggInventoryId = Long.parseLong(data);
                SummonResult result = summonService.summonDragon(player, eggInventoryId);
                if (result.getResultCode() == LogicCode.SUCCESS) {
                    String content = jsonService.writeValueAsString(result);
                    return HttpResponseUtils.success(content);
                } else {
                    return HttpResponse.error(HttpResponseCode.UNAUTHORIZED, result.getResultCode());
                }

            } catch (Exception e) {
                return HttpResponse.error(HttpResponseCode.BAD_REQUEST, LogicCode.INVALID_INPUT_DATA);
            }
        } else {
            ErrorAuthorizedResult error = (ErrorAuthorizedResult) authorizedEntity;
            return HttpResponse.error(HttpResponseCode.UNAUTHORIZED, error.getResultCode());
        }
    }
}
