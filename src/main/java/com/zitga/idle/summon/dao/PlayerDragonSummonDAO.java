package com.zitga.idle.summon.dao;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.idle.base.dao.ScheduledAsyncWriterMongoDAO;
import com.zitga.idle.player.model.Player;
import com.zitga.idle.summon.model.PlayerDragonSummon;
import org.mongodb.morphia.Datastore;

@BeanComponent
public class PlayerDragonSummonDAO extends ScheduledAsyncWriterMongoDAO<Long, PlayerDragonSummon> {

    public PlayerDragonSummonDAO(Datastore datastore) {
        super(PlayerDragonSummon.class, datastore);
    }

    public PlayerDragonSummon create(Player player) {
        PlayerDragonSummon dragonHatch = new PlayerDragonSummon(player);

        save(dragonHatch);
        return dragonHatch;
    }

    public PlayerDragonSummon findOrCreate(Player player) {
        PlayerDragonSummon dragonHatch = findOne(player.getPlayerId());
        if (dragonHatch != null) {
            dragonHatch.bindingWithPlayer(player);
        } else {
            dragonHatch = create(player);
        }

        return dragonHatch;
    }
}