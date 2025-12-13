package com.github.ringlocker.substancecraft.datagen.recipebuilder;

import com.github.ringlocker.substancecraft.recipe.recipes.FermentationTankRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.List;

public class FermentationTankRecipeBuilder extends ByproductRecipeBuilder {

    protected FermentationTankRecipeBuilder(List<Ingredient> ingredients, ItemLike result, List<ItemStack> byproducts, int time) {
        super(ingredients, result, byproducts, time, FermentationTankRecipe::new);
    }

    public static FermentationTankRecipeBuilder ferment(List<Ingredient> ingredients, ItemLike result, List<ItemStack> byproducts, int time) {
        return new FermentationTankRecipeBuilder(ingredients, result, byproducts, time);
    }

    public static FermentationTankRecipeBuilder ferment(List<Ingredient> ingredients, ItemLike result, int time) {
        return new FermentationTankRecipeBuilder(ingredients, result, null, time);
    }

}
