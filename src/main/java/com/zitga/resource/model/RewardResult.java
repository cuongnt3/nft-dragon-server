package com.zitga.resource.model;

import java.util.ArrayList;
import java.util.List;

public class RewardResult {

    private int resultCode;
    private boolean isTest = false;
    private List<Reward> rewards = new ArrayList<>();

    // ---------------------------------------- Getters ----------------------------------------
    public int getResultCode() {
        return resultCode;
    }

    public List<Reward> getRewards() {
        return rewards;
    }

    public boolean isTest() {
        return isTest;
    }

    // ---------------------------------------- Setters ----------------------------------------
    public RewardResult withCode(int logicCode) {
        this.resultCode = logicCode;
        return this;
    }

    public void setRewards(List<Reward> rewards) {
        this.rewards = rewards;
    }

    public void addRewards(List<Reward> rewardList) {
        rewards.addAll(rewardList);
    }

    public void setTest(boolean test) {
        isTest = test;
    }
}