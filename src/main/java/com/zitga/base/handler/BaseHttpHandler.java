package com.zitga.base.handler;

import com.zitga.base.constant.BasicTag;
import com.zitga.base.constant.LogicCode;
import com.zitga.base.utils.VersionUtils;
import com.zitga.bean.annotation.BeanDelayedField;
import com.zitga.config.GameConfig;
import com.zitga.core.authentication.IAuthorizedEntity;
import com.zitga.core.handler.http.support.IHttpFilter;
import com.zitga.core.message.http.ParsedHttpRequest;
import com.zitga.utils.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.SocketAddress;
import java.util.Map;

public class BaseHttpHandler implements IHttpFilter {

    protected final Logger logger = LogManager.getLogger(this.getClass());

    @BeanDelayedField
    protected GameConfig gameConfig;

    @Override
    public int filter(ParsedHttpRequest parsedRequest) {
        int validateCode = validateApiVersion(parsedRequest.getSenderAddress(), parsedRequest.getRoute(),
                parsedRequest.getHeaders());
        if (validateCode != LogicCode.SUCCESS) {
            return validateCode;
        }

        return LogicCode.SUCCESS;
    }

    @Override
    public int filter(IAuthorizedEntity entity) {
        return LogicCode.SUCCESS;
    }

    // ---------------------------------------- Helpers ----------------------------------------
    private int validateApiVersion(SocketAddress senderAddress, String route, Map<String, String> headers) {
        String apiVersionString = StringUtils.trim(headers.get(BasicTag.API_VERSION));
        if (StringUtils.isNullOrEmpty(apiVersionString)) {
            logger.debug("[{}] Invalid api version, received={}, accept={}, sender={}",
                    route, apiVersionString, gameConfig.getApiVersion(), senderAddress);
            return LogicCode.API_VERSION_INVALID;
        }

        if (!VersionUtils.validateVersion(apiVersionString)) {
            logger.debug("[{}] Invalid format api version, received={}, accept={}, sender={}",
                    route, apiVersionString, gameConfig.getApiVersion(), senderAddress);
            return LogicCode.API_VERSION_INVALID_FORMAT;
        }

        if (!VersionUtils.compareVersion(apiVersionString, gameConfig.getApiVersion())) {
            logger.debug("[{}] Invalid api version, received={}, accept={}, sender={}",
                    route, apiVersionString, gameConfig.getApiVersion(), senderAddress);
            return LogicCode.API_VERSION_WRONG;
        }

        return LogicCode.SUCCESS;
    }
}
