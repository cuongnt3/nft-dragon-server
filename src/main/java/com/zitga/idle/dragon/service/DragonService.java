package com.zitga.idle.dragon.service;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.idle.dragon.constant.DragonConstant;
import com.zitga.idle.dragon.dao.PlayerDragonDAO;
import com.zitga.idle.dragon.model.InventoryDragon;
import com.zitga.idle.dragon.model.InventoryDragonEgg;
import com.zitga.idle.dragon.model.PlayerDragonCollection;
import com.zitga.idle.enumeration.dragon.EggType;
import com.zitga.idle.enumeration.resource.ResourceType;
import com.zitga.idle.player.model.IAsyncPlayerDataManageable;
import com.zitga.idle.player.model.Player;
import com.zitga.idle.resource.annotation.RewardController;
import com.zitga.idle.resource.model.IRewardController;
import com.zitga.idle.resource.model.Reward;

@BeanComponent
@RewardController({
        ResourceType.DRAGON,
        ResourceType.EGG_FRAGMENT,
        ResourceType.EGG
})
public class DragonService implements IRewardController, IAsyncPlayerDataManageable {

    @BeanField
    private PlayerDragonDAO dragonDAO;

    @Override
    public void createPlayerData(Player player) {
        PlayerDragonCollection dragonCollection = dragonDAO.create(player);
        player.setDragonCollection(dragonCollection);
    }

    @Override
    public void loadPlayerData(Player player) {
        PlayerDragonCollection dragonCollection = dragonDAO.findOrCreate(player);
        player.setDragonCollection(dragonCollection);
    }

    public void saveDragonCollection(Player player) {
        dragonDAO.save(player.getOrLoadDragon());
    }

    @Override
    public void addReward(Player player, Reward reward) {
        switch (reward.getType()) {
            case DRAGON:
                addDragon(player, reward.getId(), reward.getData());
                break;

            case EGG:
                addDragonEgg(player, reward.getId());
                break;

            case EGG_FRAGMENT:
                addEggFragment(player, reward.getId(), reward.getNumber());
                break;
        }
    }

    @Override
    public void saveReward(Player player) {
        saveDragonCollection(player);
    }

    public InventoryDragon addDragon(Player player, int dragonId, int star) {
        PlayerDragonCollection dragonCollection = player.getOrLoadDragon();

        InventoryDragon dragon = new InventoryDragon();

        dragon.setDragonId(dragonId);
        dragon.setStar(star);
        dragonCollection.addDragon(dragon);
        return dragon;
    }

    public InventoryDragonEgg addDragonEgg(Player player, int eggId) {
        PlayerDragonCollection dragonCollection = player.getOrLoadDragon();

        InventoryDragonEgg dragonEgg = new InventoryDragonEgg(EggType.toType(eggId));
        dragonCollection.addDragonEgg(dragonEgg);
        return dragonEgg;
    }

    public void addEggFragment(Player player, int fragmentId, int number) {
        PlayerDragonCollection heroCollection = player.getOrLoadDragon();
        heroCollection.addEggFragment(fragmentId, number);
    }

    // ---------------------------------------- Helpers ----------------------------------------
    public boolean isFullDragonCollection(Player player, int number) {
        PlayerDragonCollection dragonCollection = player.getOrLoadDragon();
        return dragonCollection.getNumberDragons() + number > DragonConstant.MAX_DRAGON_NUMBER;
    }

    public boolean isFullEggCollection(Player player, int number) {
        PlayerDragonCollection dragonCollection = player.getOrLoadDragon();
        return dragonCollection.getNumberEggs() + number > DragonConstant.MAX_EGG_NUMBER;
    }
}
