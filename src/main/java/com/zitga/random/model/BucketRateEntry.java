package com.zitga.random.model;

public class BucketRateEntry implements IBucket {

    private int value;
    private float rate;

    public BucketRateEntry(int value, float rate) {
        this.value = value;
        this.rate = rate;
    }

    public int getValue() {
        return value;
    }

    @Override
    public float getRate() {
        return rate;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
}
