package com.zitga.publisher.model;

import com.zitga.authentication.model.PlayerAuthentication;
import com.zitga.enumeration.observer.ListenerPriority;

public interface IListener {

    void onListenerNotify(PlayerAuthentication player, BaseListenerData data);

    ListenerPriority getListenerPriority();
}
