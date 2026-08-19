package com.github.ringlocker.substancecraft.datagen.recipebuilder;

import com.github.ringlocker.substancecraft.recipe.recipes.OxidizerRecipe;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.Optional;

public class OxidizerRecipeBuilder extends ByproductRecipeBuilder {

    private OxidizerRecipeBuilder(final ItemLike result, final List<Ingredient> ingredients, List<ItemStackTemplate> byproducts, Optional<ItemStackTemplate> catalyst, int time) {
        super(ingredients, result, byproducts, catalyst, time, OxidizerRecipe::new);
    }

    public static OxidizerRecipeBuilder oxidize(List<Ingredient> ingredients, ItemLike result, List<ItemStackTemplate> byproducts, int time) {
        return new OxidizerRecipeBuilder(result, ingredients, byproducts, Optional.empty(), time);
    }

    public static OxidizerRecipeBuilder oxidize(List<Ingredient> ingredients, ItemLike result, int time) {
        return new OxidizerRecipeBuilder(result, ingredients, null, Optional.empty(), time);
    }

}
