package com.zitga.idle.player.model.authorized;

import com.zitga.core.authentication.IAuthorizedEntity;

public class ErrorAuthorizedResult implements IAuthorizedEntity {

    private int resultCode;

    public ErrorAuthorizedResult(int result) {
        this.resultCode = result;
    }

    public int getResultCode() {
        return resultCode;
    }

    @Override
    public String getAuthToken(int i) {
        return null;
    }

    @Override
    public void setAuthToken(int i, String s) {

    }
}
