package com.github.ringlocker.substancecraft.datagen;

import com.github.ringlocker.substancecraft.SubstanceCraft;
import com.github.ringlocker.substancecraft.block.SubstanceCraftBlockItemIds;
import com.github.ringlocker.substancecraft.block.SubstanceCraftBlocks;
import com.github.ringlocker.substancecraft.item.SubstanceCraftItems;
import com.github.ringlocker.substancecraft.datagen.recipebuilder.ElectrolysisRecipeBuilder;
import com.github.ringlocker.substancecraft.datagen.recipebuilder.ExtractorRecipeBuilder;
import com.github.ringlocker.substancecraft.datagen.recipebuilder.FermentationTankRecipeBuilder;
import com.github.ringlocker.substancecraft.datagen.recipebuilder.HashPressRecipeBuilder;
import com.github.ringlocker.substancecraft.datagen.recipebuilder.HeatedMixerRecipeBuilder;
import com.github.ringlocker.substancecraft.datagen.recipebuilder.MixerRecipeBuilder;
import com.github.ringlocker.substancecraft.datagen.recipebuilder.OxidizerRecipeBuilder;
import com.github.ringlocker.substancecraft.datagen.recipebuilder.RefineryRecipeBuilder;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class RecipeGenerator extends FabricRecipeProvider {

    private static final int SHORT_REFINE_TIME = 600;
    private static final int REFINE_TIME = 800;
    private static final int LONG_REFINE_TIME = 1000;

    private static final int SHORT_EXTRACT_TIME = 500;
    private static final int EXTRACT_TIME = 750;
    private static final int LONG_EXTRACT_TIME = 1200;

    public RecipeGenerator(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected @NotNull RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
        return new RecipeProvider(provider, recipeOutput) {
            @Override
            public void buildRecipes() {
                shaped(RecipeCategory.MISC, SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.HASH_PRESS))
                        .pattern("121")
                        .pattern(" 3 ")
                        .pattern("111")
                        .define('1', Items.SMOOTH_STONE_SLAB)
                        .define('2', Items.REDSTONE_BLOCK)
                        .define('3', Items.PISTON)
                        .unlockedBy("has_item", has(Items.SMOOTH_STONE_SLAB))
                        .unlockedBy("has_item", has(Items.REDSTONE_BLOCK))
                        .unlockedBy("has_item", has(Items.PISTON))
                        .save(recipeOutput, key("hash_press"));

                shaped(RecipeCategory.MISC, SubstanceCraftItems.EMPTY_DAB_RIG)
                        .pattern("1  ")
                        .pattern("121")
                        .pattern(" 1 ")
                        .define('1', Items.GLASS)
                        .define('2', Items.GLASS_BOTTLE)
                        .unlockedBy("has_item", has(Items.GLASS))
                        .unlockedBy("has_item", has(Items.GLASS_BOTTLE))
                        .save(recipeOutput, key("dab_rig"));

                shaped(RecipeCategory.MISC, SubstanceCraftItems.EMPTY_BONG)
                        .pattern(" 1 ")
                        .pattern(" 2 ")
                        .pattern("111")
                        .define('1', Items.GLASS)
                        .define('2', Items.GLASS_BOTTLE)
                        .unlockedBy("has_item", has(Items.GLASS))
                        .unlockedBy("has_item", has(Items.GLASS_BOTTLE))
                        .save(recipeOutput, key("bong"));

                shaped(RecipeCategory.MISC, SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.REFINERY))
                        .pattern("333")
                        .pattern("212")
                        .pattern("323")
                        .define('1', Items.CAULDRON)
                        .define('2', Items.IRON_INGOT)
                        .define('3', ItemTags.COPPER)
                        .unlockedBy("has_item", has(Items.IRON_INGOT))
                        .unlockedBy("has_item", has(ItemTags.COPPER))
                        .save(recipeOutput, key("refinery"));

                shaped(RecipeCategory.MISC, SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.MIXER))
                        .pattern("121")
                        .pattern("343")
                        .pattern("131")
                        .define('1', ItemTags.COPPER)
                        .define('2', Items.IRON_SHOVEL)
                        .define('3', Items.IRON_INGOT)
                        .define('4', Items.CAULDRON)
                        .unlockedBy("has_item", has(Items.IRON_INGOT))
                        .unlockedBy("has_item", has(ItemTags.COPPER))
                        .save(recipeOutput, key("mixer"));

                shaped(RecipeCategory.MISC, SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.HEATED_MIXER))
                        .pattern("121")
                        .pattern("343")
                        .pattern("151")
                        .define('1', ItemTags.COPPER)
                        .define('2', Items.IRON_SHOVEL)
                        .define('3', Items.IRON_INGOT)
                        .define('4', Items.CAULDRON)
                        .define('5', Items.LAVA_BUCKET)
                        .unlockedBy("has_item", has(Items.IRON_INGOT))
                        .unlockedBy("has_item", has(ItemTags.COPPER))
                        .save(recipeOutput, key("heated_mixer"));

                shaped(RecipeCategory.MISC, SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.FERMENTATION_TANK))
                        .pattern("121")
                        .pattern("222")
                        .pattern("131")
                        .define('1', ItemTags.COPPER)
                        .define('2', Items.IRON_INGOT)
                        .define('3', Items.CAULDRON)
                        .unlockedBy("has_item", has(Items.IRON_INGOT))
                        .unlockedBy("has_item", has(ItemTags.COPPER))
                        .save(recipeOutput, key("fermentation_tank"));

                shaped(RecipeCategory.MISC, SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.OXIDATION_MACHINE))
                        .pattern("323")
                        .pattern("212")
                        .pattern("323")
                        .define('1', Items.CAULDRON)
                        .define('2', Items.IRON_INGOT)
                        .define('3', ItemTags.COPPER)
                        .unlockedBy("has_item", has(Items.IRON_INGOT))
                        .unlockedBy("has_item", has(ItemTags.COPPER))
                        .save(recipeOutput, key("oxidizer"));

                shaped(RecipeCategory.MISC, SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.EXTRACTOR))
                        .pattern("343")
                        .pattern("212")
                        .pattern("323")
                        .define('1', Items.CAULDRON)
                        .define('2', Items.IRON_INGOT)
                        .define('3', ItemTags.COPPER)
                        .define('4', Items.HOPPER)
                        .unlockedBy("has_item", has(Items.IRON_INGOT))
                        .unlockedBy("has_item", has(ItemTags.COPPER))
                        .save(recipeOutput, key("extractor"));

                shaped(RecipeCategory.MISC, SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.ELECTROLYSIS_MACHINE))
                        .pattern("121")
                        .pattern("343")
                        .pattern("151")
                        .define('1', ItemTags.COPPER)
                        .define('2', Items.LEVER)
                        .define('3', Items.REDSTONE)
                        .define('4', Items.CAULDRON)
                        .define('5', Items.IRON_INGOT)
                        .unlockedBy("has_item", has(Items.IRON_INGOT))
                        .unlockedBy("has_item", has(ItemTags.COPPER))
                        .save(recipeOutput, key("electrolysis_machine"));

                shaped(RecipeCategory.MISC, SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.CHEMIST_WORKSTATION))
                        .pattern("111")
                        .pattern("232")
                        .pattern("222")
                        .define('1', Items.IRON_INGOT)
                        .define('2', Items.GLASS_BOTTLE)
                        .define('3', Items.SPRUCE_PLANKS)
                        .unlockedBy("has_item", has(Items.IRON_INGOT))
                        .unlockedBy("has_item", has(Items.GLASS_BOTTLE))
                        .unlockedBy("has_item", has(Items.SPRUCE_PLANKS))
                        .save(recipeOutput, key("chemist_workstation"));

                shaped(RecipeCategory.MISC, SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.PLANT_RESEARCH_STATION))
                        .pattern("111")
                        .pattern("232")
                        .pattern("222")
                        .define('1', Items.LEAF_LITTER)
                        .define('2', Items.IRON_INGOT)
                        .define('3', ItemTags.PLANKS)
                        .unlockedBy("has_item", has(Items.IRON_INGOT))
                        .unlockedBy("has_item", has(Items.LEAF_LITTER))
                        .unlockedBy("has_item", has(ItemTags.PLANKS))
                        .save(recipeOutput, key("plant_research_station"));

                shaped(RecipeCategory.FOOD, SubstanceCraftItems.EDIBLE, 5)
                        .pattern("000")
                        .pattern("121")
                        .define('0', SubstanceCraftItems.LIVE_RESIN)
                        .define('1', Items.WHEAT)
                        .define('2', Items.COCOA_BEANS)
                        .unlockedBy("has_item", has(SubstanceCraftItems.LIVE_RESIN))
                        .unlockedBy("has_item", has(Items.WHEAT))
                        .unlockedBy("has_item", has(Items.COCOA_BEANS))
                        .save(recipeOutput, key("edible_resin"));

                shaped(RecipeCategory.FOOD, SubstanceCraftItems.EDIBLE, 8)
                        .pattern("000")
                        .pattern("121")
                        .define('0', SubstanceCraftItems.ROSIN)
                        .define('1', Items.WHEAT)
                        .define('2', Items.COCOA_BEANS)
                        .unlockedBy("has_item", has(SubstanceCraftItems.ROSIN))
                        .unlockedBy("has_item", has(Items.WHEAT))
                        .unlockedBy("has_item", has(Items.COCOA_BEANS))
                        .save(recipeOutput, key("edible_rosin"));

                shaped(RecipeCategory.TOOLS, SubstanceCraftItems.PEN_BATTERY)
                        .pattern("0")
                        .pattern("1")
                        .pattern("0")
                        .define('0', Items.IRON_INGOT)
                        .define('1', Items.REDSTONE_BLOCK)
                        .unlockedBy("has_item", has(Items.IRON_INGOT))
                        .unlockedBy("has_item", has(Items.REDSTONE_BLOCK))
                        .save(recipeOutput, key("pen_battery"));

                shaped(RecipeCategory.TOOLS, SubstanceCraftItems.EMPTY_CART)
                        .pattern("101")
                        .pattern(" 1 ")
                        .define('0', Items.GLASS_BOTTLE)
                        .define('1', Items.IRON_INGOT)
                        .unlockedBy("has_item", has(Items.IRON_INGOT))
                        .unlockedBy("has_item", has(Items.GLASS_BOTTLE))
                        .save(recipeOutput, key("empty_cart"));

                HashMap<Item, Item> PENS = new HashMap<>(Map.of(
                        SubstanceCraftItems.RESIN_CART, SubstanceCraftItems.RESIN_PEN,
                        SubstanceCraftItems.ROSIN_CART, SubstanceCraftItems.ROSIN_PEN,
                        SubstanceCraftItems.DMT_CART, SubstanceCraftItems.DMT_PEN
                ));

                HashMap<Item, Item> CARTS = new HashMap<>(Map.of(
                        SubstanceCraftItems.RESIN_CART, SubstanceCraftItems.LIVE_RESIN,
                        SubstanceCraftItems.ROSIN_CART, SubstanceCraftItems.ROSIN,
                        SubstanceCraftItems.DMT_CART, SubstanceCraftItems.N_N_DIMETHYLTRYPTAMINE
                ));


                for (Item cart : PENS.keySet()) {
                    shaped(RecipeCategory.FOOD, cart)
                            .pattern("000")
                            .pattern("010")
                            .pattern("020")
                            .define('0', CARTS.get(cart))
                            .define('1', SubstanceCraftItems.EMPTY_CART)
                            .define('2', SubstanceCraftItems.PROPYLENE_GLYCOL)
                            .unlockedBy("has_item", has(CARTS.get(cart)))
                            .unlockedBy("has_item", has(SubstanceCraftItems.EMPTY_CART))
                            .unlockedBy("has_item", has(SubstanceCraftItems.PROPYLENE_GLYCOL))
                            .save(recipeOutput, key(CARTS.get(cart).getDescriptionId().split("\\.")[2] + "_cart"));


                    shaped(RecipeCategory.FOOD, PENS.get(cart))
                            .pattern("0")
                            .pattern("1")
                            .define('0', cart)
                            .define('1', SubstanceCraftItems.PEN_BATTERY)
                            .unlockedBy("has_item", has(cart))
                            .unlockedBy("has_item", has(SubstanceCraftItems.PEN_BATTERY))
                            .save(recipeOutput, key(CARTS.get(cart).getDescriptionId().split("\\.")[2] + "_pen"));
                }

                nineBlockStorageRecipesWithCustomPacking(
                        RecipeCategory.MISC,
                        SubstanceCraftItems.CASH,
                        RecipeCategory.MISC,
                        SubstanceCraftItems.BAND, "band", "cash"
                );

                shapeless(
                        RecipeCategory.MISC,
                        SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.GRAPEVINE)
                )
                        .requires(SubstanceCraftItems.GRAPES)
                        .unlockedBy("has_item", has(SubstanceCraftItems.GRAPES))
                        .save(recipeOutput, key("grapevine"));

                shapeless(
                        RecipeCategory.MISC,
                        SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.CORN_CROP)
                )
                        .requires(SubstanceCraftItems.CORN)
                        .unlockedBy("has_item", has(SubstanceCraftItems.CORN))
                        .save(recipeOutput, key("corn"));

                shapeless(
                        RecipeCategory.MISC,
                        SubstanceCraftItems.JOINT
                )
                        .requires(SubstanceCraftItems.MARIJUANA)
                        .requires(Items.PAPER, 3)
                        .unlockedBy("has_item", has(SubstanceCraftItems.MARIJUANA))
                        .unlockedBy("has_item", has(Items.PAPER))
                        .save(recipeOutput, key("joint"));

                generateRecipes(SubstanceCraftBlocks.MIMOSA_WOOD, FeatureFlagSet.of(FeatureFlags.VANILLA));
                planksFromLogs(SubstanceCraftBlocks.MIMOSA_HOSTILIS_PLANKS, SubstanceCraftBlockItemIds.MIMOSA_HOSTILIS_LOGS.item(), 4);
                woodFromLogs(SubstanceCraftBlocks.MIMOSA_HOSTILIS_WOOD, SubstanceCraftBlocks.MIMOSA_HOSTILIS_LOG);
                woodFromLogs(SubstanceCraftBlocks.STRIPPED_MIMOSA_HOSTILIS_WOOD, SubstanceCraftBlocks.STRIPPED_MIMOSA_HOSTILIS_LOG);

                HashPressRecipeBuilder.press(
                                List.of(Ingredient.of(SubstanceCraftItems.MARIJUANA)),
                                SubstanceCraftItems.HASH,
                                1000
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.MARIJUANA))
                        .save(recipeOutput, key("press_hash"));

                HashPressRecipeBuilder.press(
                                List.of(Ingredient.of(SubstanceCraftItems.HASH)),
                                SubstanceCraftItems.ROSIN,
                                1200
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.HASH))
                        .save(recipeOutput, key("press_rosin"));

                RefineryRecipeBuilder.refine(
                                List.of(Ingredient.of(SubstanceCraftItems.OIL_SHALE)),
                                SubstanceCraftItems.OIL,
                                List.of(new ItemStackTemplate(SubstanceCraftItems.NATURAL_GAS, 40 >> 1)),
                                SHORT_REFINE_TIME
                        )
                        .unlockedBy("has_item", has(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.OIL_SHALE)))
                        .save(recipeOutput, key("refine_oil"));

                RefineryRecipeBuilder.refine(
                                List.of(Ingredient.of(SubstanceCraftItems.OIL)),
                                SubstanceCraftItems.PETROLEUM_NAPHTHA,
                                REFINE_TIME
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.OIL))
                        .save(recipeOutput, key("refine_naphtha"));

                RefineryRecipeBuilder.refine(
                                List.of(Ingredient.of(SubstanceCraftItems.OIL)),
                                SubstanceCraftItems.KEROSENE,
                                REFINE_TIME
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.OIL))
                        .save(recipeOutput, key("refine_kerosene"));

                RefineryRecipeBuilder.refine(
                                List.of(Ingredient.of(SubstanceCraftItems.OIL)),
                                SubstanceCraftItems.GASOLINE,
                                REFINE_TIME
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.OIL))
                        .save(recipeOutput, key("refine_gasoline"));

                RefineryRecipeBuilder.refine(
                                List.of(Ingredient.of(SubstanceCraftItems.NATURAL_GAS)),
                                SubstanceCraftItems.METHANE,
                                REFINE_TIME
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.NATURAL_GAS))
                        .save(recipeOutput, key("refine_methane"));

                RefineryRecipeBuilder.refine(
                                List.of(Ingredient.of(SubstanceCraftItems.NATURAL_GAS)),
                                SubstanceCraftItems.ETHANE,
                                REFINE_TIME
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.NATURAL_GAS))
                        .save(recipeOutput, key("refine_ethane"));

                RefineryRecipeBuilder.refine(
                                List.of(Ingredient.of(SubstanceCraftItems.NATURAL_GAS)),
                                SubstanceCraftItems.BUTANE,
                                REFINE_TIME
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.NATURAL_GAS))
                        .save(recipeOutput, key("refine_butane"));

                RefineryRecipeBuilder.refine(
                                List.of(Ingredient.of(SubstanceCraftItems.NATURAL_GAS)),
                                SubstanceCraftItems.PROPANE,
                                REFINE_TIME
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.NATURAL_GAS))
                        .save(recipeOutput, key("refine_propane"));

                RefineryRecipeBuilder.refine(
                                List.of(Ingredient.of(SubstanceCraftItems.PROPANE)),
                                SubstanceCraftItems.PROPYLENE,
                                LONG_REFINE_TIME
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.PROPANE))
                        .save(recipeOutput, key("refine_propylene"));

                RefineryRecipeBuilder.refine(
                                List.of(Ingredient.of(SubstanceCraftItems.ETHANE)),
                                SubstanceCraftItems.ETHYLENE,
                                LONG_REFINE_TIME
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.ETHANE))
                        .save(recipeOutput, key("refine_ethylene"));

                RefineryRecipeBuilder.refine(
                                List.of(Ingredient.of(SubstanceCraftItems.OIL)),
                                SubstanceCraftItems.DIESEL,
                                REFINE_TIME
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.ETHANE))
                        .save(recipeOutput, key("refine_diesel"));

                RefineryRecipeBuilder.refine(
                                List.of(Ingredient.of(Items.COAL)),
                                SubstanceCraftItems.COKE,
                                List.of(new ItemStackTemplate(SubstanceCraftItems.CARBON_MONOXIDE, 50 >> 1)),
                                REFINE_TIME
                        )
                        .unlockedBy("has_item", has(Items.COAL))
                        .save(recipeOutput, key("refine_coke"));

                RefineryRecipeBuilder.refine(
                                List.of(Ingredient.of(SubstanceCraftItems.PETROLEUM_NAPHTHA)),
                                SubstanceCraftItems.BENZENE,
                                REFINE_TIME
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.PETROLEUM_NAPHTHA))
                        .save(recipeOutput, key("reform_benzene"));

                RefineryRecipeBuilder.refine(
                                List.of(Ingredient.of(SubstanceCraftItems.PETROLEUM_NAPHTHA)),
                                SubstanceCraftItems.TOLUENE,
                                REFINE_TIME
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.PETROLEUM_NAPHTHA))
                        .save(recipeOutput, key("reform_toluene"));

                RefineryRecipeBuilder.refine(
                                List.of(Ingredient.of(SubstanceCraftItems.BENZENE), Ingredient.of(SubstanceCraftItems.TOLUENE)),
                                SubstanceCraftItems.XYLENE,
                                REFINE_TIME
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.BENZENE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.TOLUENE))
                        .save(recipeOutput, key("reform_xylene"));

                RefineryRecipeBuilder.refine(
                                List.of(Ingredient.of(SubstanceCraftItems.XYLENE)),
                                SubstanceCraftItems.P_XYLENE,
                                REFINE_TIME
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.XYLENE))
                        .save(recipeOutput, key("refine_p_xylene"));

                RefineryRecipeBuilder.refine(
                                List.of(Ingredient.of(SubstanceCraftItems.XYLENE)),
                                SubstanceCraftItems.O_XYLENE,
                                REFINE_TIME
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.XYLENE))
                        .save(recipeOutput, key("refine_o_xylene"));

                RefineryRecipeBuilder.refine(
                                List.of(Ingredient.of(SubstanceCraftItems.XYLENE)),
                                SubstanceCraftItems.M_XYLENE,
                                REFINE_TIME
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.XYLENE))
                        .save(recipeOutput, key("refine_m_xylene"));


                OxidizerRecipeBuilder.oxidize(
                                List.of(Ingredient.of(SubstanceCraftItems.METHANOL)),
                                SubstanceCraftItems.FORMALDEHYDE,
                                1000
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.METHANOL))
                        .save(recipeOutput, key("oxidize_formaldehyde"));

                OxidizerRecipeBuilder.oxidize(
                                List.of(Ingredient.of(SubstanceCraftItems.AMMONIA_SOLUTION)),
                                SubstanceCraftItems.NITRIC_ACID,
                                1000
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.AMMONIA_SOLUTION))
                        .save(recipeOutput, key("oxidize_nitric_acid"));

                OxidizerRecipeBuilder.oxidize(
                                List.of(Ingredient.of(SubstanceCraftItems.TOLUENE)),
                                SubstanceCraftItems.BENZALDEHYDE,
                                1000
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.TOLUENE))
                        .save(recipeOutput, key("oxidize_benzaldehyde"));

                OxidizerRecipeBuilder.oxidize(
                                List.of(Ingredient.of(SubstanceCraftItems.BENZENE)),
                                SubstanceCraftItems.MALEIC_ANHYDRIDE,
                                1000
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.BENZENE))
                        .save(recipeOutput, key("oxidize_maleic_anhydride"));

                OxidizerRecipeBuilder.oxidize(
                                List.of(Ingredient.of(SubstanceCraftItems.PROPYLENE)),
                                SubstanceCraftItems.ACETONE,
                                1000
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.PROPYLENE))
                        .save(recipeOutput, key("oxidize_acetone"));

                OxidizerRecipeBuilder.oxidize(
                                List.of(Ingredient.of(SubstanceCraftItems.BROMIDE)),
                                SubstanceCraftItems.BROMINE,
                                1000
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.BROMIDE))
                        .save(recipeOutput, key("oxidize_bromine"));

                OxidizerRecipeBuilder.oxidize(
                                List.of(Ingredient.of(SubstanceCraftItems.PHOSPHORUS_TRICHLORIDE)),
                                SubstanceCraftItems.PHOSPHORYL_CHLORIDE,
                                1000
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.PHOSPHORUS_TRICHLORIDE))
                        .save(recipeOutput, key("oxidize_phosphoryl_chloride"));

                OxidizerRecipeBuilder.oxidize(
                                List.of(Ingredient.of(SubstanceCraftItems.O_XYLENE)),
                                SubstanceCraftItems.PHTHALIC_ANHYDRIDE,
                                new ItemStackTemplate(SubstanceCraftItems.VANADIUM_PENTOXIDE),
                                1000
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.O_XYLENE))
                        .save(recipeOutput, key("oxidize_phthalic_anhydride"));

                ElectrolysisRecipeBuilder.electrolysis(
                                List.of(Ingredient.of(SubstanceCraftItems.BRINE)),
                                SubstanceCraftItems.SODIUM_HYDROXIDE,
                                List.of(new ItemStackTemplate(SubstanceCraftItems.CHLORINE, 50 >> 1), new ItemStackTemplate(SubstanceCraftItems.HYDROGEN, 50 >> 1)),
                                1000
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.BRINE))
                        .save(recipeOutput, key("electrolysis_brine"));

                ElectrolysisRecipeBuilder.electrolysis(
                                List.of(Ingredient.of(SubstanceCraftItems.DISTILLED_WATER)),
                                SubstanceCraftItems.HYDROGEN,
                                List.of(new ItemStackTemplate(SubstanceCraftItems.OXYGEN, 50 >> 1)),
                                1000
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.DISTILLED_WATER))
                        .save(recipeOutput, key("electrolysis_water"));

                ElectrolysisRecipeBuilder.electrolysis(
                                List.of(Ingredient.of(SubstanceCraftItems.POTASSIUM_CHLORIDE)),
                                SubstanceCraftItems.POTASSIUM_HYDROXIDE,
                                1000
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.DISTILLED_WATER))
                        .save(recipeOutput, key("electrolysis_potassium_hydroxide"));

                // Hall–Héroult process
                ElectrolysisRecipeBuilder.electrolysis(
                                List.of(Ingredient.of(SubstanceCraftItems.ALUMINA), Ingredient.of(SubstanceCraftItems.CRYOLITE)),
                                SubstanceCraftItems.ALUMINUM,
                                1000
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.ALUMINA))
                        .unlockedBy("has_item", has(SubstanceCraftItems.CRYOLITE))
                        .save(recipeOutput, key("electrolysis_aluminum"));

                ExtractorRecipeBuilder.extract(
                                List.of(Ingredient.of(Items.GLASS_BOTTLE)),
                                SubstanceCraftItems.NITROGEN,
                                EXTRACT_TIME
                        )
                        .unlockedBy("has_item", has(Items.GLASS_BOTTLE))
                        .save(recipeOutput, key("extract_nitrogen"));

                ExtractorRecipeBuilder.extract(
                                List.of(Ingredient.of(SubstanceCraftItems.HALITE)),
                                SubstanceCraftItems.SALT,
                                SHORT_EXTRACT_TIME
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.HALITE))
                        .save(recipeOutput, key("extract_salt"));

                ExtractorRecipeBuilder.extract(
                                List.of(Ingredient.of(Items.GLASS_BOTTLE)),
                                SubstanceCraftItems.OXYGEN,
                                LONG_EXTRACT_TIME
                        )
                        .unlockedBy("has_item", has(Items.GLASS_BOTTLE))
                        .save(recipeOutput, key("extract_oxygen"));

                ExtractorRecipeBuilder.extract(
                                List.of(Ingredient.of(SubstanceCraftItems.BRINE)),
                                SubstanceCraftItems.BROMIDE,
                                List.of(new ItemStackTemplate(SubstanceCraftItems.MAGNESIUM, 50 >> 2)),
                                LONG_EXTRACT_TIME
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.BRINE))
                        .save(recipeOutput, key("extract_bromide"));

                ExtractorRecipeBuilder.extract(
                                List.of(Ingredient.of(Items.POTION)),
                                SubstanceCraftItems.DISTILLED_WATER,
                                EXTRACT_TIME
                        )
                        .unlockedBy("has_item", has(Items.POTION))
                        .setOutputCount(4)
                        .save(recipeOutput, key("extract_distilled_water"));

                ExtractorRecipeBuilder.extract(
                                List.of(Ingredient.of(SubstanceCraftItems.P2NP)),
                                SubstanceCraftItems.P2P,
                                EXTRACT_TIME
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.P2NP))
                        .save(recipeOutput, key("extract_p2p"));

                ExtractorRecipeBuilder.extract(
                                List.of(Ingredient.of(SubstanceCraftItems.TRONA)),
                                SubstanceCraftItems.SODIUM_CARBONATE,
                                SHORT_EXTRACT_TIME
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.TRONA))
                        .save(recipeOutput, key("extract_sodium_carbonate"));

                ExtractorRecipeBuilder.extract(
                                List.of(Ingredient.of(SubstanceCraftItems.PYROLUSITE)),
                                SubstanceCraftItems.MANGANESE_DIOXIDE,
                                SHORT_EXTRACT_TIME
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.PYROLUSITE))
                        .save(recipeOutput, key("extract_pyrolusite"));

                ExtractorRecipeBuilder.extract(
                                List.of(Ingredient.of(SubstanceCraftItems.SYLVITE)),
                                SubstanceCraftItems.POTASSIUM_CHLORIDE,
                                SHORT_EXTRACT_TIME
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.SYLVITE))
                        .save(recipeOutput, key("extract_potassium_chloride"));

                ExtractorRecipeBuilder.extract(
                                List.of(Ingredient.of(Items.CHARCOAL)),
                                SubstanceCraftItems.CARBON_DIOXIDE,
                                EXTRACT_TIME
                        )
                        .unlockedBy("has_item", has(Items.CHARCOAL))
                        .save(recipeOutput, key("extract_carbon_dioxide"));

                ExtractorRecipeBuilder.extract(
                                List.of(Ingredient.of(SubstanceCraftItems.RAW_SULFUR)),
                                SubstanceCraftItems.SULFUR,
                                SHORT_EXTRACT_TIME
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.RAW_SULFUR))
                        .save(recipeOutput, key("extract_sulfur"));

                ExtractorRecipeBuilder.extract(
                                List.of(Ingredient.of(SubstanceCraftItems.WINE_LEES)),
                                SubstanceCraftItems.POTASSIUM_BITARTRATE,
                                EXTRACT_TIME
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.WINE_LEES))
                        .save(recipeOutput, key("extract_potassium_bitartrate"));

                ExtractorRecipeBuilder.extract(
                                List.of(Ingredient.of(SubstanceCraftItems.LIMESTONE)),
                                SubstanceCraftItems.CALCIUM_OXIDE,
                                SHORT_EXTRACT_TIME
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.LIMESTONE))
                        .save(recipeOutput, key("extract_calcium_oxide"));

                ExtractorRecipeBuilder.extract(
                                List.of(Ingredient.of(Items.QUARTZ)),
                                SubstanceCraftItems.SILICA,
                                SHORT_EXTRACT_TIME
                        )
                        .unlockedBy("has_item", has(Items.QUARTZ))
                        .save(recipeOutput, key("extract_silica"));

                ExtractorRecipeBuilder.extract(
                                List.of(Ingredient.of(SubstanceCraftItems.ERGOT)),
                                SubstanceCraftItems.ERGOTAMINE,
                                EXTRACT_TIME
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.ERGOT))
                        .save(recipeOutput, key("extract_ergotamine"));

                ExtractorRecipeBuilder.extract(
                                List.of(Ingredient.of(SubstanceCraftItems.PHOSPHORITE)),
                                SubstanceCraftItems.FLOUROAPATITE,
                                SHORT_EXTRACT_TIME
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.ERGOT))
                        .save(recipeOutput, key("extract_flouroapatite"));

                ExtractorRecipeBuilder.extract(
                                List.of(Ingredient.of(SubstanceCraftItems.MARIJUANA), Ingredient.of(SubstanceCraftItems.BUTANE)),
                                SubstanceCraftItems.LIVE_RESIN,
                                LONG_EXTRACT_TIME
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.MARIJUANA))
                        .unlockedBy("has_item", has(SubstanceCraftItems.BUTANE))
                        .setOutputCount(4)
                        .save(recipeOutput, key("extract_live_resin"));

                // Bayer process
                ExtractorRecipeBuilder.extract(
                                List.of(Ingredient.of(SubstanceCraftItems.BAUXITE), Ingredient.of(SubstanceCraftItems.SODIUM_HYDROXIDE), Ingredient.of(SubstanceCraftItems.DISTILLED_WATER)),
                                SubstanceCraftItems.ALUMINA,
                                LONG_EXTRACT_TIME
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.BAUXITE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.SODIUM_HYDROXIDE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.DISTILLED_WATER))
                        .setOutputCount(2)
                        .save(recipeOutput, key("extract_alumina"));

                ExtractorRecipeBuilder.extract(
                                List.of(Ingredient.of(SubstanceCraftItems.RAW_PALLADIUM)),
                                SubstanceCraftItems.PALLADIUM,
                                SHORT_EXTRACT_TIME
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.RAW_PALLADIUM))
                        .save(recipeOutput, key("extract_palladium"));

                ExtractorRecipeBuilder.extract(
                                List.of(Ingredient.of(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.ANISE_PLANT))),
                                SubstanceCraftItems.ANISE_OIL,
                                LONG_EXTRACT_TIME
                        )
                        .unlockedBy("has_item", has(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.ANISE_PLANT)))
                        .save(recipeOutput, key("extract_anise"));

                ExtractorRecipeBuilder.extract(
                                List.of(Ingredient.of(SubstanceCraftItems.METHANOL)),
                                SubstanceCraftItems.DIMETHYL_ETHER,
                                List.of(new ItemStackTemplate(SubstanceCraftItems.DISTILLED_WATER, 100 >> 2)),
                                EXTRACT_TIME
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.METHANOL))
                        .save(recipeOutput, key("extract_dimethyl_ether"));

                MixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.SALT), Ingredient.of(Items.POTION)),
                                SubstanceCraftItems.BRINE,
                                800
                        )
                        .unlockedBy("has_item", has(Items.POTION))
                        .unlockedBy("has_item", has(SubstanceCraftItems.SALT))
                        .setOutputCount(4)
                        .save(recipeOutput, key("mix_brine"));

                MixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.ACETIC_ACID), Ingredient.of(SubstanceCraftItems.AMMONIA)),
                                SubstanceCraftItems.AMMONIUM_ACETATE,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.ACETIC_ACID))
                        .unlockedBy("has_item", has(SubstanceCraftItems.AMMONIA))
                        .save(recipeOutput, key("mix_ammonia_acetate"));


                MixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.HYDROGEN), Ingredient.of(SubstanceCraftItems.MALEIC_ANHYDRIDE)),
                                SubstanceCraftItems.TETRAHYDROFURAN,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.HYDROGEN))
                        .unlockedBy("has_item", has(SubstanceCraftItems.MALEIC_ANHYDRIDE))
                        .save(recipeOutput, key("mix_tetrahydrofuran"));


                MixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.BETA_NITROSTYRENE), Ingredient.of(SubstanceCraftItems.TETRAHYDROFURAN), Ingredient.of(SubstanceCraftItems.SODIUM_HYDROXIDE), Ingredient.of(SubstanceCraftItems.HYDROCHLORIC_ACID)),
                                SubstanceCraftItems.TWO_C_H,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.BETA_NITROSTYRENE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.TETRAHYDROFURAN))
                        .setOutputCount(2)
                        .save(recipeOutput, key("mix_2c_h"));


                MixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.TWO_C_H), Ingredient.of(SubstanceCraftItems.BROMINE), Ingredient.of(SubstanceCraftItems.SODIUM_HYDROXIDE), Ingredient.of(SubstanceCraftItems.HYDROCHLORIC_ACID)),
                                SubstanceCraftItems.TWO_C_B,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.TWO_C_H))
                        .unlockedBy("has_item", has(SubstanceCraftItems.BROMINE))
                        .setOutputCount(4)
                        .save(recipeOutput, key("mix_2c_b"));

                MixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.METHYL_FORMATE), Ingredient.of(SubstanceCraftItems.DISTILLED_WATER)),
                                SubstanceCraftItems.FORMIC_ACID,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.METHYL_FORMATE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.DISTILLED_WATER))
                        .save(recipeOutput, key("mix_formic_acid"));

                MixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.SODIUM_CARBONATE), Ingredient.of(SubstanceCraftItems.DISTILLED_WATER)),
                                SubstanceCraftItems.SODIUM_CARBONATE_SOLUTION,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.SODIUM_CARBONATE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.DISTILLED_WATER))
                        .save(recipeOutput, key("mix_sodium_carbonate_solution"));

                MixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.SODIUM_CARBONATE_SOLUTION), Ingredient.of(SubstanceCraftItems.KEROSENE), Ingredient.of(SubstanceCraftItems.COCA_LEAVES), Ingredient.of(SubstanceCraftItems.SULFURIC_ACID)),
                                SubstanceCraftItems.AGUA_RICA,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.SODIUM_CARBONATE_SOLUTION))
                        .unlockedBy("has_item", has(SubstanceCraftItems.KEROSENE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.COCA_LEAVES))
                        .unlockedBy("has_item", has(SubstanceCraftItems.SULFURIC_ACID))
                        .setOutputCount(2)
                        .save(recipeOutput, key("mix_agua_rica"));

                MixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.AGUA_RICA), Ingredient.of(SubstanceCraftItems.AMMONIA_SOLUTION), Ingredient.of(SubstanceCraftItems.POTASSIUM_PERMANGANATE)),
                                SubstanceCraftItems.COCA_PASTE,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.AGUA_RICA))
                        .unlockedBy("has_item", has(SubstanceCraftItems.AMMONIA_SOLUTION))
                        .unlockedBy("has_item", has(SubstanceCraftItems.POTASSIUM_PERMANGANATE))
                        .setOutputCount(2)
                        .save(recipeOutput, key("mix_coca_paste"));

                MixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.AMMONIA), Ingredient.of(SubstanceCraftItems.DISTILLED_WATER)),
                                SubstanceCraftItems.AMMONIA_SOLUTION,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.AMMONIA))
                        .unlockedBy("has_item", has(SubstanceCraftItems.DISTILLED_WATER))
                        .save(recipeOutput, key("mix_ammonia_solution"));

                MixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.ACETONE), Ingredient.of(SubstanceCraftItems.POTASSIUM_CARBONATE), Ingredient.of(SubstanceCraftItems.COCA_PASTE), Ingredient.of(SubstanceCraftItems.HYDROCHLORIC_ACID)),
                                SubstanceCraftItems.COCAINE,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.ACETONE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.POTASSIUM_CARBONATE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.COCA_PASTE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.HYDROCHLORIC_ACID))
                        .setOutputCount(4)
                        .save(recipeOutput, key("mix_cocaine"));

                MixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.RED_PHOSPHORUS), Ingredient.of(SubstanceCraftItems.CHLORINE)),
                                SubstanceCraftItems.PHOSPHORUS_TRICHLORIDE,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.RED_PHOSPHORUS))
                        .unlockedBy("has_item", has(SubstanceCraftItems.CHLORINE))
                        .save(recipeOutput, key("mix_phosphorus_trichloride"));

                MixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.CALCIUM_OXIDE), Ingredient.of(SubstanceCraftItems.DISTILLED_WATER)),
                                SubstanceCraftItems.CALCIUM_HYDROXIDE,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.DISTILLED_WATER))
                        .unlockedBy("has_item", has(SubstanceCraftItems.CALCIUM_OXIDE))
                        .save(recipeOutput, key("mix_calcium_hydroxide"));

                MixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.CALCIUM_HYDROXIDE), Ingredient.of(SubstanceCraftItems.POTASSIUM_BITARTRATE)),
                                SubstanceCraftItems.CALCIUM_TARTRATE,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.CALCIUM_HYDROXIDE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.POTASSIUM_BITARTRATE))
                        .save(recipeOutput, key("mix_calcium_tartrate"));

                MixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.CALCIUM_TARTRATE), Ingredient.of(SubstanceCraftItems.SULFURIC_ACID)),
                                SubstanceCraftItems.TARTARIC_ACID,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.CALCIUM_TARTRATE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.SULFURIC_ACID))
                        .save(recipeOutput, key("mix_tataric_acid"));

                MixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.ERGOTAMINE), Ingredient.of(SubstanceCraftItems.TARTARIC_ACID)),
                                SubstanceCraftItems.ERGOTAMINE_TARTRATE,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.ERGOTAMINE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.TARTARIC_ACID))
                        .save(recipeOutput, key("mix_ergotamine_tartrate"));

                MixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.SULFURIC_ACID), Ingredient.of(SubstanceCraftItems.ETHYLENE), Ingredient.of(SubstanceCraftItems.DISTILLED_WATER)),
                                SubstanceCraftItems.ETHANOL,
                                List.of(new ItemStackTemplate(SubstanceCraftItems.DIETHYL_ETHER, 33 >> 2)),
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.SULFURIC_ACID))
                        .unlockedBy("has_item", has(SubstanceCraftItems.ETHYLENE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.DISTILLED_WATER))
                        .setOutputCount(2)
                        .save(recipeOutput, key("mix_ethanol"));

                MixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.SULFURIC_ACID), Ingredient.of(SubstanceCraftItems.ETHANOL)),
                                SubstanceCraftItems.DIETHYL_ETHER,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.SULFURIC_ACID))
                        .unlockedBy("has_item", has(SubstanceCraftItems.ETHANOL))
                        .save(recipeOutput, key("mix_diethyl_ether"));

                MixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.SULFURIC_ACID), Ingredient.of(SubstanceCraftItems.ERGOTAMINE_TARTRATE), Ingredient.of(SubstanceCraftItems.DIETHYL_ETHER), Ingredient.of(SubstanceCraftItems.DISTILLED_WATER)),
                                SubstanceCraftItems.ERGOTAMINE_SULFATE,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.SULFURIC_ACID))
                        .unlockedBy("has_item", has(SubstanceCraftItems.ERGOTAMINE_TARTRATE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.DIETHYL_ETHER))
                        .unlockedBy("has_item", has(SubstanceCraftItems.DISTILLED_WATER))
                        .setOutputCount(2)
                        .save(recipeOutput, key("mix_ergotamine_sulfate"));

                MixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.SULFURIC_ACID), Ingredient.of(SubstanceCraftItems.ERGOTAMINE_SULFATE), Ingredient.of(SubstanceCraftItems.AMMONIA), Ingredient.of(SubstanceCraftItems.ETHANOL)),
                                SubstanceCraftItems.D_LYSERGIC_ACID_HYDRATE,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.SULFURIC_ACID))
                        .unlockedBy("has_item", has(SubstanceCraftItems.ERGOTAMINE_SULFATE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.AMMONIA))
                        .unlockedBy("has_item", has(SubstanceCraftItems.ETHANOL))
                        .setOutputCount(2)
                        .save(recipeOutput, key("mix_d_lysergic_acid_hydrate"));

                MixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.MAGNESIUM), Ingredient.of(SubstanceCraftItems.SULFURIC_ACID)),
                                SubstanceCraftItems.MAGNESIUM_SULFATE,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.MAGNESIUM))
                        .unlockedBy("has_item", has(SubstanceCraftItems.SULFURIC_ACID))
                        .save(recipeOutput, key("mix_magnesium_sulfate"));

                MixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.LYSERGIC_ACID_DIETHYLAMINE), Ingredient.of(Items.PAPER)),
                                SubstanceCraftItems.LYSERGIC_ACID_DIETHYLAMINE_TAB,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.LYSERGIC_ACID_DIETHYLAMINE))
                        .unlockedBy("has_item", has(Items.PAPER))
                        .setOutputCount(4)
                        .save(recipeOutput, key("mix_lsd_tab"));

                MixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.ACETIC_ACID), Ingredient.of(SubstanceCraftItems.DISTILLED_WATER)),
                                SubstanceCraftItems.VINEGAR,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.ACETIC_ACID))
                        .unlockedBy("has_item", has(SubstanceCraftItems.DISTILLED_WATER))
                        .setOutputCount(2)
                        .save(recipeOutput, key("mix_vinegar"));

                MixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.PROPYLENE), Ingredient.of(SubstanceCraftItems.CHLORINE), Ingredient.of(SubstanceCraftItems.DISTILLED_WATER)),
                                SubstanceCraftItems.PROPYLENE_OXIDE,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.PROPYLENE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.CHLORINE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.DISTILLED_WATER))
                        .setOutputCount(2)
                        .save(recipeOutput, key("mix_propylene_oxide"));

                MixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.PROPYLENE_OXIDE), Ingredient.of(SubstanceCraftItems.DISTILLED_WATER)),
                                SubstanceCraftItems.PROPYLENE_GLYCOL,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.PROPYLENE_OXIDE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.DISTILLED_WATER))
                        .save(recipeOutput, key("mix_propylene_glycol"));

                MixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.AMMONIA), Ingredient.of(SubstanceCraftItems.HYDROCHLORIC_ACID)),
                                SubstanceCraftItems.AMMONIUM_CHLORIDE,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.AMMONIA))
                        .unlockedBy("has_item", has(SubstanceCraftItems.HYDROCHLORIC_ACID))
                        .save(recipeOutput, key("mix_ammonium_chloride"));

                // Solvay Process
                MixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.CARBON_DIOXIDE), Ingredient.of(SubstanceCraftItems.AMMONIA), Ingredient.of(SubstanceCraftItems.DISTILLED_WATER), Ingredient.of(SubstanceCraftItems.SALT)),
                                SubstanceCraftItems.AMMONIUM_CHLORIDE,
                                List.of(new ItemStackTemplate(SubstanceCraftItems.SODIUM_BICARBONATE, 100 >> 2)),
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.CARBON_DIOXIDE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.AMMONIA))
                        .unlockedBy("has_item", has(SubstanceCraftItems.SALT))
                        .unlockedBy("has_item", has(SubstanceCraftItems.DISTILLED_WATER))
                        .setOutputCount(2)
                        .save(recipeOutput, key("mix_ammonium_chloride_solvay"));

                MixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.PHTHALIC_ANHYDRIDE), Ingredient.of(SubstanceCraftItems.ETHYLBENZENE)),
                                SubstanceCraftItems.TWO_ETHYLANTHRAQUINONE,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.PHTHALIC_ANHYDRIDE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.ETHYLBENZENE))
                        .save(recipeOutput, key("mix_two_ethylanthraquinone"));

                // Anthraquinone process
                MixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.HYDROGEN), Ingredient.of(SubstanceCraftItems.TWO_ETHYLANTHRAQUINONE)),
                                SubstanceCraftItems.HYDROGEN_PEROXIDE,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.HYDROGEN))
                        .unlockedBy("has_item", has(SubstanceCraftItems.TWO_ETHYLANTHRAQUINONE))
                        .save(recipeOutput, key("mix_two_hydrogen_peroxide"));

                MixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.HYDROCHLORIC_ACID), Ingredient.of(SubstanceCraftItems.METHANOL), Ingredient.of(SubstanceCraftItems.CHLORINE)),
                                SubstanceCraftItems.DICHLOROMETHANE,
                                List.of(new ItemStackTemplate(SubstanceCraftItems.METHYL_CHLORIDE, 20 >> 2), new ItemStackTemplate(SubstanceCraftItems.CARBON_TETRACHLORIDE, 50 >> 2), new ItemStackTemplate(SubstanceCraftItems.CHLOROFORM, 50 >> 2)),
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.HYDROCHLORIC_ACID))
                        .unlockedBy("has_item", has(SubstanceCraftItems.METHANOL))
                        .unlockedBy("has_item", has(SubstanceCraftItems.CHLORINE))
                        .setOutputCount(2)
                        .save(recipeOutput, key("mix_dichloromethane"));

                MixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.CHROMIUM_III_OXIDE), Ingredient.of(SubstanceCraftItems.SODIUM_CARBONATE), Ingredient.of(SubstanceCraftItems.SULFURIC_ACID)),
                                SubstanceCraftItems.SODIUM_DICHROMATE,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.CHROMIUM_III_OXIDE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.SODIUM_CARBONATE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.SULFURIC_ACID))
                        .setOutputCount(2)
                        .save(recipeOutput, key("mix_sdoium_dichromate"));

                MixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.ANISE_OIL), Ingredient.of(SubstanceCraftItems.TOLUENE), Ingredient.of(SubstanceCraftItems.DISTILLED_WATER), Ingredient.of(SubstanceCraftItems.SULFURIC_ACID), Ingredient.of(SubstanceCraftItems.SODIUM_DICHROMATE)),
                                SubstanceCraftItems.FOUR_METHOXYBENZALDEHYDE,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.ANISE_OIL))
                        .unlockedBy("has_item", has(SubstanceCraftItems.TOLUENE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.DISTILLED_WATER))
                        .unlockedBy("has_item", has(SubstanceCraftItems.SODIUM_DICHROMATE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.SULFURIC_ACID))
                        .setOutputCount(3)
                        .save(recipeOutput, key("mix_anisealdehyde"));

                MixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.FOUR_METHOXYBENZALDEHYDE), Ingredient.of(SubstanceCraftItems.FORMIC_ACID), Ingredient.of(SubstanceCraftItems.DICHLOROMETHANE), Ingredient.of(SubstanceCraftItems.HYDROGEN_PEROXIDE)),
                                SubstanceCraftItems.O_FORMYL_4_METHOXYPHENOL,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.FOUR_METHOXYBENZALDEHYDE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.FORMIC_ACID))
                        .unlockedBy("has_item", has(SubstanceCraftItems.DICHLOROMETHANE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.HYDROGEN_PEROXIDE))
                        .setOutputCount(2)
                        .save(recipeOutput, key("mix_o_formyl_4_methoxyphenol"));

                MixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.O_FORMYL_4_METHOXYPHENOL), Ingredient.of(SubstanceCraftItems.CHLOROFORM), Ingredient.of(SubstanceCraftItems.SODIUM_HYDROXIDE), Ingredient.of(SubstanceCraftItems.DISTILLED_WATER)),
                                SubstanceCraftItems.TWO_HYDROXY_5_METHYLBENZALDEHYDE,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.O_FORMYL_4_METHOXYPHENOL))
                        .unlockedBy("has_item", has(SubstanceCraftItems.CHLOROFORM))
                        .unlockedBy("has_item", has(SubstanceCraftItems.SODIUM_HYDROXIDE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.DISTILLED_WATER))
                        .setOutputCount(2)
                        .save(recipeOutput, key("mix_2_hydroxy_5_methylbenzaldehyde"));

                MixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.TWO_HYDROXY_5_METHYLBENZALDEHYDE), Ingredient.of(SubstanceCraftItems.ACETONE), Ingredient.of(SubstanceCraftItems.POTASSIUM_CARBONATE), Ingredient.of(SubstanceCraftItems.ETHANOL), Ingredient.of(SubstanceCraftItems.DIMETHYL_SULFATE)),
                                SubstanceCraftItems.TWO_5_DIMETHOXYBENZALDEHYDE,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.TWO_HYDROXY_5_METHYLBENZALDEHYDE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.ACETONE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.POTASSIUM_CARBONATE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.ETHANOL))
                        .unlockedBy("has_item", has(SubstanceCraftItems.DIMETHYL_SULFATE))
                        .setOutputCount(3)
                        .save(recipeOutput, key("mix_2_5_dimethoxybenzaldehyde"));

                HeatedMixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.DISTILLED_WATER), Ingredient.of(SubstanceCraftItems.METHANE)),
                                SubstanceCraftItems.METHANOL,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.DISTILLED_WATER))
                        .unlockedBy("has_item", has(SubstanceCraftItems.METHANE))
                        .save(recipeOutput, key("mix_methanol"));

                HeatedMixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.AMMONIA), Ingredient.of(SubstanceCraftItems.METHANOL)),
                                SubstanceCraftItems.METHYLAMINE,
                                List.of(new ItemStackTemplate(SubstanceCraftItems.DIMETHYLAMINE, 50 >> 2), new ItemStackTemplate(SubstanceCraftItems.TRIMETHYLAMINE, 25 >> 2)),
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.AMMONIA))
                        .unlockedBy("has_item", has(SubstanceCraftItems.METHANOL))
                        .save(recipeOutput, key("mix_methylamine"));

                HeatedMixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.CHLORINE), Ingredient.of(SubstanceCraftItems.METHANE)),
                                SubstanceCraftItems.CHLOROFORM,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.CHLORINE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.METHANE))
                        .save(recipeOutput, key("mix_chloroform"));

                HeatedMixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.CHLORINE), Ingredient.of(SubstanceCraftItems.HYDROGEN), Ingredient.of(SubstanceCraftItems.DISTILLED_WATER)),
                                SubstanceCraftItems.HYDROCHLORIC_ACID,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.CHLORINE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.HYDROCHLORIC_ACID))
                        .unlockedBy("has_item", has(SubstanceCraftItems.DISTILLED_WATER))
                        .setOutputCount(2)
                        .save(recipeOutput, key("mix_hcl"));

                HeatedMixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.NITROGEN), Ingredient.of(SubstanceCraftItems.HYDROGEN)),
                                SubstanceCraftItems.AMMONIA,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.NITROGEN))
                        .unlockedBy("has_item", has(SubstanceCraftItems.HYDROGEN))
                        .save(recipeOutput, key("mix_ammonia"));

                HeatedMixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.PROPANE), Ingredient.of(SubstanceCraftItems.NITRIC_ACID)),
                                SubstanceCraftItems.NITROMETHANE,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.PROPANE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.NITRIC_ACID))
                        .save(recipeOutput, key("mix_nitromethane"));

                HeatedMixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.METHANOL), Ingredient.of(SubstanceCraftItems.CARBON_MONOXIDE)),
                                SubstanceCraftItems.ACETIC_ACID,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.PROPANE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.NITRIC_ACID))
                        .save(recipeOutput, key("mix_acetic_acid"));

                HeatedMixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.TWO_5_DIMETHOXYBENZALDEHYDE), Ingredient.of(SubstanceCraftItems.NITROMETHANE), Ingredient.of(SubstanceCraftItems.AMMONIUM_ACETATE)),
                                SubstanceCraftItems.BETA_NITROSTYRENE,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.TWO_5_DIMETHOXYBENZALDEHYDE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.NITROMETHANE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.AMMONIUM_ACETATE))
                        .setOutputCount(2)
                        .save(recipeOutput, key("mix_beta_nitrostyrene"));


                HeatedMixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.CARBON_MONOXIDE), Ingredient.of(SubstanceCraftItems.METHANOL)),
                                SubstanceCraftItems.METHYL_FORMATE,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.CARBON_MONOXIDE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.METHANOL))
                        .save(recipeOutput, key("mix_methyl_formate"));

                HeatedMixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.BENZALDEHYDE), Ingredient.of(SubstanceCraftItems.NITROETHANE)),
                                SubstanceCraftItems.P2NP,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.BENZALDEHYDE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.NITROETHANE))
                        .save(recipeOutput, key("mix_p2np"));


                HeatedMixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.NITRIC_ACID), Ingredient.of(SubstanceCraftItems.ETHANE)),
                                SubstanceCraftItems.NITROETHANE,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.NITRIC_ACID))
                        .unlockedBy("has_item", has(SubstanceCraftItems.ETHANE))
                        .save(recipeOutput, key("mix_nitroethane"));


                HeatedMixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.P2P), Ingredient.of(SubstanceCraftItems.FORMIC_ACID), Ingredient.of(SubstanceCraftItems.AMMONIA)),
                                SubstanceCraftItems.AMPHETAMINE,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.P2P))
                        .unlockedBy("has_item", has(SubstanceCraftItems.FORMIC_ACID))
                        .unlockedBy("has_item", has(SubstanceCraftItems.AMMONIA))
                        .setOutputCount(4)
                        .save(recipeOutput, key("mix_amphetamine"));

                HeatedMixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.SULFUR), Ingredient.of(SubstanceCraftItems.DISTILLED_WATER), Ingredient.of(SubstanceCraftItems.OXYGEN)),
                                SubstanceCraftItems.SULFURIC_ACID,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.SULFUR))
                        .unlockedBy("has_item", has(SubstanceCraftItems.DISTILLED_WATER))
                        .unlockedBy("has_item", has(SubstanceCraftItems.OXYGEN))
                        .setOutputCount(2)
                        .save(recipeOutput, key("mix_sulfuric_acid"));


                HeatedMixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.OXYGEN), Ingredient.of(SubstanceCraftItems.MANGANESE_DIOXIDE), Ingredient.of(SubstanceCraftItems.POTASSIUM_HYDROXIDE)),
                                SubstanceCraftItems.POTASSIUM_PERMANGANATE,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.OXYGEN))
                        .unlockedBy("has_item", has(SubstanceCraftItems.MANGANESE_DIOXIDE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.POTASSIUM_HYDROXIDE))
                        .setOutputCount(2)
                        .save(recipeOutput, key("mix_potassium_permanganate"));


                HeatedMixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.POTASSIUM_HYDROXIDE), Ingredient.of(SubstanceCraftItems.CARBON_DIOXIDE)),
                                SubstanceCraftItems.POTASSIUM_CARBONATE,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.POTASSIUM_HYDROXIDE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.CARBON_DIOXIDE))
                        .save(recipeOutput, key("mix_potassium_carbonate"));

                HeatedMixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.COKE), Ingredient.of(SubstanceCraftItems.SILICA), Ingredient.of(SubstanceCraftItems.FLOUROAPATITE)),
                                SubstanceCraftItems.WHITE_PHOSPHORUS,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.COKE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.SILICA))
                        .unlockedBy("has_item", has(SubstanceCraftItems.FLOUROAPATITE))
                        .setOutputCount(2)
                        .save(recipeOutput, key("mix_white_phosphorus"));

                HeatedMixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.WHITE_PHOSPHORUS), Ingredient.of(SubstanceCraftItems.DISTILLED_WATER)),
                                SubstanceCraftItems.RED_PHOSPHORUS,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.WHITE_PHOSPHORUS))
                        .unlockedBy("has_item", has(SubstanceCraftItems.DISTILLED_WATER))
                        .save(recipeOutput, key("mix_red_phosphorus"));

                HeatedMixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.ETHANOL), Ingredient.of(SubstanceCraftItems.AMMONIA)),
                                SubstanceCraftItems.DIETHYLENE,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.ETHANOL))
                        .unlockedBy("has_item", has(SubstanceCraftItems.AMMONIA))
                        .save(recipeOutput, key("mix_diethylene"));

                HeatedMixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.DIETHYLENE), Ingredient.of(SubstanceCraftItems.D_LYSERGIC_ACID_HYDRATE), Ingredient.of(SubstanceCraftItems.MAGNESIUM_SULFATE), Ingredient.of(SubstanceCraftItems.PHOSPHORYL_CHLORIDE)),
                                SubstanceCraftItems.LYSERGIC_ACID_DIETHYLAMINE,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.DIETHYLENE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.D_LYSERGIC_ACID_HYDRATE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.MAGNESIUM_SULFATE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.PHOSPHORYL_CHLORIDE))
                        .setOutputCount(2)
                        .save(recipeOutput, key("mix_lysergic_acid_diethylamide"));

                HeatedMixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.PEYOTE_CACTUS)), Ingredient.of(SubstanceCraftItems.XYLENE), Ingredient.of(SubstanceCraftItems.SODIUM_HYDROXIDE), Ingredient.of(SubstanceCraftItems.SULFURIC_ACID)),
                                SubstanceCraftItems.MESCALINE,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.PEYOTE_CACTUS)))
                        .unlockedBy("has_item", has(SubstanceCraftItems.XYLENE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.SODIUM_HYDROXIDE))
                        .save(recipeOutput, key("mix_mescaline"));

                HeatedMixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.MIMOSA_HOSTILIS_ROOT_BARK), Ingredient.of(SubstanceCraftItems.PETROLEUM_NAPHTHA), Ingredient.of(SubstanceCraftItems.VINEGAR), Ingredient.of(SubstanceCraftItems.SODIUM_HYDROXIDE)),
                                SubstanceCraftItems.N_N_DIMETHYLTRYPTAMINE,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.MIMOSA_HOSTILIS_ROOT_BARK))
                        .unlockedBy("has_item", has(SubstanceCraftItems.PETROLEUM_NAPHTHA))
                        .unlockedBy("has_item", has(SubstanceCraftItems.VINEGAR))
                        .unlockedBy("has_item", has(SubstanceCraftItems.SODIUM_HYDROXIDE))
                        .setOutputCount(4)
                        .save(recipeOutput, key("mix_nn_dmt"));

                HeatedMixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.CHLORINE), Ingredient.of(SubstanceCraftItems.ALUMINUM)),
                                SubstanceCraftItems.ALUMINUM_CHLORIDE,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.ALUMINUM))
                        .unlockedBy("has_item", has(SubstanceCraftItems.CHLORINE))
                        .save(recipeOutput, key("mix_aluminum_chloride"));

                HeatedMixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.ETHYLENE), Ingredient.of(SubstanceCraftItems.BENZENE)),
                                SubstanceCraftItems.ETHYLBENZENE,
                                new ItemStackTemplate(SubstanceCraftItems.AMMONIUM_CHLORIDE),
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.ALUMINUM))
                        .unlockedBy("has_item", has(SubstanceCraftItems.BENZENE))
                        .save(recipeOutput, key("mix_ethylbenzene"));

                HeatedMixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.VANADINITE), Ingredient.of(SubstanceCraftItems.AMMONIUM_CHLORIDE), Ingredient.of(SubstanceCraftItems.SALT), Ingredient.of(SubstanceCraftItems.DISTILLED_WATER)),
                                SubstanceCraftItems.VANADIUM_PENTOXIDE,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.VANADINITE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.AMMONIUM_CHLORIDE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.SALT))
                        .unlockedBy("has_item", has(SubstanceCraftItems.DISTILLED_WATER))
                        .setOutputCount(2)
                        .save(recipeOutput, key("mix_vanadium_pentoxide"));

                HeatedMixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.CHROMITE), Ingredient.of(SubstanceCraftItems.DISTILLED_WATER), Ingredient.of(SubstanceCraftItems.SULFURIC_ACID), Ingredient.of(SubstanceCraftItems.SODIUM_CARBONATE)),
                                SubstanceCraftItems.CHROMIUM_III_OXIDE,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.CHROMITE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.SULFURIC_ACID))
                        .unlockedBy("has_item", has(SubstanceCraftItems.SODIUM_CARBONATE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.DISTILLED_WATER))
                        .setOutputCount(2)
                        .save(recipeOutput, key("mix_chromia"));

                HeatedMixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.SULFUR), Ingredient.of(SubstanceCraftItems.DIMETHYL_ETHER)),
                                SubstanceCraftItems.DIMETHYL_SULFATE,
                                800
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.SULFUR))
                        .unlockedBy("has_item", has(SubstanceCraftItems.DIMETHYL_ETHER))
                        .save(recipeOutput, key("mix_dimethyl_sulfate"));

                FermentationTankRecipeBuilder.ferment(
                                List.of(Ingredient.of(SubstanceCraftItems.YEAST), Ingredient.of(SubstanceCraftItems.CORN)),
                                SubstanceCraftItems.ETHANOL,
                                2000
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.YEAST))
                        .unlockedBy("has_item", has(SubstanceCraftItems.CORN))
                        .save(recipeOutput, key("ferment_ethanol"));

                FermentationTankRecipeBuilder.ferment(
                                List.of(Ingredient.of(SubstanceCraftItems.YEAST), Ingredient.of(Items.SUGAR)),
                                SubstanceCraftItems.YEAST,
                                2000
                        )
                        .setOutputCount(2)
                        .unlockedBy("has_item", has(SubstanceCraftItems.YEAST))
                        .unlockedBy("has_item", has(Items.SUGAR))
                        .save(recipeOutput, key("ferment_yeast"));

                FermentationTankRecipeBuilder.ferment(
                                List.of(Ingredient.of(SubstanceCraftItems.ERGOT), Ingredient.of(Items.SUGAR)),
                                SubstanceCraftItems.ERGOT,
                                2000
                        )
                        .setOutputCount(2)
                        .unlockedBy("has_item", has(SubstanceCraftItems.ERGOT))
                        .unlockedBy("has_item", has(Items.SUGAR))
                        .save(recipeOutput, key("ferment_ergot"));


                FermentationTankRecipeBuilder.ferment(
                                List.of(Ingredient.of(SubstanceCraftItems.GRAPES), Ingredient.of(SubstanceCraftItems.YEAST)),
                                SubstanceCraftItems.RED_WINE,
                                List.of(new ItemStackTemplate(SubstanceCraftItems.WINE_LEES, 50 >> 2)),
                                2000
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.GRAPES))
                        .unlockedBy("has_item", has(SubstanceCraftItems.YEAST))
                        .save(recipeOutput, key("ferment_wine"));

            }
        };
    }

    private static ResourceKey<Recipe<?>> key(String id) {
        return ResourceKey.create(Registries.RECIPE, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, id));
    }

    @Override
    public @NotNull String getName() {
        return "RecipeGenerator";
    }
}