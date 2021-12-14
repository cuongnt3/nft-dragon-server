package com.zitga.idle;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.idle.base.service.IdGeneratorService;
import com.zitga.idle.base.service.LazyLoadService;
import com.zitga.idle.config.game.GameConfig;
import com.zitga.idle.enumeration.lua.LuaRunMode;
import com.zitga.idle.executor.service.ExecutorService;
import com.zitga.idle.hero.service.HeroDataService;
import com.zitga.idle.lua.service.LuaDynamicRequireGenerator;
import com.zitga.idle.lua.service.LuaRequireGenerator;
import com.zitga.idle.lua.service.LuaServiceManager;
import com.zitga.idle.resource.service.RewardService;
import com.zitga.idle.resource.service.RewardValidatorService;
import com.zitga.support.JsonService;

@BeanComponent
public class ServiceController {

    private static ServiceController instance;

    public static ServiceController instance() {
        return instance;
    }

    // ---------------------------------------- Services ----------------------------------------
    @BeanField
    private IdGeneratorService idGeneratorService;

    @BeanField
    private HeroDataService heroDataService;

    @BeanField
    private LazyLoadService lazyLoadService;

    @BeanField
    private RewardService rewardService;

    @BeanField
    private JsonService jsonService;

    @BeanField
    private RewardValidatorService rewardValidatorService;

    @BeanField
    private LuaServiceManager luaServiceManager;

    @BeanField
    private ExecutorService executorService;

    public ServiceController(GameConfig gameConfig, LuaServiceManager luaServiceManager,
                             LuaRequireGenerator luaRequireGenerator,
                             LuaDynamicRequireGenerator luaDynamicRequireGenerator) throws Exception {
        instance = this;

        if (gameConfig.getLuaRunMode().getValue() < LuaRunMode.FAST.getValue()) {
            luaRequireGenerator.start();
            luaRequireGenerator.generateFile();
        }

        luaServiceManager.init();
        luaDynamicRequireGenerator.start();
    }

    public void init() {
        executorService.init();
    }

    // ---------------------------------------- Getters ----------------------------------------
    public IdGeneratorService getIdGeneratorService() {
        return idGeneratorService;
    }

    public RewardService getRewardService() {
        return rewardService;
    }

    public RewardValidatorService getRewardValidatorService() {
        return rewardValidatorService;
    }

    public LuaServiceManager getLuaServiceManager() {
        return luaServiceManager;
    }

    public HeroDataService getHeroDataService() {
        return heroDataService;
    }

    public LazyLoadService getLazyLoadService() {
        return lazyLoadService;
    }

    public JsonService getJsonService() {
        return jsonService;
    }
}