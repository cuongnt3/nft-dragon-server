package com.zitga.idle.base.service;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.idle.player.model.Player;
import com.zitga.idle.summon.service.SummonService;

@BeanComponent
public class LazyLoadService {

    @BeanField
    private SummonService summonService;

    public void loadDragonSummon(Player player) {
        summonService.loadDragonSummon(player);
    }
}