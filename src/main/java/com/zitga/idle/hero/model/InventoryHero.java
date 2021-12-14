package com.zitga.idle.hero.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zitga.core.message.socket.IDeserializable;
import com.zitga.core.message.socket.ISerializable;
import com.zitga.idle.ServiceController;
import com.zitga.idle.base.constant.BasicTag;
import com.zitga.idle.enumeration.hero.HeroFactionType;
import com.zitga.idle.enumeration.hero.HeroItemSlot;
import com.zitga.idle.hero.constant.HeroConstant;
import com.zitga.idle.hero.constant.HeroTag;
import io.netty.buffer.ByteBuf;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Entity(value = "", noClassnameStored = true)
public class  InventoryHero implements ISerializable, IDeserializable {

    @JsonProperty("0")
    @Property(BasicTag.ID_TAG)
    private long inventoryId; // id of hero in inventory

    @JsonProperty("1")
    @Property(HeroTag.HERO_ID_TAG)
    private int heroId;

    @JsonProperty("2")
    @Property(BasicTag.STAR_TAG)
    private int star;

    @JsonProperty("3")
    @Property(BasicTag.LEVEL_TAG)
    private int level;

    @JsonProperty("4")
    @Embedded(HeroTag.HERO_ITEM_TAG)
    // Key: HeroItemSlot, value: itemId
    private Map<Integer, Integer> items = new ConcurrentHashMap<>();

    @JsonProperty("5")
    @Property(HeroTag.HERO_LOCK_TAG)
    private boolean isLock;

    public InventoryHero() {
        this.inventoryId = ServiceController.instance().getIdGeneratorService().generateHeroId();

        this.star = HeroConstant.MIN_STAR;

        this.level = HeroConstant.MIN_LEVEL;
        this.isLock = false;
    }

    // ---------------------------------------- Getters ----------------------------------------
    @JsonIgnore
    public long getInventoryId() {
        return inventoryId;
    }

    @JsonIgnore
    public int getHeroId() {
        return heroId;
    }

    @JsonIgnore
    public int getStar() {
        return star;
    }

    @JsonIgnore
    public int getLevel() {
        return level;
    }

    @JsonIgnore
    public int getItem(HeroItemSlot slot) {
        return items.getOrDefault(slot.getValue(), -1);
    }

    @JsonIgnore
    public boolean isLock() {
        return isLock;
    }

    @JsonIgnore
    public Map<Integer, Integer> getItems() {
        return items;
    }

    @JsonIgnore
    public HeroFactionType getFactionType() {
        int factionType = heroId / HeroConstant.HERO_ID_DELTA;
        return HeroFactionType.toFactionType(factionType);
    }

    @JsonProperty("6")
    public boolean isHideSkin() {
        int value = items.getOrDefault(HeroConstant.HERO_HIDE_SKIN_SLOT, 0);
        if (value == 0) {
            return false;
        } else {
            return true;
        }
    }

    public void serializeBasicInfo(ByteBuf out) {
        out.writeIntLE(heroId);
        out.writeByte(star);
        out.writeShortLE(level);
    }

    @Override
    public void serialize(ByteBuf out) {
        out.writeLongLE(inventoryId);
        out.writeIntLE(heroId);
        out.writeByte(star);
        out.writeShortLE(level);

        out.writeByte(items.size());
        for (Map.Entry<Integer, Integer> item : items.entrySet()) {
            out.writeByte(item.getKey());
            out.writeIntLE(item.getValue());
        }

        out.writeBoolean(isLock);
        out.writeBoolean(isHideSkin());
    }

    // ---------------------------------------- Setters ----------------------------------------
    public void setHeroId(int heroId) {
        this.heroId = heroId;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void resetAllItem() {
        this.items.clear();
    }

    public void setItem(HeroItemSlot slot, int itemId) {
        if (itemId != -1) {
            items.put(slot.getValue(), itemId);
        } else {
            items.remove(slot.getValue());
        }
    }

    public void hideSkin(boolean isHide) {
        int value = 0;
        if (isHide) {
            value = 1;
        }

        items.put(HeroConstant.HERO_HIDE_SKIN_SLOT, value);
    }

    public void resetItem() {
        this.items.clear();
    }

    public void setLock(boolean isLock) {
        this.isLock = isLock;
    }
	
    public void deserializeBasicInfo(ByteBuf in) {
        heroId = in.readIntLE();
        star = in.readUnsignedByte();
        level = in.readShortLE();
    }

    @Override
    public void deserialize(ByteBuf in) {
        inventoryId = in.readLongLE();
        heroId = in.readIntLE();
        star = in.readUnsignedByte();
        level = in.readShortLE();

        int itemSize = in.readUnsignedByte();
        items = new ConcurrentHashMap<>();
        for (int i = 0; i < itemSize; i++) {
            int itemSlot = in.readUnsignedByte();
            int itemId = in.readIntLE();

            items.put(itemSlot, itemId);
        }

        isLock = in.readBoolean();
        hideSkin(in.readBoolean());
    }

    @Override
    public InventoryHero clone() {
        InventoryHero result = new InventoryHero();
        result.inventoryId = inventoryId;
        result.heroId = heroId;
        result.star = star;
        result.level = level;
        result.items = new ConcurrentHashMap<>(items);
        result.isLock = isLock;
        return result;
    }
}
