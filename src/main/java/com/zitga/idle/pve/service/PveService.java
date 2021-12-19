package com.zitga.idle.pve.service;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.idle.player.model.IAsyncPlayerDataManageable;
import com.zitga.idle.player.model.Player;
import com.zitga.idle.pve.dao.PlayerPveDAO;
import com.zitga.idle.pve.model.PlayerPve;

@BeanComponent
public class PveService implements IAsyncPlayerDataManageable {

    @BeanField
    private PlayerPveDAO playerPveDAO;

    @Override
    public void createPlayerData(Player player) {
        PlayerPve playerPve = playerPveDAO.create(player);
        player.setPlayerPve(playerPve);
    }

    @Override
    public void loadPlayerData(Player player) {
        PlayerPve playerPve = playerPveDAO.findOrCreate(player);
        player.setPlayerPve(playerPve);
    }


}
