package com.zitga.authentication.dao;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.authentication.model.role.AdminRole;
import com.zitga.mongo.repository.AbstractMongoRepository;
import org.mongodb.morphia.Datastore;

@BeanComponent
public class AdminRoleDAO extends AbstractMongoRepository<Integer, AdminRole> {

    public AdminRoleDAO(Datastore datastore) {
        super(AdminRole.class, datastore);
    }
}