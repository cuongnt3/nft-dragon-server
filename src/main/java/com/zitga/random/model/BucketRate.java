package com.zitga.random.model;

import com.zitga.enumeration.random.RandomType;
import com.zitga.player.model.Player;
import com.zitga.random.service.RandomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BucketRate<T extends IBucket> {

    private static final Logger logger = LoggerFactory.getLogger(BucketRate.class);

    private List<Double> bucketRates;
    private List<T> buckets;

    public BucketRate(Collection<T> rawBuckets) {
        this(new ArrayList<>(rawBuckets));
    }

    public BucketRate(List<T> rawBucket) {
        if (rawBucket == null || rawBucket.isEmpty()) {
            throw new RuntimeException("Bucket list is empty");
        }

        this.bucketRates = new ArrayList<>(rawBucket.size());
        this.buckets = rawBucket;

        double totalRate = 0;
        for (IBucket bucket : rawBucket) {
            totalRate += bucket.getRate();
        }

        // calculate real rate base on rate factor
        double currentTotalRate = 0;
        for (IBucket bucket : rawBucket) {
            currentTotalRate += bucket.getRate();
            bucketRates.add(currentTotalRate / totalRate);
        }
    }

    public int size() {
        return buckets.size();
    }

    public List<T> getBuckets() {
        return buckets;
    }

    public T randomBucket() {
        return randomBucket(null, RandomType.COMMON, 0);
    }

    public T randomBucket(Player player, RandomType randomType) {
        return randomBucket(player, randomType, 0);
    }

    // id is used to differentiate between group
    public T randomBucket(Player player, RandomType randomType, int id) {
        float value = RandomService.nextFloat(player, randomType, id);
        for (int i = 0; i < bucketRates.size(); i++) {
            if (value <= bucketRates.get(i)) {
                return buckets.get(i);
            }
        }

        logger.error("Value: {}, bucketRates: {}", value, bucketRates);
        return buckets.get(0);
    }
}