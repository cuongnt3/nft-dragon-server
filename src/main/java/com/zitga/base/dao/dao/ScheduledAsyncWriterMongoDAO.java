package com.zitga.base.dao.dao;

import com.zitga.base.service.MongoSaveScheduler;
import com.zitga.bean.annotation.BeanDelayedField;
import com.zitga.hatch.model.base.BasePlayerComponent;
import org.mongodb.morphia.Datastore;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public abstract class ScheduledAsyncWriterMongoDAO<K extends Serializable, V extends BasePlayerComponent> extends BaseAsyncWriterMongoDAO<K, V> {

    @BeanDelayedField
    private MongoSaveScheduler saveActionScheduler;

    public ScheduledAsyncWriterMongoDAO(Class<V> entityClass, Datastore datastore) {
        super(entityClass, datastore);
    }

    @Override
    public void save(V entity) {
        if (entity != null) {
            saveActionScheduler.addPendingSaveEntity(entity);
        }
    }

    @Override
    public void save(List<V> entities) {
        entities.removeIf(Objects::isNull);
        for (V entity : entities) {
            saveActionScheduler.addPendingSaveEntity(entity);
        }
    }
}