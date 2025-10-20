package com.github.ringlocker.substancecraft.effect.effects;

import com.github.ringlocker.substancecraft.effect.SubstanceCraftEffects;
import com.github.ringlocker.substancecraft.effect.damagesource.SubstanceCraftDamageSources;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

public class SimpleEffects {

    public static class OpioidOverdose extends SubstanceCraftEffects.TickingEffect {

        public OpioidOverdose() {
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

    public static class NotHungry extends SubstanceCraftEffects.TickingEffect {

        public NotHungry() {
            super(MobEffectCategory.BENEFICIAL);
        }

        @Override
        protected boolean shouldTick(int duration) {
            return duration % 20 == 0;
        }

        @Override
        protected void tick(ServerLevel level, LivingEntity entity, int amplifier) {
            if (entity instanceof ServerPlayer player) {
                player.getFoodData().setFoodLevel(20);
                player.getFoodData().setSaturation(0.5F);
            }
        }
    }

    public static class Hungry extends SubstanceCraftEffects.TickingEffect {

        public Hungry() {
            super(MobEffectCategory.HARMFUL);
        }

        @Override
        protected boolean shouldTick(int duration) {
            return duration % 100 == 0;
        }

        @Override
        protected void tick(ServerLevel level, LivingEntity entity, int amplifier) {
            if (entity instanceof ServerPlayer player) {
               player.getFoodData().addExhaustion(0.5F * amplifier);
            }
        }

    }



}
