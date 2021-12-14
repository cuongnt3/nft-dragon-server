package com.zitga.idle.battle.utils;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.idle.battle.model.battle.output.LuaBattleResult;
import com.zitga.idle.battle.model.battle.output.log.LuaBattleRoundLog;
import com.zitga.idle.battle.model.battle.output.log.LuaBattleTeamLog;
import com.zitga.idle.battle.model.message.log.BattleResultLog;
import com.zitga.idle.lua.model.LuaList;

@BeanComponent
public class BattleLogUtils {

    public static BattleResultLog createBattleLog(LuaBattleResult luaBattleResult) {
        BattleResultLog resultLog = new BattleResultLog();

        int numberRound = luaBattleResult.getNumberRounds();
        boolean isAttackerWin = luaBattleResult.isAttackerWin();

        LuaBattleTeamLog attackerTeam = luaBattleResult.getBattleTeamLog(true);
        LuaBattleTeamLog defenderTeam = luaBattleResult.getBattleTeamLog(false);

        resultLog.setNumberRound(numberRound);
        resultLog.setAttackerWin(isAttackerWin);

        resultLog.setBattleTeamLog(attackerTeam, true);
        resultLog.setBattleTeamLog(defenderTeam, false);

        LuaList luaList = luaBattleResult.getBattleRoundLog();
        for (int i = 1; i <= luaList.count(); i++) {
            LuaBattleRoundLog battleRoundLog = new LuaBattleRoundLog(luaList.get(i));
            resultLog.addBattleRoundLog(battleRoundLog);
        }

        return resultLog;
    }
}
