package com.zitga.dragon.dao;

import com.zitga.base.dao.dao.ScheduledAsyncWriterMongoDAO;
import com.zitga.bean.annotation.BeanComponent;
import com.zitga.dragon.model.PlayerDragonCollection;
import com.zitga.player.model.Player;
import org.mongodb.morphia.Datastore;

@BeanComponent
public class PlayerDragonDAO extends ScheduledAsyncWriterMongoDAO<Long, PlayerDragonCollection> {

    public PlayerDragonDAO(Datastore datastore) {
        super(PlayerDragonCollection.class, datastore);
    }

    public PlayerDragonCollection create(Player player) {
        PlayerDragonCollection heroCollection = new PlayerDragonCollection(player);

        save(heroCollection);
        return heroCollection;
    }

    public PlayerDragonCollection findOrCreate(Player player) {
        PlayerDragonCollection heroCollection = findOne(player.getPlayerId());
        if (heroCollection != null) {
            heroCollection.bindingWithPlayer(player);
        } else {
            heroCollection = create(player);
        }

        return heroCollection;
    }
}
