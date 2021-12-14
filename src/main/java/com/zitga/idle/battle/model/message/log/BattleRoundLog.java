package com.zitga.idle.battle.model.message.log;

import com.zitga.idle.battle.model.battle.output.log.LuaBaseActionResult;
import com.zitga.idle.battle.model.battle.output.log.LuaBattleTurnLog;
import com.zitga.idle.battle.model.battle.output.log.LuaHeroStatusLog;

import java.util.ArrayList;
import java.util.List;

public class BattleRoundLog {

    private int roundNumber;

    private List<BattleTurnLog> battleTurnLogs = new ArrayList<>();

    // ---------------------------------------- Getters ----------------------------------------
    public int getRoundNumber() {
        return roundNumber;
    }

    public List<BattleTurnLog> getBattleTurnLogs() {
        return battleTurnLogs;
    }

    // ---------------------------------------- Setters ----------------------------------------
    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public void addBattleTurn(LuaBattleTurnLog luaBattleTurnLog) {
        BattleTurnLog battleTurnLog = new BattleTurnLog();
        battleTurnLog.setTurnInfo(luaBattleTurnLog.getRound(), luaBattleTurnLog.getTurn());

        battleTurnLog.setActionData(luaBattleTurnLog.getActionType(), luaBattleTurnLog.getBaseHero());

        for (LuaBaseActionResult actionResult: luaBattleTurnLog.getActionResults()){
            battleTurnLog.addActionResult(actionResult);
        }

        battleTurnLog.setBattleTeamLog(luaBattleTurnLog.getBattleTeamLog(true), true);
        battleTurnLog.setBattleTeamLog(luaBattleTurnLog.getBattleTeamLog(false), false);

        battleTurnLogs.add(battleTurnLog);
    }
}
