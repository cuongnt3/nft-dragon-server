package com.nft.base.constant;

import com.zitga.core.constants.socket.BaseOpCode;

public class OpCode extends BaseOpCode {

    // admin
    public static final int ADMIN_PASSWORD_CHANGE = 30;
    public static final int ADMIN_ADD_ROLE_PERMISSION = 31;
    public static final int ADMIN_ASSIGN_ROLE = 32;
    public static final int ADMIN_GET_ADMIN = 33;
    public static final int ADMIN_GET_ROLE = 34;

    public static final int TANK_MOVE = 50;
    public static final int TANK_FIRE = 51;

    public static final int BATTLE_CREATE = 60;
    public static final int BATTLE_LIST_GET = 61;
    public static final int BATTLE_START = 62;
    public static final int BATTLE_JOIN = 63;
    public static final int BATTLE_LEAVE = 64;
    public static final int BATTLE_MEMBER_LIST = 65;

    public static final int WORLD_MAP_STATE_UPDATE = 1000;

    // notifications
    public static final int NOTIFICATION_BATTLE_START = 1500;

    public static final int NOTIFICATION_LOBBY_JOIN = 1510;
    public static final int NOTIFICATION_LOBBY_LEAVE = 1511;

    public static final int MASTER_SERVER_ADMIN_ACTION = 2001;

    public static final int LOAD_BALANCE_ADMIN_ACTION = 3100;
}