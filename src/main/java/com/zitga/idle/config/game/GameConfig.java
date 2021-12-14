package com.zitga.idle.config.game;

import com.zitga.bean.annotation.BeanConfiguration;
import com.zitga.idle.enumeration.lua.LuaRunMode;

@BeanConfiguration("config/game.properties")
public class GameConfig {

    private String apiVersion;
    private String secretKey;

    private boolean allowFakeData;

    private boolean allowBenchMark;
    private int benchMarkLowerLimit;

    private boolean allowTracking;
    private int trackingVersion;

    private boolean allowLoadBalance;

    private int luaRunMode;

    private String luaPath;
    private String csvPath;

    private ConnectorConfig master;
    private ConnectorConfig tracking;
    private ConnectorConfig loadBalance;

    public String getApiVersion() {
        return apiVersion;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public boolean getAllowFakeData() {
        return allowFakeData;
    }

    public boolean getAllowBenchMark() {
        return allowBenchMark;
    }

    public boolean getAllowTracking() {
        return allowTracking;
    }

    public int getTrackingVersion() {
        return trackingVersion;
    }

    public boolean getAllowLoadBalance() {
        return allowLoadBalance;
    }

    public int getBenchMarkLowerLimit() {
        return benchMarkLowerLimit;
    }

    public LuaRunMode getLuaRunMode() {
        return LuaRunMode.toLuaRunMode(luaRunMode);
    }

    public String getLuaPath() {
        return luaPath;
    }

    public String getCsvPath() {
        return csvPath;
    }

    public ConnectorConfig getMaster() {
        return master;
    }

    public ConnectorConfig getTracking() {
        return tracking;
    }

    public ConnectorConfig getLoadBalance() {
        return loadBalance;
    }

    @Override
    public String toString() {
        return "GameConfig{" +
                "apiVersion='" + apiVersion + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", allowFakeData=" + allowFakeData +
                ", allowBenchMark=" + allowBenchMark +
                ", benchMarkLowerLimit=" + benchMarkLowerLimit +
                ", allowTracking=" + allowTracking +
                ", trackingVersion=" + trackingVersion +
                ", allowLoadBalance=" + allowLoadBalance +
                ", luaRunMode=" + luaRunMode +
                ", luaPath='" + luaPath + '\'' +
                ", csvPath='" + csvPath + '\'' +
                ", master=" + master +
                ", tracking=" + tracking +
                ", loadBalance=" + loadBalance +
                '}';
    }
}
