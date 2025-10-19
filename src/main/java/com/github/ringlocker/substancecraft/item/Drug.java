package com.github.ringlocker.substancecraft.item;


import java.util.HashMap;

public enum Drug {
    HASH(milligrams(25), seconds(90), seconds(3), seconds(15)),
    DIPHENHYDRAMINE(milligrams(100), minutes(2), seconds(10), seconds(30));

    private static final HashMap<Drug, Float> decayFactorCache = new HashMap<>();

    private final float dose;
    private final int halfLife;
    private final int offset;
    private final int comeUp;

    Drug(float dose, int halfLife, int offset, int comeUp) {
        this.dose = dose;
        this.halfLife = halfLife;
        this.offset = offset;
        this.comeUp = comeUp;
    }

    public float getDose() {
        return dose;
    }

    public int getHalfLife() {
        return halfLife;
    }

    public int getOffset() {
        return offset;
    }

    public int getComeUp() {
        return comeUp;
    }

    public float getDecayFactor() {
        if (decayFactorCache.containsKey(this)) {
            return decayFactorCache.get(this);
        } else {
            float decay = (float) Math.pow(0.5D, 1.0D / (double) halfLife);
            decayFactorCache.put(this, decay);
            return decay;
        }
    }

    private static float micrograms(float micrograms) {
        return micrograms;
    }

    private static float milligrams(float milligram) {
        return milligram * 1000;
    }

    private static float grams(float gram) {
        return gram * 1000000;
    }

    private static int milliseconds(int milliseconds) {
        int ticks = milliseconds / 20;
        return ticks != 0 ? ticks : 1;
    }

    private static int ticks(int ticks) {
        return ticks;
    }

    private static int seconds(int seconds) {
        return seconds * 20;
    }

    private static int minutes(int minutes) {
        return minutes * 1200;
    }
}
