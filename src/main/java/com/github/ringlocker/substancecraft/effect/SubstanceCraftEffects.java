package com.github.ringlocker.substancecraft.effect;

import com.github.ringlocker.substancecraft.SubstanceCraft;
import com.github.ringlocker.substancecraft.effect.effects.SubstanceEffect;
import com.github.ringlocker.substancecraft.item.Drug;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;

public class SubstanceCraftEffects {

    public static final Holder<MobEffect> STONED = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "stoned"), new SubstanceEffect(Drug.HASH));


    private static Holder<MobEffect> register(String id, MobEffect effect) {
        return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, id), effect);
    }

    public static void registerEffects() {

    }
}
