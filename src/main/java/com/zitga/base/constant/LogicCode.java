package com.zitga.base.constant;

import com.zitga.core.constants.BaseLogicCode;

public class LogicCode extends BaseLogicCode {

    public static final int AUTH_PASSWORD_NOT_MATCH = 20;
    public static final int AUTH_VERSION_MISMATCH = 23;
    public static final int AUTH_PASSWORD_MISMATCHED_LIMIT_REACHED = 24;
    public static final int AUTH_PASSWORD_MISMATCHED = 25;

    public static final int AUTH_USERNAME_ALREADY_USED = 30;
    public static final int AUTH_USERNAME_FORMAT_INVALID = 31;
    public static final int AUTH_USERNAME_INVALID = 32;

    public static final int AUTH_PASSWORD_ALREADY_USED = 40;

    public static final int API_VERSION_INVALID = 500;
    public static final int API_VERSION_INVALID_FORMAT = 501;
    public static final int API_VERSION_WRONG = 503;

    public static final int WRONG_INBOUND_HASH = 510;
    public static final int INVALID_INBOUND_HASH = 511;
}