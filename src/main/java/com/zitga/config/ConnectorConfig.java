package com.zitga.config;

public class ConnectorConfig {

    private String host;
    private int port;
    private String secret;

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getSecret() {
        return secret;
    }

    @Override
    public String toString() {
        return "{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", secret='" + secret + '\'' +
                '}';
    }
}