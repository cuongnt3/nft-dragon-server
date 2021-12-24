package com.zitga.idle.pve.model.message;

public class DragonBotData {

    private int dragonId;

    private int level;

    private int star;

    private int position;

    private boolean isFrontLine;

    public int getDragonId() {
        return dragonId;
    }

    public int getLevel() {
        return level;
    }

    public int getStar() {
        return star;
    }

    public int getPosition() {
        return position;
    }

    public boolean isFrontLine() {
        return isFrontLine;
    }

    public void setDragonId(int dragonId) {
        this.dragonId = dragonId;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setFrontLine(boolean frontLine) {
        isFrontLine = frontLine;
    }
}
