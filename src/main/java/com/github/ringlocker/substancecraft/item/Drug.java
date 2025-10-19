package com.github.ringlocker.substancecraft.item;


import com.github.ringlocker.substancecraft.SubstanceCraft;
import com.github.ringlocker.substancecraft.effect.SubstanceCraftEffects;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;

import java.util.HashMap;
import java.util.List;

public enum Drug {
    HASH(
            milligrams(25), micrograms(400), minutes(2), seconds(3), seconds(20), fromID("stoned"),
            List.of(new DrugSideEffect(fromID("slow"), milligrams(30), milligrams(15)))
    ),
    DIPHENHYDRAMINE(
            milligrams(300), milligrams(25), minutes(4), seconds(10), seconds(90), fromID("dph"),
            List.of(new DrugSideEffect(fromID("slow"), milligrams(50), milligrams(25)))
    ),
    COCAINE(
            milligrams(100), milligrams(5), minutes(3), seconds(5), seconds(30), fromID("coke"),
            List.of(new DrugSideEffect(fromID("fast"), milligrams(30), milligrams(10)))
    ),
    AMPHETAMINE(
            milligrams(50), milligrams(2.5F), minutes(4), seconds(10), seconds(45), fromID("amphetamine"),
            List.of(new DrugSideEffect(fromID("fast"), milligrams(30), milligrams(10)))
    ),
    KETAMINE(
            milligrams(250), milligrams(50), minutes(3), seconds(5), seconds(30), fromID("ketamine"),
            List.of(new DrugSideEffect(fromID("fast"), milligrams(200), milligrams(50)))
    );

    private static final HashMap<Drug, Float> decayFactorCache = new HashMap<>();

    private final float dose;
    private final float threshold;
    private final int halfLife;
    private final int offset;
    private final int comeUp;
    private final ResourceLocation baseEffect;
    private final List<DrugSideEffect> sideEffects;

    Drug(float dose, float threshold, int halfLife, int offset, int comeUp, ResourceLocation baseEffect, List<DrugSideEffect> sideEffects) {
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

    private static ResourceLocation fromID(String id) {
        return ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, id);
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

    public record DrugSideEffect(ResourceLocation effect, int threshold, int amplifyEvery) {

        public Holder<MobEffect> getEffect() {
            return SubstanceCraftEffects.getEffect(effect);
        }

    }

}
