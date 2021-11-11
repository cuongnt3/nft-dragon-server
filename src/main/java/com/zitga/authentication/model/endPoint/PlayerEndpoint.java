package com.zitga.authentication.model.endPoint;

import com.zitga.authentication.model.PlayerAuthentication;
import com.zitga.core.authentication.socket.IPeerAuthentication;
import com.zitga.core.handler.socket.support.context.HandlerContext;
import com.zitga.core.handler.socket.support.context.Peer;
import io.netty.buffer.ByteBuf;

public class PlayerEndpoint implements IPeerAuthentication {

    private PlayerAuthentication adminAuth;

    private Peer peer;
    private HandlerContext context;

    private boolean isAuth;

    private long lastDisconnect;

    public PlayerEndpoint(PlayerAuthentication adminAuth) {
        this.adminAuth = adminAuth;
    }

    public PlayerEndpoint(PlayerAuthentication adminAuth, HandlerContext context) {
        this.adminAuth = adminAuth;
        bindingWithSocket(context);
        this.context = context;
    }

    // ---------------------------------------- Getters ----------------------------------------
    @Override
    public Peer getPeer() {
        return peer;
    }

    @Override
    public boolean isPeerAuthorized() {
        return isAuth;
    }

    public PlayerAuthentication getAdminAuth() {
        return adminAuth;
    }

    public HandlerContext getContext() {
        return context;
    }

    public long getLastDisconnect() {
        return lastDisconnect;
    }

    public void send(int opCode, int returnCode) {
        peer.send(opCode, returnCode);
    }

    public void send(ByteBuf out) {
        peer.send(out);
    }

    public void disconnect(int reason) {
        peer.disconnect(reason);
    }

    // ---------------------------------------- Setters ----------------------------------------
    @Override
    public void setPeer(Peer peer) {
        this.peer = peer;
    }

    @Override
    public void setPeerAuthorized(boolean isAuth) {
        this.isAuth = isAuth;
    }

    public void setLastDisconnect(long seconds) {
        this.lastDisconnect = seconds;
    }

    private void bindingWithSocket(HandlerContext context) {
        this.context = context;
        this.context.setAuth(this);

        this.peer = context.getPeer();
        this.isAuth = true;
    }
}