package com.github.ringlocker.substancecraft.effect;

import com.github.ringlocker.substancecraft.data.component.SubstanceInstance;
import com.github.ringlocker.substancecraft.item.Drug;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;

import java.util.HashMap;

public class SideEffectsSummary {

    private final HashMap<Drug.DrugSideEffect, MobEffectInstanceBuilder> map = new HashMap<>();

    public void add(Drug.DrugSideEffect sideEffect, int amplifier, SubstanceInstance instance) {
        if (map.containsKey(sideEffect)) {
            map.get(sideEffect).increaseAmplifier(amplifier);
        } else {
            map.put(sideEffect, new MobEffectInstanceBuilder(sideEffect, amplifier));
        }
        map.get(sideEffect).estimateDuration(instance);
    }

    public void applyTo(ServerPlayer player) {
        map.values().forEach((instance) -> player.removeEffect(instance.effect.getEffect()));
        map.values().forEach((instance) -> player.addEffect(instance.build()));
    }

    private static class MobEffectInstanceBuilder {

        private final Drug.DrugSideEffect effect;
        private int duration = -1;
        private int amp;

        public MobEffectInstanceBuilder(Drug.DrugSideEffect effect, int amp) {
            this.effect = effect;
            this.amp = amp;
        }

        public void increaseAmplifier(int amount) {
            amp += amount;
        }

        public void estimateDuration(SubstanceInstance instance) {
            duration = Math.max(duration, calcRemainingTime(instance));
        }

        public MobEffectInstance build() {
            return new MobEffectInstance(effect.getEffect(), duration, amp, false, false, true);
        }

        private int calcRemainingTime(SubstanceInstance instance) {
            double decay = instance.drug().getDecayFactor();
            double start = instance.amount();
            double threshold = effect.threshold();
            return SubstanceEffectTicker.remainingTime(decay, start, threshold);
        }


    }

}
