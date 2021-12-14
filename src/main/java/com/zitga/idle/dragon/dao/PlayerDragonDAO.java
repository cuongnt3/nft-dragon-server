package com.zitga.idle.dragon.dao;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.idle.dragon.model.PlayerDragonCollection;
import com.zitga.idle.base.dao.ScheduledAsyncWriterMongoDAO;
import com.zitga.idle.player.model.Player;
import org.mongodb.morphia.Datastore;

@BeanComponent
public class PlayerDragonDAO extends ScheduledAsyncWriterMongoDAO<Long, PlayerDragonCollection> {

    public PlayerDragonDAO(Datastore datastore) {
        super(PlayerDragonCollection.class, datastore);
    }

    public PlayerDragonCollection create(Player player) {
        PlayerDragonCollection collection = new PlayerDragonCollection(player);

        save(collection);
        return collection;
    }

    public PlayerDragonCollection findOrCreate(Player player) {
        PlayerDragonCollection collection = findOne(player.getPlayerId());
        if (collection != null) {
            collection.bindingWithPlayer(player);
        } else {
            collection = create(player);
        }

        return collection;
    }
}
