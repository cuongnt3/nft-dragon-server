package com.zitga.dragon.service;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
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
        ResourceType.EGG
})
public class DragonService implements IRewardController {

    @BeanField
    private PlayerDragonDAO dragonDAO;

    public void loadDragonCollection(Player player) {
        PlayerDragonCollection dragonCollection = dragonDAO.findOrCreate(player);
        player.setHeroCollection(dragonCollection);
    }

    public void saveHeroCollection(Player player) {
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
        }
    }

    @Override
    public void saveReward(Player player) {
        saveHeroCollection(player);
    }

    public InventoryDragon addDragon(Player player, int dragonId, int star) {
        PlayerDragonCollection heroCollection = player.getOrLoadDragon();

        InventoryDragon dragon = new InventoryDragon();

        dragon.setDragonId(dragonId);
        dragon.setStar(star);
        heroCollection.addDragon(dragon);
        return dragon;
    }

    public InventoryDragonEgg addDragonEgg(Player player, int eggId) {
        PlayerDragonCollection heroCollection = player.getOrLoadDragon();

        InventoryDragonEgg dragonEgg = new InventoryDragonEgg(EggType.toType(eggId));
        heroCollection.addDragonEgg(dragonEgg);
        return dragonEgg;
    }
}
