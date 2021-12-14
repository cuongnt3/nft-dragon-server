package com.zitga.idle.battle.model.message.log.action.skill;

import com.zitga.idle.battle.model.battle.output.log.action.skill.LuaActiveSkillResult;
import com.zitga.idle.battle.model.message.log.BaseHeroLog;
import com.zitga.idle.battle.model.message.log.action.BaseActionResult;

public class UseActiveSkillResult extends BaseActionResult {

    private int damage;

    private float initiatorPowerPercent;
    private float targetPowerPercent;

    public UseActiveSkillResult(BaseHeroLog initiator, BaseHeroLog target, int actionResultType) {
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

    public void setSkillInfo(LuaActiveSkillResult skillResult){
        this.damage = skillResult.getDamage();
        this.initiatorPowerPercent = skillResult.getInitiatorPowerPercent();
        this.targetPowerPercent = skillResult.getTargetPowerPercent();
    }
}
