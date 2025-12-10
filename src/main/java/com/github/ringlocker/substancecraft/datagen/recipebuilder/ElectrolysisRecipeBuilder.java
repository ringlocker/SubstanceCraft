package com.github.ringlocker.substancecraft.datagen.recipebuilder;

import com.github.ringlocker.substancecraft.recipe.recipes.ByproductRecipe;
import com.github.ringlocker.substancecraft.recipe.recipes.ElectrolysisRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.List;

public class ElectrolysisRecipeBuilder extends ByproductRecipeBuilder {

    protected ElectrolysisRecipeBuilder(ItemLike result, List<Ingredient> ingredients, List<ItemStack> byproducts, int time, ByproductRecipe.Factory<? extends ByproductRecipe> factory) {
        super(ingredients, result, byproducts, time, factory);
    }

    public static ElectrolysisRecipeBuilder electrolysis(List<Ingredient> ingredients, ItemLike result, List<ItemStack> byproducts, int time, ByproductRecipe.Factory<ElectrolysisRecipe> factory) {
        return new ElectrolysisRecipeBuilder(result, ingredients, byproducts, time, factory);
    }

    public static ElectrolysisRecipeBuilder electrolysis(List<Ingredient> ingredients, ItemLike result, int time, ByproductRecipe.Factory<ElectrolysisRecipe> factory) {
        return new ElectrolysisRecipeBuilder(result, ingredients, null, time, factory);
    }

}
