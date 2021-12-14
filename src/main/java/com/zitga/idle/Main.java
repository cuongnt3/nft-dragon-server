package com.zitga.idle;

import com.zitga.core.ServerBootstrap;
import com.zitga.mongo.support.log.LoggerFactoryImpl;
import org.apache.logging.log4j.core.config.Configurator;
import org.mongodb.morphia.logging.MorphiaLoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.StaticLoggerBinder;

public class Main {

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        Configurator.initialize("idle-summoner", "config/log4j.properties");
        MorphiaLoggerFactory.registerLogger(LoggerFactoryImpl.class);

        Logger logger = LoggerFactory.getLogger(Main.class);
        try {
            final StaticLoggerBinder binder = StaticLoggerBinder.getSingleton();
            logger.info("[LOG] Binded LoggerFactory: {}", binder.getLoggerFactoryClassStr());

            ServerBootstrap.start();
            ServiceController.instance().init();
        } catch (Throwable e) {
            ServerBootstrap.stop();
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}
