package com.github.ringlocker.substancecraft.world;

import com.github.ringlocker.substancecraft.block.SubstanceCraftBlocks;
import com.github.ringlocker.substancecraft.item.SubstanceCraftItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.advancements.predicates.StatePropertiesPredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.BeetrootBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchBlock;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public class SubstanceCraftLootTables {

    public static void registerLootTables() {
        addMarijuanaPlantSeedsToJungleTempleChestLoot();
        addFungiToCrops();
        addItemToVillagerHouseChestLoot(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.CORN_CROP));
        addItemToVillagerHouseChestLoot(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.GRAPEVINE));
    }

    private static void addItemToVillagerHouseChestLoot(Item item) {
        LootTableEvents.MODIFY.register((lootTable, tableBuilder, lootTableSource, provider) -> {
            List<ResourceKey<@NotNull LootTable>> chests = List.of(BuiltInLootTables.VILLAGE_PLAINS_HOUSE, BuiltInLootTables.VILLAGE_SAVANNA_HOUSE, BuiltInLootTables.VILLAGE_DESERT_HOUSE, BuiltInLootTables.VILLAGE_SNOWY_HOUSE);
            if (lootTableSource.isBuiltin() && chests.contains(lootTable)) {
                LootPool.Builder lootPool = new LootPool.Builder();
                lootPool.setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(item)
                                .setWeight(7))
                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3)));
                tableBuilder.withPool(lootPool);
            }
        });

    }


    private static void addFungiToCrops() {
        Optional<ResourceKey<LootTable>> POTATO_LOOT_TABLE = Blocks.POTATOES.getLootTable();
        Optional<ResourceKey<LootTable>> CARROT_LOOT_TABLE = Blocks.CARROTS.getLootTable();
        Optional<ResourceKey<LootTable>> WHEAT_LOOT_TABLE = Blocks.WHEAT.getLootTable();
        Optional<ResourceKey<LootTable>> BEETROOT_LOOT_TABLE = Blocks.BEETROOTS.getLootTable();

        LootTableEvents.MODIFY.register((lootTable, tableBuilder, lootTableSource, provider) -> {
            HolderGetter<Block> blocks = provider.lookupOrThrow(Registries.BLOCK);
            if (POTATO_LOOT_TABLE.isPresent() && lootTableSource.isBuiltin() && POTATO_LOOT_TABLE.get().equals(lootTable)) {
                LootItemCondition.Builder fullyGrownCondition = MatchBlock.blockMatches(blocks, Blocks.POTATOES, StatePropertiesPredicate.Builder.properties().hasProperty(CropBlock.AGE, 7));
                LootPool.Builder lootPool = new LootPool.Builder();
                lootPool.when(fullyGrownCondition)
                        .add(LootItem.lootTableItem(SubstanceCraftItems.ERGOT).when(LootItemRandomChanceCondition.randomChance(0.0015F)))
                        .add(LootItem.lootTableItem(SubstanceCraftItems.YEAST).when(LootItemRandomChanceCondition.randomChance(0.0015F)));
                tableBuilder.withPool(lootPool);
            } else if (CARROT_LOOT_TABLE.isPresent() && lootTableSource.isBuiltin() && CARROT_LOOT_TABLE.get().equals(lootTable)) {
                LootItemCondition.Builder fullyGrownCondition = MatchBlock.blockMatches(blocks, Blocks.CARROTS, StatePropertiesPredicate.Builder.properties().hasProperty(CropBlock.AGE, 7));
                LootPool.Builder lootPool = new LootPool.Builder();
                lootPool.when(fullyGrownCondition)
                        .add(LootItem.lootTableItem(SubstanceCraftItems.ERGOT).when(LootItemRandomChanceCondition.randomChance(0.0015F)))
                        .add(LootItem.lootTableItem(SubstanceCraftItems.YEAST).when(LootItemRandomChanceCondition.randomChance(0.0015F)));
                tableBuilder.withPool(lootPool);
            } else if (WHEAT_LOOT_TABLE.isPresent() && lootTableSource.isBuiltin() && WHEAT_LOOT_TABLE.get().equals(lootTable)) {
                LootItemCondition.Builder fullyGrownCondition = MatchBlock.blockMatches(blocks, Blocks.WHEAT, StatePropertiesPredicate.Builder.properties().hasProperty(CropBlock.AGE, 7));
                LootPool.Builder lootPool = new LootPool.Builder();
                lootPool.when(fullyGrownCondition)
                        .add(LootItem.lootTableItem(SubstanceCraftItems.ERGOT).when(LootItemRandomChanceCondition.randomChance(0.0015F)))
                        .add(LootItem.lootTableItem(SubstanceCraftItems.YEAST).when(LootItemRandomChanceCondition.randomChance(0.0015F)));
                tableBuilder.withPool(lootPool);
            }  else if (BEETROOT_LOOT_TABLE.isPresent() && lootTableSource.isBuiltin() && BEETROOT_LOOT_TABLE.get().equals(lootTable)) {
                LootItemCondition.Builder fullyGrownCondition = MatchBlock.blockMatches(blocks, Blocks.BEETROOTS, StatePropertiesPredicate.Builder.properties().hasProperty(BeetrootBlock.AGE, 3));
                LootPool.Builder lootPool = new LootPool.Builder();
                lootPool.when(fullyGrownCondition)
                        .add(LootItem.lootTableItem(SubstanceCraftItems.ERGOT).when(LootItemRandomChanceCondition.randomChance(0.0015F)))
                        .add(LootItem.lootTableItem(SubstanceCraftItems.YEAST).when(LootItemRandomChanceCondition.randomChance(0.0015F)));
                tableBuilder.withPool(lootPool);
            }
        });
    }

    private static void addMarijuanaPlantSeedsToJungleTempleChestLoot() {
        LootTableEvents.MODIFY.register((lootTable, tableBuilder, lootTableSource, provider) -> {
            if (lootTableSource.isBuiltin() && BuiltInLootTables.JUNGLE_TEMPLE.equals(lootTable)) {
                LootPool.Builder lootPool = new LootPool.Builder();
                lootPool.setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.MARIJUANA_PLANT))
                        .setWeight(15))
                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3)));
                tableBuilder.withPool(lootPool);
            }
        });
    }

}
