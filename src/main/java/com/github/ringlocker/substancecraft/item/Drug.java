package com.github.ringlocker.substancecraft.item;


import com.github.ringlocker.substancecraft.SubstanceCraft;
import com.github.ringlocker.substancecraft.effect.SubstanceCraftEffects;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;

import java.util.HashMap;
import java.util.List;

public enum Drug {
    HASH(
            milligrams(25), micrograms(400), minutes(1), seconds(3), seconds(20), fromID("stoned"),
            List.of(new DrugSideEffect(fromID("slow"), milligrams(30), milligrams(35), 9),
                    new DrugSideEffect(fromID("hungry"), milligrams(10), milligrams(25), 9),
                    new DrugSideEffect(fromID("color_enhancement"), milligrams(100), milligrams(50), 3),
                    new DrugSideEffect(fromID("color_resolution"), milligrams(100), milligrams(40), 5))
    ),
    DIPHENHYDRAMINE(
            milligrams(300), milligrams(25), minutes(3), seconds(10), seconds(45), fromID("dph"),
            List.of(new DrugSideEffect(fromID("slow"), milligrams(50), milligrams(25), 9))
    ),
    COCAINE(
            milligrams(150), milligrams(5), minutes(2), seconds(2), seconds(20), fromID("coke"),
            List.of(new DrugSideEffect(fromID("fast"), milligrams(30), milligrams(20), 24),
                    new DrugSideEffect(fromID("elevated_heart_rate"), milligrams(300), milligrams(150), 9),
                    new DrugSideEffect(fromID("cardiac_arrest"), milligrams(1200), milligrams(200), 9),
                    new DrugSideEffect(fromID("time_compression"), milligrams(100), milligrams(100), 24))
    ),
    AMPHETAMINE(
            milligrams(50), milligrams(2.5F), minutes(3), seconds(5), seconds(30), fromID("amphetamine"),
            List.of(new DrugSideEffect(fromID("fast"), milligrams(20), milligrams(10), 14),
                    new DrugSideEffect(fromID("not_hungry"), milligrams(30), grams(1), 0))
    ),
    KETAMINE(
            milligrams(250), milligrams(50), minutes(2), seconds(5), seconds(15), fromID("ketamine"),
            List.of(new DrugSideEffect(fromID("color_resolution"), milligrams(200), milligrams(50), 19))
    ),
    TWO_CB(
            milligrams(25), milligrams(5), minutes(3), seconds(10), seconds(45), fromID("two_cb"),
            List.of(new DrugSideEffect(fromID("color_enhancement"), milligrams(10), milligrams(8), 9, 19),
                    new DrugSideEffect(fromID("color_resolution"), milligrams(10), milligrams(10), 15, 18),
                    new DrugSideEffect(fromID("mosaic"), milligrams(25), milligrams(10), 9, 9),
                    new DrugSideEffect(fromID("dynamic_color"), milligrams(75), milligrams(20), 6, 9),
                    new DrugSideEffect(fromID("time_dilation"), milligrams(20), milligrams(10), 24))
    );

    private static final HashMap<Drug, Float> decayFactorCache = new HashMap<>();

    private final float dose;
    private final float threshold;
    private final int halfLife;
    private final int offset;
    private final int comeUp;
    private final Identifier baseEffect;
    private final List<DrugSideEffect> sideEffects;

    Drug(float dose, float threshold, int halfLife, int offset, int comeUp, Identifier baseEffect, List<DrugSideEffect> sideEffects) {
        this.dose = dose;
        this.threshold = threshold;
        this.halfLife = halfLife;
        this.offset = offset;
        this.comeUp = comeUp;
        this.baseEffect = baseEffect;
        this.sideEffects = sideEffects;
    }

    public float getDose() {
        return dose;
    }

    public float getThreshold() {
        return threshold;
    }

    public int getOffset() {
        return offset;
    }

    public int getComeUp() {
        return comeUp;
    }

    public Holder<MobEffect> getBaseEffect() {
        return SubstanceCraftEffects.getEffect(baseEffect);
    }

    public List<DrugSideEffect> getSideEffects() {
        return sideEffects;
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

    private static Identifier fromID(String id) {
        return Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, id);
    }

    private static int micrograms(int micrograms) {
        return micrograms;
    }

    private static float micrograms(float micrograms) {
        return micrograms;
    }

    private static int milligrams(int milligram) {
        return milligram * 1000;
    }

    private static float milligrams(float milligram) {
        return milligram * 1000;
    }

    private static int grams(int gram) {
        return gram * 1000000;
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

    public record DrugSideEffect(Identifier effect, int threshold, int amplifyEvery, int maxAmplifier, int hardAmplifierLimit) {

        public DrugSideEffect(Identifier effect, int threshold, int amplifyEvery, int maxAmplifier) {
            this(effect, threshold, amplifyEvery, maxAmplifier, 255);
        }

        public Holder<MobEffect> getEffect() {
            return SubstanceCraftEffects.getEffect(effect);
        }

        public int clampAmplifier(int amplifier) {
            return Math.clamp(amplifier, 0, maxAmplifier);
        }

    }

}
