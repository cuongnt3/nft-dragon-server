package com.zitga.idle.publisher.service;

import com.zitga.bean.BeanContainer;
import com.zitga.bean.ReflectionScanner;
import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.bean.annotation.BeanMethod;
import com.zitga.idle.enumeration.observer.SubjectType;
import com.zitga.idle.executor.service.ExecutorService;
import com.zitga.idle.player.model.Player;
import com.zitga.idle.publisher.annotation.Listener;
import com.zitga.idle.publisher.model.BaseListenerData;
import com.zitga.idle.publisher.model.IListener;
import com.zitga.idle.publisher.utils.ListenerComparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@BeanComponent
public class PublisherService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeanField
    private ExecutorService executorService;

    @BeanField
    private BeanContainer beanContainer;

    private Map<SubjectType, List<IListener>> listenerMap;

    @BeanMethod
    private void init() {
        logger.info("Initializing PublisherService ...");


        ReflectionScanner scanner = beanContainer.getScanner();
        Set<Class<?>> classes = scanner.scanAnnotatedType(Listener.class);

        listenerMap = new ConcurrentHashMap<>();
        for (Class<?> listenerClass : classes) {
            Listener annotation = listenerClass.getAnnotation(Listener.class);
            if (!annotation.isListenAll()) {
                for (SubjectType subjectType : annotation.value()) {
                    IListener listener = (IListener) beanContainer.getComponent(listenerClass);
                    registerListener(subjectType, listener);
                }
            } else {
                if (annotation.value().length > 0) {
                    throw new RuntimeException(String.format("Class %s, if listen to all subjects, listener array can't have any values", listenerClass));
                }

                for (SubjectType subjectType : SubjectType.values()) {
                    IListener listener = (IListener) beanContainer.getComponent(listenerClass);
                    registerListener(subjectType, listener);
                }
            }
        }

        ListenerComparator comparator = new ListenerComparator();
        for (List<IListener> listeners : listenerMap.values()) {
            listeners.sort(comparator);
        }
    }

    private void registerListener(SubjectType subjectType, IListener listener) {
        if (subjectType == null || listener == null) {
            throw new RuntimeException(String.format("Listener with type %s is not found", subjectType));
        }

        List<IListener> listeners = listenerMap.computeIfAbsent(subjectType, k -> new ArrayList<>());
        listeners.add(listener);
    }

    public void notifyListener(Player player, BaseListenerData data) {
        executorService.executeListenerTask(() -> {
            List<IListener> listeners = listenerMap.get(data.getSubjectType());
            if (listeners != null) {
                for (IListener listener : listeners) {
                    try {
                        listener.onListenerNotify(player, data);
                    } catch (Throwable e) {
                        logger.error("SubjectType = {}, player = {}", data.getSubjectType(), player.getPlayerId());
                        logger.error(e.getMessage(), e);
                    }
                }
            }
        });
    }

    public void notifyListenerSync(Player player, BaseListenerData data) {
        List<IListener> listeners = listenerMap.get(data.getSubjectType());
        if (listeners != null) {
            for (IListener listener : listeners) {
                try {
                    listener.onListenerNotify(player, data);
                } catch (Throwable e) {
                    logger.error("SubjectType = {}, player = {}", data.getSubjectType(), player.getPlayerId());
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }

    public void notifyListenerToOthers(IListener sender, Player player, BaseListenerData data) {
        executorService.executeListenerTask(() -> {
            List<IListener> listeners = listenerMap.get(data.getSubjectType());
            if (listeners != null) {
                for (IListener listener : listeners) {
                    try {
                        if (listener.equals(sender)){
                            continue;
                        }

                        listener.onListenerNotify(player, data);
                    } catch (Throwable e) {
                        logger.error("SubjectType = {}, player = {}", data.getSubjectType(), player.getPlayerId());
                        logger.error(e.getMessage(), e);
                    }
                }
            }
        });
    }
}