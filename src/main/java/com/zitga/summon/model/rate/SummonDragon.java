package com.zitga.summon.model.rate;

import com.zitga.enumeration.resource.ResourceType;
import com.zitga.random.model.IBucket;
import com.zitga.summon.constant.SummonConstant;
import com.zitga.summon.constant.SummonTag;

import java.util.Map;

public class SummonDragon implements IBucket {

    private int id;

    private int rate;

    public SummonDragon(Map<String, String> data) {
        id = Integer.parseInt(data.get(SummonTag.DRAGON_ID_TAG));
        rate = Integer.parseInt(data.get(SummonTag.DRAGON_RATE_TAG));
    }

    public void validate() {
        if (rate <= 0) {
            throw new RuntimeException(String.format("Dragon summon rate invalid: %s", id));
        }
    }

    // ---------------------------------------- Getters ----------------------------------------
    public int getId() {
        return id;
    }

    @Override
    public float getRate() {
        return rate;
    }
}
