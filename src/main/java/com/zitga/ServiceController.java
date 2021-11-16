package com.zitga;

import com.zitga.base.service.IdGeneratorService;
import com.zitga.executor.service.ExecutorService;
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
    private ExecutorService executorService;

    @BeanField
    private JsonService jsonService;

    @BeanField
    private IdGeneratorService idGeneratorService;

    public ServiceController() {
        instance = this;
    }

    public void init() {
        executorService.init();
    }

    // ---------------------------------------- Getters ----------------------------------------
    public JsonService getJsonService() {
        return jsonService;
    }

    public IdGeneratorService getIdGeneratorService() {
        return idGeneratorService;
    }
}