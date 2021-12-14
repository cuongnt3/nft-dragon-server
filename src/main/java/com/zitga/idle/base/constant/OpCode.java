package com.zitga.idle.base.constant;

import com.zitga.core.constants.socket.BaseOpCode;

public class OpCode extends BaseOpCode {

    // player
    public static final int PLAYER_ACCOUNT_BIND = 10;
    public static final int PLAYER_ACCOUNT_SWITCH = 11;

    public static final int PLAYER_DATA_GET = 20;

    // password
    public static final int PLAYER_PASSWORD_FORGOT = 25;
    public static final int PLAYER_PASSWORD_FORGOT_RESEND = 26;
    public static final int PLAYER_PASSWORD_RESET = 27;
    public static final int PLAYER_PASSWORD_CHANGE = 28;

    // add email
    public static final int PLAYER_EMAIL_ADD = 30;
    public static final int PLAYER_EMAIL_RESEND = 31;
    public static final int PLAYER_EMAIL_VERIFY = 32;

    public static final int PLAYER_NAME_CHANGE = 40;
    public static final int PLAYER_AVATAR_CHANGE = 41;
    public static final int PLAYER_LANGUAGE_CHANGE = 42;
    public static final int PLAYER_BLOCK = 43;

    // hero info
    public static final int HERO_LEVEL_UP = 50;
    public static final int HERO_EVOLVE = 51;
    public static final int HERO_LOCK = 52;
    public static final int HERO_SKIN_HIDE = 53;
    public static final int HERO_SKIN_EQUIP = 54;
    public static final int HERO_MULTI_EVOLVE = 55;

    // altar
    public static final int ALTAR_HERO_DISASSEMBLE = 60;
    public static final int ALTAR_MARKET_BUY = 61;
    public static final int ALTAR_MARKET_REFRESH = 62;
    public static final int ALTAR_HERO_RESET = 63;

    // regression
    public static final int REGRESS_HERO = 65;

    // summon
    public static final int SUMMON_HERO = 70;
    public static final int SUMMON_HERO_BY_FRAGMENT = 71;

    // prophet tree
    public static final int PROPHET_TREE_SUMMON = 80;
    public static final int PROPHET_TREE_CONVERT = 81;
    public static final int PROPHET_TREE_SAVE_CONVERTED = 82;

    // item
    public static final int ITEM_EQUIP = 90;
    public static final int ITEM_UPGRADE_ON_BLACKSMITH = 91;
    public static final int ITEM_SELL = 93;
    public static final int ITEM_EQUIPMENT_UPGRADE_ON_HERO = 94;

    public static final int ITEM_ARTIFACT_COMBINE = 100;
    public static final int ITEM_ARTIFACT_UPGRADE = 101;
    public static final int ITEM_SKIN_COMBINE = 102;

    public static final int ITEM_SKIN_BUNDLE_PURCHASE = 105;
    public static final int ITEM_SKIN_EXCHANGE = 106;
    public static final int HERO_SKIN_COLLECTION_GET = 107;

    public static final int IDLE_EFFECT_COLLECTION_GET = 108;

    public static final int ITEM_STONE_CONVERT = 110;
    public static final int ITEM_STONE_UPGRADE = 111;
    public static final int ITEM_STONE_SAVE_CONVERTED = 112;

    public static final int ITEM_TALENT_UNLOCK = 115;
    public static final int ITEM_TALENT_REFRESH = 116;
    public static final int ITEM_TALENT_SELECT = 117;
    public static final int ITEM_TALENT_RESET = 118;

    // casino
    public static final int CASINO_SPIN = 120;
    public static final int CASINO_REFRESH = 121;
    public static final int CASINO_CHIP_BUY = 122;

    // campaign
    public static final int CAMPAIGN_ITEM_CLAIM = 131;
    public static final int CAMPAIGN_RESOURCE_CLAIM = 132;
    public static final int CAMPAIGN_CHALLENGE = 133;
    public static final int CAMPAIGN_QUICK_BATTLE_TICKET_USE = 134;
    public static final int CAMPAIGN_QUICK_BATTLE = 135;

    public static final int CAMPAIGN_TRAINING_HERO_BIND = 140;
    public static final int CAMPAIGN_TRAINING_EXP_CLAIM = 141;

    // daily
    public static final int DAILY_REWARD_CLAIM = 150;

    // server
    public static final int SERVER_LIST_GET = 160;
    public static final int SERVER_NOTICE = 161;
    public static final int SERVER_OTHER_PLAYER_DETAIL_GET = 163;
    public static final int SERVER_OTHER_PLAYER_DETAIL_LIST_GET = 164;

    // tower
    public static final int TOWER_CHALLENGE = 170;
    public static final int TOWER_STAMINA_BUY = 171;

    // hand of midas
    public static final int HAND_OF_MIDAS_CLAIM = 180;

    // raid
    public static final int RAID_CHALLENGE = 190;
    public static final int RAID_STAMINA_BUY = 191;

    // record
    public static final int RECORD_LIST_GET = 200;
    public static final int RECORD_DETAIL_GET = 201;
    public static final int RECORD_REVENGE = 202;
    public static final int RECORD_TOWER_DETAIL_GET = 203;
    public static final int RECORD_ARENA_TEAM_DETAIL_GET = 204;
    public static final int RECORD_ARENA_TEAM_BOT_DETAIL_GET = 205;
    public static final int RECORD_DOMAINS_GET = 206;

    // dungeon
    public static final int DUNGEON_HERO_BIND = 210;
    public static final int DUNGEON_SMASH_SHOP_BUY = 211;
    public static final int DUNGEON_CHALLENGE = 212;
    public static final int DUNGEON_ACTIVE_BUFF_USE = 213;
    public static final int DUNGEON_SHOP_BUY = 214;
    public static final int DUNGEON_BUFF_GENERATE = 215;
    public static final int DUNGEON_BUFF_PICK = 216;

    // mastery
    public static final int MASTERY_UPGRADE = 220;
    public static final int MASTERY_RESET = 221;

    // summoner
    public static final int SUMMONER_CLASS_SELECT = 230;
    public static final int SUMMONER_SKILL_TIER_SELECT = 231;
    public static final int SUMMONER_EVOLVE = 232;

    // tavern
    public static final int TAVERN_QUEST_REROLL = 240;
    public static final int TAVERN_QUEST_ADD = 241;
    public static final int TAVERN_QUEST_LOCK = 242;
    public static final int TAVERN_QUEST_START = 243;
    public static final int TAVERN_QUEST_CANCEL = 244;
    public static final int TAVERN_QUEST_COMPLETE = 245;
    public static final int TAVERN_QUEST_SPEED_UP = 246;

    // friend
    public static final int FRIEND_DELETE = 251;
    public static final int FRIEND_SEARCH = 252;

    public static final int FRIEND_SEARCH_RECOMMENDATION = 254;

    public static final int FRIEND_REQUEST_ADD = 270;
    public static final int FRIEND_REQUEST_MANAGE = 271;

    public static final int FRIEND_POINT_SEND = 280;
    public static final int FRIEND_POINT_CLAIM = 281;

    // arena
    public static final int ARENA_OPPONENT_FIND = 290;
    public static final int ARENA_PREVIOUS_SEASON_RESULT_GET = 291;

    public static final int ARENA_CHALLENGE = 300;
    public static final int ARENA_FORMATION_SET = 301;
    public static final int ARENA_BOT_CHALLENGE = 302;
    public static final int ARENA_CHEST_OPEN = 303;

    public static final int ARENA_MARKET_BUY = 310;
    public static final int ARENA_TICKET_BUY = 311;
    public static final int ARENA_MARKET_UPGRADE = 312;

    // chat
    public static final int CHAT_SEND = 320;
    public static final int CHAT_SUBSCRIBE = 321;
    public static final int CHAT_UNSUBSCRIBE = 322;
    public static final int CHAT_SERVER_SEND = 323;

    // market
    public static final int MARKET_BUY = 340;
    public static final int MARKET_REFRESH = 341;
    public static final int MARKET_UPGRADE = 342;

    // guild
    public static final int GUILD_CREATE = 350;
    public static final int GUILD_REQUEST_JOIN = 351;
    public static final int GUILD_INFO_CHANGE = 352;
    public static final int GUILD_APPLICATION_MANAGE = 353;

    public static final int GUILD_MEMBER_KICK = 360;
    public static final int GUILD_MEMBER_ROLE_CHANGE = 361;
    public static final int GUILD_MEMBER_RECRUIT = 362;

    public static final int GUILD_LEAVE = 370;
    public static final int GUILD_SEARCH = 371;
    public static final int GUILD_SEARCH_CANDIDATE = 372;
    public static final int GUILD_SEARCH_RECOMMENDATION = 373;

    public static final int GUILD_CHECK_IN = 380;

    public static final int GUILD_MARKET_BUY = 390;
    public static final int GUILD_MARKET_REFRESH = 391;
    public static final int GUILD_MARKET_UPGRADE = 392;

    public static final int GUILD_BOSS_SELECT = 400;
    public static final int GUILD_BOSS_CHALLENGE = 401;
    public static final int GUILD_BOSS_MONTHLY_STATISTICS_GET = 402;

    public static final int GUILD_DUNGEON_CHALLENGE = 410;
    public static final int GUILD_DUNGEON_DETAIL_GET = 411;
    public static final int GUILD_DUNGEON_STATISTICS_GET = 413;

    public static final int GUILD_WAR_MEMBER_REGISTER = 420;
    public static final int GUILD_WAR_REGISTER = 421;
    public static final int GUILD_WAR_CHALLENGE = 423;
    public static final int GUILD_WAR_OPPONENT_FIND = 424;
    public static final int GUILD_WAR_PREVIOUS_BATTLE_RESULT_GET = 425;
    public static final int GUILD_WAR_PREVIOUS_SEASON_RESULT_GET = 426;

    // mail
    public static final int MAIL_COMPOSE = 430;
    public static final int MAIL_READ = 431;
    public static final int MAIL_DELETE = 432;
    public static final int MAIL_REWARD_CLAIM = 433;
    public static final int MAIL_TRANSACTION_GET = 434;
    public static final int MAIL_GET = 435;

    // tutorial
    public static final int TUTORIAL_SUMMON_HERO = 440;
    public static final int TUTORIAL_STEP_SET = 441;
    public static final int TUTORIAL_MARKET_BUY = 442;

    // remote config
    public static final int REMOTE_CONFIG_SET = 450;
    public static final int REMOTE_CONFIG_GET = 451;
    public static final int REMOTE_CONFIG_AB_TEST_DATA_SET = 452;

    // account remote config
    public static final int ACCOUNT_REMOTE_CONFIG_SET = 455;

    // quest
    public static final int QUEST_DAILY_CLAIM = 460;
    public static final int QUEST_ACHIEVEMENT_CLAIM = 461;
    public static final int QUEST_TREE_CLAIM = 462;
    public static final int QUEST_DATA_GET = 463;

    // facebook
    public static final int FACEBOOK_FAN_PAGE_JOIN = 470;

    // formation
    public static final int FORMATION_SET = 480;

    // defense mode
    public static final int DEFENSE_MODE_FORMATION_SET = 490;
    public static final int DEFENSE_MODE_CHALLENGE = 491;
    public static final int DEFENSE_MODE_IDLE_CLAIM = 494;
    public static final int DEFENSE_MODE_IDLE_ITEM_CLAIM = 495;
    public static final int DEFENSE_RECORD_LIST_GET = 496;
    public static final int DEFENSE_RECORD_DETAIL_GET = 497;

    // purchase
    public static final int PURCHASE_RAW_PACK = 500;
    public static final int PURCHASE_PROGRESS_PACK = 501;
    public static final int PURCHASE_SUBSCRIPTION_PACK = 502;
    public static final int PURCHASE_LIMITED_PACK = 503;
    public static final int PURCHASE_SUBSCRIPTION_TRIAL_PACK = 504;

    public static final int FIRST_PURCHASE_ITEM_CLAIM = 505;

    public static final int CUSTOMIZE_BUNDLE_PURCHASE = 506;
    public static final int CUSTOMIZE_BUNDLE_ITEM_SELECT = 507;

    public static final int PURCHASE_GROWTH_PACK = 508;
    public static final int PURCHASE_GROWTH_PACK_MILESTONE_CLAIM = 509;

    // raise hero
    public static final int RAISE_HERO_BIND_RAISED_HEROES = 510;
    public static final int RAISE_HERO_UNBIND_RAISED_HEROES = 511;
    public static final int RAISE_HERO_UNLOCK_SLOT = 512;
    public static final int RAISE_HERO_RESET_COUNTDOWN_SLOT = 513;

    public static final int HERO_LINKING_SUPPORT_HERO_SAVE = 520;
    public static final int HERO_LINKING_HERO_SAVE = 521;

    public static final int PROGRESS_PACK_ITEM_SELECT = 530;

    public static final int PURCHASE_PRODUCT_CHECK = 540;

    // giftCode
    public static final int GIFT_CODE_CLAIM = 550;

    // event
    public static final int EVENT_DATA_GET = 600;

    // event login
    public static final int EVENT_LOGIN_CLAIM = 610;

    // event bundle
    public static final int EVENT_BUNDLE_PURCHASE = 620;

    // event hot deal
    public static final int EVENT_HOT_DEAL_PURCHASE = 630;

    // event release festival
    public static final int EVENT_RELEASE_FESTIVAL_CLAIM = 640;

    // event exchange
    public static final int EVENT_EXCHANGE = 650;

    // event arena rank
    public static final int EVENT_ARENA_RANK_CLAIM = 660;

    // reward video
    public static final int REWARD_VIDEO_CLAIM = 661;

    // event spin quest
    public static final int EVENT_SPIN_QUEST = 670;
    public static final int EVENT_SPIN_QUEST_REROLL = 671;
    public static final int EVENT_SPIN_QUEST_CLAIM_REWARD = 672;

    // event guild quest
    public static final int EVENT_GUILD_QUEST_DONATE = 680;
    public static final int EVENT_GUILD_QUEST_CLAIM_REWARD = 681;

    // event rate up
    public static final int EVENT_RATE_UP_SUMMON = 690;

    // get tracking
    public static final int TRACKING_INFO_GET = 700;

    // event market
    public static final int EVENT_MARKET_BUY = 710;

    // event server open
    public static final int EVENT_SERVER_OPEN_MARKET_BUY = 720;
    public static final int EVENT_SERVER_OPEN_QUEST_CLAIM = 721;
    public static final int EVENT_SERVER_OPEN_PROGRESS_CLAIM = 722;

    // event sale off
    public static final int EVENT_SALE_OFF_PURCHASE = 730;

    // event mid autumn
    public static final int EVENT_MID_AUTUMN_PURCHASE = 740;
    public static final int EVENT_MID_AUTUMN_EXCHANGE = 741;
    public static final int EVENT_MID_AUTUMN_GEM_BOX_BUY = 742;
    public static final int EVENT_MID_AUTUMN_LOGIN_CLAIM = 743;
    public static final int EVENT_MID_AUTUMN_BEAST_REWARD_CLAIM = 745;
    public static final int EVENT_MID_AUTUMN_FEED_BEAST = 746;

    // event arena pass
    public static final int EVENT_ARENA_PASS_PURCHASE = 760;
    public static final int EVENT_ARENA_PASS_MILESTONE_CLAIM = 761;

    // event daily quest pass
    public static final int EVENT_DAILY_QUEST_PASS_PURCHASE = 770;
    public static final int EVENT_DAILY_QUEST_PASS_MILESTONE_CLAIM = 771;

    // event halloween
    public static final int EVENT_HALLOWEEN_BUNDLE_PURCHASE = 780;
    public static final int EVENT_HALLOWEEN_DAILY_CLAIM = 781;
    public static final int EVENT_HALLOWEEN_EXCHANGE = 782;
    public static final int EVENT_HALLOWEEN_DAILY_PURCHASE = 783;
    public static final int EVENT_HALLOWEEN_DICE_ROLL = 784;

    // event black friday
    public static final int EVENT_BLACK_FRIDAY_GEM_PACK_BUY = 790;
    public static final int EVENT_BLACK_FRIDAY_CARD_PURCHASE = 791;
    public static final int EVENT_BLACK_FRIDAY_LOGIN_CLAIM = 792;
    public static final int EVENT_BLACK_FRIDAY_BUNDLE_PURCHASE = 793;

    // event black friday
    public static final int EVENT_CHRISTMAS_DAILY_LOGIN_CLAIM = 800;
    public static final int EVENT_CHRISTMAS_BOX_OPEN = 801;
    public static final int EVENT_CHRISTMAS_EXCHANGE_RESOURCE = 802;
    public static final int EVENT_CHRISTMAS_DAILY_PURCHASE = 803;
    public static final int EVENT_CHRISTMAS_EXCLUSIVE_OFFER_PURCHASE = 804;
    public static final int EVENT_CHRISTMAS_CHALLENGE_BOSS = 805;
    public static final int EVENT_CHRISTMAS_RANKING_GET = 806;

    // event new year
    public static final int EVENT_NEW_YEAR_BOX_BUY = 810;
    public static final int EVENT_NEW_YEAR_DAILY_BUNDLE_PURCHASE = 811;
    public static final int EVENT_NEW_YEAR_CARD_PURCHASE = 812;
    public static final int EVENT_NEW_YEAR_LOTTERY_ROLL = 813;
    public static final int EVENT_NEW_YEAR_LOTTERY_RESET = 814;

    // event lunar new year
    public static final int EVENT_LUNAR_DAILY_LOGIN_CLAIM = 820;
    public static final int EVENT_LUNAR_ELITE_BUNDLE_PURCHASE = 821;
    public static final int EVENT_LUNAR_EXCHANGE = 822;
    public static final int EVENT_LUNAR_ELITE_SUMMON = 823;
    public static final int EVENT_LUNAR_ELITE_WISH_SELECT = 824;
    public static final int EVENT_LUNAR_ELITE_SKIN_BUNDLE_PURCHASE = 825;

    // event lunar path
    public static final int EVENT_LUNAR_DICE_ROLL = 830;
    public static final int EVENT_LUNAR_SHOP_EXCHANGE = 831;
    public static final int EVENT_LUNAR_QUEST_CLAIM = 832;
    public static final int EVENT_LUNAR_BOSS_DETAIL_GET = 833;
    public static final int EVENT_LUNAR_BOSS_STATISTICS_GET = 834;
    public static final int EVENT_LUNAR_BOSS_CHALLENGE = 835;
    public static final int EVENT_LUNAR_BOSS_RANKING_GET = 836;
    public static final int EVENT_LUNAR_BUNDLE_PURCHASE = 837;

    // event valentine
    public static final int EVENT_VALENTINE_LOGIN_CLAIM = 840;
    public static final int EVENT_VALENTINE_BOSS_CHALLENGE = 841;
    public static final int EVENT_VALENTINE_STAMINA_BUY = 842;
    public static final int EVENT_VALENTINE_LOVE_BUNDLE_PURCHASE = 843;
    public static final int EVENT_VALENTINE_SKIN_BUNDLE_PURCHASE = 844;
    public static final int EVENT_VALENTINE_ELITE_BUNDLE_PURCHASE = 845;
    public static final int EVENT_VALENTINE_WISH_CARD_SELECT = 846;
    public static final int EVENT_VALENTINE_CARD_OPEN = 847;

    // event community
    public static final int EVENT_COMMUNITY_DATA_GET = 850;
    public static final int EVENT_COMMUNITY_REWARD_CLAIM = 851;

    // event new hero
    public static final int EVENT_NEW_HERO_QUEST_CLAIM = 860;
    public static final int EVENT_NEW_HERO_LOGIN_CLAIM = 861;
    public static final int EVENT_NEW_HERO_SPIN = 862;
    public static final int EVENT_NEW_HERO_SUMMON = 863;
    public static final int EVENT_NEW_HERO_BUNDLE_PURCHASE = 865;
    public static final int EVENT_NEW_HERO_COLLECTION_QUEST_CLAIM = 866;
    public static final int EVENT_NEW_HERO_ROSE_BUY = 867;
    public static final int EVENT_NEW_HERO_EXCHANGE = 868;
    public static final int EVENT_NEW_HERO_CHALLENGE = 869;

    public static final int EVENT_SERVER_MERGE_QUEST_CLAIM = 870;
    public static final int EVENT_SERVER_MERGE_LOGIN_CLAIM = 871;
    public static final int EVENT_SERVER_MERGE_EXCHANGE = 872;
    public static final int EVENT_SERVER_MERGE_ACCUMULATE_QUEST_CLAIM = 873;
    public static final int EVENT_SERVER_MERGE_BUNDLE_ITEM_SELECT = 874;
    public static final int EVENT_SERVER_MERGE_BUNDLE_PURCHASE = 875;

    public static final int EVENT_EASTER_LOGIN_CLAIM = 880;
    public static final int EVENT_EASTER_CARD_PURCHASE = 881;
    public static final int EVENT_EASTER_LIMIT_OFFER_PURCHASE = 882;
    public static final int EVENT_EASTER_DAILY_BUNDLE_PURCHASE = 883;
    public static final int EVENT_EASTER_EGG_BREAK = 884;
    public static final int EVENT_EASTER_EGG_EXCHANGE = 885;

    public static final int EVENT_NEW_HERO_BOSS_RANKING_GET = 890;
    public static final int EVENT_NEW_HERO_RATE_UP_SUMMON = 891;
    public static final int EVENT_NEW_HERO_SKIN_BUNDLE_PURCHASE = 892;

    public static final int EVENT_BIRTHDAY_LOGIN_CLAIM = 900;
    public static final int EVENT_BIRTHDAY_DAILY_BUNDLE_PURCHASE = 901;
    public static final int EVENT_BIRTHDAY_ANNIVERSARY_OFFER_PURCHASE = 902;
    public static final int EVENT_BIRTHDAY_EXCHANGE = 903;
    public static final int EVENT_BIRTHDAY_WHEEL_SPIN = 904;
    public static final int EVENT_BIRTHDAY_WHEEL_CLAIM = 905;

    public static final int EVENT_NEW_HERO_TREASURE_ISLAND_UNLOCK = 910;
    public static final int EVENT_NEW_HERO_TREASURE_LINE_COMPLETE_REWARD = 911;
    public static final int EVENT_NEW_HERO_TREASURE_REWARD_BUY = 912;

    public static final int EVENT_SKIN_BUNDLE_PURCHASE = 920;

    //event new hero release
    public static final int EVENT_HERO_RELEASE_BUNDLE_PURCHASE = 930;
    public static final int EVENT_HERO_RELEASE_COLLECTION_QUEST_CLAIM = 931;
    public static final int EVENT_HERO_RELEASE_SUMMON = 932;
    public static final int EVENT_HERO_RELEASE_QUEST_CLAIM = 933;

    //battle pass
    public static final int EVENT_BATTLE_PASS_PURCHASE = 950;
    public static final int EVENT_BATTLE_PASS_LEVEL_PURCHASE = 951;
    public static final int EVENT_BATTLE_PASS_LEVEL_REWARD = 952;
    public static final int EVENT_BATTLE_PASS_EXCHANGE_EXP = 953;
    public static final int EVENT_BATTLE_PASS_QUEST_CLAIM = 954;

    // fake data
    public static final int FAKE_DATA = 1000;
    public static final int FAKE_SUMMON_MASS = 1001;
    public static final int FAKE_CONVERT_MASS = 1002;
    public static final int FAKE_CASINO_SPIN = 1003;
    public static final int FAKE_CLEAR_SUMMON_PRODUCTION = 1004;
    public static final int FAKE_CLEAR_EVENT_RATE_UP_SUMMON = 1005;
    public static final int FAKE_EVENT_RATE_UP_SUMMON_MASS = 1006;

    // Sun Game
    public static final int SUN_GAME_LOGIN = 1100;
    public static final int SUN_GAME_PENDING_ORDER_GET = 1101;

    // event new server open
    public static final int EVENT_NEW_SERVER_OPEN_QUEST_CLAIM = 1110;
    public static final int EVENT_NEW_SERVER_OPEN_BUNDLE_ITEM_SELECT = 1111;
    public static final int EVENT_NEW_SERVER_OPEN_BUNDLE_PURCHASE = 1112;
    public static final int EVENT_NEW_SERVER_OPEN_LOGIN_CLAIM = 1113;

    //event server open spin
    public static final int EVENT_SERVER_OPEN_SPIN = 1115;

    //event new server open 2
    public static final int EVENT_NEW_SERVER_OPEN_2_QUEST_CLAIM = 1120;
    public static final int EVENT_NEW_SERVER_OPEN_2_BUNDLE_PURCHASE = 1121;
    public static final int EVENT_NEW_SERVER_OPEN_2_BUNDLE_ITEM_SELECT = 1122;

    //event new server open 3
    public static final int EVENT_NEW_SERVER_OPEN_3_BUNDLE_PURCHASE = 1125;
    public static final int EVENT_NEW_SERVER_OPEN_3_BUNDLE_ITEM_SELECT = 1126;
    public static final int EVENT_NEW_SERVER_OPEN_3_QUEST_CLAIM = 1127;

    //event idle effect bundle
    public static final int EVENT_IDLE_EFFECT_CUSTOMIZE_BUNDLE_PURCHASE = 1130;
    public static final int EVENT_IDLE_EFFECT_BUNDLE_ITEM_SELECT = 1131;
    public static final int EVENT_IDLE_EFFECT_RANDOM_BUNDLE_PURCHASE  = 1132;

    // ---------------------------------------- FOR FEATURE ----------------------------------------
    // Arena team
    public static final int ARENA_TEAM_FORMATION_SAVE = 1500;
    public static final int ARENA_TEAM_OPPONENT_FIND = 1501;
    public static final int ARENA_TEAM_RANKING_GET = 1502;
    public static final int ARENA_TEAM_CHALLENGE = 1503;
    public static final int ARENA_TEAM_BOT_CHALLENGE = 1504;
    public static final int ARENA_TEAM_OPPONENT_DETAIL_GET = 1505;
    public static final int ARENA_TEAM_PREVIOUS_SEASON_RESULT_GET = 1506;
    public static final int ARENA_TEAM_TICKET_BUY = 1507;
    public static final int ARENA_TEAM_MARKET_BUY = 1508;
    public static final int ARENA_TEAM_MARKET_UPGRADE = 1509;

    // Player comeback
    public static final int COMEBACK_DAILY_LOGIN_CLAIM = 1510;
    public static final int COMEBACK_DAILY_QUEST_CLAIM = 1511;
    public static final int COMEBACK_BUNDLE_PURCHASE = 1512;

    // Domains
    public static final int DOMAINS_CONTRIBUTE_HERO_SAVE = 1520;
    public static final int DOMAINS_CREW_CREATE = 1523;
    public static final int DOMAINS_CREW_LEADER_CHANGE = 1524;
    public static final int DOMAINS_CREW_DELETE = 1525;
    public static final int DOMAINS_CREW_MEMBER_KICK = 1526;

    public static final int DOMAINS_MEMBER_SEARCH = 1530;
    public static final int DOMAINS_MEMBER_INVITE = 1531;
    public static final int DOMAINS_MEMBER_INVITATIONS_GET = 1533;
    public static final int DOMAINS_MEMBER_INVITATION_MANAGE = 1534;

    public static final int DOMAINS_CREW_SEARCH = 1540;
    public static final int DOMAINS_CREW_APPLICATION_GET = 1541;
    public static final int DOMAINS_CREW_RECOMMENDATION_GET = 1542;
    public static final int DOMAINS_CREW_APPLICATION_MANAGE = 1543;
    public static final int DOMAINS_CREW_APPLICATION_SEND = 1544;
    public static final int DOMAINS_CREW_MEMBER_LEAVE = 1545;
    public static final int DOMAINS_CREW_MEMBER_READY_UPDATE = 1546;
    public static final int DOMAINS_CREW_APPLICATION_DELETE_ALL = 1547;
    public static final int DOMAINS_OPEN_CHEST = 1548;

    public static final int CREW_MEMBER_DETAIL_GET = 1550;
    public static final int CREW_MEMBER_RECRUIT_SEND = 1551;

    public static final int CREW_BATTLE_START = 1555;
    public static final int CREW_CHALLENGE = 1556;
    public static final int CREW_SAVE_CHALLENGE_FORMATION = 1557;

    // hero statistics
    public static final int HERO_STATISTIC_DATA_GET = 1900;

    // master
    public static final int MASTER_SERVER_ACTION = 2000;

    // tracking
    public static final int TRACKING_SERVER_ACTION = 2500;

    // load balance
    public static final int LOAD_BALANCE_SERVER_ACTION = 3000;
}