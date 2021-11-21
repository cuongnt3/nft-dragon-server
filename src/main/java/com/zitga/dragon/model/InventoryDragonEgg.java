package com.zitga.dragon.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zitga.ServiceController;
import com.zitga.base.constant.BasicTag;
import com.zitga.dragon.constant.DragonTag;
import com.zitga.enumeration.dragon.EggType;
import com.zitga.utils.TimeUtils;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;

import java.util.Date;

@Entity(value = "", noClassnameStored = true)
public class InventoryDragonEgg {

    @JsonProperty("0")
    @Property(BasicTag.ID_TAG)
    private long inventoryId; // id of hero in inventory

    @JsonProperty("1")
    @Property(DragonTag.DRAGON_ID_TAG)
    private int eggType;

    @JsonProperty("2")
    @Property(DragonTag.RECEIVE_TIME_TAG)
    private Date receiveTime;

    public InventoryDragonEgg() {
        // For serialize purpose
    }

    public InventoryDragonEgg(EggType type) {
        this.inventoryId = ServiceController.instance().getIdGeneratorService().generateDragonId();
        this.eggType = type.getValue();
        receiveTime = TimeUtils.getCurrentTimeInGMT();
    }

    // ---------------------------------------- Getters ----------------------------------------
    public long getInventoryId() {
        return inventoryId;
    }

    @Override
    public InventoryDragonEgg clone() {
        InventoryDragonEgg result = new InventoryDragonEgg();
        result.inventoryId = inventoryId;
        result.eggType = eggType;
        result.receiveTime = receiveTime;
        return result;
    }
}
