package com.github.ringlocker.substancecraft.datagen.recipebuilder;

import com.github.ringlocker.substancecraft.recipe.recipes.HeatedMixerRecipe;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.Optional;

public class HeatedMixerRecipeBuilder extends ByproductRecipeBuilder {

    public HeatedMixerRecipeBuilder(List<Ingredient> ingredients, ItemLike result, List<ItemStackTemplate> byproducts, Optional<ItemStackTemplate> catalyst, int time) {
        super(ingredients, result, byproducts, catalyst, time, HeatedMixerRecipe::new);
    }

    public static HeatedMixerRecipeBuilder mix(List<Ingredient> ingredients, ItemLike result, List<ItemStackTemplate> byproducts, int time) {
        return new HeatedMixerRecipeBuilder(ingredients, result, byproducts, Optional.empty(), time);
    }

    public static HeatedMixerRecipeBuilder mix(List<Ingredient> ingredients, ItemLike result, int time) {
        return new HeatedMixerRecipeBuilder(ingredients, result, null, Optional.empty(), time);
    }

    public static HeatedMixerRecipeBuilder mix(List<Ingredient> ingredients, ItemLike result, ItemStackTemplate catalyst, int time) {
        return new HeatedMixerRecipeBuilder(ingredients, result, null, Optional.empty(), time);
    }

}
