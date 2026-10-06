package com.github.ringlocker.substancecraft.datagen;

import com.github.ringlocker.substancecraft.block.SubstanceCraftBlocks;
import com.github.ringlocker.substancecraft.item.SubstanceCraftItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.IntRange;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.LimitCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class LootTableGenerator extends FabricBlockLootSubProvider {

    public LootTableGenerator(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        HolderLookup.RegistryLookup<@NotNull Enchantment> registryLookup = registries.lookupOrThrow(Registries.ENCHANTMENT);

        add(SubstanceCraftBlocks.HASH_PRESS, createNameableBlockEntityTable(SubstanceCraftBlocks.HASH_PRESS));
        add(SubstanceCraftBlocks.REFINERY, createNameableBlockEntityTable(SubstanceCraftBlocks.REFINERY));
        add(SubstanceCraftBlocks.OXIDATION_MACHINE, createNameableBlockEntityTable(SubstanceCraftBlocks.OXIDATION_MACHINE));
        add(SubstanceCraftBlocks.ELECTROLYSIS_MACHINE, createNameableBlockEntityTable(SubstanceCraftBlocks.ELECTROLYSIS_MACHINE));
        add(SubstanceCraftBlocks.EXTRACTOR, createNameableBlockEntityTable(SubstanceCraftBlocks.EXTRACTOR));
        add(SubstanceCraftBlocks.FERMENTATION_TANK, createNameableBlockEntityTable(SubstanceCraftBlocks.FERMENTATION_TANK));
        add(SubstanceCraftBlocks.MIXER, createNameableBlockEntityTable(SubstanceCraftBlocks.MIXER));
        add(SubstanceCraftBlocks.HEATED_MIXER, createNameableBlockEntityTable(SubstanceCraftBlocks.HEATED_MIXER));

        add(SubstanceCraftBlocks.OIL_SHALE, block -> createSilkTouchDispatchTable(
                block,
                applyExplosionDecay(block, LootItem.lootTableItem(SubstanceCraftItems.OIL_SHALE)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)))
                        .apply(ApplyBonusCount.addUniformBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))
                        .apply(LimitCount.limitCount(IntRange.range(1, 4))))));

        add(SubstanceCraftBlocks.HALITE, block -> createSilkTouchDispatchTable(
                block,
                applyExplosionDecay(block, LootItem.lootTableItem(SubstanceCraftItems.HALITE)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)))
                        .apply(ApplyBonusCount.addUniformBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))
                        .apply(LimitCount.limitCount(IntRange.range(1, 4))))));

        add(SubstanceCraftBlocks.SYLVITE, block -> createSilkTouchDispatchTable(
                block,
                applyExplosionDecay(block, LootItem.lootTableItem(SubstanceCraftItems.SYLVITE)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)))
                        .apply(ApplyBonusCount.addUniformBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))
                        .apply(LimitCount.limitCount(IntRange.range(1, 4))))));


        add(SubstanceCraftBlocks.SULFUR_ORE, block -> createSilkTouchDispatchTable(
                block,
                applyExplosionDecay(block, LootItem.lootTableItem(SubstanceCraftItems.RAW_SULFUR)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)))
                        .apply(ApplyBonusCount.addUniformBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))
                        .apply(LimitCount.limitCount(IntRange.range(1, 4))))));

        add(SubstanceCraftBlocks.DEEPSLATE_SULFUR_ORE, block -> createSilkTouchDispatchTable(
                block,
                applyExplosionDecay(block, LootItem.lootTableItem(SubstanceCraftItems.RAW_SULFUR)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)))
                        .apply(ApplyBonusCount.addUniformBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))
                        .apply(LimitCount.limitCount(IntRange.range(1, 4))))));

        add(SubstanceCraftBlocks.TRONA_ORE, block -> createSilkTouchDispatchTable(
                block,
                applyExplosionDecay(block, LootItem.lootTableItem(SubstanceCraftItems.TRONA)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)))
                        .apply(ApplyBonusCount.addUniformBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))
                        .apply(LimitCount.limitCount(IntRange.range(1, 4))))));

        add(SubstanceCraftBlocks.DEEPSLATE_TRONA_ORE, block -> createSilkTouchDispatchTable(
                block,
                applyExplosionDecay(block, LootItem.lootTableItem(SubstanceCraftItems.TRONA)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)))
                        .apply(ApplyBonusCount.addUniformBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))
                        .apply(LimitCount.limitCount(IntRange.range(1, 4))))));

        add(SubstanceCraftBlocks.PYROLUSITE_ORE, block -> createSilkTouchDispatchTable(
                block,
                applyExplosionDecay(block, LootItem.lootTableItem(SubstanceCraftItems.PYROLUSITE)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)))
                        .apply(ApplyBonusCount.addUniformBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))
                        .apply(LimitCount.limitCount(IntRange.range(1, 4))))));

        add(SubstanceCraftBlocks.DEEPSLATE_PYROLUSITE_ORE, block -> createSilkTouchDispatchTable(
                block,
                applyExplosionDecay(block, LootItem.lootTableItem(SubstanceCraftItems.PYROLUSITE)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)))
                        .apply(ApplyBonusCount.addUniformBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))
                        .apply(LimitCount.limitCount(IntRange.range(1, 4))))));

        add(SubstanceCraftBlocks.LIMESTONE, block -> createSilkTouchDispatchTable(
                block,
                applyExplosionDecay(block, LootItem.lootTableItem(SubstanceCraftItems.LIMESTONE)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)))
                        .apply(ApplyBonusCount.addUniformBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))
                        .apply(LimitCount.limitCount(IntRange.range(1, 4))))));

        add(SubstanceCraftBlocks.PHOSPHORITE, block -> createSilkTouchDispatchTable(
                block,
                applyExplosionDecay(block, LootItem.lootTableItem(SubstanceCraftItems.PHOSPHORITE)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)))
                        .apply(ApplyBonusCount.addUniformBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))
                        .apply(LimitCount.limitCount(IntRange.range(1, 4))))));

        dropSelf(SubstanceCraftBlocks.MIMOSA_HOSTILIS_LOG);
        dropSelf(SubstanceCraftBlocks.MIMOSA_HOSTILIS_WOOD);
        dropSelf(SubstanceCraftBlocks.MIMOSA_HOSTILIS_PLANKS);
        dropSelf(SubstanceCraftBlocks.MIMOSA_HOSTILIS_STAIRS);
        dropSelf(SubstanceCraftBlocks.MIMOSA_HOSTILIS_SLAB);
        dropSelf(SubstanceCraftBlocks.MIMOSA_HOSTILIS_BUTTON);
        dropSelf(SubstanceCraftBlocks.MIMOSA_HOSTILIS_FENCE);
        dropSelf(SubstanceCraftBlocks.MIMOSA_HOSTILIS_FENCE_GATE);
        dropSelf(SubstanceCraftBlocks.MIMOSA_HOSTILIS_WALL);
        dropSelf(SubstanceCraftBlocks.MIMOSA_HOSTILIS_PRESSURE_PLATE);
        dropSelf(SubstanceCraftBlocks.STRIPPED_MIMOSA_HOSTILIS_WOOD);
        dropSelf(SubstanceCraftBlocks.STRIPPED_MIMOSA_HOSTILIS_LOG);
        dropSelf(SubstanceCraftBlocks.MIMOSA_HOSTILIS_ROOT);
        dropSelf(SubstanceCraftBlocks.STRIPPED_MIMOSA_HOSTILIS_ROOT);
        dropSelf(SubstanceCraftBlocks.MIMOSA_HOSTILIS_SAPLING);
        
        dropSelf(SubstanceCraftBlocks.CHEMIST_WORKSTATION);
        dropSelf(SubstanceCraftBlocks.PLANT_RESEARCH_STATION);

        add(SubstanceCraftBlocks.MIMOSA_HOSTILIS_LEAVES, createLeavesDrops(SubstanceCraftBlocks.MIMOSA_HOSTILIS_LEAVES, SubstanceCraftBlocks.MIMOSA_HOSTILIS_SAPLING, 0.075F, 0.1F, 0.125F, 0.15F));
    }

}
