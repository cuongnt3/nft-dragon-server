package com.zitga.idle.authentication.dao;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.idle.authentication.constant.AuthTag;
import com.zitga.idle.authentication.model.PlayerAuthentication;
import com.zitga.idle.base.service.IdGeneratorService;
import com.zitga.mongo.repository.AbstractMongoRepository;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.FindOptions;
import org.mongodb.morphia.query.Query;

import java.util.List;

@BeanComponent
public class PlayerAuthenticationDAO extends AbstractMongoRepository<Long, PlayerAuthentication> {

    @BeanField
    private IdGeneratorService idGeneratorService;

    private FindOptions findOneOptions;

    public PlayerAuthenticationDAO(Datastore datastore) {
        super(PlayerAuthentication.class, datastore);

        findOneOptions = new FindOptions();
        findOneOptions.limit(1);
    }

    // ---------------------------------------- Find Auth ----------------------------------------
    public PlayerAuthentication findWithUserName(String userName) {
        Query<PlayerAuthentication> query = datastore.createQuery(PlayerAuthentication.class);
        query.field(AuthTag.USER_NAME_TAG).equal(userName);

        List<PlayerAuthentication> auths = query.asList(findOneOptions);
        if (!auths.isEmpty()) {
            return auths.get(0);
        }

        return null;
    }

    public PlayerAuthentication createAuthWithUserName(String userName, String hashedPassword) {
        long playerId = idGeneratorService.generatePlayerId();

        PlayerAuthentication auth = new PlayerAuthentication(userName, hashedPassword, playerId);
        save(auth);
        return auth;
    }
}