package com.zitga.idle.battle.model.message.log.action.attack;

import com.zitga.idle.battle.model.battle.output.log.action.attack.LuaAttackResult;
import com.zitga.idle.battle.model.message.log.BaseHeroLog;
import com.zitga.idle.battle.model.message.log.action.BaseActionResult;

public class AttackResult extends BaseActionResult {

    private int damage;

    private float initiatorPowerPercent;
    private float targetPowerPercent;

    public AttackResult(BaseHeroLog initiator, BaseHeroLog target, int actionResultType) {
        super(initiator, target, actionResultType);
    }

    public int getDamage() {
        return damage;
    }

    public float getInitiatorPowerPercent() {
        return initiatorPowerPercent;
    }

    public float getTargetPowerPercent() {
        return targetPowerPercent;
    }

    public void setAttackInfo(LuaAttackResult result) {
        this.damage = result.getDamage();
        this.initiatorPowerPercent = result.getInitiatorPowerPercent();
        this.targetPowerPercent = result.getTargetPowerPercent();
    }
}
