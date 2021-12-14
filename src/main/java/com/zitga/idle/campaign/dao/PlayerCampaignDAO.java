package com.zitga.idle.campaign.dao;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.idle.base.dao.ScheduledAsyncWriterMongoDAO;
import com.zitga.idle.campaign.model.PlayerCampaign;
import com.zitga.idle.player.model.Player;
import org.mongodb.morphia.Datastore;

@BeanComponent
public class PlayerCampaignDAO extends ScheduledAsyncWriterMongoDAO<Long, PlayerCampaign> {

    public PlayerCampaignDAO(Datastore datastore) {
        super(PlayerCampaign.class, datastore);
    }

    public PlayerCampaign create(Player player) {
        PlayerCampaign playerCampaign = new PlayerCampaign(player);

        save(playerCampaign);
        return playerCampaign;
    }

    public PlayerCampaign findOrCreate(Player player) {
        PlayerCampaign playerCampaign = findOne(player.getPlayerId());
        if (playerCampaign != null) {
            playerCampaign.bindingWithPlayer(player);
        } else {
            playerCampaign = create(player);
        }

        return playerCampaign;
    }
}