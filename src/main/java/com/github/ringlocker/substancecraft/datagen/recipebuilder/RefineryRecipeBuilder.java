package com.github.ringlocker.substancecraft.datagen.recipebuilder;

import com.github.ringlocker.substancecraft.recipe.recipes.ByproductRecipe;
import com.github.ringlocker.substancecraft.recipe.recipes.RefineryRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.List;

public class RefineryRecipeBuilder extends ByproductRecipeBuilder {

    private RefineryRecipeBuilder(final ItemLike result, final List<Ingredient> ingredients, List<ItemStack> byproducts, int time, ByproductRecipe.Factory<RefineryRecipe> factory) {
        super(ingredients, result, byproducts, time, factory);
    }

    public static RefineryRecipeBuilder refine(List<Ingredient> ingredients, ItemLike result, List<ItemStack> byproducts, int time, ByproductRecipe.Factory<RefineryRecipe> factory) {
        return new RefineryRecipeBuilder(result, ingredients, byproducts, time, factory);
    }

    public static RefineryRecipeBuilder refine(List<Ingredient> ingredients, ItemLike result, int time, ByproductRecipe.Factory<RefineryRecipe> factory) {
        return new RefineryRecipeBuilder(result, ingredients, null, time, factory);
    }

}