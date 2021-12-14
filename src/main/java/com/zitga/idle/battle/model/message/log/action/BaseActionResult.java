package com.zitga.idle.battle.model.message.log.action;

import com.zitga.idle.battle.model.message.log.BaseHeroLog;

public abstract class BaseActionResult {

    protected BaseHeroLog initiator;
    protected BaseHeroLog target;

    protected int actionResultType;
    protected float targetHpPercent;

    public BaseActionResult(BaseHeroLog initiator, BaseHeroLog target, int actionResultType) {
        this.initiator = initiator;
        this.target = target;
        this.actionResultType = actionResultType;
    }

    public BaseHeroLog getInitiator() {
        return initiator;
    }

    public BaseHeroLog getTarget() {
        return target;
    }

    public int getActionResultType() {
        return actionResultType;
    }

    public float getTargetHpPercent() {
        return targetHpPercent;
    }

    public void setTargetHpPercent(float targetHpPercent) {
        this.targetHpPercent = targetHpPercent;
    }
}
