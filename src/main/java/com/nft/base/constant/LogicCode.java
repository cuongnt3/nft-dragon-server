package com.nft.base.constant;

import com.zitga.core.constants.BaseLogicCode;

public class LogicCode extends BaseLogicCode {

    public static final int AUTH_PASSWORD_NOT_MATCH = 20;
    public static final int AUTH_VERSION_MISMATCH = 23;
    public static final int AUTH_PASSWORD_MISMATCHED_LIMIT_REACHED = 24;
    public static final int AUTH_PASSWORD_MISMATCHED = 25;

    public static final int AUTH_PASSWORD_ALREADY_USED = 40;

    public static final int ADMIN_DUPLICATE_ROLE = 91;
    public static final int ADMIN_ACTION_NOT_FOUND = 92;
    public static final int ADMIN_ROLE_NOT_FOUND = 94;

    public static final int BATTLE_NOT_FOUND = 200;

    public static final int BATTLE_ALREADY_JOIN = 210;
    public static final int BATTLE_ALREADY_IN_BATTLE = 211;
    public static final int NOT_IN_BATTLE = 212;
}