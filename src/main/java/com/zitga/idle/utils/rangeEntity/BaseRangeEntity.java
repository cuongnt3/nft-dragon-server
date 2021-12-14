package com.zitga.idle.utils.rangeEntity;

public abstract class BaseRangeEntity {

    public static final long RANGE_MULTIPLIER = 10000L;

    private final long min;
    private final long max;

    public BaseRangeEntity(long min, long max) {
        if (min >= 0) {
            this.min = min;
        } else {
            // extends range to prevent overflow
            this.min = RANGE_MULTIPLIER * Integer.MIN_VALUE;
        }

        if (max >= 0) {
            this.max = max;
        } else {
            // extends range to prevent overflow
            this.max = RANGE_MULTIPLIER * Integer.MAX_VALUE;
        }

        if (this.min > this.max) {
            throw new RuntimeException(String.format("Min value is larger than max value, min = %s, max = %s", this.min, this.max));
        }
    }

    public long getMin() {
        return min;
    }

    public long getMax() {
        return max;
    }

    public long getRange() {
        return max - min + 1;
    }

    public boolean isInRange(long value) {
        return min <= value && value <= max;
    }
}