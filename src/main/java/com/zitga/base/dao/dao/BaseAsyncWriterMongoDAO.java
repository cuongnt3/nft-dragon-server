package com.zitga.base.dao.dao;

import com.zitga.bean.annotation.BeanDelayedField;
import com.zitga.executor.service.ExecutorService;
import com.zitga.mongo.repository.AbstractMongoRepository;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.UpdateOptions;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;

public abstract class BaseAsyncWriterMongoDAO<K extends Serializable, V> extends AbstractMongoRepository<K, V> {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeanDelayedField
    protected ExecutorService executorService;

    public BaseAsyncWriterMongoDAO(Class<V> entityClass, Datastore datastore) {
        super(entityClass, datastore);
    }

    @Override
    public void delete(K key) {
        executorService.executeDbTask(() -> {
            try {
                flushDeleteKey(key);
            } catch (Throwable e) {
                logger.error(e.getMessage(), e);
            }
        });
    }

    @Override
    public void delete(List<? extends K> keys) {
        executorService.executeDbTask(() -> {
            try {
                flushDeleteKeys(keys);
            } catch (Throwable e) {
                logger.error(e.getMessage(), e);
            }
        });
    }

    @Override
    public void delete(V entity) {
        logger.error("Delete by entity is not supported");
    }

    @Override
    public void deleteAll() {
        logger.error("Delete all is not supported");
    }

    // ---------------------------------------- Execute ----------------------------------------
    protected void executeUpdate(Query<V> query, UpdateOperations<V> updateOperations) {
        executorService.executeDbTask(() -> {
            try {
                datastore.update(query, updateOperations);
            } catch (Throwable e) {
                logger.error(e.getMessage(), e);
            }
        });
    }

    protected void executeUpdate(Query<V> query, UpdateOperations<V> updateOperations, UpdateOptions updateOptions) {
        executorService.executeDbTask(() -> {
            try {
                datastore.update(query, updateOperations, updateOptions);
            } catch (Throwable e) {
                logger.error(e.getMessage(), e);
            }
        });
    }

    protected void executeDelete(Query<V> query) {
        executorService.executeDbTask(() -> {
            try {
                datastore.delete(query);
            } catch (Throwable e) {
                logger.error(e.getMessage(), e);
            }
        });
    }

    // ---------------------------------------- Flush ----------------------------------------
    public void flushSaveEntity(V entity) {
        super.save(entity);
    }

    public void flushSaveEntities(List<V> entities) {
        super.save(entities);
    }

    public void flushDeleteKey(K key) {
        super.delete(key);
    }

    public void flushDeleteKeys(List<? extends K> keys) {
        super.delete(keys);
    }
}