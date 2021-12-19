package com.zitga.idle.battleInfo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zitga.idle.base.constant.BasicTag;
import com.zitga.idle.base.constant.CollectionName;
import com.zitga.idle.base.model.BasePlayerComponent;
import com.zitga.idle.battleInfo.constant.BattleInfoTag;
import com.zitga.idle.battleInfo.model.formation.TeamFormation;
import com.zitga.idle.enumeration.common.GameMode;
import com.zitga.idle.player.model.Player;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Version;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Entity(value = CollectionName.PLAYER_BATTLE_INFO, noClassnameStored = true)
public class PlayerBattleInfo extends BasePlayerComponent {

    @JsonIgnore
    @Version
    @Property(BasicTag.DOCUMENT_VERSION_TAG)
    private long documentVersion;

    @Embedded(BattleInfoTag.FORMATION_TAG)
    // key: game mode
    private Map<Integer, TeamFormation> formations = new ConcurrentHashMap<>();

    public PlayerBattleInfo() {
        // For serialize purpose
        this.initWithDefaultValues();
    }

    public PlayerBattleInfo(Player player) {
        super(player);
        this.initWithDefaultValues();
    }

    public void initWithDefaultValues() {
        formations = new ConcurrentHashMap<>();
    }

    // ---------------------------------------- Setters ----------------------------------------
    public void addFormation(GameMode gameMode, TeamFormation teamFormation) {
        formations.put(gameMode.getValue(), teamFormation);
    }
}