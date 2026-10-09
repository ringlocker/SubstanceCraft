package com.github.ringlocker.substancecraft.datagen;

import com.github.ringlocker.substancecraft.block.SubstanceCraftBlocks;
import com.github.ringlocker.substancecraft.block.blocks.*;
import com.github.ringlocker.substancecraft.item.SubstanceCraftItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.advancements.predicates.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.IntRange;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.LimitCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class LootTableGenerator extends FabricBlockLootSubProvider {

    public LootTableGenerator(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        HolderLookup.RegistryLookup<Enchantment> registryLookup = registries.lookupOrThrow(Registries.ENCHANTMENT);

        add(SubstanceCraftBlocks.HASH_PRESS, createNameableBlockEntityTable(SubstanceCraftBlocks.HASH_PRESS));
        add(SubstanceCraftBlocks.REFINERY, createNameableBlockEntityTable(SubstanceCraftBlocks.REFINERY));
        add(SubstanceCraftBlocks.OXIDATION_MACHINE, createNameableBlockEntityTable(SubstanceCraftBlocks.OXIDATION_MACHINE));
        add(SubstanceCraftBlocks.ELECTROLYSIS_MACHINE, createNameableBlockEntityTable(SubstanceCraftBlocks.ELECTROLYSIS_MACHINE));
        add(SubstanceCraftBlocks.EXTRACTOR, createNameableBlockEntityTable(SubstanceCraftBlocks.EXTRACTOR));
        add(SubstanceCraftBlocks.FERMENTATION_TANK, createNameableBlockEntityTable(SubstanceCraftBlocks.FERMENTATION_TANK));
        add(SubstanceCraftBlocks.MIXER, createNameableBlockEntityTable(SubstanceCraftBlocks.MIXER));
        add(SubstanceCraftBlocks.HEATED_MIXER, createNameableBlockEntityTable(SubstanceCraftBlocks.HEATED_MIXER));

        cropDrops(registryLookup,
                SubstanceCraftBlocks.MARIJUANA_PLANT,
                List.of(new CropDrop(SubstanceCraftItems.MARIJUANA, 1, 2), new CropDrop(SubstanceCraftItems.MARIJUANA_TRIM, 1, 3)),
                SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.MARIJUANA_PLANT),
                createAgeRequirement(SubstanceCraftBlocks.MARIJUANA_PLANT, MarijuanaPlant.AGE_PROPERTY, 7));

        cropDrops(registryLookup,
                SubstanceCraftBlocks.COCA_CROP,
                List.of(new CropDrop(SubstanceCraftItems.COCA_LEAVES, 2, 4)),
                SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.COCA_CROP),
                createAgeRequirement(SubstanceCraftBlocks.COCA_CROP, CocaCrop.AGE_PROPERTY, 5));

        cropDrops(registryLookup,
                SubstanceCraftBlocks.CORN_CROP,
                List.of(new CropDrop(SubstanceCraftItems.CORN, 1, 3)),
                SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.CORN_CROP),
                createAgeRequirement(SubstanceCraftBlocks.CORN_CROP, CornCrop.AGE_PROPERTY, 7));


        cropDrops(registryLookup,
                SubstanceCraftBlocks.GRAPEVINE,
                List.of(new CropDrop(SubstanceCraftItems.GRAPES, 1, 2)),
                SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.GRAPEVINE),
                createAgeRequirement(SubstanceCraftBlocks.GRAPEVINE, Grapevine.AGE_PROPERTY, 7));

        ageBasedSelfDrop(SubstanceCraftBlocks.PSILOCYBIN, SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.PSILOCYBIN), PsilocybinMushroom.AGE, 2);
        ageBasedSelfDrop(SubstanceCraftBlocks.PALE_PSILOCYBIN, SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.PALE_PSILOCYBIN), PotentPsilocybinMushroom.AGE, 2);
        ageBasedSelfDrop(SubstanceCraftBlocks.PEYOTE_CACTUS, SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.PEYOTE_CACTUS), PeyoteCactus.AGE, 2);
        ageBasedSelfDrop(SubstanceCraftBlocks.ANISE_PLANT, SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.ANISE_PLANT), AnisePlant.AGE, 2);

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

        add(SubstanceCraftBlocks.PHOSPHORITE, block -> createSilkTouchDispatchTable(
                block,
                applyExplosionDecay(block, LootItem.lootTableItem(SubstanceCraftItems.PHOSPHORITE)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)))
                        .apply(ApplyBonusCount.addUniformBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))
                        .apply(LimitCount.limitCount(IntRange.range(1, 4))))));

        add(SubstanceCraftBlocks.CRYOLITE_ORE, block -> createSilkTouchDispatchTable(
                block,
                applyExplosionDecay(block, LootItem.lootTableItem(SubstanceCraftItems.CRYOLITE)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)))
                        .apply(ApplyBonusCount.addUniformBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))
                        .apply(LimitCount.limitCount(IntRange.range(1, 4))))));

        add(SubstanceCraftBlocks.DEEPSLATE_CRYOLITE_ORE, block -> createSilkTouchDispatchTable(
                block,
                applyExplosionDecay(block, LootItem.lootTableItem(SubstanceCraftItems.CRYOLITE)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)))
                        .apply(ApplyBonusCount.addUniformBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))
                        .apply(LimitCount.limitCount(IntRange.range(1, 4))))));

        add(SubstanceCraftBlocks.VANADINITE_ORE, block -> createSilkTouchDispatchTable(
                block,
                applyExplosionDecay(block, LootItem.lootTableItem(SubstanceCraftItems.VANADINITE)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)))
                        .apply(ApplyBonusCount.addUniformBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))
                        .apply(LimitCount.limitCount(IntRange.range(1, 4))))));

        add(SubstanceCraftBlocks.DEEPSLATE_VANADINITE_ORE, block -> createSilkTouchDispatchTable(
                block,
                applyExplosionDecay(block, LootItem.lootTableItem(SubstanceCraftItems.VANADINITE)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)))
                        .apply(ApplyBonusCount.addUniformBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))
                        .apply(LimitCount.limitCount(IntRange.range(1, 4))))));

        add(SubstanceCraftBlocks.BAUXITE_ORE, block -> createSilkTouchDispatchTable(
                block,
                applyExplosionDecay(block, LootItem.lootTableItem(SubstanceCraftItems.BAUXITE)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)))
                        .apply(ApplyBonusCount.addUniformBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))
                        .apply(LimitCount.limitCount(IntRange.range(1, 4))))));

        add(SubstanceCraftBlocks.DEEPSLATE_BAUXITE_ORE, block -> createSilkTouchDispatchTable(
                block,
                applyExplosionDecay(block, LootItem.lootTableItem(SubstanceCraftItems.BAUXITE)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)))
                        .apply(ApplyBonusCount.addUniformBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))
                        .apply(LimitCount.limitCount(IntRange.range(1, 4))))));

        add(SubstanceCraftBlocks.PALLADIUM_ORE, block -> createSilkTouchDispatchTable(
                block,
                applyExplosionDecay(block, LootItem.lootTableItem(SubstanceCraftItems.RAW_PALLADIUM)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)))
                        .apply(ApplyBonusCount.addUniformBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))
                        .apply(LimitCount.limitCount(IntRange.range(1, 4))))));

        add(SubstanceCraftBlocks.DEEPSLATE_PALLADIUM_ORE, block -> createSilkTouchDispatchTable(
                block,
                applyExplosionDecay(block, LootItem.lootTableItem(SubstanceCraftItems.RAW_PALLADIUM)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)))
                        .apply(ApplyBonusCount.addUniformBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))
                        .apply(LimitCount.limitCount(IntRange.range(1, 4))))));

        add(SubstanceCraftBlocks.CHROMITITE, block -> createSilkTouchDispatchTable(
                block,
                applyExplosionDecay(block, LootItem.lootTableItem(SubstanceCraftItems.CHROMITE)
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

    private void cropDrops(HolderLookup.RegistryLookup<Enchantment> enchantments, Block crop, List<CropDrop> drops, Item seed, LootItemCondition.Builder isMaxAge)
    {
        LootTable.Builder lootTable = LootTable.lootTable();

        lootTable.withPool(LootPool.lootPool().add(LootItem.lootTableItem(seed)));
        lootTable.withPool(LootPool.lootPool().when(isMaxAge).add(
                LootItem.lootTableItem(seed)).apply(ApplyBonusCount.addBonusBinomialDistributionCount(enchantments.getOrThrow(Enchantments.FORTUNE), 0.5714286F, 2))
        );

        for (CropDrop drop : drops) {
            LootPool.Builder dropPool = LootPool.lootPool();
            if (drop.requiresMaxAge) dropPool.when(isMaxAge);
            dropPool.add(LootItem.lootTableItem(drop.item))
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(drop.min, drop.max)))
                    .apply(ApplyBonusCount.addUniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)));

            lootTable.withPool(dropPool);
        }

        add(crop, applyExplosionDecay(crop, lootTable));
    }

    private void ageBasedSelfDrop(Block crop, Item seed, IntegerProperty age, int maxAge) {
        LootTable.Builder lootTable = LootTable.lootTable();
        for (int i = 0; i <= maxAge; i++) {
            lootTable.withPool(LootPool.lootPool().when(createAgeRequirement(crop, age, i)).add(LootItem.lootTableItem(seed)).setRolls(ConstantValue.exactly(i + 1)));
        }
        add(crop, applyExplosionDecay(crop, lootTable));
    }

    private LootItemCondition.Builder createAgeRequirement(Block block, IntegerProperty property, int age) {
        return LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(property, age));
    }

    private record CropDrop(Item item, int min, int max, boolean requiresMaxAge) {

        public CropDrop(Item item, int min, int max) {
            this(item, min, max, true);
        }

    }

}
