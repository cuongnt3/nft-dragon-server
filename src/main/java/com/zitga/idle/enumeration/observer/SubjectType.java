package com.zitga.idle.enumeration.observer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum SubjectType {

    NEW_DAY(0),
    REGISTER(1),
    LOGIN(2),
    BASIC_INFO_CHANGE(3),
    LOGOUT(4),

    QUEST_CHANGE(10),
    DAILY_QUEST_CHANGE(11),
    EVENT_DAILY_QUEST_CHANGE(12),

    WEEKLY_QUEST_CHANGE(15),

    FEATURE_UNLOCK(50),

    // summon
    SUMMON_HERO(100),

    // prophet tree
    PROPHET_TREE_SUMMON(200),
    PROPHET_TREE_CONVERT(201),

    // hero
    HERO_ADD(300),
    HERO_REMOVE(301),

    HERO_EVOLVE(302),
    HERO_LEVEL_UP(303),
    HERO_DISASSEMBLE(304),
    HERO_RESET(305),
    HERO_REGRESS(306),

    HERO_FRAGMENT_ADD(350),
    HERO_FRAGMENT_REMOVE(351),

    HERO_FOOD_ADD(360),
    HERO_FOOD_REMOVE(361),

    // hand of midas
    HAND_OF_MIDAS_USE(400),

    // tavern
    TAVERN_COMPLETE(500),

    // arena
    ARENA_CHALLENGE(600),
    ARENA_ELO_UPDATE(601),

    // arena team
    ARENA_TEAM_ELO_UPDATE(650),
    ARENA_TEAM_CHALLENGE(651),

    // campaign
    CAMPAIGN_CHALLENGE(700),
    CAMPAIGN_IDLE_REWARD_CLAIM(701),
    CAMPAIGN_AUTO_TRAIN_CLAIM(702),
    CAMPAIGN_QUICK_BATTLE_TICKET_EARN(703),
    CAMPAIGN_QUICK_BATTLE(704),

    // casino
    CASINO_SPIN(800),

    // market
    MARKET_BUY(900),
    MARKET_REFRESH(901),

    // guild
    GUILD_BOSS_CHALLENGE(1000),
    GUILD_BOSS_DAILY_REWARD_CLAIM(1001),

    GUILD_DUNGEON_CHALLENGE(1010),
    GUILD_DUNGEON_REFRESH(1011),

    GUILD_MARKET_BUY(1020),

    GUILD_WAR(1030),
    GUILD_WAR_CHALLENGE(1031),

    GUILD_REFRESH(1050),
    GUILD_CREATE(1051),
    GUILD_REQUEST_JOIN(1052),
    GUILD_ACCEPT_JOIN(1053),
    GUILD_LEAVE(1054),
    GUILD_KICK(1055),
    GUILD_DELETE(1056),
    GUILD_CHECK_IN(1057),

    // equipment
    EQUIPMENT_ADD(1100),
    EQUIPMENT_REMOVE(1101),
    EQUIPMENT_UPGRADE(1102),
    EQUIPMENT_EQUIP(1104),
    ITEM_EQUIP(1105),

    // tower
    TOWER_CHALLENGE(1200),

    // dungeon
    DUNGEON_CHALLENGE(1300),
    DUNGEON_HERO_BIND(1301),
    DUNGEON_ACTIVE_BUFF_USE(1302),
    DUNGEON_MARKET_BUY(1303),
    DUNGEON_SMASH_SHOP_BUY(1304),

    // summoner
    SUMMONER_LEVEL_UP(1400),
    SUMMONER_EVOLVE(1401),
    SUMMONER_EXP_CLAIM(1402),

    // mastery
    MASTERY_UPGRADE(1500),
    MASTERY_RESET(1501),

    // friend
    FRIEND_REFRESH(1600),

    FRIEND_POINT_SEND(1610),
    FRIEND_POINT_RECEIVE(1611),

    FRIEND_BOSS_SCOUT(1620),
    FRIEND_BOSS_CLEAR(1621),
    FRIEND_BOSS_CHALLENGE(1622),

    // stone
    STONE_UNLOCK(1700),
    STONE_UPGRADE(1701),
    STONE_CONVERT(1702),

    // artifact
    ARTIFACT_ADD(1800),
    ARTIFACT_REMOVE(1801),
    ARTIFACT_UPGRADE(1802),
    ARTIFACT_EQUIP(1803),

    ARTIFACT_FRAGMENT_ADD(1850),
    ARTIFACT_FRAGMENT_REMOVE(1851),

    // tutorial
    TUTORIAL_STEP_SET(1900),

    // formation
    FORMATION_UPDATE(2000),

    // raid
    RAID_CHALLENGE(2100),

    // vip
    VIP_LEVEL_REACH(2200),

    // email
    EMAIL_VERIFY(2300),

    // email
    FACEBOOK_JOIN(2400),

    VIDEO_CLAIM(2500),

    // purchase
    PURCHASE_PACKAGE(5000),

    PURCHASE_FREE_PACKAGE(5010),

    // iap
    PACK_GROUP_STATE_UPDATE(5100),

    // event mid autumn
    FEED_BEAST(5200),

    // event halloween
    DICE_GAME_LAP_COMPLETE(5210),

    // skin
    SKIN_ADD(5300),

    // defense mode
    DEFENSE_CHALLENGE(5400),
    DEFENSE_IDLE_CLAIM(5401),

    // hero linking
    LINKING_SUPPORT_HERO_REMOVE(5500),

    // raise hero
    RAISE_HERO_UNBIND(5600),

    // Domains
    DOMAIN_CREW_CREATE(5700),

    // money
    MONEY_EARN(10000),
    MONEY_SPEND(10001),
    MONEY_PRE_EARN(10002),
    ;

    private static final Map<Integer, SubjectType> subjectTypeMap = new ConcurrentHashMap<>();

    private final int value;

    SubjectType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static SubjectType toSubjectType(int subjectType) {
        return subjectTypeMap.get(subjectType);
    }

    static {
        SubjectType[] subjectTypes = SubjectType.values();
        for (SubjectType subjectType : subjectTypes) {
            subjectTypeMap.put(subjectType.getValue(), subjectType);
        }
    }
}