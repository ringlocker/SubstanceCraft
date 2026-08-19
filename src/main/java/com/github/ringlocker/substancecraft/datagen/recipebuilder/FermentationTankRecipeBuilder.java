package com.github.ringlocker.substancecraft.datagen.recipebuilder;

import com.github.ringlocker.substancecraft.recipe.recipes.FermentationTankRecipe;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.Optional;

public class FermentationTankRecipeBuilder extends ByproductRecipeBuilder {

    protected FermentationTankRecipeBuilder(List<Ingredient> ingredients, ItemLike result, List<ItemStackTemplate> byproducts, Optional<ItemStackTemplate> catalyst, int time) {
        super(ingredients, result, byproducts, catalyst, time, FermentationTankRecipe::new);
    }

    public static FermentationTankRecipeBuilder ferment(List<Ingredient> ingredients, ItemLike result, List<ItemStackTemplate> byproducts, int time) {
        return new FermentationTankRecipeBuilder(ingredients, result, byproducts, Optional.empty(), time);
    }

    public static FermentationTankRecipeBuilder ferment(List<Ingredient> ingredients, ItemLike result, int time) {
        return new FermentationTankRecipeBuilder(ingredients, result, null, Optional.empty(), time);
    }

}
