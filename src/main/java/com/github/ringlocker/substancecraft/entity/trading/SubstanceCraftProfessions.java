package com.github.ringlocker.substancecraft.entity.trading;

import com.github.ringlocker.substancecraft.SubstanceCraft;
import com.github.ringlocker.substancecraft.block.SubstanceCraftBlocks;
import com.google.common.collect.ImmutableSet;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PoiHelper;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.villager.VillagerProfession;
import net.minecraft.world.item.trading.TradeSet;

public class SubstanceCraftProfessions {

    public static final ResourceKey<PoiType> DEALER_POI = createPoiKey("dealer");
    public static final ResourceKey<PoiType> EXOTIC_FARMER_POI = createPoiKey("exotic_farmer");

    public static final ResourceKey<VillagerProfession> DEALER = createProfessionKey("dealer");
    public static final ResourceKey<VillagerProfession> EXOTIC_FARMER = createProfessionKey("exotic_farmer");


    public static void registerVillagerProfessions() {
        PoiHelper.register(DEALER_POI.identifier(), 1, 1, SubstanceCraftBlocks.CHEMIST_WORKSTATION);
        PoiHelper.register(EXOTIC_FARMER_POI.identifier(), 1, 1, SubstanceCraftBlocks.PLANT_RESEARCH_STATION);

        register(
                DEALER,
                DEALER_POI,
                SoundEvents.VILLAGER_WORK_CLERIC,
                Int2ObjectMap.ofEntries(
                        Int2ObjectMap.entry(1, SubstanceCraftTradeSets.DEALER_1),
                        Int2ObjectMap.entry(2, SubstanceCraftTradeSets.DEALER_2),
                        Int2ObjectMap.entry(3, SubstanceCraftTradeSets.DEALER_3)
                )
        );

        register(
                EXOTIC_FARMER,
                EXOTIC_FARMER_POI,
                SoundEvents.VILLAGER_WORK_FARMER,
                Int2ObjectMap.ofEntries(
                        Int2ObjectMap.entry(1, SubstanceCraftTradeSets.EXOTIC_FARMER_1),
                        Int2ObjectMap.entry(2, SubstanceCraftTradeSets.EXOTIC_FARMER_2),
                        Int2ObjectMap.entry(3, SubstanceCraftTradeSets.EXOTIC_FARMER_3)
                )
        );
    }

    private static void register(ResourceKey<VillagerProfession> name, ResourceKey<PoiType> jobSite, SoundEvent sound, Int2ObjectMap<ResourceKey<TradeSet>> trades) {
        Registry.register(
                BuiltInRegistries.VILLAGER_PROFESSION,
                name,
                new VillagerProfession(
                        Component.translatable("entity." + name.identifier().getNamespace() + ".villager." + name.identifier().getPath()),
                        poiType -> poiType.is(jobSite),
                        poiType -> poiType.is(jobSite),
                        ImmutableSet.of(),
                        ImmutableSet.of(),
                        sound,
                        trades
                )
        );
    }

    private static ResourceKey<VillagerProfession> createProfessionKey(final String name) {
        return ResourceKey.create(Registries.VILLAGER_PROFESSION, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, name));
    }

    private static ResourceKey<PoiType> createPoiKey(String name) {
        return ResourceKey.create(Registries.POINT_OF_INTEREST_TYPE, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, name));
    }

}
