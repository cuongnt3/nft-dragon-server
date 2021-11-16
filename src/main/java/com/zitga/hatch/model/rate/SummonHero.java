package com.zitga.hatch.model.rate;

import com.zitga.idle.base.constant.BasicTag;
import com.zitga.idle.eventNewHeroSummon.constant.EventNewHeroSummonConstant;
import com.zitga.idle.random.model.IBucket;
import com.zitga.idle.summon.constant.SummonTag;

import java.util.Map;

public class SummonHero implements IBucket {

    private final int heroId;
    private final int star;
    private final float heroRate;

    public SummonHero(int heroId, int star) {
        this.heroId = heroId;
        this.star = star;
        this.heroRate = EventNewHeroSummonConstant.DEFAULT_HERO_RATE;
    }

    public SummonHero(Map<String, String> data, int star) {
        this.heroId = Integer.parseInt(data.get(SummonTag.HERO_ID_TAG));
        this.heroRate = Float.parseFloat(data.get(SummonTag.HERO_RATE_TAG));
        this.star = star;
    }

    public SummonHero(Map<String, String> data) {
        this.heroId = Integer.parseInt(data.get(SummonTag.HERO_ID_TAG));
        this.star = Integer.parseInt(data.get(BasicTag.STAR_TAG));
        this.heroRate = Float.parseFloat(data.get(SummonTag.HERO_RATE_TAG));
    }

    // ---------------------------------------- Getters ----------------------------------------
    public int getHeroId() {
        return heroId;
    }

    public int getStar() {
        return star;
    }

    @Override
    public float getRate() {
        return heroRate;
    }
}
