package com.zitga.idle.statistics.utils;

import com.zitga.idle.enumeration.common.GameMode;

public class StatisticsUtils {

    public static String generateStatisticsKey(int heroId, int server, GameMode gameMode, int campaignMap) {
        return String.format("%s_%s_%s_%s", heroId, server, gameMode.getValue(), campaignMap);
    }

    public static String generateGameModeKey(int gameMode, int campaignMap) {
        return String.format("%s_%s", gameMode, campaignMap);
    }

    public static int getHeroId(String key) {
        String[] parser = key.split("_");
        return Integer.parseInt(parser[0]);
    }

    public static int getServer(String key) {
        String[] parser = key.split("_");
        return Integer.parseInt(parser[1]);
    }

    public static int getGameMode(String key) {
        String[] parser = key.split("_");
        return Integer.parseInt(parser[2]);
    }

    public static int getCampaignMap(String key) {
        String[] parser = key.split("_");
        return Integer.parseInt(parser[3]);
    }
}
