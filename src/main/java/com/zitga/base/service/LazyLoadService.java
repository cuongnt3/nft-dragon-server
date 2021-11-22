package com.zitga.base.service;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.player.model.Player;
import com.zitga.summon.service.SummonService;

@BeanComponent
public class LazyLoadService {

    @BeanField
    private SummonService summonService;

    public void loadDragonSummon(Player player) {
        summonService.loadDragonSummon(player);
    }
}