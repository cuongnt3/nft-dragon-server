package com.zitga.executor.service;

import com.zitga.config.GameConfig;
import com.zitga.executor.constant.ExecutorConstant;
import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.core.scheduler.SchedulerService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@BeanComponent
public class ExecutorService {

    @BeanField
    private GameConfig gameConfig;

    private ScheduledThreadPoolExecutor executor;

    private final ThreadPoolExecutor playerExecutor;

    public ExecutorService(SchedulerService schedulerService) {
        executor = schedulerService.getSchedulerThreadGroup();

        playerExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
    }

    public void init() {

    }

    // ---------------------------------------- Executor helper ----------------------------------------
    public void scheduleRealtimeTask(Runnable runnable, int intervalInMiliSeconds) {
        executor.scheduleAtFixedRate(runnable, ExecutorConstant.START_DELAY, intervalInMiliSeconds, TimeUnit.MILLISECONDS);
    }

    public void scheduleTask(Runnable runnable, int intervalInSeconds) {
        executor.scheduleAtFixedRate(runnable, ExecutorConstant.START_DELAY, intervalInSeconds, TimeUnit.SECONDS);
    }

    public void executeListenerTask(Runnable runnable) {
        playerExecutor.execute(runnable);
    }
}