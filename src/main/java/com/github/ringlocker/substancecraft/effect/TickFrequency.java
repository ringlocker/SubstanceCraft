package com.github.ringlocker.substancecraft.effect;

public enum TickFrequency {

    PER_TICK(1),
    PER_SECOND(20),
    FIVE_SECOND(100);

    private final int frequency;

    TickFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getFrequency() {
        return frequency;
    }

}
