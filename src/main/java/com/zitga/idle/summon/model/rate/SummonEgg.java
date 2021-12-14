package com.zitga.idle.summon.model.rate;

import com.zitga.idle.enumeration.resource.ResourceType;
import com.zitga.idle.random.model.IBucket;
import com.zitga.idle.summon.constant.SummonConstant;
import com.zitga.idle.summon.constant.SummonTag;

import java.util.Map;

public class SummonEgg implements IBucket {

    private int id;

    private int rate;

    private ResourceType type;

    private int star;

    public SummonEgg(Map<String, String> data) {
        id = Integer.parseInt(data.get(SummonTag.ITEM_ID_TAG));
        rate = Integer.parseInt(data.get(SummonTag.ITEM_RATE_TAG));

        int resType = Integer.parseInt(data.get(SummonTag.ITEM_ID_TAG));
        type = ResourceType.toResourceType(resType);

        if (type == ResourceType.DRAGON) {
            star = Integer.parseInt(data.get(SummonTag.ITEM_STAR_TAG));
        }
    }

    public void validate() {
        if (rate <= 0) {
            throw new RuntimeException(String.format("Egg summon rate invalid: %s", id));
        }

        if (type == ResourceType.DRAGON) {
            if (star < SummonConstant.MIN_SUMMON_STAR || star > SummonConstant.MAX_SUMMON_STAR){
                throw new RuntimeException(String.format("Egg summon dragon star invalid: %s", star));
            }
        }
    }

    // ---------------------------------------- Getters ----------------------------------------
    public int getId() {
        return id;
    }

    public ResourceType getType() {
        return type;
    }

    public int getStar() {
        return star;
    }

    @Override
    public float getRate() {
        return rate;
    }
}
