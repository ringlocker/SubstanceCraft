package com.github.ringlocker.substancecraft.datagen.tag;

import com.github.ringlocker.substancecraft.block.SubstanceCraftBlockItemIds;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class ItemTagProvider extends FabricTagsProvider.ItemTagsProvider {

    public ItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        builder(SubstanceCraftBlockItemIds.MIMOSA_HOSTILIS_LOGS.item())
                .add(
                        SubstanceCraftBlockItemIds.MIMOSA_HOSTILIS_LOG,
                        SubstanceCraftBlockItemIds.MIMOSA_HOSTILIS_WOOD,
                        SubstanceCraftBlockItemIds.STRIPPED_MIMOSA_HOSTILIS_LOG,
                        SubstanceCraftBlockItemIds.STRIPPED_MIMOSA_HOSTILIS_WOOD
                );
    }
}
