package com.zitga.idle.battle.model.message.log;

import com.zitga.idle.battle.model.battle.output.log.LuaBaseHero;

public class BaseHeroLog {

    private int heroId;
    private String name;
    private int teamId;
    private int level;
    private int star;

    private int position;
    private boolean isFontLine;

    public BaseHeroLog(LuaBaseHero luaBaseHero) {
        setInfo(luaBaseHero.getHeroId(),
                luaBaseHero.getHeroName(),
                luaBaseHero.getTeamId(),
                luaBaseHero.getLevel(),
                luaBaseHero.getStar());

        setPosition(luaBaseHero.getPosition(), luaBaseHero.isFrontLine());
    }

    // ---------------------------------------- Getters ----------------------------------------
//    public int getHeroId() {
//        return heroId;
//    }
//
//    public String getName() {
//        return name;
//    }
//
    public int getTeamId() {
        return teamId;
    }
//
//    public int getLevel() {
//        return level;
//    }
//
//    public int getStar() {
//        return star;
//    }

    public int getPosition() {
        return position;
    }

    public boolean isFontLine() {
        return isFontLine;
    }

    // ---------------------------------------- Setters ----------------------------------------
    public void setInfo(int heroId, String name, int teamId, int level, int star) {
        this.heroId = heroId;
        this.name = name;
        this.teamId = teamId;
        this.level = level;
        this.star = star;
    }

    public void setPosition(int position, boolean isFontLine) {
        this.position = position;
        this.isFontLine = isFontLine;
    }
}
