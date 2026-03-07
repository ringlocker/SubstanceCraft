package com.github.ringlocker.substancecraft.effect.effects;

import com.github.ringlocker.substancecraft.effect.TickFrequency;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public abstract class TickingEffect extends BasicEffect {

    private final TickFrequency frequency;

    protected TickingEffect(MobEffectCategory category, TickFrequency frequency) {
        super(category);
        this.frequency = frequency;
    }

    protected boolean shouldTick(int duration) {
        return duration % frequency.getFrequency() == 0;
    }

    protected abstract void tick(ServerLevel level, LivingEntity entity, int amplifier);

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return this.shouldTick(duration);
    }

    @Override
    public boolean applyEffectTick(ServerLevel level, LivingEntity entity, int amplifier) {
        this.tick(level, entity, amplifier);
        return super.applyEffectTick(level, entity, amplifier);
    }

}