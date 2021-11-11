package com.zitga.base.utils;

import com.zitga.authentication.constant.AuthConstant;

public class VersionUtils {

    public static boolean validateVersion(String version) {
        String[] versionNumbers = version.split("\\.");
        if (versionNumbers.length != AuthConstant.VERSION_NUMBER_COUNT_DEFAULT) {
            return false;
        }

        return true;
    }

    public static boolean compareVersion(String currentVersion, String unlockVersion) {
        String[] currentVersionNumbers = currentVersion.split("\\.");
        String[] unlockVersionNumbers = unlockVersion.split("\\.");
        for (int i = 0; i < AuthConstant.VERSION_NUMBER_COUNT_DEFAULT; i++) {
            try {
                int number = Integer.parseInt(currentVersionNumbers[i]);
                int unlockNumber = Integer.parseInt(unlockVersionNumbers[i]);
                if (unlockNumber == number) {
                    continue;
                } else if (unlockNumber < number) {
                    return true;
                } else {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }

        return true;
    }
}
