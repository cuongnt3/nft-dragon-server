package com.zitga.summon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zitga.base.constant.BasicTag;
import com.zitga.base.constant.CollectionName;
import com.zitga.enumeration.summon.SummonType;
import com.zitga.summon.constant.SummonTag;
import com.zitga.base.model.BasePlayerComponent;
import com.zitga.player.model.Player;
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

    @Embedded(SummonTag.LAST_HATCH_TIME_TAG)
    // key: hatch type
    private final Map<Integer, Date> lastSummonTimeMap = new ConcurrentHashMap<>();

    public PlayerDragonSummon() {
        // For serialize purpose
        initWithDefaultValues();
    }

    public PlayerDragonSummon(Player player) {
        super(player);
        initWithDefaultValues();
    }

    private void initWithDefaultValues() {
        Date lastFree = TimeUtils.addMonths(TimeUtils.getCurrentTimeInGMT(), -1);
        for (int i = 0; i < SummonType.values().length; i++) {
            SummonType summonType = SummonType.toType(i);
            if (summonType == SummonType.BASIC || summonType == SummonType.PREMIUM) {
                lastSummonTimeMap.put(i, lastFree);
            }
        }
    }

    // ---------------------------------------- Getters ----------------------------------------
    public Date getLastHatchTime(SummonType summonType) {
        return lastSummonTimeMap.get(summonType.getValue());
    }

    // ---------------------------------------- Setters ----------------------------------------
    public void updateHatchTime(SummonType summonType, Date date) {
        this.lastSummonTimeMap.put(summonType.getValue(), date);
    }
}
