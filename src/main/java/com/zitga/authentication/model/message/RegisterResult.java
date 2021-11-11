package com.zitga.authentication.model.message;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class RegisterResult {

    private int resultCode;

    private long playerId;

    private String data;

    // ---------------------------------------- Getters ----------------------------------------
    public long getPlayerId() {
        return playerId;
    }

    public String getData() {
        return data;
    }

    @JsonIgnore
    public int getResultCode() {
        return resultCode;
    }

    // ---------------------------------------- Setters ----------------------------------------
    public RegisterResult withCode(int result) {
        this.resultCode = result;

        return this;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public void setData(String data) {
        this.data = data;
    }
}
