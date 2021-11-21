package com.zitga.statistics.model;

import com.zitga.base.constant.CollectionName;
import com.zitga.base.model.BasePlayerComponent;
import com.zitga.enumeration.random.RandomType;
import com.zitga.player.model.Player;
import com.zitga.random.constant.RandomConstant;
import com.zitga.statistics.constant.StatisticsTag;
import com.zitga.utils.RandomUtils;
import com.zitga.utils.TimeUtils;
import io.netty.buffer.ByteBuf;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Entity(value = CollectionName.PLAYER_STATISTICS, noClassnameStored = true)
public class PlayerStatistics extends BasePlayerComponent {


    @Embedded(StatisticsTag.SAMPLE_INDEX_TAG)
    // key: RandomType + id
    private final Map<String, Integer> sampleIndexMap = new ConcurrentHashMap<>();

    public PlayerStatistics() {
        // For serialize purpose
    }

    public PlayerStatistics(Player player) {
        super(player);
    }

    // ------------------------------------------ Getters ------------------------------------------

    // ------------------------------------------ Setters ------------------------------------------
    public int getAndIncreaseSampleIndex(RandomType randomType, int id) {
        String tag = String.format("%s_%s", randomType.getValue(), id);

        int index = sampleIndexMap.getOrDefault(tag, RandomUtils.nextInt(RandomConstant.RANDOM_SPACE_SIZE));

        int newIndex = (index + 1) % RandomConstant.RANDOM_SPACE_SIZE;
        sampleIndexMap.put(tag, newIndex);

        return index;
    }
}