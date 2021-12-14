package com.zitga.idle.dragon.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zitga.idle.ServiceController;
import com.zitga.idle.dragon.constant.DragonConstant;
import com.zitga.idle.dragon.constant.DragonTag;
import com.zitga.idle.base.constant.BasicTag;
import com.zitga.idle.enumeration.hero.HeroItemSlot;
import com.zitga.idle.item.constant.ItemConstant;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Entity(value = "", noClassnameStored = true)
public class InventoryDragon {

    @JsonProperty("0")
    @Property(BasicTag.ID_TAG)
    private long inventoryId; // id of hero in inventory

    @JsonProperty("1")
    @Property(DragonTag.DRAGON_ID_TAG)
    private int dragonId;

    @JsonProperty("2")
    @Property(BasicTag.STAR_TAG)
    private int star;

    @JsonProperty("3")
    @Property(BasicTag.LEVEL_TAG)
    private int level;

    @JsonProperty("4")
    @Embedded(DragonTag.DRAGON_ITEM_TAG)
    // Key: HeroItemSlot, value: itemId
    private Map<Integer, Integer> items = new ConcurrentHashMap<>();

    public InventoryDragon() {
        this.inventoryId = ServiceController.instance().getIdGeneratorService().generateDragonId();

        this.star = DragonConstant.MIN_STAR;

        this.level = DragonConstant.MIN_LEVEL;
    }

    // ---------------------------------------- Getters ----------------------------------------
    public long getInventoryId() {
        return inventoryId;
    }

    public int getDragonId() {
        return dragonId;
    }

    public int getStar() {
        return star;
    }

    public int getLevel() {
        return level;
    }

    public int getItem(HeroItemSlot slot) {
        return items.getOrDefault(slot.getValue(), ItemConstant.NOT_EXISTED_ITEM);
    }

    @Override
    public InventoryDragon clone() {
        InventoryDragon result = new InventoryDragon();
        result.inventoryId = inventoryId;
        result.dragonId = dragonId;
        result.star = star;
        result.level = level;
        result.items = new ConcurrentHashMap<>(items);
        return result;
    }

    // ---------------------------------------- Setters ----------------------------------------
    public void setInventoryId(long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public void setDragonId(int dragonId) {
        this.dragonId = dragonId;
    }

    public void setStar(int star) {
        this.star = star;
    }
}
