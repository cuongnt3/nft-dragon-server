package com.zitga.hatch.model.config;

import com.zitga.idle.enumeration.common.MoneyType;
import com.zitga.idle.enumeration.summon.SummonType;
import com.zitga.idle.resource.constant.ResourceTag;
import com.zitga.idle.resource.model.Cost;
import com.zitga.idle.resource.model.Reward;
import com.zitga.idle.summon.constant.SummonTag;

import java.util.Map;

public class HatchConfig {

    private final SummonType summonType;
    private final int summonNumber;

    private final Cost summonByScrollCost;
    private final Cost summonByGemCost;

    private Reward summonBonus;

    public HatchConfig(Map<String, String> data) {
        summonType = SummonType.toSummonType(Integer.parseInt(data.get(SummonTag.SUMMON_TYPE_TAG)));
        summonNumber = Integer.parseInt(data.get(SummonTag.SUMMON_NUMBER_TAG));

        MoneyType moneyType = MoneyType.toMoneyType(Integer.parseInt(data.get(ResourceTag.MONEY_TYPE_TAG)));
        int summonPrice = Integer.parseInt(data.get(SummonTag.SUMMON_PRICE_TAG));
        summonByScrollCost = new Cost(moneyType, summonPrice);

        int gemPrice = Integer.parseInt(data.get(SummonTag.SUMMON_GEM_PRICE_TAG));
        summonByGemCost = new Cost(MoneyType.GEM, gemPrice);

        if (data.containsKey(ResourceTag.RESOURCE_NUMBER_TAG)){
            int rewardNumber = Integer.parseInt(data.get(ResourceTag.RESOURCE_NUMBER_TAG));
            if (rewardNumber > 0) {
                summonBonus = new Reward(data);
            }
        }
    }

    public SummonType getSummonType() {
        return summonType;
    }

    public int getSummonNumber() {
        return summonNumber;
    }

    public Cost getSummonCost(boolean isUseGem) {
        if (isUseGem) {
            return summonByGemCost;
        } else {
            return summonByScrollCost;
        }
    }

    public Reward getSummonBonus() {
        return summonBonus;
    }
}
