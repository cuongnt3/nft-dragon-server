package com.zitga.idle.publisher.model;

import com.zitga.idle.enumeration.observer.ListenerPriority;
import com.zitga.idle.player.model.Player;

public interface IListener {

    void onListenerNotify(Player player, BaseListenerData data);

    ListenerPriority getListenerPriority();
}
