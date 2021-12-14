package com.zitga.idle.campaign.utils;

public class CampaignCsvPathUtils {

    private static final String CAMPAIGN_PATH = "campaign";
    private static final String CAMPAIGN_QUICK_BATTLE_PATH = "quick_battle";

    // ---------------------------------------- Campaign ----------------------------------------
    public static String getCampaignConfigPath() {
        return String.format("%s/campaign_config.csv", CAMPAIGN_PATH);
    }

    public static String getCampaignInstantRewardPath() {
        return String.format("%s/instant_rewards.csv", CAMPAIGN_PATH);
    }

    public static String getCampaignAbTestDataFolderPath() {
        return String.format("%s/ab_test", CAMPAIGN_PATH);
    }

    public static String getCampaignABTestDefenderTeamPath(int dataId) {
        return String.format("%s/ab_test/data_%d/defender_team.csv", CAMPAIGN_PATH, dataId);
    }

    public static String getCampaignIdleItemRewardPath() {
        return String.format("%s/idle_item_reward.csv", CAMPAIGN_PATH);
    }

    public static String getCampaignIdleMoneyRewardPath() {
        return String.format("%s/idle_money_reward.csv", CAMPAIGN_PATH);
    }

    public static String getCampaignDefenderTeamPath() {
        return String.format("%s/defender_team.csv", CAMPAIGN_PATH);
    }

    public static String getCampaignStageLevelRequiredPath() {
        return String.format("%s/level_required.csv", CAMPAIGN_PATH);
    }

    public static String getCampaignQuickBattleTicketConfigPath() {
        return String.format("%s/%s/campaign_quick_battle_ticket_config.csv", CAMPAIGN_PATH, CAMPAIGN_QUICK_BATTLE_PATH);
    }

    public static String getCampaignQuickBattleConfigPath() {
        return String.format("%s/%s/campaign_quick_battle_config.csv", CAMPAIGN_PATH, CAMPAIGN_QUICK_BATTLE_PATH);
    }
}