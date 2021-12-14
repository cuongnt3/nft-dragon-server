package com.zitga.idle.player.utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlayerUtils {

    public static boolean isContainDuplicatedPlayerId(List<Long> playerIdList) {
        Set<Long> playerIdSet = new HashSet<>(playerIdList);
        if (playerIdSet.size() < playerIdList.size()) {
            return true;
        }

        return false;
    }
}
