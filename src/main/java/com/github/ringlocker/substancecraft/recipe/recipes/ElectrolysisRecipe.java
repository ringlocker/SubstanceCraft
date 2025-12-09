package com.github.ringlocker.substancecraft.recipe.recipes;

import com.github.ringlocker.substancecraft.recipe.serializer.OneInputRecipeSerializer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.List;

public class ElectrolysisRecipe extends OneInputRecipe {

    public static final String ID = "electrolysis";

    public ElectrolysisRecipe(Ingredient ingredient, ItemStack result,  List<ItemStack> byproducts, int time) {
        super(Type.INSTANCE, Serializer.INSTANCE, ID, ingredient, result, byproducts, time);
    }

    @Override
    public Component getTypeString() {
        return Component.literal("Electrolysis");
    }

    public static class Type implements RecipeType<ElectrolysisRecipe> {
        public static final Type INSTANCE = new Type();
    }

    public static class Serializer {
        public static final RecipeSerializer<ElectrolysisRecipe> INSTANCE = new OneInputRecipeSerializer<>(ElectrolysisRecipe::new);
    }

}
