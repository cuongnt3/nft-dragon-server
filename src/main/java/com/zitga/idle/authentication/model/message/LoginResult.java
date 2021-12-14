package com.zitga.idle.authentication.model.message;

public class LoginResult {

    private long playerId;

    private String data;

    public long getPlayerId() {
        return playerId;
    }

    public String getData() {
        return data;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public void setData(String data) {
        this.data = data;
    }
}
