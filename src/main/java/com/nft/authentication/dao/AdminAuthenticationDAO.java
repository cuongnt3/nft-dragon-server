package com.nft.authentication.dao;

import com.nft.base.constant.DatabaseConstant;
import com.zitga.bean.annotation.BeanComponent;
import com.nft.authentication.constant.AdminAuthTag;
import com.nft.authentication.model.AdminAuthentication;
import com.zitga.mongo.repository.AbstractMongoRepository;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.FindOptions;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import java.util.List;

@BeanComponent
public class AdminAuthenticationDAO extends AbstractMongoRepository<Long, AdminAuthentication> {

    private FindOptions findOneOptions;

    public AdminAuthenticationDAO(Datastore datastore) {
        super(AdminAuthentication.class, datastore);

        findOneOptions = new FindOptions();
        findOneOptions.limit(1);
    }

    // ---------------------------------------- Find Auth ----------------------------------------
    public AdminAuthentication findWithUserName(String userName) {
        Query<AdminAuthentication> query = datastore.createQuery(AdminAuthentication.class);
        query.field(AdminAuthTag.USER_NAME_TAG).equal(userName);

        List<AdminAuthentication> auths = query.asList(findOneOptions);
        if (!auths.isEmpty()) {
            return auths.get(0);
        }

        return null;
    }

    // ---------------------------------------- Admin role ----------------------------------------
    public void updateAdminRole(long adminId, int role) {
        Query<AdminAuthentication> query = datastore.createQuery(AdminAuthentication.class);
        query.field(DatabaseConstant.ID_TAG).equal(adminId);

        UpdateOperations<AdminAuthentication> updateOperations = datastore.createUpdateOperations(AdminAuthentication.class);
        updateOperations.set(AdminAuthTag.ROLE_TAG, role);

        datastore.update(query, updateOperations);
    }
}