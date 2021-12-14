package com.zitga.idle.config;

import com.zitga.bean.annotation.BeanConfiguration;

@BeanConfiguration("config/giftcode.properties")
public class GiftCodeConfig {

    private String host;
    private int port;

    private int apiVersion;
    private int gameId;

    private String secretKey;

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public int getApiVersion() {
        return apiVersion;
    }

    public int getGameId() {
        return gameId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    @Override
    public String toString() {
        return "GiftCodeConfig{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", apiVersion=" + apiVersion +
                ", gameId=" + gameId +
                ", secretKey='" + secretKey + '\'' +
                '}';
    }
}
