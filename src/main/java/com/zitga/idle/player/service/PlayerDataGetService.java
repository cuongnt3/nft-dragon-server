package com.zitga.idle.player.service;

import com.zitga.bean.BeanContainer;
import com.zitga.bean.ReflectionScanner;
import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.bean.annotation.BeanMethod;
import com.zitga.idle.enumeration.player.PlayerDataType;
import com.zitga.idle.player.annotation.PlayerDataSerializer;
import com.zitga.idle.player.model.Player;
import com.zitga.idle.player.model.message.PlayerDataGetResult;
import com.zitga.idle.player.service.serializer.BasePlayerData;
import com.zitga.utils.BitUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@BeanComponent
public class PlayerDataGetService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeanField
    private BeanContainer beanContainer;

    // key: player data type
    private Map<PlayerDataType, BasePlayerData> serializerMap;

    @BeanMethod
    private void init() {
        logger.info("Initializing PlayerDataService ...");

        ReflectionScanner scanner = beanContainer.getScanner();
        Set<Class<?>> classes = scanner.scanAnnotatedType(PlayerDataSerializer.class);

        serializerMap = new ConcurrentHashMap<>();
        for (Class<?> serializerClass : classes) {
            PlayerDataSerializer annotation = serializerClass.getAnnotation(PlayerDataSerializer.class);
            for (PlayerDataType type : annotation.value()) {
                if (serializerMap.containsKey(type)) {
                    throw new RuntimeException(String.format("Duplicated type %s for %s", type, serializerClass));
                }

                BasePlayerData serializer = (BasePlayerData) beanContainer.getComponent(serializerClass);
                if (serializer == null) {
                    throw new RuntimeException(String.format("Serializer is not found with type %s for %s", type, serializerClass));
                }

                serializerMap.put(type, serializer);
            }
        }
    }

    public PlayerDataGetResult serializeData(Player player, long bitMask) throws Exception {
        PlayerDataGetResult result = new PlayerDataGetResult();
        for (int i = 0; i < Long.SIZE; i++) {
            if (BitUtils.isOn(bitMask, i)) {
                PlayerDataType dataType = PlayerDataType.toPlayerDataType(i);
                if (dataType != null) {

                    // player data type
                    BasePlayerData serializer = serializerMap.get(dataType);
                    if (serializer != null) {
                        serializer.serializeData(player, dataType, result);
                    }

                    logger.trace("Player data type: {}", dataType);
                }
            }
        }

        return result;
    }
}