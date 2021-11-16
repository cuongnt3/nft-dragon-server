package com.zitga.hatch.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zitga.base.constant.BasicTag;
import com.zitga.base.constant.CollectionName;
import com.zitga.enumeration.hatch.HatchType;
import com.zitga.hatch.constant.HatchTag;
import com.zitga.hatch.model.base.BasePlayerComponent;
import com.zitga.player.model.Player;
import com.zitga.utils.TimeUtils;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Version;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Entity(value = CollectionName.PLAYER_DRAGON_HATCH, noClassnameStored = true)
public class PlayerDragonHatch extends BasePlayerComponent {

    @JsonIgnore
    @Version
    @Property(BasicTag.DOCUMENT_VERSION_TAG)
    private long documentVersion;

    @Embedded(HatchTag.LAST_HATCH_TIME_TAG)
    // key: hatch type
    private final Map<Integer, Date> lastHatchTimeMap = new ConcurrentHashMap<>();

    public PlayerDragonHatch() {
        // For serialize purpose
        initWithDefaultValues();
    }

    public PlayerDragonHatch(Player player) {
        super(player);
        initWithDefaultValues();
    }

    private void initWithDefaultValues() {
        Date lastFree = TimeUtils.addMonths(TimeUtils.getCurrentTimeInGMT(), -1);
        for (int i = 0; i < HatchType.values().length; i++) {
            HatchType summonType = HatchType.toType(i);
            if (summonType == HatchType.BASIC || summonType == HatchType.PREMIUM) {
                lastHatchTimeMap.put(i, lastFree);
            }
        }
    }

    // ---------------------------------------- Getters ----------------------------------------
    public Date getLastHatchTime(HatchType hatchType) {
        return lastHatchTimeMap.get(hatchType.getValue());
    }

    // ---------------------------------------- Setters ----------------------------------------
    public void updateHatchTime(HatchType hatchType, Date date) {
        this.lastHatchTimeMap.put(hatchType.getValue(), date);
    }
}
