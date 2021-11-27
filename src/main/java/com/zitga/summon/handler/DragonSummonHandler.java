package com.zitga.summon.handler;

import com.zitga.authentication.handler.LoginHttpHandler;
import com.zitga.base.constant.HttpResponseCode;
import com.zitga.base.constant.LogicCode;
import com.zitga.base.utils.HttpResponseUtils;
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
import com.zitga.player.constant.PlayerConstant;
import com.zitga.player.model.Player;
import com.zitga.player.model.authorized.ErrorAuthorizedResult;
import com.zitga.player.service.PlayerAuthorizedService;
import com.zitga.summon.constant.SummonRoute;
import com.zitga.summon.model.message.SummonResult;
import com.zitga.summon.service.SummonService;
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
        if (gameConfig.getAllowFakeData()) {
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
        } else {
            return HttpResponse.error(HttpResponseCode.FORBIDDEN, LogicCode.FAKE_FORBIDDEN);
        }
    }
}
