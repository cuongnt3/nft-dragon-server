package com.zitga.base.service;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.dragon.service.DragonService;
import com.zitga.summon.service.SummonService;
import com.zitga.player.model.Player;

@BeanComponent
public class LazyLoadService {

    @BeanField
    private SummonService summonService;

    @BeanField
    private DragonService dragonService;

    public void loadDragonSummon(Player player) {
        summonService.loadDragonSummon(player);
    }

    public void loadDragonCollection(Player player){
        dragonService.loadDragonCollection(player);
    }
}