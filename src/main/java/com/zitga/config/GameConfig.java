package com.zitga.config;

import com.zitga.bean.annotation.BeanConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@BeanConfiguration("config/game.properties")
public class GameConfig {

    private String apiVersion;
    private String secretKey;

    private String csvPath;

    private Boolean allowFakeData;

    public String getApiVersion() {
        return apiVersion;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public String getCsvPath() {
        return csvPath;
    }

    public Boolean getAllowFakeData() {
        return allowFakeData;
    }

    @Override
    public String toString() {
        return "GameConfig{" +
                "apiVersion='" + apiVersion + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", csvPath='" + csvPath + '\'' +
                ", allowFakeData=" + allowFakeData +
                '}';
    }
}