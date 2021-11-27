package com.zitga.summon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zitga.base.constant.BasicTag;
import com.zitga.base.constant.CollectionName;
import com.zitga.base.model.BasePlayerComponent;
import com.zitga.player.model.Player;
import com.zitga.summon.constant.SummonTag;
import com.zitga.utils.TimeUtils;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Version;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Entity(value = CollectionName.PLAYER_DRAGON_SUMMON, noClassnameStored = true)
public class PlayerDragonSummon extends BasePlayerComponent {

    @JsonIgnore
    @Version
    @Property(BasicTag.DOCUMENT_VERSION_TAG)
    private long documentVersion;

    @JsonProperty("0")
    @Embedded(SummonTag.START_HATCH_TIME_TAG)
    // key: inventory of egg
    private final Map<Long, Date> startHatchTimeMap = new ConcurrentHashMap<>();

    public PlayerDragonSummon() {
        // For serialize purpose
        initWithDefaultValues();
    }

    public PlayerDragonSummon(Player player) {
        super(player);
        initWithDefaultValues();
    }

    private void initWithDefaultValues() {

    }

    // ---------------------------------------- Getters ----------------------------------------
    @JsonIgnore
    public Date getStartHatchTime(long eggInventoryId) {
        return startHatchTimeMap.get(eggInventoryId);
    }

    // ---------------------------------------- Setters ----------------------------------------
    public void onCompletedHatchEgg(long eggInventoryId) {
        startHatchTimeMap.remove(eggInventoryId);
    }

    public void onStartIncubateEgg(long eggInventoryId) {
        startHatchTimeMap.put(eggInventoryId, TimeUtils.getCurrentTimeInGMT());
    }
}
