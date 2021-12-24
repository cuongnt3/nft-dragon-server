package com.zitga.idle.pve.hander;

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
import com.zitga.idle.player.constant.PlayerConstant;
import com.zitga.idle.player.model.Player;
import com.zitga.idle.player.model.authorized.ErrorAuthorizedResult;
import com.zitga.idle.player.service.PlayerAuthorizedService;
import com.zitga.idle.pve.constant.PveRoute;
import com.zitga.idle.pve.model.message.DefenderTeamGetResult;
import com.zitga.idle.pve.service.PveService;
import com.zitga.support.JsonService;

@HttpController(PveRoute.HTTP_PVE_ROUTE)
@BeanComponent
public class PveDefenderGetHandler {

    @BeanField
    private PveService pveService;

    @BeanField
    private JsonService jsonService;

    @HttpRoute(value = PveRoute.GET_FORMATION_ROUTE, method = HttpMethod.GET)
    @HttpFilter(LoginHttpHandler.class)
    @HttpAuthorizeHandler(value = PlayerAuthorizedService.class, isCreateNewUser = false)
    public HttpResponse handle(@HttpAuthorizedEntity IAuthorizedEntity authorizedEntity) {
        if (authorizedEntity instanceof Player) {
            try {
                Player player = (Player) authorizedEntity;
                String data = player.getAuthToken(PlayerConstant.PLAYER_DATA);
                int stage = Integer.parseInt(data);

                DefenderTeamGetResult result = pveService.getDefenderFormation(player, stage);
                if (result.getResultCode() == LogicCode.SUCCESS) {
                    return HttpResponseUtils.success(jsonService.writeValueAsString(result));
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
