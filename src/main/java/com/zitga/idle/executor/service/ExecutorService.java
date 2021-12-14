package com.zitga.idle.executor.service;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.core.scheduler.SchedulerService;
import com.zitga.email.service.GoogleEmailService;
import com.zitga.idle.base.model.MongoSaveTask;
import com.zitga.idle.base.service.MongoSaveScheduler;
import com.zitga.idle.executor.constant.ExecutorConstant;
import com.zitga.idle.masterServer.service.MasterConnectorService;
import com.zitga.idle.player.service.CachedPlayerService;
import com.zitga.idle.statistics.service.StatisticsService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@BeanComponent
public class ExecutorService {

    @BeanField
    private MongoSaveScheduler mongoSaveScheduler;

    @BeanField
    private CachedPlayerService cachedPlayerService;

    @BeanField
    private MasterConnectorService masterConnectorService;

    @BeanField
    private StatisticsService statisticsService;

    @BeanField
    private GoogleEmailService googleEmailService;

    private final ScheduledThreadPoolExecutor scheduler;

    private final ThreadPoolExecutor dbExecutor;
    private final ThreadPoolExecutor handlerExecutor;
    private final ThreadPoolExecutor playerExecutor;

    private final List<java.util.concurrent.ExecutorService> beanExecutors = new ArrayList<>();
    private int numberBeanTask = 1;

    public ExecutorService(SchedulerService schedulerService) {
        // Don't delegate bean task to dbExecutor
        dbExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

        scheduler = schedulerService.getSchedulerThreadGroup();
        beanExecutors.add(scheduler);

        handlerExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
        beanExecutors.add(handlerExecutor);

        playerExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
        beanExecutors.add(playerExecutor);
    }

    public void init() {
//        scheduleTask(notificationScheduler, ExecutorConstant.SHORT_INTERVAL);
//
//        scheduleTask(masterConnectorService, ExecutorConstant.MEDIUM_INTERVAL);
//        scheduleTask(trackingConnectorService, ExecutorConstant.MEDIUM_INTERVAL);
//        scheduleTask(loadBalanceConnectorService, ExecutorConstant.MEDIUM_INTERVAL);

        Set<MongoSaveTask> tasks = new HashSet<>(mongoSaveScheduler.getTaskMap().values());
        for (MongoSaveTask task : tasks) {
            scheduleTask(task, ExecutorConstant.LONG_INTERVAL);
        }

        scheduleTask(cachedPlayerService, ExecutorConstant.TEN_MINUTE_INTERVAL);

//        scheduleTask(chatService, ExecutorConstant.TEN_MINUTE_INTERVAL);
//        scheduleTask(recordService, ExecutorConstant.TEN_MINUTE_INTERVAL);
//        scheduleTask(statisticsService, ExecutorConstant.TEN_MINUTE_INTERVAL);
//
//        scheduleTask(purchaseValidationService, ExecutorConstant.HALF_HOUR_INTERVAL);
//        scheduleTask(googleEmailService, ExecutorConstant.HALF_HOUR_INTERVAL);
//
//        scheduleTask(heroStatisticService, ExecutorConstant.FIVE_MINUTE_INTERVAL);
//        scheduleTask(cachedHeroStatisticService, ExecutorConstant.LONG_INTERVAL);
    }

    // ---------------------------------------- Schedule ----------------------------------------
    public void scheduleTask(Runnable runnable, int intervalInSeconds) {
        scheduler.scheduleAtFixedRate(runnable, ExecutorConstant.START_DELAY, intervalInSeconds, TimeUnit.SECONDS);
    }

    // ---------------------------------------- Execute ----------------------------------------
    public void executeDbTask(Runnable runnable) {
        dbExecutor.execute(runnable);
    }

    public void executeHandlerTask(Runnable runnable) {
        handlerExecutor.execute(runnable);
    }

    public void executeListenerTask(Runnable runnable) {
        playerExecutor.execute(runnable);
    }

    public void executePlayerTask(Runnable runnable) {
        playerExecutor.execute(runnable);
    }

    public void executeBeanTask(Runnable runnable) {
        beanExecutors.get(numberBeanTask % beanExecutors.size()).execute(runnable);
        numberBeanTask++;
    }
}