package com.altimetrik.transactionsapi.modal;

public class Statistics {

    private double min;
    private double max;
    private double avg;
    private double sum;
    private long count;

    public Statistics() {
    }

    public Statistics(double min, double max, double avg, double sum, long count) {
        this.min = min;
        this.max = max;
        this.avg = avg;
        this.sum = sum;
        this.count = count;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "min=" + min +
                ", max=" + max +
                ", avg=" + avg +
                ", sum=" + sum +
                ", count=" + count +
                '}';
    }
}
