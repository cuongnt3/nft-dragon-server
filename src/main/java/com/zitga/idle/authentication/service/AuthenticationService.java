package com.zitga.idle.authentication.service;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.core.authentication.IAuthorizedEntity;
import com.zitga.core.handler.http.support.IHttpAuthenticationHandler;
import com.zitga.core.message.http.ParsedHttpRequest;
import com.zitga.idle.authentication.constant.AuthConstant;
import com.zitga.idle.authentication.constant.AuthTag;
import com.zitga.idle.authentication.dao.PlayerAuthenticationDAO;
import com.zitga.idle.authentication.model.PlayerAuthentication;
import com.zitga.idle.base.constant.LogicCode;
import com.zitga.idle.config.game.GameConfig;
import com.zitga.idle.player.model.authorized.ErrorAuthorizedResult;
import com.zitga.support.encryption.HashHelper;
import com.zitga.utils.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.SocketAddress;
import java.util.Map;

@BeanComponent
public class AuthenticationService implements IHttpAuthenticationHandler {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @BeanField
    private CachedAuthService cachedAuthService;

    @BeanField
    private PlayerAuthenticationDAO authenticationDAO;

    @BeanField
    private LoginService loginService;

    @BeanField
    private GameConfig gameConfig;

    private int validateHash(SocketAddress senderAddress, String route, String username, String hashedPassword,
                             String deviceId, String hash) {
        if (StringUtils.isNullOrEmpty(hash)) {
            logger.debug("[{}] Invalid hash sha256, received={}, sender={}",
                    route, hash, senderAddress);
            return LogicCode.INVALID_INBOUND_HASH;
        }

        username = StringUtils.trim(username);
        hashedPassword = StringUtils.trim(hashedPassword);
        if (StringUtils.isNullOrEmpty(username) || StringUtils.isNullOrEmpty(hashedPassword)) {
            return LogicCode.INVALID_INPUT_DATA;
        }

        String serverHash = HashHelper.hashSHA256(username + hashedPassword + deviceId + gameConfig.getApiVersion() +
                gameConfig.getSecretKey());
        if (!serverHash.equals(hash)) {
            logger.debug("[{}] Invalid hash sha256, received={}, expected={} sender={}",
                    route, hash, serverHash, senderAddress);
            return LogicCode.WRONG_INBOUND_HASH;
        }

        return LogicCode.SUCCESS;
    }

    private PlayerAuthentication getPlayer(String userName, String password, String deviceId) {
        userName = StringUtils.trim(userName);
        password = StringUtils.trim(password);

        PlayerAuthentication playerAuth = cachedAuthService.getFromCacheByUserName(userName, password);
        if (playerAuth == null) {
            playerAuth = authenticationDAO.findWithUserName(userName);
            if (playerAuth == null) {
                return null;
            }
        }

        playerAuth.setAuthToken(AuthConstant.HASH_PASSWORD, password);
        playerAuth.setAuthToken(AuthConstant.DEVICE_ID, deviceId);
        return playerAuth;
    }

    // --------------------------------------------------------------------------------
    @Override
    public IAuthorizedEntity authenticate(ParsedHttpRequest parsedHttpRequest, boolean b) {
        SocketAddress senderAddress = parsedHttpRequest.getSenderAddress();
        String route = parsedHttpRequest.getRoute();
        Map<String, String> headers = parsedHttpRequest.getHeaders();
        Map<String, Object> params = parsedHttpRequest.getParams();

        String userName = StringUtils.trim((String) params.get(AuthTag.USER_NAME_TAG));
        String password = StringUtils.trim((String) params.get(AuthTag.PASSWORD_TAG));
        String deviceId = StringUtils.trim((String) params.get(AuthTag.DEVICE_ID_TAG));

        String hash = StringUtils.trim(headers.get(AuthTag.SIGN_TAG));

        int validateHashResult = validateHash(senderAddress, route, userName, password, deviceId, hash);
        if (validateHashResult != LogicCode.SUCCESS) {
            return new ErrorAuthorizedResult(validateHashResult);
        }

        return getPlayer(userName, password, deviceId);
    }
}
