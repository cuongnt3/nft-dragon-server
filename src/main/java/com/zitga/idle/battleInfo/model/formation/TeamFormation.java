package com.zitga.idle.battleInfo.model.formation;

import com.zitga.idle.battle.model.message.BattleHeroInbound;
import com.zitga.idle.battleInfo.constant.BattleInfoTag;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Entity(value = "", noClassnameStored = true)
public class TeamFormation {

    @Embedded(BattleInfoTag.FRONT_LINE_TAG)
    // Key: position, value: heroInventoryId
    private Map<Integer, Long> frontLine = new ConcurrentHashMap<>();

    @Embedded(BattleInfoTag.BACK_LINE_TAG)
    // Key: position, value: heroInventoryId
    private Map<Integer, Long> backLine = new ConcurrentHashMap<>();

    public TeamFormation() {
        // For serialize purpose
    }

    // ---------------------------------------- Getters ----------------------------------------
    public Map<Integer, Long> getFrontLine() {
        return frontLine;
    }

    public Map<Integer, Long> getBackLine() {
        return backLine;
    }

    public boolean isContainHero(long heroInventoryId) {
        if (frontLine.containsValue(heroInventoryId)) {
            return true;
        }

        if (backLine.containsValue(heroInventoryId)) {
            return true;
        }

        return false;
    }

    public int getNumberHeroes() {
        return frontLine.size() + backLine.size();
    }

    public Map<Integer, Long> getAllHero() {
        Map<Integer, Long> allHero = new ConcurrentHashMap<>(frontLine);
        allHero.putAll(backLine);
        return allHero;
    }

    public boolean isFrontLine(int position, long heroInventoryId) {
        long heroId = frontLine.getOrDefault(position, 0L);
        if (heroId == heroInventoryId) {
            return true;
        }
        return false;
    }

    // ---------------------------------------- Setters ----------------------------------------
    public void addHero(BattleHeroInbound heroInbound) {
        if (heroInbound.isFrontLine()) {
            frontLine.put(heroInbound.getPosition(), heroInbound.getHeroInventoryId());
        } else {
            backLine.put(heroInbound.getPosition(), heroInbound.getHeroInventoryId());
        }
    }
}
