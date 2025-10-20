package com.github.ringlocker.substancecraft.effect.effects;

import com.github.ringlocker.substancecraft.effect.SubstanceCraftEffects;
import com.github.ringlocker.substancecraft.effect.damagesource.SubstanceCraftDamageSources;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

public class SimpleEffects {

    public static class Overdose extends SubstanceCraftEffects.TickingEffect {

        public Overdose() {
            super(MobEffectCategory.HARMFUL);
        }

        @Override
        protected boolean shouldTick(int duration) {
            return duration % 20 == 0;
        }

        @Override
        protected void tick(ServerLevel level, LivingEntity entity, int amplifier) {
            boolean hasNarcan = entity.hasEffect(SubstanceCraftEffects.NARCAN);
            if (!hasNarcan) {
                entity.hurtServer(level, getDamageSource(level), 2.0F * (amplifier + 1));
            } else {
                MobEffectInstance instance = entity.getEffect(SubstanceCraftEffects.NARCAN);
                if (instance == null) entity.hurtServer(level, getDamageSource(level), 2.0F * (amplifier + 1));
                else {
                    int narcAmplifier = instance.getAmplifier();
                    if (narcAmplifier < amplifier) entity.hurtServer(level, getDamageSource(level), 2.0F * (amplifier + 1 - (narcAmplifier + 1)));
                }
            }
        }

        private static DamageSource getDamageSource(ServerLevel level) {
            return SubstanceCraftDamageSources.getDamageSource(level, SubstanceCraftDamageSources.OVERDOSE);
        }
    }

}
