package com.zitga.idle.enumeration.event;

public enum EventActionType {

    // mid autumn
    MID_AUTUMN_SPECIAL_OFFER_PURCHASE(0),
    MID_AUTUMN_EXCHANGE(1),
    MID_AUTUMN_GEM_BOX_BUY(2),

    // halloween
    HALLOWEEN_EXCHANGE(10),
    HALLOWEEN_BUNDLE_PURCHASE(11),

    // black friday
    BLACK_FRIDAY_CARD_PURCHASE(20),
    BLACK_FRIDAY_GEM_PACK_BUY(21),
    BLACK_FRIDAY_BUNDLE_PURCHASE(22),

    // christmas
    CHRISTMAS_EXCLUSIVE_OFFER_PURCHASE(30),
    CHRISTMAS_RESOURCE_EXCHANGE(31),

    // new year
    NEW_YEAR_GEM_BOX_BUY(40),
    NEW_YEAR_CARD_PURCHASE(41),

    // lunar path
    LUNAR_PATH_SHOP_EXCHANGE(50),
    LUNAR_PATH_BUNDLE_PURCHASE(51),

    // lunar new year
    LUNAR_NEW_YEAR_ELITE_BUNDLE_PURCHASE(52),
    LUNAR_NEW_YEAR_EXCHANGE(53),
    LUNAR_NEW_YEAR_ELITE_SKIN_BUNDLE_PURCHASE(54),

    // event valentine
    VALENTINE_LOVE_CHALLENGE_BUNDLE_PURCHASE(60),
    VALENTINE_ELITE_BUNDLE_PURCHASE(61),
    VALENTINE_SKIN_BUNDLE_PURCHASE(62),

    // event server merge
    SERVER_MERGE_EXCHANGE(70),
    SERVER_MERGE_LIMITED_BUNDLE(71),

    // event easter
    EASTER_BUNNY_CARD_PURCHASE(80),
    EASTER_LIMIT_OFFER_PURCHASE(81),
    EASTER_EXCLUSIVE_OFFER_PURCHASE(82),
    EASTER_EGG_EXCHANGE(83),
    EASTER_QUEST_CLAIM(84),

    // event birthday
    BIRTHDAY_ANNIVERSARY_OFFER_PURCHASE(90),
    BIRTHDAY_EXCHANGE(91),

    //event new hero release
    NEW_HERO_RELEASE_BUNDLE_PURCHASE(100),

    //battle pass
    BATTLE_PASS_PURCHASE(110),
    BATTLE_PASS_LEVEL_PURCHASE(111),
    BATTLE_PASS_EXCHANGE_EXP(112)
    ;

    private final int value;

    EventActionType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static EventActionType toType(int type) {
        return EventActionType.values()[type];
    }
}
