package com.zitga.summon.service;

import com.zitga.base.constant.LogicCode;
import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.dragon.model.InventoryDragon;
import com.zitga.dragon.model.InventoryDragonEgg;
import com.zitga.dragon.model.PlayerDragonCollection;
import com.zitga.dragon.service.DragonService;
import com.zitga.player.model.Player;
import com.zitga.resource.model.Reward;
import com.zitga.summon.dao.PlayerDragonSummonDAO;
import com.zitga.summon.model.PlayerDragonSummon;
import com.zitga.summon.model.message.SummonResult;
import com.zitga.utils.TimeUtils;

import java.util.Date;

@BeanComponent
public class SummonService {

    @BeanField
    private SummonDataService summonDataService;

    @BeanField
    private PlayerDragonSummonDAO dragonHatchDAO;

    @BeanField
    private DragonService dragonService;

    public void loadDragonSummon(Player player) {
        PlayerDragonSummon dragonSummon = dragonHatchDAO.findOrCreate(player);
        player.setDragonSummon(dragonSummon);
    }

    public void saveDragonSummon(Player player) {
        dragonHatchDAO.save(player.getOrLoadDragonSummon());
    }

    // ---------------------------------------- Handler ----------------------------------------
    public int incubateEgg(Player player, long eggInventoryId) {
        PlayerDragonSummon dragonSummon = player.getOrLoadDragonSummon();
        PlayerDragonCollection dragonCollection = player.getOrLoadDragon();

        if (dragonService.isFullDragonCollection(player, 1)) {
            return LogicCode.DRAGON_COLLECTION_FULL;
        }

        InventoryDragonEgg dragonEgg = dragonCollection.getDragonEgg(eggInventoryId);
        if (dragonEgg == null) {
            return LogicCode.DRAGON_EGG_INVENTORY_ID_INVALID;
        }

        Date startHatchTime = dragonSummon.getStartHatchTime(eggInventoryId);
        if (startHatchTime != null) {
            return LogicCode.DRAGON_EGG_ALREADY_INCUBATED;
        }

        dragonSummon.onStartIncubateEgg(eggInventoryId);
        saveDragonSummon(player);
        return LogicCode.SUCCESS;
    }

    public SummonResult summonDragon(Player player, long eggInventoryId) {
        SummonResult result = new SummonResult();

        PlayerDragonSummon dragonSummon = player.getOrLoadDragonSummon();
        PlayerDragonCollection dragonCollection = player.getOrLoadDragon();

        if (dragonService.isFullDragonCollection(player, 1)) {
            return result.withCode(LogicCode.DRAGON_COLLECTION_FULL);
        }

        InventoryDragonEgg dragonEgg = dragonCollection.getDragonEgg(eggInventoryId);
        if (dragonEgg == null) {
            return result.withCode(LogicCode.DRAGON_EGG_INVENTORY_ID_INVALID);
        }

        Date startHatchTime = dragonSummon.getStartHatchTime(eggInventoryId);
        if (startHatchTime == null) {
            return result.withCode(LogicCode.DRAGON_EGG_NOT_INCUBATED);
        }

        if (!isCanSummon(startHatchTime, dragonEgg.getEggType())) {
            return result.withCode(LogicCode.DRAGON_EGG_IN_HATCH_DURATION);
        }

        removeHatchedEgg(player, eggInventoryId);
        summonAndSaveData(player, dragonEgg, result);

        return result.withCode(LogicCode.SUCCESS);
    }

    // ---------------------------------------- Summon helpers ----------------------------------------
    public void removeHatchedEgg(Player player, long inventoryId) {
        PlayerDragonCollection dragonCollection = player.getOrLoadDragon();
        PlayerDragonSummon dragonSummon = player.getOrLoadDragonSummon();
        dragonCollection.removeDragonEgg(inventoryId);
        dragonSummon.onCompletedHatchEgg(inventoryId);
    }

    public void summonAndSaveData(Player player, InventoryDragonEgg dragonEgg, SummonResult result) {
        Reward summonReward = summonDataService.randomHatchEgg(player, dragonEgg);

        switch (summonReward.getType()) {
            case DRAGON:
                InventoryDragon inventoryDragon = dragonService.addDragon(player, summonReward.getId(), summonReward.getData());
                result.setDragon(inventoryDragon);
                break;

            case EGG_FRAGMENT:
                result.setReward(summonReward);
                break;

        }

        // save data
        dragonService.saveDragonCollection(player);
        saveDragonSummon(player);
    }

    public boolean isCanSummon(Date hatchTime, int eggType) {
        int hatchDuration = summonDataService.getHatchDuration(eggType);

        Date expectedTime = TimeUtils.addSeconds(hatchTime, hatchDuration);
        Date now = TimeUtils.getCurrentTimeInGMT();
        return now.after(expectedTime);
    }
}