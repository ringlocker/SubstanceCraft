package com.github.ringlocker.substancecraft.effect.effects;

import com.github.ringlocker.substancecraft.item.SubstanceTintColors;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class BasicEffect  extends MobEffect {

    public BasicEffect(MobEffectCategory category) {
        super(category, SubstanceTintColors.FULL_TRANSPARENT);
    }

}
