package com.github.ringlocker.substancecraft.recipe;

import com.github.ringlocker.substancecraft.SubstanceCraft;
import com.github.ringlocker.substancecraft.recipe.recipes.ElectrolysisRecipe;
import com.github.ringlocker.substancecraft.recipe.recipes.ExtractorRecipe;
import com.github.ringlocker.substancecraft.recipe.recipes.FermentationTankRecipe;
import com.github.ringlocker.substancecraft.recipe.recipes.HashPressRecipe;
import com.github.ringlocker.substancecraft.recipe.recipes.HeatedMixerRecipe;
import com.github.ringlocker.substancecraft.recipe.recipes.MixerRecipe;
import com.github.ringlocker.substancecraft.recipe.recipes.OxidizerRecipe;
import com.github.ringlocker.substancecraft.recipe.recipes.RefineryRecipe;
import net.fabricmc.fabric.api.recipe.v1.sync.RecipeSynchronization;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.RecipeType;
import java.util.ArrayList;

public class SubstanceCraftRecipes {

    public static final ArrayList<RecipeType<?>> types = new ArrayList<>();

    public static void registerRecipes() {
        types.add(Registry.register(BuiltInRegistries.RECIPE_TYPE, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, HashPressRecipe.ID), HashPressRecipe.Type.INSTANCE));
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID,  HashPressRecipe.ID), HashPressRecipe.Serializer.INSTANCE);

        types.add(Registry.register(BuiltInRegistries.RECIPE_TYPE, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, RefineryRecipe.ID), RefineryRecipe.Type.INSTANCE));
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, RefineryRecipe.ID), RefineryRecipe.Serializer.INSTANCE);

        types.add(Registry.register(BuiltInRegistries.RECIPE_TYPE, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, ElectrolysisRecipe.ID), ElectrolysisRecipe.Type.INSTANCE));
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID,  ElectrolysisRecipe.ID), ElectrolysisRecipe.Serializer.INSTANCE);

        types.add(Registry.register(BuiltInRegistries.RECIPE_TYPE, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, OxidizerRecipe.ID), OxidizerRecipe.Type.INSTANCE));
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID,  OxidizerRecipe.ID), OxidizerRecipe.Serializer.INSTANCE);

        types.add(Registry.register(BuiltInRegistries.RECIPE_TYPE, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, ExtractorRecipe.ID), ExtractorRecipe.Type.INSTANCE));
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID,  ExtractorRecipe.ID), ExtractorRecipe.Serializer.INSTANCE);

        types.add(Registry.register(BuiltInRegistries.RECIPE_TYPE, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, MixerRecipe.ID), MixerRecipe.Type.INSTANCE));
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID,  MixerRecipe.ID), MixerRecipe.Serializer.INSTANCE);

        types.add(Registry.register(BuiltInRegistries.RECIPE_TYPE, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, HeatedMixerRecipe.ID), HeatedMixerRecipe.Type.INSTANCE));
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID,  HeatedMixerRecipe.ID), HeatedMixerRecipe.Serializer.INSTANCE);

        types.add(Registry.register(BuiltInRegistries.RECIPE_TYPE, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, FermentationTankRecipe.ID), FermentationTankRecipe.Type.INSTANCE));
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID,  FermentationTankRecipe.ID), FermentationTankRecipe.Serializer.INSTANCE);

        synchronizeRecipes();
    }

    public static void synchronizeRecipes() {
        RecipeSynchronization.synchronizeRecipeSerializer(HashPressRecipe.Serializer.INSTANCE);
        RecipeSynchronization.synchronizeRecipeSerializer(RefineryRecipe.Serializer.INSTANCE);
        RecipeSynchronization.synchronizeRecipeSerializer(ElectrolysisRecipe.Serializer.INSTANCE);
        RecipeSynchronization.synchronizeRecipeSerializer(OxidizerRecipe.Serializer.INSTANCE);
        RecipeSynchronization.synchronizeRecipeSerializer(ExtractorRecipe.Serializer.INSTANCE);
        RecipeSynchronization.synchronizeRecipeSerializer(MixerRecipe.Serializer.INSTANCE);
        RecipeSynchronization.synchronizeRecipeSerializer(HeatedMixerRecipe.Serializer.INSTANCE);
        RecipeSynchronization.synchronizeRecipeSerializer(FermentationTankRecipe.Serializer.INSTANCE);
    }

}
