package com.github.ringlocker.substancecraft.datagen.recipebuilder;

import com.github.ringlocker.substancecraft.recipe.recipes.RefineryRecipe;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.Optional;

public class RefineryRecipeBuilder extends ByproductRecipeBuilder {

    private RefineryRecipeBuilder(final ItemLike result, final List<Ingredient> ingredients, List<ItemStackTemplate> byproducts, Optional<ItemStackTemplate> catalyst, int time) {
        super(ingredients, result, byproducts, catalyst, time, RefineryRecipe::new);
    }

    public static RefineryRecipeBuilder refine(List<Ingredient> ingredients, ItemLike result, List<ItemStackTemplate> byproducts, int time) {
        return new RefineryRecipeBuilder(result, ingredients, byproducts, Optional.empty(), time);
    }

    public static RefineryRecipeBuilder refine(List<Ingredient> ingredients, ItemLike result, int time) {
        return new RefineryRecipeBuilder(result, ingredients, null, Optional.empty(), time);
    }

    public static RefineryRecipeBuilder refine(List<Ingredient> ingredients, ItemLike result, Item catalyst, int time) {
        return new RefineryRecipeBuilder(result, ingredients, null, Optional.of(new ItemStackTemplate(catalyst)), time);
    }

}