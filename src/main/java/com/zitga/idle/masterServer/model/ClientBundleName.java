package com.zitga.idle.masterServer.model;

import com.zitga.idle.base.constant.CollectionName;
import com.zitga.idle.masterServer.constant.MasterTag;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

@Entity(value = CollectionName.CLIENT_BUNDLE_NAME, noClassnameStored = true)
public class ClientBundleName {

    @Id
    private ObjectId id;

    @Property(MasterTag.BUNDLE_NAME_TAG)
    private String bundleName;

    @Property(MasterTag.IS_ENABLE_TAG)
    private boolean isEnable;

    public String getBundleName() {
        return bundleName;
    }

    public boolean isEnable() {
        return isEnable;
    }
}