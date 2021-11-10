package com.nft.publisher.model.battle;

import com.nft.enumeration.observer.SubjectType;
import com.nft.publisher.model.BaseListenerData;

public class DisconnectListenerData extends BaseListenerData {

    @Override
    public SubjectType getSubjectType() {
        return SubjectType.DISCONNECTED;
    }
}