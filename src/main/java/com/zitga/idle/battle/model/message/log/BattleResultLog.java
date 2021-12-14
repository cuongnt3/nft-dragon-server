package com.zitga.idle.battle.model.message.log;

import com.zitga.idle.battle.model.battle.output.log.LuaBattleRoundLog;
import com.zitga.idle.battle.model.battle.output.log.LuaBattleTeamLog;
import com.zitga.idle.battle.model.battle.output.log.LuaBattleTurnLog;
import com.zitga.idle.battle.model.battle.output.log.LuaHeroStatusLog;

import java.util.ArrayList;
import java.util.List;

public class BattleResultLog {

    private boolean isAttackerWin;
    private int numberRound;

    private BattleTeamLog attackerTeam;
    private BattleTeamLog defenderTeam;

    private List<BattleRoundLog> battleRoundLogs = new ArrayList<>();

    // ---------------------------------------- Getters ----------------------------------------
    public boolean isAttackerWin() {
        return isAttackerWin;
    }

    public int getNumberRound() {
        return numberRound;
    }

//    public BattleTeamLog getAttackerTeam() {
//        return attackerTeam;
//    }
//
//    public BattleTeamLog getDefenderTeam() {
//        return defenderTeam;
//    }

    public List<BattleRoundLog> getBattleRoundLogs() {
        return battleRoundLogs;
    }

    public void setAttackerWin(boolean attackerWin) {
        isAttackerWin = attackerWin;
    }

    public void setNumberRound(int numberRound) {
        this.numberRound = numberRound;
    }

    public void setBattleTeamLog(LuaBattleTeamLog luaBattleTeamLog, boolean isAttacker) {
        BattleTeamLog battleTeamLog = new BattleTeamLog();
        battleTeamLog.setTeamId(luaBattleTeamLog.getTeamId());

        List<LuaHeroStatusLog> heroStatusLogs = luaBattleTeamLog.getHeroStatusList();
        for (LuaHeroStatusLog heroStatusLog : heroStatusLogs) {
            battleTeamLog.addBaseHeroLog(heroStatusLog);
        }

        if (isAttacker) {
            attackerTeam = battleTeamLog;
        } else {
            defenderTeam = battleTeamLog;
        }
    }

    public void addBattleRoundLog(LuaBattleRoundLog luaBattleRoundLog) {
        BattleRoundLog battleRoundLog = new BattleRoundLog();
        battleRoundLog.setRoundNumber(luaBattleRoundLog.getRoundNumber());

        List<LuaBattleTurnLog> battleTurnLogs = luaBattleRoundLog.getBattleTurnList();
        for (LuaBattleTurnLog battleTurnLog : battleTurnLogs) {
            battleRoundLog.addBattleTurn(battleTurnLog);
        }

        battleRoundLogs.add(battleRoundLog);
    }
}
