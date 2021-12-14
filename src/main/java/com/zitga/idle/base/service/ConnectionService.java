package com.zitga.idle.base.service;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.idle.authentication.service.CachedAuthService;
import com.zitga.idle.publisher.service.PublisherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@BeanComponent
public class ConnectionService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeanField
    private CachedAuthService cachedAuthService;

    @BeanField
    private PublisherService publisherService;
}