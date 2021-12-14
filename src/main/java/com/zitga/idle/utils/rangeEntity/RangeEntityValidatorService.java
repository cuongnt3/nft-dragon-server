package com.zitga.idle.utils.rangeEntity;

import com.zitga.bean.annotation.BeanComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@BeanComponent
public class RangeEntityValidatorService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public <T extends BaseRangeEntity> void validate(List<T> entities) {
        long minValue = Long.MAX_VALUE;
        long maxValue = Long.MIN_VALUE;

        long totalRange = 0;
        for (T entity : entities) {
            totalRange += entity.getRange();

            if (minValue > entity.getMin()) {
                minValue = entity.getMin();
            }

            if (maxValue < entity.getMax()) {
                maxValue = entity.getMax();
            }
        }

        long space = maxValue - minValue + 1;
        if (totalRange != space) {
            logger.info("{}", space);
            logger.info("{}", totalRange);
            throw new RuntimeException("Range of value didn't cover all space");
        }
    }
}
