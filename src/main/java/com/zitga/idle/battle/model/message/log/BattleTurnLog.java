package com.zitga.idle.battle.model.message.log;

import com.zitga.idle.battle.model.battle.output.log.LuaBaseActionResult;
import com.zitga.idle.battle.model.battle.output.log.LuaBaseHero;
import com.zitga.idle.battle.model.battle.output.log.LuaBattleTeamLog;
import com.zitga.idle.battle.model.battle.output.log.LuaHeroStatusLog;
import com.zitga.idle.battle.model.battle.output.log.action.attack.LuaAttackResult;
import com.zitga.idle.battle.model.battle.output.log.action.skill.LuaActiveSkillResult;
import com.zitga.idle.battle.model.message.log.action.BaseActionResult;
import com.zitga.idle.battle.model.message.log.action.attack.AttackResult;
import com.zitga.idle.battle.model.message.log.action.skill.UseActiveSkillResult;
import com.zitga.idle.battle.model.message.log.effect.BattleTeamEffectLog;
import com.zitga.idle.enumeration.battle.ActionResultType;

import java.util.ArrayList;
import java.util.List;

public class BattleTurnLog {

    private int round;
    private int turn;

    private BaseHeroLog initiator;
    private int actionType;

    private List<BaseActionResult> baseActionResults = new ArrayList<>();

    private BattleTeamLog attacker;
    private BattleTeamLog defender;

    private BattleTeamEffectLog attackerEffect;
    private BattleTeamEffectLog defenderEffect;

    // ---------------------------------------- Getters ----------------------------------------
    public int getRound() {
        return round;
    }

    public int getTurn() {
        return turn;
    }

    public BaseHeroLog getInitiator() {
        return initiator;
    }

    public int getActionType() {
        return actionType;
    }

    public List<BaseActionResult> getBaseActionResults() {
        return baseActionResults;
    }

//    public BattleTeamLog getAttacker() {
//        return attacker;
//    }
//
//    public BattleTeamLog getDefender() {
//        return defender;
//    }

    public BattleTeamEffectLog getAttackerEffect() {
        return attackerEffect;
    }

    public BattleTeamEffectLog getDefenderEffect() {
        return defenderEffect;
    }

    // ---------------------------------------- Setters ----------------------------------------
    public void setTurnInfo(int round, int turn) {
        this.round = round;
        this.turn = turn;
    }

    public void setActionData(int actionType, LuaBaseHero baseHero) {
        this.actionType = actionType;

        initiator = new BaseHeroLog(baseHero);
    }

    public void addActionResult(LuaBaseActionResult baseActionResult) {
        int actionResultType = baseActionResult.getActionResultType();
        BaseHeroLog initiator = new BaseHeroLog(baseActionResult.getInitiator());
        BaseHeroLog target = new BaseHeroLog(baseActionResult.getTarget());
        ActionResultType type = ActionResultType.toType(actionResultType);

        switch (type) {
            case ATTACK:
                AttackResult attackResult = new AttackResult(initiator, target, actionResultType);
                attackResult.setTargetHpPercent(baseActionResult.getTargetHpPercent());

                LuaAttackResult luaAttackResult = (LuaAttackResult) baseActionResult;
                attackResult.setAttackInfo(luaAttackResult);
                baseActionResults.add(attackResult);
                break;
            case USE_ACTIVE_DAMAGE_SKILL:
                UseActiveSkillResult skillResult = new UseActiveSkillResult(initiator, target, actionResultType);
                skillResult.setTargetHpPercent(baseActionResult.getTargetHpPercent());

                LuaActiveSkillResult luaActiveSkillResult = (LuaActiveSkillResult) baseActionResult;
                skillResult.setSkillInfo(luaActiveSkillResult);
                baseActionResults.add(skillResult);
                break;
        }
    }

    public void setBattleTeamLog(LuaBattleTeamLog luaBattleTeamLog, boolean isAttacker) {
        BattleTeamLog battleTeamLog = new BattleTeamLog();
        battleTeamLog.setTeamId(luaBattleTeamLog.getTeamId());

        BattleTeamEffectLog battleTeamEffectLog = new BattleTeamEffectLog();

        List<LuaHeroStatusLog> heroStatusLogs = luaBattleTeamLog.getHeroStatusList();
        for (LuaHeroStatusLog heroStatusLog : heroStatusLogs) {
            battleTeamLog.addBaseHeroLog(heroStatusLog);
            battleTeamEffectLog.addBaseHeroEffectLog(heroStatusLog);
        }

        if (isAttacker) {
            attacker = battleTeamLog;
            attackerEffect = battleTeamEffectLog;
        } else {
            defender = battleTeamLog;
            defenderEffect = battleTeamEffectLog;
        }
    }
}
