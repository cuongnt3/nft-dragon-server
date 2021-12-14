package com.zitga.idle.enumeration.event;

public enum EventType {
    DUNGEON(1),
    ARENA_TEAM(2),
    ARENA(3),
    GUILD_DUNGEON(4),
    GUILD_WAR(5),

    EVENT_SUMMON_QUEST(6),
    EVENT_PROPHET_TREE_QUEST(7),
    EVENT_ARENA_QUEST(8),
    EVENT_CASINO_QUEST(9),
    EVENT_TAVERN_QUEST(10),
    EVENT_HERO_COLLECT_QUEST(11),

    EVENT_EXCHANGE(12),

    EVENT_BUNDLE(13),
    EVENT_HOT_DEAL(14),

    EVENT_LOGIN(15),

    EVENT_RELEASE_FESTIVAL(16),

    EVENT_SALE_OFF(17),

    EVENT_SPIN_QUEST(18),
    EVENT_ARENA_RANK(19),
    EVENT_GUILD_QUEST(20),

    EVENT_RATE_UP(21),
    EVENT_MARKET(22),
    EVENT_SERVER_OPEN(23),

    EVENT_MID_AUTUMN(24),
    EVENT_BLACK_FRIDAY(25),
    EVENT_AUCTION(26),

    EVENT_ARENA_PASS(27),
    EVENT_DAILY_QUEST_PASS(28),

    EVENT_HALLOWEEN(29),

    EVENT_CHRISTMAS(30),
    EVENT_NEW_YEAR(31),

    EVENT_LUNAR_NEW_YEAR(32),
    EVENT_VALENTINE(33),
    EVENT_LUNAR_PATH(34),

    EVENT_NEW_HERO_QUEST(35),
    EVENT_NEW_HERO_SPIN(36),
    EVENT_NEW_HERO_LOGIN(37),
    EVENT_NEW_HERO_SUMMON(38),
    EVENT_NEW_HERO_BUNDLE(39),
    EVENT_NEW_HERO_COLLECTION(40),

    EVENT_SERVER_MERGE(41),
    EVENT_EASTER(42),

    EVENT_NEW_HERO_EXCHANGE(43),
    EVENT_NEW_HERO_CHALLENGE_BOSS(44),
    EVENT_NEW_HERO_LEADER_BOARD(45),
    EVENT_NEW_HERO_TREASURE(46),
    EVENT_NEW_HERO_RATE_UP(47),
    EVENT_NEW_HERO_SKIN_BUNDLE(48),
    EVENT_NEW_HERO_UNUSED_2(49),

    // event community
    EVENT_FACEBOOK_COMMUNITY(50),
    EVENT_TWITTER_COMMUNITY(51),
    EVENT_INSTAGRAM_COMMUNITY(52),
    EVENT_REDDIT_COMMUNITY(53),
    EVENT_DISCORD_COMMUNITY(54),

    EVENT_BIRTHDAY(55),
    EVENT_SKIN_BUNDLE(56),

    EVENT_HERO_RELEASE(57),

    EVENT_BATTLE_PASS(58),

    EVENT_NEW_SERVER_OPEN(59),
    EVENT_SERVER_OPEN_SPIN(60),
    EVENT_SERVER_OPEN_ARENA(61),

    EVENT_SERVER_OPEN_2(62),
    EVENT_SERVER_OPEN_2_ARENA(63),

    EVENT_SERVER_OPEN_3(64),

    EVENT_IDLE_EFFECT_BUNDLE(65)
    ;

    private final int value;

    EventType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static EventType toEventType(int type) {
        return EventType.values()[type - 1];
    }
}