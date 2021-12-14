package com.zitga.idle.masterServer.model;

import com.zitga.idle.base.constant.CollectionName;
import com.zitga.idle.masterServer.constant.MasterTag;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

@Entity(value = CollectionName.SHARED_SECRET, noClassnameStored = true)
public class SharedSecret {

    @Id
    private int id;

    @Property(MasterTag.SECRET_TAG)
    private String secret;

    @Property(MasterTag.IS_ENABLE_TAG)
    private boolean isEnable;

    public int getId() {
        return id;
    }

    public String getSecret() {
        return secret;
    }

    public boolean isEnable() {
        return isEnable;
    }
}
