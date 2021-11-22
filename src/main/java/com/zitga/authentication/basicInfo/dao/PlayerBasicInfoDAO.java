package com.zitga.authentication.basicInfo.dao;

import com.zitga.authentication.basicInfo.model.PlayerBasicInfo;
import com.zitga.base.dao.dao.ScheduledAsyncWriterMongoDAO;
import com.zitga.bean.annotation.BeanComponent;
import com.zitga.player.model.Player;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.CountOptions;

@BeanComponent
public class PlayerBasicInfoDAO extends ScheduledAsyncWriterMongoDAO<Long, PlayerBasicInfo> {

    private final CountOptions countOneOptions;

    public PlayerBasicInfoDAO(Datastore datastore) {
        super(PlayerBasicInfo.class, datastore);

        countOneOptions = new CountOptions();
        countOneOptions.limit(1);
    }

    public PlayerBasicInfo create(Player player) {
        PlayerBasicInfo basicInfo = new PlayerBasicInfo(player);

        save(basicInfo);
        return basicInfo;
    }

    public PlayerBasicInfo findOrCreate(Player player) {
        PlayerBasicInfo basicInfo = findOne(player.getPlayerId());
        if (basicInfo != null) {
            basicInfo.bindingWithPlayer(player);
        } else {
            basicInfo = create(player);
        }

        return basicInfo;
    }
}