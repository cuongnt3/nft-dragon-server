package com.zitga.config;

import com.zitga.bean.annotation.BeanConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@BeanConfiguration("config/game.properties")
public class GameConfig {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private int version;

    public int getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return "GameConfig{" +
                "version=" + version +
                '}';
    }
}