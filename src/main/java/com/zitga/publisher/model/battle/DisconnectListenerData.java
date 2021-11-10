package com.zitga.publisher.model.battle;

import com.zitga.enumeration.observer.SubjectType;
import com.zitga.publisher.model.BaseListenerData;

public class DisconnectListenerData extends BaseListenerData {

    @Override
    public SubjectType getSubjectType() {
        return SubjectType.DISCONNECTED;
    }
}