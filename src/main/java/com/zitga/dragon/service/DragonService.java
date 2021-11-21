package com.zitga.dragon.service;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.dragon.constant.DragonConstant;
import com.zitga.dragon.dao.PlayerDragonDAO;
import com.zitga.dragon.model.InventoryDragon;
import com.zitga.dragon.model.InventoryDragonEgg;
import com.zitga.dragon.model.PlayerDragonCollection;
import com.zitga.enumeration.dragon.EggType;
import com.zitga.enumeration.resource.ResourceType;
import com.zitga.player.model.Player;
import com.zitga.resource.annotation.RewardController;
import com.zitga.resource.model.IRewardController;
import com.zitga.resource.model.Reward;

@BeanComponent
@RewardController({
        ResourceType.DRAGON,
        ResourceType.EGG_FRAGMENT,
        ResourceType.EGG
})
public class DragonService implements IRewardController {

    @BeanField
    private PlayerDragonDAO dragonDAO;

    public void loadDragonCollection(Player player) {
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
