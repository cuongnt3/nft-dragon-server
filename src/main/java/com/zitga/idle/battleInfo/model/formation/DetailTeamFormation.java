package com.zitga.idle.battleInfo.model.formation;

import com.zitga.idle.battleInfo.constant.BattleInfoTag;
import com.zitga.idle.dragon.model.InventoryDragon;
import com.zitga.idle.dragon.model.PlayerDragonCollection;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Entity(value = "", noClassnameStored = true)
public class DetailTeamFormation {

    @Property(BattleInfoTag.FORMATION_TAG)
    private int formation = 1;

    @Embedded(BattleInfoTag.FRONT_LINE_TAG)
    // Key: position, start from 1
    private Map<Integer, InventoryDragon> detailFrontLine = new ConcurrentHashMap<>();

    @Embedded(BattleInfoTag.BACK_LINE_TAG)
    // Key: position, start from 1
    private Map<Integer, InventoryDragon> detailBackLine = new ConcurrentHashMap<>();

    public DetailTeamFormation() {
        // for serialize purpose
    }

    public DetailTeamFormation(TeamFormation teamFormation, PlayerDragonCollection heroCollection) {
        if (teamFormation != null && heroCollection != null) {
            formation = teamFormation.getFormation();

            Map<Integer, Long> frontLine = teamFormation.getFrontLine();
            for (Map.Entry<Integer, Long> entry : frontLine.entrySet()) {
                int position = entry.getKey();
                long heroInventoryId = entry.getValue();

                InventoryDragon hero = heroCollection.getDragon(heroInventoryId);
                if (hero != null) {
                    detailFrontLine.put(position, hero.clone());
                }
            }

            Map<Integer, Long> backLine = teamFormation.getBackLine();
            for (Map.Entry<Integer, Long> entry : backLine.entrySet()) {
                int position = entry.getKey();
                long heroInventoryId = entry.getValue();

                InventoryDragon hero = heroCollection.getDragon(heroInventoryId);
                if (hero != null) {
                    detailBackLine.put(position, hero.clone());
                }
            }
        }
    }

    // ---------------------------------------- Getters ----------------------------------------
    public Map<Integer, InventoryDragon> getDetailFrontLine() {
        return detailFrontLine;
    }

    public Map<Integer, InventoryDragon> getDetailBackLine() {
        return detailBackLine;
    }

    public int getFormation() {
        return formation;
    }

    // ---------------------------------------- Setters ----------------------------------------
    public void setFormation(int formation) {
        this.formation = formation;
    }
}