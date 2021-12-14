package com.zitga.idle.localization.utils;

public class LocalizationCsvPathUtils {

    private static final String LOCALIZATION_PATH = "localization";

    public static String getLocalizationMailPath() {
        return String.format("%s/mail", LOCALIZATION_PATH);
    }
}
