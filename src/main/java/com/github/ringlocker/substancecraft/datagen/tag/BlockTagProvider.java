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

        builder(BlockTags.MINEABLE_WITH_AXE)
                .add(SubstanceCraftBlockItemIds.CHEMIST_WORKSTATION)
                .add(SubstanceCraftBlockItemIds.PLANT_RESEARCH_STATION)
                .add(SubstanceCraftBlockItemIds.MIMOSA_HOSTILIS_LOG)
                .add(SubstanceCraftBlockItemIds.MIMOSA_HOSTILIS_WOOD)
                .add(SubstanceCraftBlockItemIds.MIMOSA_HOSTILIS_PLANKS)
                .add(SubstanceCraftBlockItemIds.MIMOSA_HOSTILIS_STAIRS)
                .add(SubstanceCraftBlockItemIds.MIMOSA_HOSTILIS_SLAB)
                .add(SubstanceCraftBlockItemIds.MIMOSA_HOSTILIS_BUTTON)
                .add(SubstanceCraftBlockItemIds.MIMOSA_HOSTILIS_FENCE)
                .add(SubstanceCraftBlockItemIds.MIMOSA_HOSTILIS_FENCE_GATE)
                .add(SubstanceCraftBlockItemIds.MIMOSA_HOSTILIS_WALL)
                .add(SubstanceCraftBlockItemIds.MIMOSA_HOSTILIS_PRESSURE_PLATE)
                .add(SubstanceCraftBlockItemIds.STRIPPED_MIMOSA_HOSTILIS_WOOD)
                .add(SubstanceCraftBlockItemIds.STRIPPED_MIMOSA_HOSTILIS_LOG)
                .add(SubstanceCraftBlockItemIds.MIMOSA_HOSTILIS_ROOT)
                .add(SubstanceCraftBlockItemIds.STRIPPED_MIMOSA_HOSTILIS_ROOT);

        builder(BlockTags.STAIRS)
                .add(SubstanceCraftBlockItemIds.MIMOSA_HOSTILIS_STAIRS);

        builder(BlockTags.SLABS)
                .add(SubstanceCraftBlockItemIds.MIMOSA_HOSTILIS_SLAB);

        builder(BlockTags.WALLS)
                .add(SubstanceCraftBlockItemIds.MIMOSA_HOSTILIS_WALL);

        builder(BlockTags.WOODEN_BUTTONS)
                .add(SubstanceCraftBlockItemIds.MIMOSA_HOSTILIS_BUTTON);

        builder(BlockTags.WOODEN_PRESSURE_PLATES)
                .add(SubstanceCraftBlockItemIds.MIMOSA_HOSTILIS_BUTTON);

        builder(BlockTags.WOODEN_FENCES)
                .add(SubstanceCraftBlockItemIds.MIMOSA_HOSTILIS_FENCE);

        builder(BlockTags.FENCE_GATES)
                .add(SubstanceCraftBlockItemIds.MIMOSA_HOSTILIS_FENCE_GATE);

        builder(SubstanceCraftBlockItemIds.MIMOSA_HOSTILIS_LOGS.block())
                .add(
                        SubstanceCraftBlockItemIds.MIMOSA_HOSTILIS_LOG,
                        SubstanceCraftBlockItemIds.MIMOSA_HOSTILIS_WOOD,
                        SubstanceCraftBlockItemIds.STRIPPED_MIMOSA_HOSTILIS_LOG,
                        SubstanceCraftBlockItemIds.STRIPPED_MIMOSA_HOSTILIS_WOOD
                );

    }

}