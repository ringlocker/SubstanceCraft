package com.github.ringlocker.substancecraft.entity;

import com.github.ringlocker.substancecraft.SubstanceCraft;
import com.github.ringlocker.substancecraft.entity.entities.ExoticDealer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class SubstanceCraftEntities {

    private static final Identifier EXOTIC_DEALER_LOCATION = Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "exotic_dealer");

    public static final EntityType<ExoticDealer> EXOTIC_DEALER = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            EXOTIC_DEALER_LOCATION,
            EntityType.Builder.of(ExoticDealer::new, MobCategory.CREATURE).sized(0.6f, 1.95f).eyeHeight(1.62F).clientTrackingRange(10).build(ResourceKey.create(Registries.ENTITY_TYPE, EXOTIC_DEALER_LOCATION))
    );

    public static void registerEntities() {
        FabricDefaultAttributeRegistry.register(EXOTIC_DEALER, ExoticDealer.createMobAttributes());
    }

}
