package com.github.ringlocker.substancecraft.datagen.recipebuilder;

import com.github.ringlocker.substancecraft.recipe.recipes.HashPressRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.List;

public class HashPressRecipeBuilder extends ByproductRecipeBuilder {

    private HashPressRecipeBuilder(final ItemLike result, final List<Ingredient> ingredients, List<ItemStack> byproducts, int time) {
        super(ingredients, result, byproducts, time, HashPressRecipe::new);
    }

    public static HashPressRecipeBuilder press(List<Ingredient> ingredients, ItemLike result, List<ItemStack> byproducts, int time) {
        return new HashPressRecipeBuilder(result, ingredients, byproducts, time);
    }

    public static HashPressRecipeBuilder press(List<Ingredient> ingredients, ItemLike result, int time) {
        return new HashPressRecipeBuilder(result, ingredients, null, time);
    }

}