package com.github.ringlocker.substancecraft.datagen.tag;

import com.github.ringlocker.substancecraft.block.SubstanceCraftBlockItemIds;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;

import java.util.concurrent.CompletableFuture;

public class BlockTagProvider extends FabricTagsProvider.BlockTagsProvider {

    public BlockTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        builder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(SubstanceCraftBlockItemIds.HASH_PRESS).add(SubstanceCraftBlockItemIds.REFINERY)
                .add(SubstanceCraftBlockItemIds.OXIDATION_MACHINE).add(SubstanceCraftBlockItemIds.ELECTROLYSIS_MACHINE)
                .add(SubstanceCraftBlockItemIds.MIXER).add(SubstanceCraftBlockItemIds.HEATED_MIXER)
                .add(SubstanceCraftBlockItemIds.FERMENTATION_TANK)
                .add(SubstanceCraftBlockItemIds.HALITE)
                .add(SubstanceCraftBlockItemIds.SYLVITE)
                .add(SubstanceCraftBlockItemIds.SULFUR_ORE)
                .add(SubstanceCraftBlockItemIds.DEEPSLATE_SULFUR_ORE)
                .add(SubstanceCraftBlockItemIds.TRONA)
                .add(SubstanceCraftBlockItemIds.PYROLUSITE_ORE)
                .add(SubstanceCraftBlockItemIds.DEEPSLATE_PYROLUSITE_ORE)
                .add(SubstanceCraftBlockItemIds.LIMESTONE)
                .add(SubstanceCraftBlockItemIds.PHOSPHORITE);

        builder(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(SubstanceCraftBlockItemIds.OIL_SHALE);
    }

}