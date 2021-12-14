package com.zitga.idle.item.utils;

public class ItemCsvPathUtils {

    private static final String ITEM_PATH = "item";

    // ---------------------------------------- Item ----------------------------------------
    public static String getSkinPath() {
        return String.format("%s/skin/skin.csv", ITEM_PATH);
    }

    public static String getArtifactPath() {
        return String.format("%s/artifact.csv", ITEM_PATH);
    }

    public static String getStonePath() {
        return String.format("%s/stone.csv", ITEM_PATH);
    }

    public static String getEquipmentPath() {
        return String.format("%s/equipment.csv", ITEM_PATH);
    }

    public static String getEquipmentSetPath() {
        return String.format("%s/equipment_set.csv", ITEM_PATH);
    }


    public static String getTalentStatPath() {
        return String.format("%s/talent/talent_stat.csv", ITEM_PATH);
    }

    public static String getIdleEffectCsvPath() {
        return String.format("%s/idle_effect/idle_effect.csv", ITEM_PATH);
    }

    // ---------------------------------------- Avatar / Frame ----------------------------------------
}