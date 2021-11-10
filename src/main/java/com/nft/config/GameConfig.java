package com.nft.config;

import com.zitga.bean.annotation.BeanConfiguration;

@BeanConfiguration("config/game.properties")
public class GameConfig {

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