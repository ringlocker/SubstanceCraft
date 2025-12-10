package com.github.ringlocker.substancecraft.datagen.recipebuilder;

import com.github.ringlocker.substancecraft.recipe.recipes.ByproductRecipe;
import com.github.ringlocker.substancecraft.recipe.recipes.HeatedMixerRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.List;

public class HeatedMixerRecipeBuilder extends ByproductRecipeBuilder {

    public HeatedMixerRecipeBuilder(List<Ingredient> ingredients, ItemLike result, List<ItemStack> byproducts, int time, ByproductRecipe.Factory<? extends ByproductRecipe> factory) {
        super(ingredients, result, byproducts, time, factory);
    }

    public static HeatedMixerRecipeBuilder mix(List<Ingredient> ingredients, ItemLike result, List<ItemStack> byproducts, int time, ByproductRecipe.Factory<HeatedMixerRecipe> factory) {
        return new HeatedMixerRecipeBuilder(ingredients, result, byproducts, time, factory);
    }

    public static HeatedMixerRecipeBuilder mix(List<Ingredient> ingredients, ItemLike result, int time, ByproductRecipe.Factory<HeatedMixerRecipe> factory) {
        return new HeatedMixerRecipeBuilder(ingredients, result, null, time, factory);
    }

}
