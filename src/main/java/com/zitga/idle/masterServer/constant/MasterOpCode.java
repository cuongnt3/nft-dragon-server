package com.zitga.idle.masterServer.constant;

public class MasterOpCode {

    public static final int HAND_SHAKE = 0;
    public static final int PING = 1;

    public static final int MAIL_CREATED = 10;
    public static final int ADMIN_MAIL_UPDATED = 11;
    public static final int ADMIN_SYSTEM_MAIL_UPDATED = 12;

    public static final int PLAYER_LOGIN = 20;
    public static final int PLAYER_BASIC_INFO_UPDATED = 21;
    public static final int PLAYER_BAN = 22;
    public static final int PLAYER_DATA_RELOAD = 23;

    public static final int RANKING_UPDATED = 30;

    public static final int EVENT_UPDATED = 40;
    public static final int EVENT_PLAYER_REWARD_STATE_UPDATED = 41;
    public static final int EVENT_GUILD_QUEST_DONATION_UPDATED = 42;

    public static final int FRIEND_REQUEST_ADDED = 50;
    public static final int FRIEND_ADDED = 51;
    public static final int FRIEND_DELETED = 52;
    public static final int FRIEND_POINT_SENT = 53;

    public static final int CHAT_UPDATED = 60;
    public static final int CHAT_ADDED = 61;
    public static final int CHAT_DELETED = 62;
    public static final int CHAT_PLAYER_DELETED = 63;

    public static final int ARENA_RANKING_UPDATED = 71;
    public static final int ARENA_ELO_UPDATED = 72;
    public static final int ARENA_BE_ATTACKED = 73;

    public static final int BATTLE_RECORD_ADDED = 80;
    public static final int ARENA_TEAM_RECORD_ADDED = 81;

    public static final int GUILD_WAR_TIME_UPDATED = 90;
    public static final int GUILD_DUNGEON_RANKING_UPDATED = 91;
    public static final int GUILD_WAR_RANKING_UPDATED = 92;
    public static final int GUILD_MEMBER_UPDATED = 93;
    public static final int GUILD_WAR_REGISTERED = 94;

    public static final int CLUSTER_UPDATED = 100;

    public static final int MAINTENANCE_START = 110;
    public static final int MAINTENANCE_STOP = 111;

    public static final int FEATURE_CONFIG_UPDATE = 120;

    public static final int LINKING_SUPPORT_HERO_CHANGE = 130;

    public static final int ARENA_TEAM_RANKING_UPDATED = 140;
    public static final int ARENA_TEAM_ELO_UPDATED = 141;
    public static final int ARENA_TEAM_BE_ATTACKED = 142;

    public static final int DOMAIN_CREW_DELETED = 150;
    public static final int DOMAIN_CREW_MEMBER_UPDATED = 151;
    public static final int DOMAIN_CREW_CHALLENGE_UPDATED = 152;
    public static final int DOMAIN_CREW_UPDATED = 153;
    public static final int DOMAIN_CREW_MEMBER_READY_UPDATED = 154;
    public static final int DOMAIN_CREW_APPLICATION_UPDATED = 155;
    public static final int DOMAIN_CREW_INVITATION_UPDATED = 156;

    // event
    public static final int EVENT_CHRISTMAS_RANKING_UPDATED = 200;

    public static final int EVENT_LUNAR_PATH_CLEAR_BOSS_REWARD_RECEIVED = 210;
    public static final int EVENT_LUNAR_PATH_POINT_CHANGED = 211;
    public static final int EVENT_LUNAR_PATH_LUNAR_BOSS_RANKING_UPDATED = 212;
    public static final int EVENT_LUNAR_PATH_LUNAR_MEMBER_KICKED = 213;

    public static final int EVENT_NEW_HERO_BOSS_RANKING_UPDATED = 220;
}