package com.zitga.idle.publisher.utils;

import com.zitga.idle.publisher.model.IListener;

import java.util.Comparator;

public class ListenerComparator implements Comparator<IListener> {

    @Override
    public int compare(IListener first, IListener second) {
        return first.getListenerPriority().getValue() - second.getListenerPriority().getValue();
    }
}
