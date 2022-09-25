package com.sakkaris.qudotctl.models;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class QuDotResult {
    private String state;
    private long frequency;
    private double percentage;

    public QuDotResult() { }
    public QuDotResult(String state, long frequency, double percentage) {
        this.state = state;
        this.frequency = frequency;
        this.percentage = percentage;
    }

    public String getState() {
        return state;
    }

    public long getFrequency() {
        return frequency;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setFrequency(long frequency) {
        this.frequency = frequency;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}
