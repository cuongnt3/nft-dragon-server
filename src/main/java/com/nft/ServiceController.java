package com.nft;

import com.nft.base.service.IdManagerService;
import com.nft.executor.service.ExecutorService;
import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.support.JsonService;

@BeanComponent
public class ServiceController {

    private static ServiceController instance;

    public static ServiceController instance() {
        return instance;
    }

    // ---------------------------------------- Services ----------------------------------------
    @BeanField
    private IdManagerService idManagerService;

    @BeanField
    private ExecutorService executorService;

    @BeanField
    private JsonService jsonService;

    public ServiceController() {
        instance = this;
    }

    public void init() {
        executorService.init();
    }

    // ---------------------------------------- Getters ----------------------------------------
    public IdManagerService getIdManager() {
        return idManagerService;
    }

    public JsonService getJsonService() {
        return jsonService;
    }
}