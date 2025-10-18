package com.github.ringlocker.substancecraft.datagen;

import com.github.ringlocker.substancecraft.SubstanceCraft;
import com.github.ringlocker.substancecraft.block.SubstanceCraftBlocks;
import com.github.ringlocker.substancecraft.item.SubstanceCraftItems;
import com.github.ringlocker.substancecraft.recipe.generic.ByproductRecipe;
import com.github.ringlocker.substancecraft.recipe.generic.MultipleInputRecipe;
import com.github.ringlocker.substancecraft.recipe.generic.OneInputRecipe;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class AdvancementGenerator extends FabricAdvancementProvider {

    protected AdvancementGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(HolderLookup.Provider provider, Consumer<AdvancementHolder> writer) {
        AdvancementHolder welcome = Advancement.Builder.advancement()
                .display(
                        SubstanceCraftItems.MARIJUANA_TRIM,
                        Component.literal("SubstanceCraft"),
                        Component.literal("You may find useful information in advancement descriptions"),
                        ResourceLocation.withDefaultNamespace("gui/advancements/backgrounds/stone"),
                        AdvancementType.TASK,
                        false,
                        false,
                        false
                )
                .addCriterion("free", PlayerTrigger.TriggerInstance.tick())
                .save(writer, SubstanceCraft.MOD_ID + ":welcome");

        addNaturalResources(writer, welcome);
        addAgriculture(writer, welcome);
        addWorkstations(writer, welcome);
        addSyntheses(writer);
    }

    private void addNaturalResources(Consumer<AdvancementHolder> writer, AdvancementHolder parent) {

        AdvancementHolder naturalResources = Advancement.Builder.advancement()
                .parent(parent)
                .display(
                        Blocks.IRON_ORE.asItem(),
                        Component.literal("Natural Resources"),
                        Component.literal("Look at advancement descriptions for where to find each resource"),
                        null,
                        AdvancementType.TASK,
                        false,
                        false,
                        false
                )
                .addCriterion("free", PlayerTrigger.TriggerInstance.tick())
                .save(writer, SubstanceCraft.MOD_ID + ":natural_resources");


        AdvancementHolder freedom = Advancement.Builder.advancement()
                .parent(naturalResources)
                .display(
                        SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.OIL_SHALE),
                        Component.literal("Freedom"),
                        Component.literal("Oil Shale is found under deep oceans between y=-48 and y=16"),
                        null,
                        AdvancementType.TASK,
                        false,
                        false,
                        false
                )
                .addCriterion("got_oil", InventoryChangeTrigger.TriggerInstance.hasItems(SubstanceCraftItems.OIL_SHALE))
                .save(writer, SubstanceCraft.MOD_ID + ":oil");

        AdvancementHolder halite = Advancement.Builder.advancement()
                .parent(naturalResources)
                .display(
                        SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.HALITE),
                        Component.literal("Halite"),
                        Component.literal("Halite is found under oceans between y=0 and y=48 and any anywhere in dripstone caves"),
                        null,
                        AdvancementType.TASK,
                        false,
                        false,
                        false
                )
                .addCriterion("got_halite", InventoryChangeTrigger.TriggerInstance.hasItems(SubstanceCraftItems.HALITE))
                .save(writer, SubstanceCraft.MOD_ID + ":halite");

        AdvancementHolder sylvite = Advancement.Builder.advancement()
                .parent(naturalResources)
                .display(
                        SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.SYLVITE),
                        Component.literal("Sylvite"),
                        Component.literal("Sylvite is found in deserts and badlands between y=8 and y=96"),
                        null,
                        AdvancementType.TASK,
                        false,
                        false,
                        false
                )
                .addCriterion("got_sylvite", InventoryChangeTrigger.TriggerInstance.hasItems(SubstanceCraftItems.SYLVITE))
                .save(writer, SubstanceCraft.MOD_ID + ":sylvite");

        AdvancementHolder sulfur = Advancement.Builder.advancement()
                .parent(naturalResources)
                .display(
                        SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.SULFUR_ORE),
                        Component.literal("Sulfur"),
                        Component.literal("Sulfur is found underground between y=-64 and y=16"),
                        null,
                        AdvancementType.TASK,
                        false,
                        false,
                        false
                )
                .addCriterion("got_sulfur", InventoryChangeTrigger.TriggerInstance.hasItems(SubstanceCraftItems.SULFUR))
                .save(writer, SubstanceCraft.MOD_ID + ":sulfur");

        AdvancementHolder trona = Advancement.Builder.advancement()
                .parent(naturalResources)
                .display(
                        SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.TRONA),
                        Component.literal("Trona"),
                        Component.literal("Trona is found underground between y=0 and y=72"),
                        null,
                        AdvancementType.TASK,
                        false,
                        false,
                        false
                )
                .addCriterion("got_trona", InventoryChangeTrigger.TriggerInstance.hasItems(SubstanceCraftItems.TRONA))
                .save(writer, SubstanceCraft.MOD_ID + ":trona");

        AdvancementHolder pyrolusite = Advancement.Builder.advancement()
                .parent(naturalResources)
                .display(
                        SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.PYROLUSITE_ORE),
                        Component.literal("Pyrolusite"),
                        Component.literal("Pyrolusite is found underground between y=0 and y=72"),
                        null,
                        AdvancementType.TASK,
                        false,
                        false,
                        false
                )
                .addCriterion("got_pyrolusite", InventoryChangeTrigger.TriggerInstance.hasItems(SubstanceCraftItems.PYROLUSITE))
                .save(writer, SubstanceCraft.MOD_ID + ":pyrolusite");

    }

    private void addAgriculture(Consumer<AdvancementHolder> writer, AdvancementHolder parent) {
        AdvancementHolder agriculture = Advancement.Builder.advancement()
                .parent(parent)
                .display(
                        Items.WHEAT_SEEDS,
                        Component.literal("Agriculture"),
                        Component.literal("Look at advancement descriptions for useful information"),
                        null,
                        AdvancementType.TASK,
                        false,
                        false,
                        false
                )
                .addCriterion("free", PlayerTrigger.TriggerInstance.tick())
                .save(writer, SubstanceCraft.MOD_ID + ":agriculture");

        AdvancementHolder weed = Advancement.Builder.advancement()
                .parent(agriculture)
                .display(
                        SubstanceCraftItems.MARIJUANA_TRIM,
                        Component.literal("Marijuana"),
                        Component.literal("Marijuana can naturally occur in jungle biomes and marijuana seeds can be found in jungle pyramid chests. Harvested by right clicking, grows twice as fast on farmland"),
                        null,
                        AdvancementType.TASK,
                        false,
                        false,
                        false
                )
                .addCriterion("got_weed", InventoryChangeTrigger.TriggerInstance.hasItems(SubstanceCraftItems.MARIJUANA))
                .save(writer, SubstanceCraft.MOD_ID + ":weed");

        AdvancementHolder coca = Advancement.Builder.advancement()
                .parent(agriculture)
                .display(
                        SubstanceCraftItems.COCA_LEAVES,
                        Component.literal("Coca Plant"),
                        Component.literal("Coca Plants can naturally occur in jungle biomes. Harvested by right clicking, grows twice as fast on farmland"),
                        null,
                        AdvancementType.TASK,
                        false,
                        false,
                        false
                )
                .addCriterion("got_coca", InventoryChangeTrigger.TriggerInstance.hasItems(SubstanceCraftItems.COCA_LEAVES))
                .save(writer, SubstanceCraft.MOD_ID + ":coca");

        AdvancementHolder corn = Advancement.Builder.advancement()
                .parent(agriculture)
                .display(
                        SubstanceCraftItems.CORN,
                        Component.literal("Corn"),
                        Component.literal("Corn seeds can be found in some village chests"),
                        null,
                        AdvancementType.TASK,
                        false,
                        false,
                        false
                )
                .addCriterion("got_corn", InventoryChangeTrigger.TriggerInstance.hasItems(SubstanceCraftItems.CORN))
                .save(writer, SubstanceCraft.MOD_ID + ":corn");
    }

    private void addWorkstations(Consumer<AdvancementHolder> writer, AdvancementHolder parent) {
        AdvancementHolder workstations = Advancement.Builder.advancement()
                .parent(parent)
                .display(
                        Blocks.CRAFTING_TABLE.asItem(),
                        Component.literal("Workstations"),
                        Component.literal("Look at advancement descriptions for useful information"),
                        null,
                        AdvancementType.TASK,
                        false,
                        false,
                        false
                )
                .addCriterion("free", PlayerTrigger.TriggerInstance.tick())
                .save(writer, SubstanceCraft.MOD_ID + ":workstations");

        AdvancementHolder hashPress = Advancement.Builder.advancement()
                .parent(workstations)
                .display(
                        SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.HASH_PRESS),
                        Component.literal("Hash Press"),
                        Component.literal("Used for pressing hash. Crafted with 1 redstone block, 1 piston, and 5 smooth stone slabs"),
                        null,
                        AdvancementType.TASK,
                        false,
                        false,
                        false
                )
                .addCriterion("got_hash_press", InventoryChangeTrigger.TriggerInstance.hasItems(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.HASH_PRESS)))
                .save(writer, SubstanceCraft.MOD_ID + ":hash_press");

        AdvancementHolder refinery = Advancement.Builder.advancement()
                .parent(workstations)
                .display(
                        SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.REFINERY),
                        Component.literal("Refinery"),
                        Component.literal("Used for refining substances. Crafted with 5 copper blocks, 1 cauldron, and 3 iron ingots"),
                        null,
                        AdvancementType.TASK,
                        false,
                        false,
                        false
                )
                .addCriterion("got_refinery", InventoryChangeTrigger.TriggerInstance.hasItems(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.REFINERY)))
                .save(writer, SubstanceCraft.MOD_ID + ":refinery");

        AdvancementHolder mixer = Advancement.Builder.advancement()
                .parent(workstations)
                .display(
                        SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.MIXER),
                        Component.literal("Mixer"),
                        Component.literal("Used for mixing substances. Crafted with 4 copper blocks, 1 cauldron, 3 iron ingots, and 1 iron shovel"),
                        null,
                        AdvancementType.TASK,
                        false,
                        false,
                        false
                )
                .addCriterion("got_mixer", InventoryChangeTrigger.TriggerInstance.hasItems(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.MIXER)))
                .save(writer, SubstanceCraft.MOD_ID + ":mixer");

        AdvancementHolder heatedMixer = Advancement.Builder.advancement()
                .parent(workstations)
                .display(
                        SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.HEATED_MIXER),
                        Component.literal("Heated Mixer"),
                        Component.literal("Used for mixing substances with heat. Crafted with 4 copper blocks, 1 cauldron, 2 iron ingots, 1 iron shovel, and 1 lava bucket"),
                        null,
                        AdvancementType.TASK,
                        false,
                        false,
                        false
                )
                .addCriterion("got_heated_mixer", InventoryChangeTrigger.TriggerInstance.hasItems(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.HEATED_MIXER)))
                .save(writer, SubstanceCraft.MOD_ID + ":heated_mixer");


        AdvancementHolder oxidizer = Advancement.Builder.advancement()
                .parent(workstations)
                .display(
                        SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.OXIDATION_MACHINE),
                        Component.literal("Oxidizer"),
                        Component.literal("Used for oxidizing substances. Crafted with 4 copper blocks, 1 cauldron, and 4 iron ingots"),
                        null,
                        AdvancementType.TASK,
                        false,
                        false,
                        false
                )
                .addCriterion("got_oxidizer", InventoryChangeTrigger.TriggerInstance.hasItems(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.OXIDATION_MACHINE)))
                .save(writer, SubstanceCraft.MOD_ID + ":oxidizer");

        AdvancementHolder extractor = Advancement.Builder.advancement()
                .parent(workstations)
                .display(
                        SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.EXTRACTOR),
                        Component.literal("Extractor"),
                        Component.literal("Used for extracting substances. Crafted with 4 copper blocks, 1 cauldron, 1 hopper, and 3 iron ingots"),
                        null,
                        AdvancementType.TASK,
                        false,
                        false,
                        false
                )
                .addCriterion("got_extractor", InventoryChangeTrigger.TriggerInstance.hasItems(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.EXTRACTOR)))
                .save(writer, SubstanceCraft.MOD_ID + ":extractor");

        AdvancementHolder electrolysis = Advancement.Builder.advancement()
                .parent(workstations)
                .display(
                        SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.ELECTROLYSIS_MACHINE),
                        Component.literal("Electrolysis Machine"),
                        Component.literal("Used for electrolysis reactions with substances. Crafted with 4 copper blocks, 1 cauldron, 2 redstone, 1 lever, and 1 iron ingot"),
                        null,
                        AdvancementType.TASK,
                        false,
                        false,
                        false
                )
                .addCriterion("got_electrolysis_machine", InventoryChangeTrigger.TriggerInstance.hasItems(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.ELECTROLYSIS_MACHINE)))
                .save(writer, SubstanceCraft.MOD_ID + ":electrolysis_machine");

        AdvancementHolder fermentationTank = Advancement.Builder.advancement()
                .parent(workstations)
                .display(
                        SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.FERMENTATION_TANK),
                        Component.literal("Fermentation Tank"),
                        Component.literal("Used for fermenting substances. Crafted with 4 copper blocks, 1 cauldron, and 4 iron ingots"),
                        null,
                        AdvancementType.TASK,
                        false,
                        false,
                        false
                )
                .addCriterion("got_fermentation_tank", InventoryChangeTrigger.TriggerInstance.hasItems(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.FERMENTATION_TANK)))
                .save(writer, SubstanceCraft.MOD_ID + ":fermentation_tank");

    }

    private void addSyntheses(Consumer<AdvancementHolder> writer) {
        AdvancementHolder syntheses = Advancement.Builder.advancement()
                .display(
                        SubstanceCraftItems.SALT,
                        Component.literal("Syntheses"),
                        Component.literal(""),
                        ResourceLocation.withDefaultNamespace("block/iron_block"),
                        AdvancementType.TASK,
                        false,
                        false,
                        false
                )
                .addCriterion("free", PlayerTrigger.TriggerInstance.tick())
                .save(writer, SubstanceCraft.MOD_ID + ":syntheses");

        generateSynthesisTree(SubstanceCraftItems.COCAINE, writer, syntheses);
        generateSynthesisTree(SubstanceCraftItems.AMPHETAMINE, writer, syntheses);
        generateSynthesisTree(SubstanceCraftItems.TWO_C_B, writer, syntheses);

        RecipeCache.clear();
    }

    @SuppressWarnings("deprecation")
    private static void generateSynthesisTree(Item toSynthesize, Consumer<AdvancementHolder> writer, AdvancementHolder parent) {
        AdvancementHolder itemAdvancement = Advancement.Builder.advancement().parent(parent)
                .display(toSynthesize, getNameFromItem(toSynthesize), Component.literal(""), null, AdvancementType.TASK, false, false, false)
                .addCriterion("free", PlayerTrigger.TriggerInstance.tick())
                .save(writer, SubstanceCraft.MOD_ID + ":" + toSynthesize.builtInRegistryHolder().key().location().getPath() + randomID());

        Recipe<?> recipe = getRecipeForItem(toSynthesize);
        if (recipe != null) {
            List<Ingredient> ingredients = getIngredients(recipe);
            for (Ingredient ingredient : ingredients) {
                Item item = getItemFromIngredient(ingredient);
                if (item == null) {
                    System.err.println("Could not find item for ingredient");
                    break;
                }
                generateSynthesisTree(item, writer, itemAdvancement);
            }
        }
    }

    private static String randomID() {
        return UUID.randomUUID().toString().split("-")[0];
    }

    private static Component getNameFromItem(Item item) {
        return item == Items.POTION
                ? Component.literal("Water Bottle")
                : new ItemStack(item).getItemName();
    }

    @Nullable
    @SuppressWarnings("deprecation")
    private static Item getItemFromIngredient(Ingredient ingredient) {
        Optional<Holder<Item>> itemOptional = ingredient.items().findFirst();
        return itemOptional.map(Holder::value).orElse(null);
    }

    private static List<Ingredient> getIngredients(Recipe<?> recipe) {
        if (recipe instanceof MultipleInputRecipe) {
            return ((MultipleInputRecipe) recipe).getInputs();
        } else if (recipe instanceof OneInputRecipe) {
            return List.of(((OneInputRecipe) recipe).getInput());
        } else {
            return List.of();
        }
    }

    @Nullable
    private static Recipe<?> getRecipeForItem(Item item) {
        HashMap<RecipeType<?>, List<Recipe<?>>> recipes = RecipeCache.getRecipesByType();
        List<Recipe<?>> matches = new ArrayList<>();
        for (RecipeType<?> type : recipes.keySet()) {
            for (Recipe<?> recipe : recipes.get(type)) {
                appendIfMatch(item, recipe, matches);
            }
        }
        return matches.isEmpty() ? null : matches.getFirst();
    }

    private static void appendIfMatch(Item item, Recipe<?> checkIfMatchItem, List<Recipe<?>> matches) {
        ByproductRecipe byproductRecipe = (ByproductRecipe) checkIfMatchItem;
        if (byproductRecipe.getResult().getItem() == item) matches.add(checkIfMatchItem);
        List<ItemStack> byproduct = byproductRecipe.getByproducts();
        for (ItemStack stack : byproduct) {
            if (stack.getItem() == item) {
                matches.add(checkIfMatchItem);
                return;
            }
        }
    }

    public static class RecipeCache {

        private static HashMap<RecipeType<?>, List<Recipe<?>>> recipesByType = new HashMap<>();

        public static HashMap<RecipeType<?>, List<Recipe<?>>> getRecipesByType() {
            return recipesByType;
        }

        public static void cacheRecipe(Recipe<?> recipe) {
            recipesByType.putIfAbsent(recipe.getType(), new ArrayList<>());
            recipesByType.get(recipe.getType()).add(recipe);
        }

        public static void clear() {
            recipesByType.clear();
            recipesByType = null;
        }

    }

}
