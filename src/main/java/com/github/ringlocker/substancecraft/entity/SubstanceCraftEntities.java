package com.github.ringlocker.substancecraft.entity;

import com.github.ringlocker.substancecraft.SubstanceCraft;
import com.github.ringlocker.substancecraft.entity.entities.Dealer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class SubstanceCraftEntities {

    private static final ResourceLocation DEALER_LOCATION = ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "dealer");
    public static final EntityType<Dealer> DEALER = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            DEALER_LOCATION,
            EntityType.Builder.of(Dealer::new, MobCategory.CREATURE).sized(0.6f, 1.95f).eyeHeight(1.62F).clientTrackingRange(10).build(ResourceKey.create(Registries.ENTITY_TYPE, DEALER_LOCATION))
    );

    public static void registerEntities() {
        FabricDefaultAttributeRegistry.register(DEALER, Dealer.createMobAttributes());
    }

}
