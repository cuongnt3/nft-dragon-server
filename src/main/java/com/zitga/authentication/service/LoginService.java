package com.zitga.authentication.service;

import com.zitga.authentication.constant.AuthConstant;
import com.zitga.authentication.dao.PlayerAuthenticationDAO;
import com.zitga.authentication.model.PlayerAuthentication;
import com.zitga.authentication.model.message.LoginResult;
import com.zitga.base.constant.LogicCode;
import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.config.GameConfig;
import com.zitga.core.constants.http.HttpCode;
import com.zitga.core.message.http.HttpResponse;
import com.zitga.player.model.Player;
import com.zitga.player.service.PlayerService;
import com.zitga.support.JsonService;
import com.zitga.support.encryption.HashHelper;
import com.zitga.utils.GZipUtils;
import com.zitga.utils.StringUtils;
import com.zitga.utils.TimeUtils;

@BeanComponent
public class LoginService {

    @BeanField
    private CachedAuthService cachedAuthService;

    @BeanField
    private GameConfig gameConfig;

    @BeanField
    private JsonService jsonService;

    @BeanField
    private PlayerAuthenticationDAO authDAO;

    @BeanField
    private PlayerService playerService;

    public HttpResponse loginByUserName(PlayerAuthentication playerAuth) throws Exception {
        LoginResult result = new LoginResult();

        String password = playerAuth.getAuthToken(AuthConstant.HASH_PASSWORD);
        if (StringUtils.isNullOrEmpty(password)) {
            return HttpResponse.error(HttpCode.FORBIDDEN, LogicCode.INVALID_INPUT_DATA);
        }

        if (!playerAuth.checkPassword()) {
            return HttpResponse.error(HttpCode.FORBIDDEN, LogicCode.AUTH_PASSWORD_NOT_MATCH);
        }

        if (!playerAuth.checkValidDevice()) {
            return HttpResponse.error(HttpCode.FORBIDDEN, LogicCode.AUTH_ALREADY_LOGIN_OTHER_DEVICE);
        }

        playerAuth.updateLastDevicePlayed();
        authDAO.save(playerAuth);

        playerService.loadPlayer(playerAuth);

        result.setPlayerId(playerAuth.getId());
        result.setData(generateNewToken(playerAuth));

        String data = jsonService.writeValueAsString(result);
        String zipData = GZipUtils.compress(data);
        return HttpResponse.ok(zipData);
    }

    private String generateNewToken(PlayerAuthentication authentication) {
        long recentLogin = TimeUtils.toSecond(authentication.getRecentLoginTime());
        return HashHelper.hashSHA256(authentication.getId() + recentLogin + gameConfig.getSecretKey());
    }
}