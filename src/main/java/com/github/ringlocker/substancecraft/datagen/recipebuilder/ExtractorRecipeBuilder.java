package com.github.ringlocker.substancecraft.datagen.recipebuilder;

import com.github.ringlocker.substancecraft.recipe.recipes.ExtractorRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.List;

public class ExtractorRecipeBuilder extends ByproductRecipeBuilder {

    protected ExtractorRecipeBuilder(List<Ingredient> ingredients, ItemLike result, List<ItemStack> byproducts, int time) {
        super(ingredients, result, byproducts, time, ExtractorRecipe::new);
    }

    public static ExtractorRecipeBuilder extract(List<Ingredient> ingredients, ItemLike result, List<ItemStack> byproducts, int time) {
        return new ExtractorRecipeBuilder(ingredients, result, byproducts, time);
    }

    public static ExtractorRecipeBuilder extract(List<Ingredient> ingredients, ItemLike result, int time) {
        return new ExtractorRecipeBuilder(ingredients, result, null, time);
    }

}
