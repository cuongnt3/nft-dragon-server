package com.zitga.dragon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zitga.base.constant.BasicTag;
import com.zitga.base.constant.CollectionName;
import com.zitga.base.model.BasePlayerComponent;
import com.zitga.dragon.constant.DragonTag;
import com.zitga.player.model.Player;
import org.mongodb.morphia.annotations.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Entity(value = CollectionName.PLAYER_DRAGON_COLLECTION, noClassnameStored = true)
public class PlayerDragonCollection extends BasePlayerComponent {

    @JsonIgnore
    @Version
    @Property(BasicTag.DOCUMENT_VERSION_TAG)
    private long documentVersion;

    @JsonProperty("0")
    @Embedded(DragonTag.DRAGON_LIST_TAG)
    // Key: id of hero in collection
    private final Map<Long, InventoryDragon> dragons = new ConcurrentHashMap<>();

    @JsonProperty("1")
    @Embedded(DragonTag.DRAGON_EGG_LIST_TAG)
    // Key: id of egg in collection
    private final Map<Long, InventoryDragonEgg> dragonEggs = new ConcurrentHashMap<>();

    @Transient
    @NotSaved
    private final Object dragonLock = new Object();

    @Transient
    @NotSaved
    private final Object dragonEggLock = new Object();

    public PlayerDragonCollection() {
        // For serialize purpose
    }

    public PlayerDragonCollection(Player player) {
        super(player);
    }

    // ---------------------------------------- Getters ----------------------------------------
    public void addDragon(InventoryDragon dragon) {
        synchronized (dragonLock) {
            dragons.put(dragon.getInventoryId(), dragon);
        }
    }

    public void addDragonEgg(InventoryDragonEgg egg) {
        synchronized (dragonLock) {
            dragonEggs.put(egg.getInventoryId(), egg);
        }
    }
}
