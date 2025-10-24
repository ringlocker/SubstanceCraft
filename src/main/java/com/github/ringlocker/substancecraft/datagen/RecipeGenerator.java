package com.github.ringlocker.substancecraft.datagen;

import com.github.ringlocker.substancecraft.SubstanceCraft;
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
import com.github.ringlocker.substancecraft.recipe.recipes.ElectrolysisRecipe;
import com.github.ringlocker.substancecraft.recipe.recipes.ExtractorRecipe;
import com.github.ringlocker.substancecraft.recipe.recipes.FermentationTankRecipe;
import com.github.ringlocker.substancecraft.recipe.recipes.HashPressRecipe;
import com.github.ringlocker.substancecraft.recipe.recipes.HeatedMixerRecipe;
import com.github.ringlocker.substancecraft.recipe.recipes.MixerRecipe;
import com.github.ringlocker.substancecraft.recipe.recipes.OxidizerRecipe;
import com.github.ringlocker.substancecraft.recipe.recipes.RefineryRecipe;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class RecipeGenerator extends FabricRecipeProvider {

    private static final int SHORT_REFINE_TIME = 600;
    private static final int REFINE_TIME = 800;
    private static final int LONG_REFINE_TIME = 1000;

    public RecipeGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
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

                shaped(RecipeCategory.MISC, SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.REFINERY))
                        .pattern("333")
                        .pattern("212")
                        .pattern("323")
                        .define('1', Items.CAULDRON)
                        .define('2', Items.IRON_INGOT)
                        .define('3', Items.COPPER_BLOCK)
                        .unlockedBy("has_item", has(Items.IRON_INGOT))
                        .unlockedBy("has_item", has(Items.COPPER_BLOCK))
                        .save(recipeOutput, key("refinery"));

                shaped(RecipeCategory.MISC, SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.MIXER))
                        .pattern("121")
                        .pattern("343")
                        .pattern("131")
                        .define('1', Items.COPPER_BLOCK)
                        .define('2', Items.IRON_SHOVEL)
                        .define('3', Items.IRON_INGOT)
                        .define('4', Items.CAULDRON)
                        .unlockedBy("has_item", has(Items.IRON_INGOT))
                        .unlockedBy("has_item", has(Items.COPPER_BLOCK))
                        .save(recipeOutput, key("mixer"));

                shaped(RecipeCategory.MISC, SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.HEATED_MIXER))
                        .pattern("121")
                        .pattern("343")
                        .pattern("151")
                        .define('1', Items.COPPER_BLOCK)
                        .define('2', Items.IRON_SHOVEL)
                        .define('3', Items.IRON_INGOT)
                        .define('4', Items.CAULDRON)
                        .define('5', Items.LAVA_BUCKET)
                        .unlockedBy("has_item", has(Items.IRON_INGOT))
                        .unlockedBy("has_item", has(Items.COPPER_BLOCK))
                        .save(recipeOutput, key("heated_mixer"));

                shaped(RecipeCategory.MISC, SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.FERMENTATION_TANK))
                        .pattern("121")
                        .pattern("222")
                        .pattern("131")
                        .define('1', Items.COPPER_BLOCK)
                        .define('2', Items.IRON_INGOT)
                        .define('3', Items.CAULDRON)
                        .unlockedBy("has_item", has(Items.IRON_INGOT))
                        .unlockedBy("has_item", has(Items.COPPER_BLOCK))
                        .save(recipeOutput, key("fermentation_tank"));

                shaped(RecipeCategory.MISC, SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.OXIDATION_MACHINE))
                        .pattern("323")
                        .pattern("212")
                        .pattern("323")
                        .define('1', Items.CAULDRON)
                        .define('2', Items.IRON_INGOT)
                        .define('3', Items.COPPER_BLOCK)
                        .unlockedBy("has_item", has(Items.IRON_INGOT))
                        .unlockedBy("has_item", has(Items.COPPER_BLOCK))
                        .save(recipeOutput, key("oxidizer"));

                shaped(RecipeCategory.MISC, SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.EXTRACTOR))
                        .pattern("343")
                        .pattern("212")
                        .pattern("323")
                        .define('1', Items.CAULDRON)
                        .define('2', Items.IRON_INGOT)
                        .define('3', Items.COPPER_BLOCK)
                        .define('4', Items.HOPPER)
                        .unlockedBy("has_item", has(Items.IRON_INGOT))
                        .unlockedBy("has_item", has(Items.COPPER_BLOCK))
                        .save(recipeOutput, key("extractor"));

                shaped(RecipeCategory.MISC, SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.ELECTROLYSIS_MACHINE))
                        .pattern("121")
                        .pattern("343")
                        .pattern("151")
                        .define('1', Items.COPPER_BLOCK)
                        .define('2', Items.LEVER)
                        .define('3', Items.REDSTONE)
                        .define('4', Items.CAULDRON)
                        .define('5', Items.IRON_INGOT)
                        .unlockedBy("has_item", has(Items.IRON_INGOT))
                        .unlockedBy("has_item", has(Items.COPPER_BLOCK))
                        .save(recipeOutput, key("electrolysis_machine"));

                nineBlockStorageRecipesWithCustomPacking(
                        RecipeCategory.MISC,
                        SubstanceCraftItems.CASH,
                        RecipeCategory.MISC,
                        SubstanceCraftItems.BAND, "band", "cash"
                );

                HashPressRecipeBuilder.press(
                                Ingredient.of(SubstanceCraftItems.MARIJUANA),
                                SubstanceCraftItems.HASH,
                                1000,
                                HashPressRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.MARIJUANA))
                        .save(recipeOutput, key("press_hash"));

                RefineryRecipeBuilder.refine(
                                Ingredient.of(SubstanceCraftItems.OIL_SHALE),
                                SubstanceCraftItems.OIL,
                                List.of(new ItemStack(SubstanceCraftItems.NATURAL_GAS, 40 >> 1)),
                                SHORT_REFINE_TIME,
                                RefineryRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.OIL_SHALE)))
                        .save(recipeOutput, key("refine_oil"));

                RefineryRecipeBuilder.refine(
                                Ingredient.of(SubstanceCraftItems.OIL),
                                SubstanceCraftItems.PETROLEUM_NAPHTHA,
                                REFINE_TIME,
                                RefineryRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.OIL))
                        .save(recipeOutput, key("refine_naphtha"));

                RefineryRecipeBuilder.refine(
                                Ingredient.of(SubstanceCraftItems.OIL),
                                SubstanceCraftItems.KEROSENE,
                                REFINE_TIME,
                                RefineryRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.OIL))
                        .save(recipeOutput, key("refine_kerosene"));

                RefineryRecipeBuilder.refine(
                                Ingredient.of(SubstanceCraftItems.OIL),
                                SubstanceCraftItems.GASOLINE,
                                REFINE_TIME,
                                RefineryRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.OIL))
                        .save(recipeOutput, key("refine_gasoline"));

                RefineryRecipeBuilder.refine(
                                Ingredient.of(SubstanceCraftItems.OIL),
                                SubstanceCraftItems.DIPHENHYDRAMINE,
                                LONG_REFINE_TIME,
                                RefineryRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.OIL))
                        .save(recipeOutput, key("refine_dph"));

                RefineryRecipeBuilder.refine(
                                Ingredient.of(SubstanceCraftItems.OIL),
                                SubstanceCraftItems.KETAMINE,
                                LONG_REFINE_TIME,
                                RefineryRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.OIL))
                        .save(recipeOutput, key("refine_ketamine"));

                RefineryRecipeBuilder.refine(
                                Ingredient.of(SubstanceCraftItems.NATURAL_GAS),
                                SubstanceCraftItems.METHANOL,
                                REFINE_TIME,
                                RefineryRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.NATURAL_GAS))
                        .save(recipeOutput, key("refine_methanol"));

                RefineryRecipeBuilder.refine(
                                Ingredient.of(SubstanceCraftItems.NATURAL_GAS),
                                SubstanceCraftItems.ETHANE,
                                REFINE_TIME,
                                RefineryRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.NATURAL_GAS))
                        .save(recipeOutput, key("refine_ethane"));

                RefineryRecipeBuilder.refine(
                                Ingredient.of(SubstanceCraftItems.NATURAL_GAS),
                                SubstanceCraftItems.BUTANE,
                                REFINE_TIME,
                                RefineryRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.NATURAL_GAS))
                        .save(recipeOutput, key("refine_butane"));

                RefineryRecipeBuilder.refine(
                                Ingredient.of(SubstanceCraftItems.NATURAL_GAS),
                                SubstanceCraftItems.PROPANE,
                                REFINE_TIME,
                                RefineryRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.NATURAL_GAS))
                        .save(recipeOutput, key("refine_propane"));

                RefineryRecipeBuilder.refine(
                                Ingredient.of(SubstanceCraftItems.PROPANE),
                                SubstanceCraftItems.PROPYLENE,
                                LONG_REFINE_TIME,
                                RefineryRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.PROPANE))
                        .save(recipeOutput, key("refine_propylene"));

                RefineryRecipeBuilder.refine(
                                Ingredient.of(SubstanceCraftItems.ETHANE),
                                SubstanceCraftItems.ETHYLENE,
                                LONG_REFINE_TIME,
                                RefineryRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.ETHANE))
                        .save(recipeOutput, key("refine_ethylene"));

                RefineryRecipeBuilder.refine(
                                Ingredient.of(SubstanceCraftItems.OIL),
                                SubstanceCraftItems.DIESEL,
                                REFINE_TIME,
                                RefineryRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.ETHANE))
                        .save(recipeOutput, key("refine_diesel"));

                RefineryRecipeBuilder.refine(
                                Ingredient.of(Items.COAL),
                                SubstanceCraftItems.COKE,
                                List.of(new ItemStack(SubstanceCraftItems.CARBON_MONOXIDE, 50 >> 1)),
                                REFINE_TIME,
                                RefineryRecipe::new
                        )
                        .unlockedBy("has_item", has(Items.COAL))
                        .save(recipeOutput, key("refine_coke"));

                RefineryRecipeBuilder.refine(
                                Ingredient.of(SubstanceCraftItems.PETROLEUM_NAPHTHA),
                                SubstanceCraftItems.BENZENE,
                                REFINE_TIME,
                                RefineryRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.PETROLEUM_NAPHTHA))
                        .save(recipeOutput, key("reform_benzene"));

                RefineryRecipeBuilder.refine(
                                Ingredient.of(SubstanceCraftItems.PETROLEUM_NAPHTHA),
                                SubstanceCraftItems.TOLUENE,
                                REFINE_TIME,
                                RefineryRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.PETROLEUM_NAPHTHA))
                        .save(recipeOutput, key("reform_toluene"));

                OxidizerRecipeBuilder.oxidize(
                                Ingredient.of(SubstanceCraftItems.METHANOL),
                                SubstanceCraftItems.FORMALDEHYDE,
                                1000,
                                OxidizerRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.METHANOL))
                        .save(recipeOutput, key("oxidize_formaldehyde"));

                OxidizerRecipeBuilder.oxidize(
                                Ingredient.of(SubstanceCraftItems.AMMONIA),
                                SubstanceCraftItems.NITRIC_ACID,
                                1000,
                                OxidizerRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.METHANOL))
                        .save(recipeOutput, key("oxidize_nitric_acid"));

                OxidizerRecipeBuilder.oxidize(
                                Ingredient.of(SubstanceCraftItems.TOLUENE),
                                SubstanceCraftItems.BENZALDEHYDE,
                                1000,
                                OxidizerRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.TOLUENE))
                        .save(recipeOutput, key("oxidize_benzaldehyde"));

                OxidizerRecipeBuilder.oxidize(
                                Ingredient.of(SubstanceCraftItems.BENZENE),
                                SubstanceCraftItems.MALEIC_ANHYDRIDE,
                                1000,
                                OxidizerRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.BENZENE))
                        .save(recipeOutput, key("oxidize_maleic_anhydride"));

                OxidizerRecipeBuilder.oxidize(
                                Ingredient.of(SubstanceCraftItems.PROPYLENE),
                                SubstanceCraftItems.ACETONE,
                                1000,
                                OxidizerRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.PROPYLENE))
                        .save(recipeOutput, key("oxidize_acetone"));

                OxidizerRecipeBuilder.oxidize(
                                Ingredient.of(SubstanceCraftItems.BROMIDE),
                                SubstanceCraftItems.BROMINE,
                                1000,
                                OxidizerRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.BROMIDE))
                        .save(recipeOutput, key("oxidize_bromine"));

                ElectrolysisRecipeBuilder.electrolysis(
                                Ingredient.of(SubstanceCraftItems.BRINE),
                                SubstanceCraftItems.SODIUM_HYDROXIDE,
                                List.of(new ItemStack(SubstanceCraftItems.CHLORINE, 30 >> 1), new ItemStack(SubstanceCraftItems.HYDROGEN, 30 >> 1)),
                                1000,
                                ElectrolysisRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.BRINE))
                        .save(recipeOutput, key("electrolysis_brine"));

                ElectrolysisRecipeBuilder.electrolysis(
                                Ingredient.of(SubstanceCraftItems.DISTILLED_WATER),
                                SubstanceCraftItems.HYDROGEN,
                                List.of(new ItemStack(SubstanceCraftItems.OXYGEN, 50 >> 1)),
                                1000,
                                ElectrolysisRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.DISTILLED_WATER))
                        .save(recipeOutput, key("electrolysis_water"));

                ElectrolysisRecipeBuilder.electrolysis(
                                Ingredient.of(SubstanceCraftItems.POTASSIUM_CHLORIDE),
                                SubstanceCraftItems.POTASSIUM_HYDROXIDE,
                                1000,
                                ElectrolysisRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.DISTILLED_WATER))
                        .save(recipeOutput, key("electrolysis_potassium_hydroxide"));

                ExtractorRecipeBuilder.extract(
                                Ingredient.of(Items.GLASS_BOTTLE),
                                SubstanceCraftItems.NITROGEN,
                                600,
                                ExtractorRecipe::new
                        )
                        .unlockedBy("has_item", has(Items.GLASS_BOTTLE))
                        .save(recipeOutput, key("extract_nitrogen"));

                ExtractorRecipeBuilder.extract(
                                Ingredient.of(SubstanceCraftItems.HALITE),
                                SubstanceCraftItems.SALT,
                                600,
                                ExtractorRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.HALITE))
                        .save(recipeOutput, key("extract_salt"));

                ExtractorRecipeBuilder.extract(
                                Ingredient.of(Items.GLASS_BOTTLE),
                                SubstanceCraftItems.OXYGEN,
                                1200,
                                ExtractorRecipe::new
                        )
                        .unlockedBy("has_item", has(Items.GLASS_BOTTLE))
                        .save(recipeOutput, key("extract_oxygen"));

                ExtractorRecipeBuilder.extract(
                                Ingredient.of(SubstanceCraftItems.BRINE),
                                SubstanceCraftItems.BROMIDE,
                                1200,
                                ExtractorRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.BRINE))
                        .save(recipeOutput, key("extract_bromide"));

                ExtractorRecipeBuilder.extract(
                                Ingredient.of(Items.POTION),
                                SubstanceCraftItems.DISTILLED_WATER,
                                800,
                                ExtractorRecipe::new
                        )
                        .unlockedBy("has_item", has(Items.POTION))
                        .save(recipeOutput, key("extract_distilled_water"));

                ExtractorRecipeBuilder.extract(
                                Ingredient.of(SubstanceCraftItems.P2NP),
                                SubstanceCraftItems.P2P,
                                1200,
                                ExtractorRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.P2NP))
                        .save(recipeOutput, key("extract_p2p"));

                ExtractorRecipeBuilder.extract(
                                Ingredient.of(SubstanceCraftItems.TRONA),
                                SubstanceCraftItems.SODIUM_CARBONATE,
                                1200,
                                ExtractorRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.TRONA))
                        .save(recipeOutput, key("extract_sodium_carbonate"));

                ExtractorRecipeBuilder.extract(
                                Ingredient.of(SubstanceCraftItems.PYROLUSITE),
                                SubstanceCraftItems.MANGANESE_DIOXIDE,
                                1200,
                                ExtractorRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.PYROLUSITE))
                        .save(recipeOutput, key("extract_pyrolusite"));

                ExtractorRecipeBuilder.extract(
                                Ingredient.of(SubstanceCraftItems.SYLVITE),
                                SubstanceCraftItems.POTASSIUM_CHLORIDE,
                                1200,
                                ExtractorRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.SYLVITE))
                        .save(recipeOutput, key("extract_potassium_chloride"));

                ExtractorRecipeBuilder.extract(
                                Ingredient.of(Items.CHARCOAL),
                                SubstanceCraftItems.CARBON_DIOXIDE,
                                1200,
                                ExtractorRecipe::new
                        )
                        .unlockedBy("has_item", has(Items.CHARCOAL))
                        .save(recipeOutput, key("extract_carbon_dioxide"));

                MixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.SALT), Ingredient.of(Items.POTION)),
                                SubstanceCraftItems.BRINE,
                                800,
                                MixerRecipe::new
                        )
                        .unlockedBy("has_item", has(Items.POTION))
                        .unlockedBy("has_item", has(SubstanceCraftItems.SALT))
                        .save(recipeOutput, key("mix_brine"));

                MixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.ACETIC_ACID), Ingredient.of(SubstanceCraftItems.AMMONIA)),
                                SubstanceCraftItems.AMMONIUM_ACETATE,
                                800,
                                MixerRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.ACETIC_ACID))
                        .unlockedBy("has_item", has(SubstanceCraftItems.AMMONIA))
                        .save(recipeOutput, key("mix_ammonia_acetate"));


                MixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.HYDROGEN), Ingredient.of(SubstanceCraftItems.MALEIC_ANHYDRIDE)),
                                SubstanceCraftItems.TETRAHYDROFURAN,
                                800,
                                MixerRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.HYDROGEN))
                        .unlockedBy("has_item", has(SubstanceCraftItems.MALEIC_ANHYDRIDE))
                        .save(recipeOutput, key("mix_tetrahydrofuran"));


                MixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.BETA_NITROSTYRENE), Ingredient.of(SubstanceCraftItems.TETRAHYDROFURAN), Ingredient.of(SubstanceCraftItems.SODIUM_HYDROXIDE), Ingredient.of(SubstanceCraftItems.HYDROCHLORIC_ACID)),
                                SubstanceCraftItems.TWO_C_H,
                                800,
                                MixerRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.BETA_NITROSTYRENE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.TETRAHYDROFURAN))
                        .save(recipeOutput, key("mix_2c_h"));


                MixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.TWO_C_H), Ingredient.of(SubstanceCraftItems.BROMINE), Ingredient.of(SubstanceCraftItems.SODIUM_HYDROXIDE), Ingredient.of(SubstanceCraftItems.HYDROCHLORIC_ACID)),
                                SubstanceCraftItems.TWO_C_B,
                                800,
                                MixerRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.TWO_C_H))
                        .unlockedBy("has_item", has(SubstanceCraftItems.BROMINE))
                        .save(recipeOutput, key("mix_2c_b"));

                MixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.METHYL_FORMATE), Ingredient.of(SubstanceCraftItems.DISTILLED_WATER)),
                                SubstanceCraftItems.FORMIC_ACID,
                                800,
                                MixerRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.METHYL_FORMATE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.DISTILLED_WATER))
                        .save(recipeOutput, key("mix_formic_acid"));

                MixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.SODIUM_CARBONATE), Ingredient.of(SubstanceCraftItems.DISTILLED_WATER)),
                                SubstanceCraftItems.SODIUM_CARBONATE_SOLUTION,
                                800,
                                MixerRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.SODIUM_CARBONATE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.DISTILLED_WATER))
                        .save(recipeOutput, key("mix_sodium_carbonate_solution"));

                MixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.SODIUM_CARBONATE_SOLUTION), Ingredient.of(SubstanceCraftItems.KEROSENE), Ingredient.of(SubstanceCraftItems.COCA_LEAVES), Ingredient.of(SubstanceCraftItems.SULFURIC_ACID)),
                                SubstanceCraftItems.AGUA_RICA,
                                800,
                                MixerRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.SODIUM_CARBONATE_SOLUTION))
                        .unlockedBy("has_item", has(SubstanceCraftItems.KEROSENE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.COCA_LEAVES))
                        .unlockedBy("has_item", has(SubstanceCraftItems.SULFURIC_ACID))
                        .save(recipeOutput, key("mix_agua_rica"));

                MixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.AGUA_RICA), Ingredient.of(SubstanceCraftItems.AMMONIA_SOLUTION), Ingredient.of(SubstanceCraftItems.POTASSIUM_PERMANGANATE)),
                                SubstanceCraftItems.COCA_PASTE,
                                800,
                                MixerRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.AGUA_RICA))
                        .unlockedBy("has_item", has(SubstanceCraftItems.AMMONIA_SOLUTION))
                        .unlockedBy("has_item", has(SubstanceCraftItems.POTASSIUM_PERMANGANATE))
                        .save(recipeOutput, key("mix_coca_paste"));

                MixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.AMMONIA), Ingredient.of(SubstanceCraftItems.DISTILLED_WATER)),
                                SubstanceCraftItems.AMMONIA_SOLUTION,
                                800,
                                MixerRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.AMMONIA))
                        .unlockedBy("has_item", has(SubstanceCraftItems.DISTILLED_WATER))
                        .save(recipeOutput, key("mix_ammonia_solution"));


                MixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.ACETONE), Ingredient.of(SubstanceCraftItems.POTASSIUM_CARBONATE), Ingredient.of(SubstanceCraftItems.COCA_PASTE), Ingredient.of(SubstanceCraftItems.HYDROCHLORIC_ACID)),
                                SubstanceCraftItems.COCAINE,
                                1200,
                                MixerRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.ACETONE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.POTASSIUM_CARBONATE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.COCA_PASTE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.HYDROCHLORIC_ACID))
                        .save(recipeOutput, key("mix_cocaine"));


                HeatedMixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.AMMONIA), Ingredient.of(SubstanceCraftItems.METHANOL)),
                                SubstanceCraftItems.METHYLAMINE,
                                800,
                                HeatedMixerRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.AMMONIA))
                        .unlockedBy("has_item", has(SubstanceCraftItems.METHANOL))
                        .save(recipeOutput, key("mix_methylamine"));

                HeatedMixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.CHLORINE), Ingredient.of(SubstanceCraftItems.METHANE)),
                                SubstanceCraftItems.CHLOROFORM,
                                800,
                                HeatedMixerRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.CHLORINE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.METHANE))
                        .save(recipeOutput, key("mix_chloroform"));

                HeatedMixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.CHLORINE), Ingredient.of(SubstanceCraftItems.HYDROGEN), Ingredient.of(SubstanceCraftItems.DISTILLED_WATER)),
                                SubstanceCraftItems.HYDROCHLORIC_ACID,
                                800,
                                HeatedMixerRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.CHLORINE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.HYDROCHLORIC_ACID))
                        .unlockedBy("has_item", has(SubstanceCraftItems.DISTILLED_WATER))
                        .save(recipeOutput, key("mix_hcl"));

                HeatedMixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.NITROGEN), Ingredient.of(SubstanceCraftItems.HYDROGEN)),
                                SubstanceCraftItems.AMMONIA,
                                800,
                                HeatedMixerRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.CHLORINE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.METHANE))
                        .save(recipeOutput, key("mix_ammonia"));

                HeatedMixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.PROPANE), Ingredient.of(SubstanceCraftItems.NITRIC_ACID)),
                                SubstanceCraftItems.NITROMETHANE,
                                800,
                                HeatedMixerRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.PROPANE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.NITRIC_ACID))
                        .save(recipeOutput, key("mix_nitromethane"));

                HeatedMixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.METHANOL), Ingredient.of(SubstanceCraftItems.CARBON_MONOXIDE)),
                                SubstanceCraftItems.ACETIC_ACID,
                                800,
                                HeatedMixerRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.PROPANE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.NITRIC_ACID))
                        .save(recipeOutput, key("mix_acetic_acid"));

                HeatedMixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.BENZALDEHYDE), Ingredient.of(SubstanceCraftItems.NITROMETHANE), Ingredient.of(SubstanceCraftItems.AMMONIUM_ACETATE)),
                                SubstanceCraftItems.BETA_NITROSTYRENE,
                                800,
                                HeatedMixerRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.PROPANE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.NITRIC_ACID))
                        .save(recipeOutput, key("mix_beta_nitrostyrene"));


                HeatedMixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.CARBON_MONOXIDE), Ingredient.of(SubstanceCraftItems.METHANOL)),
                                SubstanceCraftItems.METHYL_FORMATE,
                                800,
                                HeatedMixerRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.CARBON_MONOXIDE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.METHANOL))
                        .save(recipeOutput, key("mix_methyl_formate"));

                HeatedMixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.BENZALDEHYDE), Ingredient.of(SubstanceCraftItems.NITROETHANE)),
                                SubstanceCraftItems.P2NP,
                                800,
                                HeatedMixerRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.BENZALDEHYDE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.NITROETHANE))
                        .save(recipeOutput, key("mix_p2np"));


                HeatedMixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.NITRIC_ACID), Ingredient.of(SubstanceCraftItems.ETHANE)),
                                SubstanceCraftItems.NITROETHANE,
                                800,
                                HeatedMixerRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.NITRIC_ACID))
                        .unlockedBy("has_item", has(SubstanceCraftItems.ETHANE))
                        .save(recipeOutput, key("mix_nitroethane"));


                HeatedMixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.P2P), Ingredient.of(SubstanceCraftItems.FORMIC_ACID),  Ingredient.of(SubstanceCraftItems.AMMONIA)),
                                SubstanceCraftItems.AMPHETAMINE,
                                800,
                                HeatedMixerRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.P2P))
                        .unlockedBy("has_item", has(SubstanceCraftItems.FORMIC_ACID))
                        .unlockedBy("has_item", has(SubstanceCraftItems.AMMONIA))
                        .save(recipeOutput, key("mix_amphetamine"));

                HeatedMixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.SULFUR), Ingredient.of(SubstanceCraftItems.DISTILLED_WATER),  Ingredient.of(SubstanceCraftItems.OXYGEN)),
                                SubstanceCraftItems.SULFURIC_ACID,
                                800,
                                HeatedMixerRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.SULFUR))
                        .unlockedBy("has_item", has(SubstanceCraftItems.DISTILLED_WATER))
                        .unlockedBy("has_item", has(SubstanceCraftItems.OXYGEN))
                        .save(recipeOutput, key("mix_sulfuric_acid"));


                HeatedMixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.OXYGEN), Ingredient.of(SubstanceCraftItems.MANGANESE_DIOXIDE),  Ingredient.of(SubstanceCraftItems.POTASSIUM_HYDROXIDE)),
                                SubstanceCraftItems.POTASSIUM_PERMANGANATE,
                                800,
                                HeatedMixerRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.OXYGEN))
                        .unlockedBy("has_item", has(SubstanceCraftItems.MANGANESE_DIOXIDE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.POTASSIUM_HYDROXIDE))
                        .save(recipeOutput, key("mix_potassium_permanganate"));


                HeatedMixerRecipeBuilder.mix(
                                List.of(Ingredient.of(SubstanceCraftItems.POTASSIUM_HYDROXIDE), Ingredient.of(SubstanceCraftItems.CARBON_DIOXIDE)),
                                SubstanceCraftItems.POTASSIUM_CARBONATE,
                                800,
                                HeatedMixerRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.POTASSIUM_HYDROXIDE))
                        .unlockedBy("has_item", has(SubstanceCraftItems.CARBON_DIOXIDE))
                        .save(recipeOutput, key("mix_potassium_carbonate"));


                FermentationTankRecipeBuilder.ferment(
                                List.of(Ingredient.of(SubstanceCraftItems.YEAST), Ingredient.of(SubstanceCraftItems.CORN)),
                                SubstanceCraftItems.ETHANOL,
                                2000,
                                FermentationTankRecipe::new
                        )
                        .unlockedBy("has_item", has(SubstanceCraftItems.YEAST))
                        .unlockedBy("has_item", has(SubstanceCraftItems.CORN))
                        .save(recipeOutput, key("ferment_ethanol"));

                FermentationTankRecipeBuilder.ferment(
                                List.of(Ingredient.of(SubstanceCraftItems.YEAST), Ingredient.of(Items.SUGAR)),
                                SubstanceCraftItems.YEAST,
                                2000,
                                FermentationTankRecipe::new
                        )
                        .setOutputCount(2)
                        .unlockedBy("has_item", has(SubstanceCraftItems.YEAST))
                        .unlockedBy("has_item", has(Items.SUGAR))
                        .save(recipeOutput, key("ferment_yeast"));

                FermentationTankRecipeBuilder.ferment(
                                List.of(Ingredient.of(SubstanceCraftItems.ERGOT), Ingredient.of(Items.SUGAR)),
                                SubstanceCraftItems.ERGOT,
                                2000,
                                FermentationTankRecipe::new
                        )
                        .setOutputCount(2)
                        .unlockedBy("has_item", has(SubstanceCraftItems.ERGOT))
                        .unlockedBy("has_item", has(Items.SUGAR))
                        .save(recipeOutput, key("ferment_ergot"));
            }
        };
    }

    private static ResourceKey<Recipe<?>> key(String id) {
        return ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, id));
    }

    @Override
    public @NotNull String getName() {
        return "RecipeGenerator";
    }
}