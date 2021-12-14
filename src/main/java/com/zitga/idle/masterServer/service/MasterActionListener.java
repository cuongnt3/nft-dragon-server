package com.zitga.idle.masterServer.service;

import com.zitga.bean.BeanContainer;
import com.zitga.bean.ReflectionScanner;
import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanDelayedMethod;
import com.zitga.bean.annotation.BeanField;
import com.zitga.core.handler.socket.support.context.Peer;
import com.zitga.core.utils.socket.SerializeHelper;
import com.zitga.core.utils.socket.SocketUtils;
import com.zitga.core.utils.socket.tcpClient.ITcpClientListener;
import com.zitga.idle.base.constant.OpCode;
import com.zitga.idle.base.handler.BaseSubHandler;
import com.zitga.idle.config.game.ConnectorConfig;
import com.zitga.idle.config.game.GameConfig;
import com.zitga.idle.masterServer.annotation.MasterHandler;
import com.zitga.idle.masterServer.constant.MasterOpCode;
import com.zitga.support.encryption.HashHelper;
import com.zitga.utils.TimeUtils;
import io.netty.buffer.ByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@BeanComponent
public class MasterActionListener implements ITcpClientListener {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeanField
    private BeanContainer beanContainer;

    private ConnectorConfig connectorConfig;

    // key: MasterOpCode
    private Map<Integer, BaseSubHandler> masterHandlerMap;
    private Peer peer;

    @BeanDelayedMethod
    private void init(GameConfig gameConfig) {
        logger.info("Initializing MasterActionListener ...");

        connectorConfig = gameConfig.getMaster();

        ReflectionScanner scanner = beanContainer.getScanner();
        Set<Class<?>> classes = scanner.scanAnnotatedType(MasterHandler.class);

        masterHandlerMap = new ConcurrentHashMap<>();
        for (Class<?> handlerClass : classes) {
            MasterHandler annotation = handlerClass.getAnnotation(MasterHandler.class);
            if (masterHandlerMap.containsKey(annotation.value())) {
                throw new RuntimeException(String.format("Duplicated opCode %s for %s", annotation.value(), handlerClass));
            }

            BaseSubHandler handler = (BaseSubHandler) beanContainer.getComponent(handlerClass);
            if (handler == null) {
                throw new RuntimeException(String.format("Handler is not found with opCode %s for %s", annotation.value(), handlerClass));
            }

            masterHandlerMap.put(annotation.value(), handler);
        }
    }

    @Override
    public int getOpCode() {
        return OpCode.MASTER_SERVER_ACTION;
    }

    @Override
    public void onConnected(Peer peer) {
        logger.info("[MASTER] Connected to master server at {}", peer.getRemoteAddress());

        this.peer = peer;
        this.loginToMasterServer();
    }

    @Override
    public void onDisconnected() {
        logger.warn("[MASTER] Disconnected to master server at {}", peer.getRemoteAddress());
    }

    @Override
    public void onReceive(Peer peer, int opCode, ByteBuf in) throws Exception {
        int subOpCode = in.readUnsignedByte();

        BaseSubHandler subHandler = masterHandlerMap.get(subOpCode);
        if (subHandler != null) {
            subHandler.handle(peer, in);
        } else {
            logger.error("[MASTER] No SubHandler found for MasterOpCode={}", subOpCode);
        }
    }

    private void loginToMasterServer() {
        ByteBuf out = SocketUtils.createByteBuf(OpCode.MASTER_SERVER_ACTION);
        out.writeByte(MasterOpCode.HAND_SHAKE);

        long timeInSeconds = TimeUtils.getCurrentTimeInSecond();
        out.writeLongLE(timeInSeconds);

        String handShake = HashHelper.hashSHA256(connectorConfig.getSecret() + timeInSeconds);
        SerializeHelper.writeString(out, handShake);

        peer.send(out);
    }
}