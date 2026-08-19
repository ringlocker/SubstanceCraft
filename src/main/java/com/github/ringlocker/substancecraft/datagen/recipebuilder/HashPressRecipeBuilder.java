package com.github.ringlocker.substancecraft.datagen.recipebuilder;

import com.github.ringlocker.substancecraft.recipe.recipes.HashPressRecipe;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.Optional;

public class HashPressRecipeBuilder extends ByproductRecipeBuilder {

    private HashPressRecipeBuilder(final ItemLike result, final List<Ingredient> ingredients, List<ItemStackTemplate> byproducts, Optional<ItemStackTemplate> catalyst, int time) {
        super(ingredients, result, byproducts, catalyst, time, HashPressRecipe::new);
    }

    public static HashPressRecipeBuilder press(List<Ingredient> ingredients, ItemLike result, int time) {
        return new HashPressRecipeBuilder(result, ingredients, null, Optional.empty(), time);
    }

}