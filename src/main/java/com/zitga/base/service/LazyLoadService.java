package com.zitga.base.service;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.hatch.service.HatchService;
import com.zitga.player.model.Player;

@BeanComponent
public class LazyLoadService {

    @BeanField
    private HatchService hatchService;

    public void loadHeroSummon(Player player) {
        hatchService.loadHeroSummon(player);
    }


}