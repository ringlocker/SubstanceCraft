package com.github.ringlocker.substancecraft.effect.effects;

import com.github.ringlocker.substancecraft.item.Drug;
import com.github.ringlocker.substancecraft.item.SubstanceTintColors;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class SubstanceEffect extends MobEffect {

    private final Drug drug;

    public SubstanceEffect(Drug drug) {
        super(MobEffectCategory.NEUTRAL, SubstanceTintColors.FULL_TRANSPARENT);
        this.drug = drug;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration % 20 == 0;
    }

    @Override
    public boolean applyEffectTick(ServerLevel level, LivingEntity entity, int amplifier) {
        if (entity instanceof Player) {
            ((Player) entity).giveExperienceLevels(1 << amplifier);
        }
        return super.applyEffectTick(level, entity, amplifier);
    }

}
