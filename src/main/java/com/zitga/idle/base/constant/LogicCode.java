package com.zitga.idle.base.constant;

import com.zitga.core.constants.BaseLogicCode;

public class LogicCode extends BaseLogicCode {

    // handshake
    public static final int HANDSHAKE_CLIENT_TIME_INVALID = 11;
    public static final int HANDSHAKE_CLIENT_VERSION_FORBIDDEN = 12;
    public static final int HANDSHAKE_BUNDLE_NAME_FORBIDDEN = 13;
    public static final int HANDSHAKE_SHARED_SECRET_INVALID = 14;
    public static final int HANDSHAKE_CHALLENGE_INVALID = 15;
    public static final int HANDSHAKE_NUMBER_CSV_FILE_MISMATCHED = 16;
    public static final int HANDSHAKE_CSV_HASH_MISMATCHED = 17;

    public static final int OPCODE_REQUEST_TOO_FAST = 19;

    // fake
    public static final int FAKE_FORBIDDEN = 25;

    public static final int AUTH_PASSWORD_NOT_MATCH = 20;
    public static final int AUTH_ALREADY_LOGIN_OTHER_DEVICE = 21;

    public static final int AUTH_USERNAME_ALREADY_USED = 30;
    public static final int AUTH_USERNAME_FORMAT_INVALID = 31;
    public static final int AUTH_USERNAME_INVALID = 32;

    // auth
    public static final int AUTH_ALREADY_REGISTERED = 30;
    public static final int AUTH_ANOTHER_ACCOUNT_ALREADY_BOUND = 31;
    public static final int AUTH_SAME_ACCOUNT_ALREADY_BOUND = 32;
    public static final int AUTH_PASSWORD_MISMATCHED = 34;
    public static final int AUTH_PLAYER_BANNED = 35;
    public static final int AUTH_SERVER_ID_INVALID = 36;
    public static final int AUTH_ACCOUNT_USER_NAME_NOT_FOUND = 37;
    public static final int AUTH_PASSWORD_MISMATCHED_LIMIT_REACHED = 38;

    public static final int AUTH_ALREADY_BINDED = 40;
    public static final int AUTH_PASSWORD_ALREADY_USED = 41;
    public static final int AUTH_ACCOUNT_LOCATION_ABUSED = 42;

    // dragon
    public static final int DRAGON_COLLECTION_FULL = 50;
    public static final int DRAGON_EGG_INVENTORY_ID_INVALID = 51;
    public static final int DRAGON_EGG_NOT_INCUBATED = 52;
    public static final int DRAGON_EGG_ALREADY_INCUBATED = 53;
    public static final int DRAGON_EGG_IN_HATCH_DURATION = 54;

    // hero
    public static final int HERO_COLLECTION_FULL = 70;
    public static final int HERO_LEVEL_LIMIT_REACHED = 71;
    public static final int HERO_STAR_LIMIT_REACHED = 72;
    public static final int HERO_NOT_FOUND = 73;
    public static final int HERO_ALREADY_LOCKED = 74;
    public static final int HERO_EVOLVE_MATERIAL_INVALID = 75;
    public static final int HERO_ALREADY_IN_HERO_LINKING = 76;
    public static final int HERO_EVOLVE_FOOD_NOT_ENOUGH = 77;
    public static final int HERO_ALREADY_IN_AUTO_TRAINING = 78;
    public static final int HERO_ALREADY_IN_RAISED_HERO = 79;

    // daily
    public static final int DAILY_REWARD_ALREADY_CLAIMED = 80;

    // altar
    public static final int ALTAR_CAN_NOT_DISASSEMBLE = 85;
    public static final int ALTAR_CAN_NOT_RESET = 86;

    // hero fragment
    public static final int HERO_FRAGMENT_NOT_ENOUGH = 90;
    public static final int HERO_FRAGMENT_NOT_FOUND = 91;

    // prophet
    public static final int PROPHET_TREE_HERO_CAN_NOT_CONVERT = 100;
    public static final int PROPHET_TREE_CONVERTED_HERO_NOT_FOUND = 101;
    public static final int PROPHET_TREE_HERO_ALREADY_IN_RAISED_HERO = 102;
    public static final int PROPHET_TREE_CONVERTED_HERO_ALREADY_IN_HERO_LINKING = 103;

    // summon
    public static final int SUMMON_SCROLL_CAN_NOT_BUY = 110;

    public static final int SKIN_EXCHANGE_RECIPE_NOT_FOUND = 115;

    public static final int IDLE_EFFECT_CAN_NOT_EQUIP = 117;

    // item
    public static final int ITEM_HERO_LEVEL_NOT_ENOUGH = 120;
    public static final int ITEM_LEVEL_LIMIT_REACHED = 121;
    public static final int SKIN_CAN_NOT_HIDE = 122;
    public static final int ITEM_NOT_FOUND = 123;
    public static final int SKIN_CAN_NOT_EQUIP = 124;
    public static final int ITEM_HERO_NOT_UNLOCK = 125;
    public static final int ITEM_TALENT_ALREADY_LOCKED = 126;
    public static final int ITEM_TALENT_DOES_NOT_EXISTED = 127;
    public static final int ITEM_TALENT_REFRESH_SLOT_INVALID = 128;
    public static final int ITEM_TALENT_POOL_IS_EMPTY = 129;

    // raid
    public static final int RAID_TURN_BUY_LIMIT_REACHED = 130;
    public static final int RAID_STAGE_NOT_FOUND = 131;
    public static final int RAID_LEVEL_NOT_ENOUGH = 132;

    // dungeon
    public static final int DUNGEON_HERO_ALREADY_BOUND = 140;
    public static final int DUNGEON_HERO_ALREADY_DEAD = 141;
    public static final int DUNGEON_HERO_NOT_BOUND = 142;
    public static final int DUNGEON_SHOP_NOT_FOUND = 143;
    public static final int DUNGEON_NOT_OPEN = 144;
    public static final int DUNGEON_BINDING_HERO_INVALID = 145;
    public static final int DUNGEON_HERO_SLOT_INVALID = 146;
    public static final int DUNGEON_HERO_NOT_FOUND = 148;
    public static final int DUNGEON_BUFF_ITEM_NOT_FOUND = 149;
    public static final int DUNGEON_STAGE_LIMIT_REACHED = 150;
    public static final int DUNGEON_STAGE_INVALID = 151;
    public static final int DUNGEON_STAGE_NOT_REACHED = 152;
    public static final int DUNGEON_BUFF_ALREADY_PICKED = 153;
    public static final int DUNGEON_BUFF_NOT_GENERATED = 154;
    public static final int DUNGEON_BUFF_ITEM_LIMITED = 155;
    public static final int DUNGEON_BUFF_SMASH_NOT_COMPLETED = 156;
    public static final int DUNGEON_BUFF_PICK_INDEX_INVALID = 157;
    public static final int DUNGEON_BUFF_ITEM_CAN_NOT_USE = 158;

    // mastery
    public static final int MASTERY_LEVEL_LIMIT_REACHED = 160;
    public static final int MASTERY_MILESTONE_LOCKED = 164;

    // summoner
    public static final int SUMMONER_CLASS_CAN_NOT_SELECT = 171;
    public static final int SUMMONER_SKILL_TIER_CAN_NOT_SELECT = 172;
    public static final int SUMMONER_STAR_LIMIT_REACHED = 173;

    // casino
    public static final int CASINO_PREMIUM_CHIP_CAN_NOT_BUY = 180;
    public static final int CASINO_BASIC_CHIP_BUY_LIMIT_REACHED = 181;

    // hand of midas
    public static final int HAND_OF_MIDAS_CAN_NOT_CLAIM = 190;
    public static final int HAND_OF_MIDAS_WATCH_ADS_LIMIT_REACHED = 191;

    // tower
    public static final int TOWER_STAMINA_LIMIT_REACHED = 200;
    public static final int TOWER_STAGE_LIMIT_REACHED = 201;
    public static final int TOWER_STAGE_INVALID = 202;

    // tavern
    public static final int TAVERN_NO_QUEST_CAN_REROLL = 210;
    public static final int TAVERN_QUEST_CAN_NOT_CHANGE_LOCK = 211;
    public static final int TAVERN_QUEST_CAN_NOT_CANCEL = 212;
    public static final int TAVERN_QUEST_CAN_NOT_START = 213;
    public static final int TAVERN_QUEST_NOT_FOUND = 214;
    public static final int TAVERN_HERO_ANOTHER_QUEST_ALREADY_JOINED = 215;
    public static final int TAVERN_QUEST_REQUIREMENT_NOT_FULFILLED = 216;
    public static final int TAVERN_QUEST_NOT_COMPLETED = 217;
    public static final int TAVERN_QUEST_ALREADY_COMPLETED = 218;
    public static final int TAVERN_QUEST_LIMIT_REACHED = 219;
    public static final int TAVERN_QUEST_CAN_NOT_SPEED_UP = 220;

    // feature
    public static final int FEATURE_LOCKED = 230;

    // campaign
    public static final int CAMPAIGN_AUTO_TRAIN_SLOT_EXCEEDED = 240;
    public static final int CAMPAIGN_STAGE_INVALID = 241;
    public static final int CAMPAIGN_STAGE_NOT_FOUND = 242;
    public static final int CAMPAIGN_QUICK_BATTLE_TICKET_NOT_FOUND = 243;
    public static final int CAMPAIGN_TRAIN_HERO_STAR_INVALID = 244;
    public static final int CAMPAIGN_TRAIN_SLOT_EMPTY = 245;
    public static final int CAMPAIGN_LEVEL_NOT_ENOUGH = 246;
    public static final int CAMPAIGN_QUICK_BATTLE_LIMIT_REACHED = 247;
    public static final int CAMPAIGN_TRAIN_HERO_ALREADY_IN_RAISED_HERO = 248;
    public static final int CAMPAIGN_QUICK_BATTLE_WATCH_ADS_LIMIT_REACHED = 249;

    // friend
    public static final int FRIEND_ALREADY_EXISTED = 250;
    public static final int FRIEND_REQUEST_ALREADY_EXISTED = 251;
    public static final int FRIEND_FULL = 252;
    public static final int FRIEND_NOT_FOUND = 253;
    public static final int FRIEND_REQUEST_NOT_FOUND = 254;
    public static final int FRIEND_CAN_NOT_SCOUT = 255;
    public static final int FRIEND_SCOUT_RATE_NOT_FOUND = 256;
    public static final int FRIEND_NUMBER_RAID_LIMIT_REACHED = 260;
    public static final int FRIEND_TARGET_FULL = 262;
    public static final int FRIEND_REQUEST_FULL = 263;

    public static final int FRIEND_POINT_LIMIT_REACHED = 265;
    public static final int FRIEND_POINT_NOTHING_TO_RECEIVE = 266;

    // mail
    public static final int MAIL_SEND_LIMIT_REACHED = 270;
    public static final int MAIL_NOT_FOUND = 271;
    public static final int MAIL_CAN_NOT_DELETE = 272;
    public static final int MAIL_REWARD_CAN_NOT_RECEIVE = 273;
    public static final int MAIL_LENGTH_INVALID = 274;

    // arena
    public static final int ARENA_OPPONENT_NOT_FOUND = 290;
    public static final int ARENA_FORMATION_NOT_SET = 291;
    public static final int ARENA_TICKET_BUY_LIMIT_REACHED = 292;
    public static final int ARENA_SEASON_REWARD_NOT_FOUND = 293;
    public static final int ARENA_BOT_OPPONENT_NOT_FOUND = 294;

    // record
    public static final int RECORD_NOT_FOUND = 300;

    // chat
    public static final int CHAT_FORBIDDEN = 310;
    public static final int CHAT_LENGTH_INVALID = 311;

    // reward video
    public static final int REWARD_VIDEO_REWARD_NOT_FOUND = 320;
    public static final int REWARD_VIDEO_LIMIT_REACHED = 321;

    // remote config
    public static final int REMOTE_CONFIG_KEY_NOT_FOUND = 330;
    public static final int REMOTE_CONFIG_VALUE_TYPE_MISMATCHED = 331;

    // language
    public static final int LANGUAGE_NOT_SUPPORTED = 340;

    // guild
    public static final int GUILD_ALREADY_JOINED = 350;
    public static final int GUILD_NOT_FOUND = 351;
    public static final int GUILD_PLAYER_IN_BLOCK_DURATION = 352;
    public static final int GUILD_MEMBER_FULL = 353;
    public static final int GUILD_MEMBER_NOT_FOUND = 354;
    public static final int GUILD_CAN_NOT_LEAVE = 355;
    public static final int GUILD_ALREADY_LEFT = 356;
    public static final int YOU_NOT_IN_A_GUILD = 357;

    public static final int GUILD_NAME_FORMAT_INVALID = 360;
    public static final int GUILD_NAME_ALREADY_BANNED = 361;
    public static final int GUILD_NAME_ALREADY_EXISTED = 362;
    public static final int GUILD_DESCRIPTION_FORMAT_INVALID = 363;
    public static final int GUILD_DESCRIPTION_ALREADY_BANNED = 364;

    public static final int GUILD_CREATOR_LEVEL_NOT_ENOUGH = 370;
    public static final int GUILD_PERMISSION_FORBIDDEN = 371;
    public static final int GUILD_SUB_LEADER_LIMIT_REACHED = 372;
    public static final int GUILD_MEMBER_ALREADY_CHECK_IN = 373;
    public static final int GUILD_MEMBER_INACTIVE_TOO_LONG = 376;

    public static final int GUILD_APPLICATION_ALREADY_EXISTED = 380;
    public static final int GUILD_APPLICATION_FULL = 381;
    public static final int GUILD_APPLICATION_NOT_FOUND = 382;

    public static final int GUILD_BOSS_NOT_FOUND = 400;
    public static final int GUILD_BOSS_ALREADY_CHALLENGED = 401;
    public static final int GUILD_BOSS_CHALLENGE_FORBIDDEN = 402;
    public static final int GUILD_BOSS_CAN_NOT_SELECT = 403;
    public static final int GUILD_BOSS_MONTHLY_STATISTICS_NOT_FOUND = 404;

    public static final int GUILD_DUNGEON_ALREADY_CHALLENGED = 410;
    public static final int GUILD_DUNGEON_NOT_FOUND = 411;
    public static final int GUILD_DUNGEON_STAGE_NOT_FOUND = 412;
    public static final int GUILD_DUNGEON_NOT_OPEN = 413;
    public static final int GUILD_DUNGEON_STAGE_ALREADY_COMPLETED = 414;

    public static final int GUILD_WAR_OPPONENT_NOT_REGISTERED = 420;
    public static final int GUILD_WAR_ALREADY_REGISTERED = 421;
    public static final int GUILD_WAR_OPPONENT_NOT_FOUND = 422;
    public static final int GUILD_WAR_OPPONENT_INVALID = 423;
    public static final int GUILD_WAR_NUMBER_MEMBER_MISMATCHED = 424;
    public static final int GUILD_WAR_OPPONENT_CAN_NOT_BE_FOUND = 425;
    public static final int GUILD_WAR_OPPONENT_CAN_NOT_BE_ATTACKED = 426;
    public static final int GUILD_WAR_NOT_REGISTERED = 427;
    public static final int GUILD_WAR_NOT_PARTICIPATED = 428;

    public static final int GUILD_WAR_PHASE_1_CLOSED = 430;
    public static final int GUILD_WAR_PHASE_2_CLOSED = 431;
    public static final int GUILD_WAR_PHASE_3_CLOSED = 432;

    public static final int QUEST_NOT_FOUND = 450;
    public static final int QUEST_REWARD_CAN_NOT_CLAIM = 451;
    public static final int QUEST_UNLOCK_REQUIREMENT_NOT_COMPLETED = 452;

    // market
    public static final int MARKET_ITEM_NOT_FOUND = 460;
    public static final int MARKET_ITEM_SOLD_OUT = 461;
    public static final int MARKET_TYPE_INVALID = 462;
    public static final int MARKET_UPGRADE_LEVEL_INVALID = 463;

    // formation
    public static final int FORMATION_INVALID = 470;

    // avatar
    public static final int AVATAR_NOT_FOUND = 480;
    public static final int AVATAR_FRAME_NOT_FOUND = 481;

    // tutorial
    public static final int TUTORIAL_STEP_NOT_FOUND = 490;
    public static final int TUTORIAL_FIRST_TIME_PURCHASED = 491;
    // password
    public static final int PASSWORD_EMAIL_SEND_LIMIT_REACHED = 501;
    public static final int PASSWORD_CODE_ENTER_LIMIT_REACHED = 502;

    public static final int PASSWORD_CODE_EXPIRED = 503;
    public static final int PASSWORD_CODE_MISMATCHED = 504;

    // validate email
    public static final int EMAIL_SEND_LIMIT_REACHED = 510;
    public static final int EMAIL_CODE_ENTER_LIMIT_REACHED = 511;

    public static final int EMAIL_VALIDATION_CODE_EXPIRED = 512;
    public static final int EMAIL_VALIDATION_CODE_MISMATCHED = 513;

    public static final int EMAIL_ALREADY_EXISTED = 514;

    public static final int EMAIL_CAN_NOT_SEND = 515;
    public static final int EMAIL_ALREADY_VERIFIED = 516;
    public static final int EMAIL_NOT_FOUND = 517;
    public static final int EMAIL_NOT_VERIFIED = 518;

    public static final int API_VERSION_INVALID = 600;
    public static final int API_VERSION_INVALID_FORMAT = 601;
    public static final int API_VERSION_WRONG = 603;

    public static final int WRONG_INBOUND_HASH = 610;
    public static final int INVALID_INBOUND_HASH = 611;

    public static final int INVALID_PLAYER_TOKEN = 620;
    public static final int WRONG_PLAYER_TOKEN = 621;
    public static final int PLAYER_NOT_IN_CACHE = 622;

    // tracking server
    public static final int TRACKING_SECRET_INVALID = 2500;

    // load balance server
    public static final int LOAD_BALANCE_SECRET_INVALID = 3000;
    public static final int LOAD_BALANCE_SERVER_FULL = 3001;
    public static final int LOAD_BALANCE_SERVER_MAINTENANCE = 3002;
}