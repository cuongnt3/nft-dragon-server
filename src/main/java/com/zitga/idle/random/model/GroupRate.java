package com.zitga.idle.random.model;

import com.zitga.idle.base.constant.BasicTag;

import java.util.Map;

public class GroupRate implements IBucket {

    private final int groupId;
    private final float groupRate;

    public GroupRate(Map<String, String> data) {
        groupId = Integer.parseInt(data.get(BasicTag.GROUP_TAG));
        groupRate = Float.parseFloat(data.get(BasicTag.GROUP_RATE_TAG));
    }

    public GroupRate(GroupRate group) {
        groupId = group.getId();
        groupRate = group.getRate();
    }

    // ---------------------------------------- Getters ----------------------------------------
    public int getId() {
        return groupId;
    }

    @Override
    public float getRate() {
        return groupRate;
    }
}