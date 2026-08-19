package com.github.ringlocker.substancecraft.datagen.recipebuilder;

import com.github.ringlocker.substancecraft.recipe.recipes.ElectrolysisRecipe;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.Optional;

public class ElectrolysisRecipeBuilder extends ByproductRecipeBuilder {

    protected ElectrolysisRecipeBuilder(ItemLike result, List<Ingredient> ingredients, List<ItemStackTemplate> byproducts, Optional<ItemStackTemplate> catalyst, int time) {
        super(ingredients, result, byproducts, catalyst, time, ElectrolysisRecipe::new);
    }

    public static ElectrolysisRecipeBuilder electrolysis(List<Ingredient> ingredients, ItemLike result, List<ItemStackTemplate> byproducts, int time) {
        return new ElectrolysisRecipeBuilder(result, ingredients, byproducts, Optional.empty(), time);
    }

    public static ElectrolysisRecipeBuilder electrolysis(List<Ingredient> ingredients, ItemLike result, int time) {
        return new ElectrolysisRecipeBuilder(result, ingredients, null, Optional.empty(), time);
    }

}
