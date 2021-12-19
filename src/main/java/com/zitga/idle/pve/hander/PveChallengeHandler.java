package com.zitga.idle.pve.hander;

import com.zitga.bean.annotation.BeanComponent;
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
import com.zitga.idle.player.constant.PlayerConstant;
import com.zitga.idle.player.model.Player;
import com.zitga.idle.player.model.authorized.ErrorAuthorizedResult;
import com.zitga.idle.player.service.PlayerAuthorizedService;
import com.zitga.idle.pve.constant.PveRoute;

@HttpController(PveRoute.HTTP_PVE_ROUTE)
@BeanComponent
public class PveChallengeHandler {

    @HttpRoute(value = PveRoute.HTTP_CHALLENGE_ROUTE, method = HttpMethod.POST)
    @HttpFilter(LoginHttpHandler.class)
    @HttpAuthorizeHandler(value = PlayerAuthorizedService.class, isCreateNewUser = false)
    public HttpResponse handle(@HttpAuthorizedEntity IAuthorizedEntity authorizedEntity) {
        if (authorizedEntity instanceof Player) {

            try {
                Player player = (Player) authorizedEntity;
//                String data = player.getAuthToken(PlayerConstant.PLAYER_DATA);
//                int resultCode = summonService.incubateEgg(player, eggInventoryId);
//                if (resultCode == LogicCode.SUCCESS) {
//                    return HttpResponseUtils.success("");
//                } else {
//                    return HttpResponse.error(HttpResponseCode.UNAUTHORIZED, resultCode);
//                }
                return HttpResponse.ok();

            } catch (Exception e) {
                return HttpResponse.error(HttpResponseCode.BAD_REQUEST, LogicCode.INVALID_INPUT_DATA);
            }
        } else {
            ErrorAuthorizedResult error = (ErrorAuthorizedResult) authorizedEntity;
            return HttpResponse.error(HttpResponseCode.UNAUTHORIZED, error.getResultCode());
        }
    }
}
