package com.zitga.idle.enumeration.hero;

public enum HeroItemSlot {
    WEAPON(1),
    ARMOR(2),
    HELM(3),
    ACCESSORY(4),
    ARTIFACT(5),
    STONE(6),
    SKIN(7),
    IDLE_EFFECT(8),

    // talent slot
    TALENT_1(10),
    TALENT_2(11),
    TALENT_3(12)
    ;

    private final int value;

    HeroItemSlot(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    private boolean compare(int i) {
        return value == i;
    }

    public static HeroItemSlot toHeroItemSlot(int slot) {
        HeroItemSlot[] heroItemSlots = HeroItemSlot.values();
        for (HeroItemSlot itemSlot : heroItemSlots) {
            if (itemSlot.getValue() == slot) {
                return itemSlot;
            }
        }
        return HeroItemSlot.WEAPON;
    }
}