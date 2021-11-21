package com.zitga.base.service;

import com.zitga.base.dao.dao.ScheduledAsyncWriterMongoDAO;
import com.zitga.base.model.MongoSaveTask;
import com.zitga.bean.BeanContainer;
import com.zitga.bean.ReflectionScanner;
import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.bean.annotation.BeanMethod;
import com.zitga.bean.utils.BeanUtils;
import com.zitga.base.model.BasePlayerComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@BeanComponent
public class MongoSaveScheduler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeanField
    private BeanContainer beanContainer;

    // key: classType
    private final Map<Class<?>, ScheduledAsyncWriterMongoDAO> mongoDAOMap = new ConcurrentHashMap<>();

    // key: classType
    private final Map<Class<?>, MongoSaveTask> taskMap = new ConcurrentHashMap<>();

    @BeanMethod
    private void init() {
        logger.info("Initializing MongoSaveActionScheduler ...");

        ReflectionScanner scanner = beanContainer.getScanner();
        Set<Class<?>> classes = scanner.scanSubTypeOf(ScheduledAsyncWriterMongoDAO.class);

        List<MongoSaveTask> tasks = initTask();
        int currentTaskIndex = 0;

        for (Class<?> writerClass : classes) {
            if (!BeanUtils.isCanCreateInstance(writerClass)) {
                continue;
            }

            ScheduledAsyncWriterMongoDAO dao = (ScheduledAsyncWriterMongoDAO) beanContainer.getComponent(writerClass);
            if (dao == null) {
                throw new RuntimeException(String.format("DAO is not found for %s", writerClass));
            }

            if (mongoDAOMap.containsKey(writerClass)) {
                throw new RuntimeException(String.format("Duplicated dao for %s", writerClass));
            }

            mongoDAOMap.put(dao.getEntityClass(), dao);
            taskMap.put(dao.getEntityClass(), tasks.get(currentTaskIndex));

            currentTaskIndex = (currentTaskIndex + 1) % tasks.size();
        }
    }

    private List<MongoSaveTask> initTask() {
        List<MongoSaveTask> tasks = new ArrayList<>();

        for (int i = 0; i < Runtime.getRuntime().availableProcessors() * 2; i++) {
            MongoSaveTask task = new MongoSaveTask(i, mongoDAOMap);
            tasks.add(task);
        }

        logger.info("[SAVE SCHEDULER] Initializing {} tasks", tasks.size());
        return tasks;
    }


    public Map<Class<?>, MongoSaveTask> getTaskMap() {
        return taskMap;
    }

    public void addPendingSaveEntity(BasePlayerComponent entity) {
        MongoSaveTask task = taskMap.get(entity.getClass());
        task.addPendingSaveEntity(entity);
    }
}