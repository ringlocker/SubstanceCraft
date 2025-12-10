package com.github.ringlocker.substancecraft.datagen.recipebuilder;

import com.github.ringlocker.substancecraft.recipe.recipes.ByproductRecipe;
import com.github.ringlocker.substancecraft.recipe.recipes.OxidizerRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.List;

public class OxidizerRecipeBuilder extends ByproductRecipeBuilder {

    private OxidizerRecipeBuilder(final ItemLike result, final List<Ingredient> ingredients, List<ItemStack> byproducts, int time, ByproductRecipe.Factory<OxidizerRecipe> factory) {
        super(ingredients, result, byproducts, time, factory);
    }

    public static OxidizerRecipeBuilder oxidize(List<Ingredient> ingredients, ItemLike result, List<ItemStack> byproducts, int time, ByproductRecipe.Factory<OxidizerRecipe> factory) {
        return new OxidizerRecipeBuilder(result, ingredients, byproducts, time, factory);
    }

    public static OxidizerRecipeBuilder oxidize(List<Ingredient> ingredients, ItemLike result, int time, ByproductRecipe.Factory<OxidizerRecipe> factory) {
        return new OxidizerRecipeBuilder(result, ingredients, null, time, factory);
    }

}
