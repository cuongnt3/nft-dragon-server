package com.zitga.idle.battleInfo.dao;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.idle.base.constant.DatabaseConstant;
import com.zitga.idle.base.dao.ScheduledAsyncWriterMongoDAO;
import com.zitga.idle.battleInfo.constant.BattleInfoTag;
import com.zitga.idle.battleInfo.model.PlayerBattleInfo;
import com.zitga.idle.player.model.Player;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import java.util.Collection;
import java.util.List;

@BeanComponent
public class PlayerBattleInfoDAO extends ScheduledAsyncWriterMongoDAO<Long, PlayerBattleInfo> {

    public PlayerBattleInfoDAO(Datastore datastore) {
        super(PlayerBattleInfo.class, datastore);
    }

    public PlayerBattleInfo create(Player player) {
        PlayerBattleInfo battleInfo = new PlayerBattleInfo(player);

        save(battleInfo);
        return battleInfo;
    }

    public PlayerBattleInfo findOrCreate(Player player) {
        PlayerBattleInfo battleInfo = findOne(player.getPlayerId());
        if (battleInfo != null) {
            battleInfo.bindingWithPlayer(player);
        } else {
            battleInfo = create(player);
        }

        return battleInfo;
    }

    public PlayerBattleInfo findFriendBattleInfo(long friendId) {
        Query<PlayerBattleInfo> query = datastore.createQuery(PlayerBattleInfo.class);
        query.field(DatabaseConstant.ID_TAG).equal(friendId);

        return query.get();
    }

    public void saveActiveLinkingGroup(long friend, List<Integer> inactiveGroups) {
        Query<PlayerBattleInfo> query = datastore.createQuery(PlayerBattleInfo.class);
        query.field(DatabaseConstant.ID_TAG).equal(friend);

        UpdateOperations<PlayerBattleInfo> updateOperations = datastore.createUpdateOperations(PlayerBattleInfo.class);

        for (int group : inactiveGroups) {
            String tag = String.format("%s.%s", BattleInfoTag.LINKING_GROUP_TAG, group);
            updateOperations.unset(tag);
        }

        executeUpdate(query, updateOperations);
    }

    public void saveContributeHeroes(long playerId, Collection<Long> contributeHeroes) {
        Query<PlayerBattleInfo> query = datastore.createQuery(PlayerBattleInfo.class);
        query.field(DatabaseConstant.ID_TAG).equal(playerId);

        UpdateOperations<PlayerBattleInfo> updateOperations = datastore.createUpdateOperations(PlayerBattleInfo.class);
        updateOperations.set(BattleInfoTag.DOMAIN_CONTRIBUTE_HERO_LIST_TAG, contributeHeroes);

        executeUpdate(query, updateOperations);
    }
}