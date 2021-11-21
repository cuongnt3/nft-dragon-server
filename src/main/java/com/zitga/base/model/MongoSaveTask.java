package com.zitga.base.model;

import com.zitga.base.dao.dao.ScheduledAsyncWriterMongoDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MongoSaveTask implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final int taskId;

    // key: collectionName_playerId
    private final Map<String, BasePlayerComponent> pendingSaveEntities = new ConcurrentHashMap<>();

    // key: classType
    private final Map<Class<?>, ScheduledAsyncWriterMongoDAO> mongoDAOMap;

    public MongoSaveTask(int taskId, Map<Class<?>, ScheduledAsyncWriterMongoDAO> mongoDAOMap) {
        this.taskId = taskId;
        this.mongoDAOMap = mongoDAOMap;
    }

    @Override
    public void run() {
        try {
            logger.info("[DB SCHEDULER] [TASK {} START] Number pending save entities: {}", taskId, pendingSaveEntities.size());

            Instant startTime = Instant.now();

            Map<String, BasePlayerComponent> entities = new ConcurrentHashMap<>(pendingSaveEntities);
            pendingSaveEntities.clear();

            // flush all pending save entities
            Iterator<Map.Entry<String, BasePlayerComponent>> iterator = entities.entrySet().iterator();
            while (iterator.hasNext()) {
                try {
                    Map.Entry<String, BasePlayerComponent> entry = iterator.next();
                    iterator.remove();

                    BasePlayerComponent entity = entry.getValue();

                    ScheduledAsyncWriterMongoDAO mongoDAO = mongoDAOMap.get(entity.getClass());
                    mongoDAO.flushSaveEntity(entity);
                } catch (Throwable e) {
                    logger.error(e.getMessage(), e);
                }
            }

            Instant endTime = Instant.now();
            long elapsed = Duration.between(startTime, endTime).toMillis();

            logger.info("[DB SCHEDULER] [TASK {} FINISH] elapsed = {}ms", taskId, elapsed);
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
        }
    }

    public void addPendingSaveEntity(BasePlayerComponent entity) {
        String tag = String.format("%s_%s", entity.getClass().getName(), entity.getPlayer());
        pendingSaveEntities.put(tag, entity);
    }
}
