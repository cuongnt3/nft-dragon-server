package com.zitga.idle.enumeration.notification;

public enum NotificationType {
    MAIL_CHANGED(0),
    FRIEND_ADD(1),
    FRIEND_DELETE(2),
    FRIEND_REQUEST(3),
    FRIEND_SEND_POINT(4),
    SERVER_OPEN_UPDATED(5),
    QUEST_UPDATED(6),
    QUEST_COMPLETED(7),
    IAP_UPDATED(8),
    GUILD_MEMBER_ADDED(9),
    GUILD_MEMBER_KICKED(10),
    EVENT_UPDATED(11),
    AVATAR_UPDATED(12),
    ACHIEVEMENT_UPDATED(13),
    ACHIEVEMENT_COMPLETED(14),
    ARENA_BE_ATTACKED(15),
    EVENT_SERVER_OPEN_QUEST_COMPLETED(16),
    EVENT_GUILD_QUEST_DONATE(17),
    GUILD_WAR_REGISTERED(18),
    RAISED_HERO_PENTAGRAM_UPDATE(19),
    SUN_GAME_WEB_PURCHASE(20),
    LINKING_SUPPORT_HERO_CHANGED(21),
    FEATURE_UPDATED(22),
    ARENA_TEAM_BE_ATTACKED(23),
    COMEBACK_DATA_UPDATED(24),

    DOMAINS_CREW_UPDATED(25),
    DOMAINS_CREW_DISBANDED(26),
    DOMAINS_CREW_MEMBER_ADDED(27),
    DOMAINS_CREW_MEMBER_KICKED(28),
    DOMAINS_CREW_CHALLENGE_START(29),
    DOMAINS_CREW_CHALLENGE_UPDATED(30),
    DOMAINS_CREW_MEMBER_READY_UPDATED(31),
    DOMAINS_CREW_APPLICATION_UPDATED(32),
    DOMAINS_CREW_INVITATION_UPDATED(33),

    DAILY_QUEST_UPDATED(34)
    ;

    private final int value;

    NotificationType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static NotificationType toNotificationType(int type) {
        if (type >= 0 && type < NotificationType.values().length) {
            return NotificationType.values()[type];
        }

        return null;
    }
}