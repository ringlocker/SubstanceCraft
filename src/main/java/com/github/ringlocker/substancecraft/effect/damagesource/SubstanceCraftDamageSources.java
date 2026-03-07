package com.github.ringlocker.substancecraft.effect.damagesource;

import com.github.ringlocker.substancecraft.SubstanceCraft;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;

public class SubstanceCraftDamageSources {

    public static final ResourceKey<DamageType> OVERDOSE = fromID("overdose");
    public static final ResourceKey<DamageType> CARDIAC_ARREST = fromID("cardiac_arrest");

    public static void registerDamageSources() {
    }

    private static ResourceKey<DamageType> fromID(String id) {
       return ResourceKey.create(Registries.DAMAGE_TYPE, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, id));
    }

    public static DamageSource getDamageSource(ServerLevel level, ResourceKey<DamageType> damageType) {
        Registry<DamageType> damageTypes = level.registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE);
        return new DamageSource(damageTypes.getOrThrow(damageType));
    }

}
