package com.zitga.player.service;

import com.zitga.authentication.constant.AuthTag;
import com.zitga.authentication.model.PlayerAuthentication;
import com.zitga.authentication.service.CachedAuthService;
import com.zitga.base.constant.BasicTag;
import com.zitga.base.constant.LogicCode;
import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.config.GameConfig;
import com.zitga.core.authentication.IAuthorizedEntity;
import com.zitga.core.handler.http.support.IHttpAuthenticationHandler;
import com.zitga.core.message.http.ParsedHttpRequest;
import com.zitga.player.constant.PlayerConstant;
import com.zitga.player.constant.PlayerTag;
import com.zitga.player.model.Player;
import com.zitga.player.model.authorized.ErrorAuthorizedResult;
import com.zitga.support.encryption.HashHelper;
import com.zitga.utils.StringUtils;
import com.zitga.utils.TimeUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.SocketAddress;
import java.util.Date;
import java.util.Map;

@BeanComponent
public class PlayerAuthorizedService implements IHttpAuthenticationHandler {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @BeanField
    private CachedAuthService cachedAuthService;

    @BeanField
    private CachedPlayerService cachedPlayerService;

    @BeanField
    private GameConfig gameConfig;

    private int validateHash(SocketAddress senderAddress, String route, long playerId, String data, String hash) {
        if (StringUtils.isNullOrEmpty(hash)) {
            logger.debug("[{}] Invalid hash sha256, received={}, sender={}",
                    route, hash, senderAddress);
            return LogicCode.INVALID_INBOUND_HASH;
        }

        String serverHash = HashHelper.hashSHA256(playerId + data + gameConfig.getApiVersion() +
                gameConfig.getSecretKey());
        if (!serverHash.equals(hash)) {
            logger.debug("[{}] Invalid hash sha256, received={}, expected={} sender={}",
                    route, hash, serverHash, senderAddress);
            return LogicCode.WRONG_INBOUND_HASH;
        }

        return LogicCode.SUCCESS;
    }

    private int validateToken(SocketAddress senderAddress, String route, long playerId, String token) {
        if (StringUtils.isNullOrEmpty(token)) {
            logger.debug("[{}] Invalid player token, received={}, sender={}",
                    route, token, senderAddress);
            return LogicCode.INVALID_PLAYER_TOKEN;
        }

        Player player = cachedPlayerService.getPlayer(playerId);
        if (player == null) {
            return LogicCode.PLAYER_NOT_IN_CACHE;
        }

        PlayerAuthentication authentication = player.getAuthentication();
        Date recentLoginTime = authentication.getRecentLoginTime();
        if (recentLoginTime == null) {
            return LogicCode.PLAYER_NOT_IN_CACHE;
        }

        String serverToken = HashHelper.hashSHA256(playerId + TimeUtils.toSecond(recentLoginTime) +
                gameConfig.getSecretKey());
        if (!serverToken.equals(token)) {
            logger.debug("[{}] Invalid token , received={}, sender={}", route, token, senderAddress);
            return LogicCode.WRONG_PLAYER_TOKEN;
        }

        return LogicCode.SUCCESS;
    }

    private Player getPlayer(long playerId, String data) {
        Player player = cachedPlayerService.getPlayer(playerId);
        player.setAuthToken(PlayerConstant.PLAYER_DATA, data);

        return player;
    }

    // --------------------------------------------------------------------------------
    @Override
    public IAuthorizedEntity authenticate(ParsedHttpRequest parsedHttpRequest, boolean b) {
        SocketAddress senderAddress = parsedHttpRequest.getSenderAddress();
        String route = parsedHttpRequest.getRoute();
        Map<String, String> headers = parsedHttpRequest.getHeaders();
        Map<String, Object> params = parsedHttpRequest.getParams();

        String hash = StringUtils.trim(headers.get(AuthTag.SIGN_TAG));
        String token = StringUtils.trim(headers.get(PlayerTag.PLAYER_TOKEN_TAG));

        String data = StringUtils.trim((String) params.get(PlayerTag.PLAYER_DATA_TAG));
        String playerIdString = StringUtils.trim((String) params.get(BasicTag.PLAYER_ID_TAG));

        long playerId;
        try {
            playerId = Long.parseLong(playerIdString);
        } catch (Exception e) {
            return new ErrorAuthorizedResult(LogicCode.INVALID_INPUT_DATA);
        }

        int validateHashResult = validateHash(senderAddress, route, playerId, data, hash);
        if (validateHashResult != LogicCode.SUCCESS) {
            return new ErrorAuthorizedResult(validateHashResult);
        }

        int validateResult = validateToken(senderAddress, route, playerId, token);
        if (validateResult != LogicCode.SUCCESS) {
            return new ErrorAuthorizedResult(validateResult);
        }

        return getPlayer(playerId, data);
    }
}
