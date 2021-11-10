package com.zitga.publisher.model;

import com.zitga.authentication.model.AdminAuthentication;
import com.zitga.enumeration.observer.ListenerPriority;

public interface IListener {

    void onListenerNotify(AdminAuthentication player, BaseListenerData data);

    ListenerPriority getListenerPriority();
}
