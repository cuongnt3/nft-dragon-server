package com.zitga.idle.battle.model.message.log;

import com.zitga.idle.battle.model.battle.output.log.LuaHeroStatusLog;
import com.zitga.idle.battle.model.message.HeroStatusLog;

import java.util.ArrayList;
import java.util.List;

public class BattleTeamLog {

    private int teamId;
    private List<HeroStatusLog> heroStatusLogs = new ArrayList<>();

    // ---------------------------------------- Getters ----------------------------------------
    public int getTeamId() {
        return teamId;
    }

    public List<HeroStatusLog> getHeroStatusLogs() {
        return heroStatusLogs;
    }

    // ---------------------------------------- Setters ----------------------------------------
    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public void addBaseHeroLog(LuaHeroStatusLog heroStatusLog) {
        HeroStatusLog statusLog = new HeroStatusLog();

        statusLog.setHpPercent(heroStatusLog.getHpPercent());
        statusLog.setPowerPercent(heroStatusLog.getPowerPercent());
        statusLog.setBaseHero(heroStatusLog.getBaseHero());

        heroStatusLogs.add(statusLog);
    }
}
