package com.zitga.summon.service;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.summon.dao.PlayerDragonSummonDAO;
import com.zitga.summon.model.PlayerDragonSummon;
import com.zitga.player.model.Player;

@BeanComponent
public class SummonService {

    @BeanField
    private SummonDataService summonDataService;

    @BeanField
    private PlayerDragonSummonDAO dragonHatchDAO;

    public void loadHeroSummon(Player player) {
        PlayerDragonSummon heroSummon = dragonHatchDAO.findOrCreate(player);
        player.setDragonSummon(heroSummon);
    }

    public void saveHeroSummon(Player player) {
        dragonHatchDAO.save(player.getOrLoadDragonSummon());
    }

}