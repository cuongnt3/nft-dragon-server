package com.zitga.idle.pve.dao;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.idle.base.dao.ScheduledAsyncWriterMongoDAO;
import com.zitga.idle.player.model.Player;
import com.zitga.idle.pve.model.PlayerPve;
import org.mongodb.morphia.Datastore;

@BeanComponent
public class PlayerPveDAO extends ScheduledAsyncWriterMongoDAO<Long, PlayerPve> {

    public PlayerPveDAO(Datastore datastore) {
        super(PlayerPve.class, datastore);
    }

    public PlayerPve create(Player player) {
        PlayerPve collection = new PlayerPve(player);

        save(collection);
        return collection;
    }

    public PlayerPve findOrCreate(Player player) {
        PlayerPve collection = findOne(player.getPlayerId());
        if (collection != null) {
            collection.bindingWithPlayer(player);
        } else {
            collection = create(player);
        }

        return collection;
    }
}
