package com.zitga.executor.service;

import com.zitga.base.service.MongoSaveScheduler;
import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.core.scheduler.SchedulerService;
import com.zitga.executor.constant.ExecutorConstant;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@BeanComponent
public class ExecutorService {

    @BeanField
    private MongoSaveScheduler mongoSaveScheduler;

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