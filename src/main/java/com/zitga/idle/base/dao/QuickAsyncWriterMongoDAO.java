package com.zitga.idle.base.dao;

import org.mongodb.morphia.Datastore;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public abstract class QuickAsyncWriterMongoDAO<K extends Serializable, V> extends BaseAsyncWriterMongoDAO<K, V> {

    public QuickAsyncWriterMongoDAO(Class<V> entityClass, Datastore datastore) {
        super(entityClass, datastore);
    }

    @Override
    public void save(V entity) {
        if (entity != null) {
            executorService.executeDbTask(() -> {
                try {
                    flushSaveEntity(entity);
                } catch (Throwable e) {
                    logger.error(e.getMessage(), e);
                }
            });
        }
    }

    @Override
    public void save(List<V> entities) {
        entities.removeIf(Objects::isNull);
        executorService.executeDbTask(() -> {
            try {
                flushSaveEntities(entities);
            } catch (Throwable e) {
                logger.error(e.getMessage(), e);
            }
        });
    }
}