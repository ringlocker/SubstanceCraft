package com.github.ringlocker.substancecraft.datagen.recipebuilder;

import com.github.ringlocker.substancecraft.recipe.recipes.MixerRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.List;

public class MixerRecipeBuilder extends ByproductRecipeBuilder {

    protected MixerRecipeBuilder(List<Ingredient> ingredients, ItemLike result, List<ItemStack> byproducts, int time) {
        super(ingredients, result, byproducts, time, MixerRecipe::new);
    }

    public static MixerRecipeBuilder mix(List<Ingredient> ingredients, ItemLike result, List<ItemStack> byproducts, int time) {
        return new MixerRecipeBuilder(ingredients, result, byproducts, time);
    }

    public static MixerRecipeBuilder mix(List<Ingredient> ingredients, ItemLike result, int time) {
        return new MixerRecipeBuilder(ingredients, result, null, time);
    }

}
