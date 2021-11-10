package com.nft.authentication.dao;

import com.nft.authentication.model.data.AdminOpCodeAction;
import com.zitga.mongo.repository.AbstractMongoRepository;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;

import java.util.List;

public class AdminOpCodeActionDAO extends AbstractMongoRepository<ObjectId, AdminOpCodeAction> {

    public AdminOpCodeActionDAO(Datastore datastore) {
        super(AdminOpCodeAction.class, datastore);
    }

    @Override
    public void save(List<AdminOpCodeAction> entities) {
        // do nothing
    }

    @Override
    public void delete(ObjectId key) {
        // do nothing
    }

    @Override
    public void delete(AdminOpCodeAction entity) {
        // do nothing
    }

    @Override
    public void delete(List<? extends ObjectId> keys) {
        // do nothing
    }

    @Override
    public void deleteAll() {
        // do nothing
    }

    @Override
    public void save(AdminOpCodeAction entity) {
        // do nothing
    }
}