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

    @JsonProperty("2")
    @Embedded(DragonTag.EGG_FRAGMENT_LIST_TAG)
    // Key: egg fragment id
    private final Map<Integer, Integer> eggFragments = new ConcurrentHashMap<>();

    @Transient
    @NotSaved
    private final Object dragonLock = new Object();

    @Transient
    @NotSaved
    private final Object dragonEggLock = new Object();

    @Transient
    @NotSaved
    private final Object eggFragmentLock = new Object();

    public PlayerDragonCollection() {
        // For serialize purpose
    }

    public PlayerDragonCollection(Player player) {
        super(player);
    }

    // ---------------------------------------- Getters ----------------------------------------
    public int getNumberDragons() {
        return dragons.size();
    }

    public int getNumberEggs() {
        return dragonEggs.size();
    }

    public InventoryDragonEgg getDragonEgg(long eggInventory) {
        return dragonEggs.get(eggInventory);
    }

    // ---------------------------------------- Setters ----------------------------------------
    public void addDragon(InventoryDragon dragon) {
        synchronized (dragonLock) {
            dragons.put(dragon.getInventoryId(), dragon);
        }
    }

    public void addDragonEgg(InventoryDragonEgg egg) {
        synchronized (dragonEggLock) {
            dragonEggs.put(egg.getInventoryId(), egg);
        }
    }

    public void addEggFragment(int eggFragmentId, int number) {
        synchronized (eggFragmentLock) {
            int currentNumber = eggFragments.getOrDefault(eggFragmentId, 0);
            eggFragments.put(eggFragmentId, currentNumber + number);
        }
    }

    public void removeDragonEgg(long inventoryId) {
        synchronized (dragonEggLock) {
            dragonEggs.remove(inventoryId);
        }
    }
}
