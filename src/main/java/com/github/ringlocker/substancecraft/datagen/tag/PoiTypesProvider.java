package com.github.ringlocker.substancecraft.datagen.tag;

import com.github.ringlocker.substancecraft.entity.trading.SubstanceCraftProfessions;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.PoiTypeTags;
import net.minecraft.tags.TagEntry;
import net.minecraft.world.entity.ai.village.poi.PoiType;

import java.util.concurrent.CompletableFuture;

public class PoiTypesProvider extends FabricTagsProvider<PoiType> {

    public PoiTypesProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, Registries.POINT_OF_INTEREST_TYPE, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        getOrCreateRawBuilder(PoiTypeTags.ACQUIRABLE_JOB_SITE)
                .add(TagEntry.element(SubstanceCraftProfessions.DEALER_POI.identifier()))
                .add(TagEntry.element(SubstanceCraftProfessions.EXOTIC_FARMER_POI.identifier()));
    }

}
