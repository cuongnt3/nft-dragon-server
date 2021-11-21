package com.zitga.random.service;

import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.bean.annotation.BeanMethod;
import com.zitga.enumeration.random.RandomType;
import com.zitga.executor.service.ExecutorService;
import com.zitga.player.model.Player;
import com.zitga.random.constant.RandomConstant;
import com.zitga.random.model.IBucket;
import com.zitga.support.encryption.HashHelper;
import com.zitga.utils.FileService;
import com.zitga.utils.GZipUtils;
import com.zitga.utils.RandomUtils;
import com.zitga.utils.ResourcesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@BeanComponent
public class RandomService {

    private static final Logger logger = LoggerFactory.getLogger(RandomService.class);

    @BeanField
    private FileService fileService;

    @BeanField
    private ExecutorService executorService;

    private static List<Float> randomSamples;

    @BeanMethod
    private void init() {
        logger.info("Initializing RandomService ...");

        executorService.executeBeanTask(() -> {
            try {
                createRandomSample();
            } catch (Throwable e) {
                logger.error(e.getMessage(), e);
            }
        });
    }

    // ---------------------------------------- Init ----------------------------------------
    private void createRandomSample() throws Exception {
        randomSamples = new ArrayList<>(RandomConstant.RANDOM_SPACE_SIZE);

        logger.info("Reading random sample file ...");
        String compressedText = ResourcesUtils.getText(RandomConstant.SAMPLE_FILE_PATH);
        logger.info("Random sample file size: {} KB", compressedText.length() / 1024);

        logger.info("Comparing hash256 value ...");
        String calculatedHash = HashHelper.hashSHA256(compressedText);
        if (!calculatedHash.equalsIgnoreCase(RandomConstant.RANDOM_SAMPLE_HASH_256_VALUE)) {
            throw new RuntimeException("Wrong hash256 value of random sample file");
        }

        logger.info("Splitting random sample file content ...");
        String plainText = GZipUtils.decompress(compressedText);
        String[] separatedValues = plainText.split(RandomConstant.SEPARATOR);
        logger.info("Number of random sample: {}", separatedValues.length);

        logger.info("Converting to sample value ...");
        for (String value : separatedValues) {
            float sample = Float.parseFloat(value);
            randomSamples.add(sample);
        }

        if (randomSamples.size() != RandomConstant.RANDOM_SPACE_SIZE) {
            throw new RuntimeException("Number random sample isn't enough");
        }
    }

    // ---------------------------------------- Generate sample ----------------------------------------
    // id is used to differentiate between group
    public static float nextFloat(Player player, RandomType randomType, int id) {
        if (player == null || randomType == RandomType.COMMON) {
            return RandomUtils.nextFloat();
        }

        int index = player.getStatistics().getAndIncreaseSampleIndex(randomType, id);
        return randomSamples.get(index);
    }

    public static <T extends IBucket> T randomRawBucket(List<T> rawBucketRates) {
        return randomRawBucket(null, RandomType.COMMON, 0, rawBucketRates);
    }

    public static <T extends IBucket> T randomRawBucket(Player player, RandomType randomType, List<T> rawBucketRates) {
        return randomRawBucket(player, randomType, 0, rawBucketRates);
    }

    // id is used to differentiate between group
    public static <T extends IBucket> T randomRawBucket(Player player, RandomType randomType, int id, List<T> rawBucketRates) {
        List<Double> bucketRates = getBucketRateList(rawBucketRates);

        float value = nextFloat(player, randomType, id);
        for (int i = 0; i < bucketRates.size(); i++) {
            if (value <= bucketRates.get(i)) {
                return rawBucketRates.get(i);
            }
        }

        logger.error("Value: {}, bucketRates: {}", value, bucketRates);
        return rawBucketRates.get(0);
    }

    // ---------------------------------------- Helpers ----------------------------------------
    private static <T extends IBucket> List<Double> getBucketRateList(List<T> rawBucketRates) {
        List<Double> result = new ArrayList<>();

        double totalRate = 0;
        for (IBucket bucket : rawBucketRates) {
            totalRate += bucket.getRate();
        }

        // calculate real rate base on rate factor
        double currentTotal = 0;
        for (T rateBucket : rawBucketRates) {
            currentTotal += rateBucket.getRate();
            result.add(currentTotal / totalRate);
        }

        return result;
    }
}