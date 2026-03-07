package com.github.ringlocker.substancecraft.datagen.recipebuilder;

import com.github.ringlocker.substancecraft.recipe.recipes.HeatedMixerRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.List;

public class HeatedMixerRecipeBuilder extends ByproductRecipeBuilder {

    public HeatedMixerRecipeBuilder(List<Ingredient> ingredients, ItemLike result, List<ItemStack> byproducts, int time) {
        super(ingredients, result, byproducts, time, HeatedMixerRecipe::new);
    }

    public static HeatedMixerRecipeBuilder mix(List<Ingredient> ingredients, ItemLike result, List<ItemStack> byproducts, int time) {
        return new HeatedMixerRecipeBuilder(ingredients, result, byproducts, time);
    }

    public static HeatedMixerRecipeBuilder mix(List<Ingredient> ingredients, ItemLike result, int time) {
        return new HeatedMixerRecipeBuilder(ingredients, result, null, time);
    }

}
