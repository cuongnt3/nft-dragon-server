package com.zitga.hatch.dao;

import com.zitga.base.dao.dao.ScheduledAsyncWriterMongoDAO;
import com.zitga.bean.annotation.BeanComponent;
import com.zitga.hatch.model.PlayerDragonHatch;
import com.zitga.player.model.Player;
import org.mongodb.morphia.Datastore;

@BeanComponent
public class PlayerDragonHatchDAO extends ScheduledAsyncWriterMongoDAO<Long, PlayerDragonHatch> {

    public PlayerDragonHatchDAO(Datastore datastore) {
        super(PlayerDragonHatch.class, datastore);
    }

    public PlayerDragonHatch create(Player player) {
        PlayerDragonHatch dragonHatch = new PlayerDragonHatch(player);

        save(dragonHatch);
        return dragonHatch;
    }

    public PlayerDragonHatch findOrCreate(Player player) {
        PlayerDragonHatch dragonHatch = findOne(player.getPlayerId());
        if (dragonHatch != null) {
            dragonHatch.bindingWithPlayer(player);
        } else {
            dragonHatch = create(player);
        }

        return dragonHatch;
    }
}