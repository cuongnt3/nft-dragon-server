package com.zitga.idle.statistics.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zitga.core.message.socket.IDeserializable;
import com.zitga.core.message.socket.ISerializable;
import com.zitga.idle.base.constant.BasicTag;
import com.zitga.idle.base.constant.CollectionName;
import com.zitga.idle.base.model.BasePlayerComponent;
import com.zitga.idle.enumeration.common.MoneyType;
import com.zitga.idle.enumeration.random.RandomType;
import com.zitga.idle.player.model.Player;
import com.zitga.idle.random.constant.RandomConstant;
import com.zitga.idle.statistics.constant.StatisticsTag;
import com.zitga.utils.RandomUtils;
import com.zitga.utils.TimeUtils;
import io.netty.buffer.ByteBuf;
import org.mongodb.morphia.annotations.*;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Entity(value = CollectionName.PLAYER_STATISTICS, noClassnameStored = true)
public class PlayerStatistics extends BasePlayerComponent implements ISerializable, IDeserializable{

    @JsonIgnore
    @Version
    @Property(BasicTag.DOCUMENT_VERSION_TAG)
    private long documentVersion;

    @Property(StatisticsTag.TOTAL_ONLINE_TAG)
    private long totalOnlineTime;

    @Property(StatisticsTag.NUMBER_LOGIN_TIME_TAG)
    private int numberLoginTime;

    @Property(StatisticsTag.TOTAL_SPEED_UP_TIME_TAG)
    private long totalSpeedUpTime;

    @Embedded(StatisticsTag.EARNED_MONEY_TAG)
    // key: MoneyType
    private final Map<Integer, Long> earnedMoneyAmount = new ConcurrentHashMap<>();

    @Embedded(StatisticsTag.SPENT_MONEY_TAG)
    // key: MoneyType
    private final Map<Integer, Long> spentMoneyAmount = new ConcurrentHashMap<>();

    @Embedded(StatisticsTag.SAMPLE_INDEX_TAG)
    // key: RandomType + id
    private final Map<String, Integer> sampleIndexMap = new ConcurrentHashMap<>();

    @Transient
    @NotSaved
    private Date lastIncreaseOnlineTime;

    public PlayerStatistics() {
        // For serialize purpose
        numberLoginTime = 1;
    }

    public PlayerStatistics(Player player) {
        super(player);
        numberLoginTime = 1;
    }

    // ------------------------------------------ Getters ------------------------------------------
    public long getTotalOnlineTime(Date lastLoginTime) {
        long onlineTimeToAdd;
        if (lastIncreaseOnlineTime != null) {
            onlineTimeToAdd = TimeUtils.getCurrentTimeInSecond() - TimeUtils.toSecond(lastIncreaseOnlineTime);
        } else {
            onlineTimeToAdd = TimeUtils.getCurrentTimeInSecond() - TimeUtils.toSecond(lastLoginTime);
        }

        return totalOnlineTime + onlineTimeToAdd;
    }

    public int getNumberLoginTime() {
        return numberLoginTime;
    }

    public Date getLastIncreaseOnlineTime() {
        return lastIncreaseOnlineTime;
    }

    public long getTotalSpeedUpTime() {
        return totalSpeedUpTime;
    }

    public long getEarnedMoney(MoneyType moneyType) {
        return earnedMoneyAmount.getOrDefault(moneyType.getValue(), 0L);
    }

    public long getSpentMoney(MoneyType moneyType) {
        return spentMoneyAmount.getOrDefault(moneyType.getValue(), 0L);
    }

    @Override
    public void serialize(ByteBuf out) {
        out.writeLongLE(totalOnlineTime);
        out.writeIntLE(numberLoginTime);
        out.writeLongLE(totalSpeedUpTime);
    }

    // ------------------------------------------ Setters ------------------------------------------
    public void increaseNumberLogin() {
        numberLoginTime++;
    }

    public void increaseOnlineTime(long onlineTime) {
        totalOnlineTime += onlineTime;
        lastIncreaseOnlineTime = TimeUtils.getCurrentTimeInGMT();
    }


    public void increaseMoneyEarn(MoneyType moneyType, long amount) {
        long currentAmount = earnedMoneyAmount.getOrDefault(moneyType.getValue(), 0L);
        earnedMoneyAmount.put(moneyType.getValue(), currentAmount + amount);
    }

    public void increaseMoneySpend(MoneyType moneyType, long amount) {
        long currentAmount = spentMoneyAmount.getOrDefault(moneyType.getValue(), 0L);
        spentMoneyAmount.put(moneyType.getValue(), currentAmount + amount);
    }

    public int getAndIncreaseSampleIndex(RandomType randomType, int id) {
        String tag = String.format("%s_%s", randomType.getValue(), id);

        int index = sampleIndexMap.getOrDefault(tag, RandomUtils.nextInt(RandomConstant.RANDOM_SPACE_SIZE));

        int newIndex = (index + 1) % RandomConstant.RANDOM_SPACE_SIZE;
        sampleIndexMap.put(tag, newIndex);

        return index;
    }

    public void speedUp(int seconds) {
        totalSpeedUpTime += seconds;
    }

    @Override
    public void deserialize(ByteBuf in) {
        totalOnlineTime = in.readLongLE();
        numberLoginTime = in.readIntLE();
        totalSpeedUpTime = in.readLongLE();
    }
}