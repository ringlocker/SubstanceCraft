package com.github.ringlocker.substancecraft.datagen.recipebuilder;

import com.github.ringlocker.substancecraft.recipe.recipes.MixerRecipe;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.Optional;

public class MixerRecipeBuilder extends ByproductRecipeBuilder {

    protected MixerRecipeBuilder(List<Ingredient> ingredients, ItemLike result, List<ItemStackTemplate> byproducts, Optional<ItemStackTemplate> catalyst, int time) {
        super(ingredients, result, byproducts, catalyst, time, MixerRecipe::new);
    }

    public static MixerRecipeBuilder mix(List<Ingredient> ingredients, ItemLike result, List<ItemStackTemplate> byproducts, int time) {
        return new MixerRecipeBuilder(ingredients, result, byproducts, Optional.empty(), time);
    }

    public static MixerRecipeBuilder mix(List<Ingredient> ingredients, ItemLike result, int time) {
        return new MixerRecipeBuilder(ingredients, result, null, Optional.empty(), time);
    }

    public static MixerRecipeBuilder mix(List<Ingredient> ingredients, ItemLike result, ItemStackTemplate catalyst, int time) {
        return new MixerRecipeBuilder(ingredients, result, null, Optional.of(catalyst), time);
    }

}
