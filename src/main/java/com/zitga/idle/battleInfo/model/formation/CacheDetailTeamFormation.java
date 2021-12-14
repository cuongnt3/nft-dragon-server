package com.zitga.idle.battleInfo.model.formation;

public class CacheDetailTeamFormation {
    private DetailTeamFormation detailTeamFormation;
    private long timeCreate;

    public DetailTeamFormation getDetailTeamFormation() {
        return detailTeamFormation;
    }

    public long getTimeCreate() {
        return timeCreate;
    }

    public void setDetailTeamFormation(DetailTeamFormation detailTeamFormation) {
        this.detailTeamFormation = detailTeamFormation;
    }

    public void setTimeCreate(long timeCreate) {
        this.timeCreate = timeCreate;
    }
}
