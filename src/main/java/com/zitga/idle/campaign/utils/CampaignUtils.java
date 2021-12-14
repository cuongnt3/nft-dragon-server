package com.zitga.idle.campaign.utils;

import com.zitga.idle.campaign.constant.CampaignConstant;

public class CampaignUtils {
    public static int getMapId(int stage) {
        return stage / CampaignConstant.STAGE_MODIFIER;
    }
}
