package com.zitga.idle.statistics.dao;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.idle.base.dao.ScheduledAsyncWriterMongoDAO;
import com.zitga.idle.player.model.Player;
import com.zitga.idle.statistics.model.PlayerStatistics;
import org.mongodb.morphia.Datastore;

@BeanComponent
public class PlayerStatisticsDAO extends ScheduledAsyncWriterMongoDAO<Long, PlayerStatistics> {

    public PlayerStatisticsDAO(Datastore datastore) {
        super(PlayerStatistics.class, datastore);
    }

    public PlayerStatistics create(Player player) {
        PlayerStatistics statistics = new PlayerStatistics(player);

        save(statistics);
        return statistics;
    }

    public PlayerStatistics findOrCreate(Player player) {
        PlayerStatistics statistics = findOne(player.getPlayerId());
        if (statistics != null) {
            statistics.bindingWithPlayer(player);
        } else {
            statistics = create(player);
        }

        return statistics;
    }
}