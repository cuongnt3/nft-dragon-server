package com.nft.publisher.model;

import com.nft.authentication.model.AdminAuthentication;
import com.nft.enumeration.observer.ListenerPriority;

public interface IListener {

    void onListenerNotify(AdminAuthentication player, BaseListenerData data);

    ListenerPriority getListenerPriority();
}
