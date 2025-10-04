package com.github.ringlocker.substancecraft.datagen;

import com.github.ringlocker.substancecraft.block.SubstanceCraftBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;

import java.util.concurrent.CompletableFuture;

public class BlockTagGenerator extends FabricTagProvider.BlockTagProvider {

    public BlockTagGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(SubstanceCraftBlocks.HASH_PRESS).add(SubstanceCraftBlocks.REFINERY)
                .add(SubstanceCraftBlocks.EXTRACTOR).add(SubstanceCraftBlocks.CATALYTIC_REFORMER)
                .add(SubstanceCraftBlocks.OXIDATION_MACHINE).add(SubstanceCraftBlocks.ELECTROLYSIS_MACHINE)
                .add(SubstanceCraftBlocks.MIXER).add(SubstanceCraftBlocks.HEATED_MIXER)
                .add(SubstanceCraftBlocks.FERMENTATION_TANK)
                .add(SubstanceCraftBlocks.HALITE)
                .add(SubstanceCraftBlocks.SYLVITE)
                .add(SubstanceCraftBlocks.SULFUR_ORE)
                .add(SubstanceCraftBlocks.DEEPSLATE_SULFUR_ORE)
                .add(SubstanceCraftBlocks.TRONA)
                .add(SubstanceCraftBlocks.PYROLUSITE_ORE)
                .add(SubstanceCraftBlocks.DEEPSLATE_PYROLUSITE_ORE);

        valueLookupBuilder(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(SubstanceCraftBlocks.OIL_SHALE);
    }

}