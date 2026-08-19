package com.github.ringlocker.substancecraft.datagen.recipebuilder;

import com.github.ringlocker.substancecraft.recipe.recipes.ExtractorRecipe;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.Optional;

public class ExtractorRecipeBuilder extends ByproductRecipeBuilder {

    protected ExtractorRecipeBuilder(List<Ingredient> ingredients, ItemLike result, List<ItemStackTemplate> byproducts, Optional<ItemStackTemplate> catalyst, int time) {
        super(ingredients, result, byproducts, catalyst, time, ExtractorRecipe::new);
    }

    public static ExtractorRecipeBuilder extract(List<Ingredient> ingredients, ItemLike result, List<ItemStackTemplate> byproducts, int time) {
        return new ExtractorRecipeBuilder(ingredients, result, byproducts, Optional.empty(), time);
    }

    public static ExtractorRecipeBuilder extract(List<Ingredient> ingredients, ItemLike result, int time) {
        return new ExtractorRecipeBuilder(ingredients, result, null, Optional.empty(), time);
    }

}
