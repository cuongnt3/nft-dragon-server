package com.zitga.idle.item.utils;

import com.zitga.idle.enumeration.hero.HeroItemSlot;
import com.zitga.idle.enumeration.resource.ResourceType;
import com.zitga.idle.item.constant.ItemConstant;

public class ItemUtils {

    public static HeroItemSlot getSlotEquipmentFromId(int equipmentId) {
        return HeroItemSlot.toHeroItemSlot(equipmentId / ItemConstant.ITEM_SPACE_GROUP);
    }

    public static ResourceType getResourcesTypeFromSlotType(HeroItemSlot slot) {
        switch (slot) {
            case WEAPON:
            case ARMOR:
            case HELM:
            case ACCESSORY:
                return ResourceType.ITEM_EQUIPMENT;

        }
        return ResourceType.MONEY;
    }
}
