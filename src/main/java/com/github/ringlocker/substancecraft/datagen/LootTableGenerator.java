package com.github.ringlocker.substancecraft.datagen;

import com.github.ringlocker.substancecraft.block.SubstanceCraftBlocks;
import com.github.ringlocker.substancecraft.block.blocks.CocaCrop;
import com.github.ringlocker.substancecraft.item.SubstanceCraftItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.storage.loot.IntRange;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.LimitCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class LootTableGenerator extends FabricBlockLootTableProvider {

    public LootTableGenerator(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
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

        add(SubstanceCraftBlocks.CORN_CROP, createCropDrops(
                SubstanceCraftBlocks.CORN_CROP, SubstanceCraftItems.CORN, SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.CORN_CROP),
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(SubstanceCraftBlocks.CORN_CROP).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropBlock.AGE, 7))));

        add(SubstanceCraftBlocks.COCA_CROP, createCropDrops(
                SubstanceCraftBlocks.COCA_CROP, SubstanceCraftItems.COCA_LEAVES, SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.COCA_CROP),
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(SubstanceCraftBlocks.COCA_CROP).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CocaCrop.AGE, 5))));

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

        add(SubstanceCraftBlocks.TRONA, block -> createSilkTouchDispatchTable(
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
    }

}
