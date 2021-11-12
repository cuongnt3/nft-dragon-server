package com.zitga.authentication.service;

import com.zitga.authentication.constant.AuthConstant;
import com.zitga.authentication.dao.PlayerAuthenticationDAO;
import com.zitga.authentication.model.PlayerAuthentication;
import com.zitga.authentication.model.message.RegisterResult;
import com.zitga.base.constant.LogicCode;
import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.config.GameConfig;
import com.zitga.support.encryption.HashHelper;
import com.zitga.utils.NameValidator;
import com.zitga.utils.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.SocketAddress;

@BeanComponent
public class RegisterService {

    protected final Logger logger = LogManager.getLogger(this.getClass());

    @BeanField
    private GameConfig gameConfig;

    @BeanField
    private PlayerAuthenticationDAO authenticationDAO;

    @BeanField
    private CachedAuthService cachedAuthService;

    public RegisterResult register(SocketAddress address,
                                   String userName,
                                   String password,
                                   String sign) {
        RegisterResult result = new RegisterResult();

        if (StringUtils.isNullOrEmpty(password)) {
            return result.withCode(LogicCode.INVALID_INPUT_DATA);
        }

        if (StringUtils.isNullOrEmpty(userName)) {
            return result.withCode(LogicCode.INVALID_INPUT_DATA);
        }

        password = StringUtils.trim(password);
        userName = StringUtils.trim(userName);

        String hashedSign = HashHelper.hashSHA256(userName + password +
                gameConfig.getApiVersion() + gameConfig.getSecretKey());

        if (!hashedSign.equalsIgnoreCase(sign)) {
            logger.error("[REGISTER] [WRONG_HASH] Plain sign: {}, Hashed sign: {}",
                    sign, hashedSign);
            return result.withCode(LogicCode.WRONG_INBOUND_HASH);
        }

        if (userName.length() < AuthConstant.USER_NAME_LENGTH_MIN || userName.length() > AuthConstant.USER_NAME_LENGTH_MAX) {
            return result.withCode(LogicCode.AUTH_USERNAME_FORMAT_INVALID);
        }

        if (!NameValidator.isValidAccountName(userName)) {
            return result.withCode(LogicCode.AUTH_USERNAME_INVALID);
        }

        PlayerAuthentication playerAuth = cachedAuthService.getFromCacheByUserName(userName, password);
        if (playerAuth != null) {
            return result.withCode(LogicCode.AUTH_USERNAME_ALREADY_USED);
        }

        playerAuth = authenticationDAO.findWithUserName(userName);
        if (playerAuth != null) {
            return result.withCode(LogicCode.AUTH_USERNAME_ALREADY_USED);
        }

        // Create player if not existed
        playerAuth = authenticationDAO.createAuthWithUserName(userName, password);
        if (playerAuth == null) {
            return result.withCode(LogicCode.INVALID_INPUT_DATA);
        }

        onRegister(result, playerAuth);
        return result.withCode(LogicCode.SUCCESS);
    }

    public void onRegister(RegisterResult result, PlayerAuthentication playerAuth) {
        result.setPlayerId(playerAuth.getId());

        logger.debug("Player = {} REGISTER SUCCESS ", playerAuth.getId());
    }
}
