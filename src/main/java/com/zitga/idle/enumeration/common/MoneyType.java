package com.zitga.idle.enumeration.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum MoneyType {
    GOLD(0),
    GEM(1),
    VIP_POINT(2),

    // For leveling up hero
    MAGIC_POTION(10),
    ANCIENT_POTION(11),
    AWAKEN_BOOK(12),

    // For upgrade item currency
    MAGICAL_CRYSTAL(15),

    // Gain when disassembling hero in Altar
    HERO_SHARD(20),

    // Need to regress hero
    REGRESSION_CURRENCY(25),

    // Casino
    CASINO_BASIC_CHIP(30),
    CASINO_PREMIUM_CHIP(31),
    CASINO_POINT(32),

    // pvp chest
    ARENA_CHEST_1(35),
    ARENA_CHEST_2(36),
    ARENA_CHEST_3(37),

    // Arena
    ARENA_TICKET(40),
    ARENA_COIN(41),
    ARENA_FREE_CHALLENGE_TURN(42),

    // Arena team
    ARENA_TEAM_TICKET(45),
    ARENA_TEAM_COIN(46),

    // Summon
    SUMMON_BASIC_SCROLL(50),
    SUMMON_PREMIUM_SCROLL(51),
    SUMMON_POINT(52), // accumulates when summon by scroll

    // Prophet tree
    PROPHET_ORB(60),
    PROPHET_WOOD(61),

    // Friend
    FRIEND_POINT(70),
    FRIEND_STAMINA(71),

    // Raid
    RAID_GOLD_TURN(75),
    RAID_MAGIC_POTION_TURN(76),
    RAID_HERO_FRAGMENT_TURN(77),

    // Dust
    STONE_DUST(80), // for upgrading and converting stone

    // Tower
    TOWER_STAMINA(85),

    // Tavern
    QUEST_BASIC_SCROLL(90), // for obtaining a low-level quest
    QUEST_PREMIUM_SCROLL(91), // for obtaining a high-level quest

    // Mastery
    MASTERY_POINT(100), // for upgrading mastery

    // Summoner
    SUMMONER_ANCIENT_BOOK(110), // for evolving summoner

    DOMAIN_CHALLENGE_STAMINA(112),
    DOMAIN_MARKET_COIN(113),

    DOMAIN_CHEST_LEVEL_1(115),
    DOMAIN_CHEST_LEVEL_2(116),
    DOMAIN_CHEST_LEVEL_3(117),
    DOMAIN_CHEST_LEVEL_4(118),
    DOMAIN_CHEST_LEVEL_5(119),

    // Guild
    GUILD_BASIC_COIN(120),
    GUILD_PREMIUM_COIN(121),
    GUILD_BOSS_STAMINA(122),
    GUILD_DUNGEON_STAMINA(123),
    GUILD_WAR_STAMINA(124),

    // Event guild quest
    EVENT_GUILD_QUEST_GOLD(130),
    EVENT_GUILD_QUEST_SILVER(131),

    // Event server opening
    EVENT_SERVER_OPEN_POINT(135),
    EVENT_SERVER_MERGE_POINT(136),

    // Event market
    EVENT_MARKET_TIER_1_SOUL(140),
    EVENT_MARKET_TIER_2_SOUL(141),
    EVENT_MARKET_TIER_3_SOUL(142),

    // Market upgrade coin
    ARENA_MARKET_UPGRADE_COIN(150),
    GUILD_MARKET_UPGRADE_COIN(151),
    BLACK_MARKET_UPGRADE_COIN(152),
    ALTAR_MARKET_UPGRADE_COIN(153),
    ARENA_TEAM_MARKET_UPGRADE_COIN(154),

    // Event rate up
    EVENT_RATE_UP_SUMMON_POINT(160),

    // event new hero
    EVENT_NEW_HERO_ROSE(165),
    EVENT_NEW_HERO_STAR(166),
    EVENT_NEW_HERO_ENULE_BLADE(167),
    EVENT_NEW_HERO_TREASURE_COMPASS(168),

    // raise hero
    RAISED_HERO_UNLOCK_SLOT_CURRENCY(170),

    // event mid autumn
    EVENT_MID_AUTUMN_LANTERN(180),
    EVENT_MID_AUTUMN_MOON_CAKE(181),

    // event pass
    EVENT_ARENA_PASS_POINT(190),
    EVENT_DAILY_QUEST_PASS_POINT(191),

    //battle pass
    EVENT_BATTLE_PASS_EXP(195),

    // Defense mode
    UNUSED_1(200),
    UNUSED_2(201),
    UNUSED_3(202),
    UNUSED_4(203),
    UNUSED_5(204),
    UNUSED_6(205),
    UNUSED_7(206),
    UNUSED_8(207),
    UNUSED_9(208),
    UNUSED_10(209),

    // event halloween
    EVENT_HALLOWEEN_DICE(210),
    EVENT_HALLOWEEN_PUMPKIN(211),

    // event christmas
    EVENT_CHRISTMAS_CANDY_BAR(220),
    EVENT_CHRISTMAS_BOX(221),
    EVENT_CHRISTMAS_STAMINA(222),

    // event birthday
    EVENT_BIRTHDAY_WHEEL(225),

    // event new year
    EVENT_NEW_YEAR_LOTTERY_TICKET(230),

    // event easter
    EVENT_EASTER_SILVER_EGG(235),
    EVENT_EASTER_YELLOW_EGG(236),
    EVENT_EASTER_RAINBOW_EGG(237),
    EVENT_EASTER_YELLOW_HAMMER(238),
    EVENT_EASTER_RAINBOW_HAMMER(239),

    // event lunar path
    EVENT_LUNAR_PATH_DICE(240),
    EVENT_LUNAR_PATH_FLAG(241),
    EVENT_LUNAR_PATH_GUILD_POINT(242),
    EVENT_LUNAR_PATH_CHALLENGE_STAMINA(245),

    // event lunar new year
    EVENT_LUNAR_NEW_YEAR_ENVELOPE(243),
    SUMMON_ELITE_SCROLL(244),
    EVENT_LUNAR_NEW_YEAR_SUMMON_POINT(246),

    //event valentine
    EVENT_VALENTINE_LUCKY_COIN(250),
    EVENT_VALENTINE_CHALLENGE_STAMINA(251);

    private static final Map<Integer, MoneyType> moneyTypeMap = new ConcurrentHashMap<>();
    private final int value;

    MoneyType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static MoneyType toMoneyType(int type) {
        return moneyTypeMap.get(type);
    }

    static {
        MoneyType[] moneyTypes = MoneyType.values();
        for (MoneyType moneyType : moneyTypes) {
            moneyTypeMap.put(moneyType.getValue(), moneyType);
        }
    }
}