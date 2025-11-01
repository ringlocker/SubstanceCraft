package com.github.ringlocker.substancecraft.recipe.recipes;

import com.github.ringlocker.substancecraft.recipe.generic.OneInputRecipe;
import com.github.ringlocker.substancecraft.recipe.generic.OneInputRecipeSerializer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.List;

public class RefineryRecipe extends OneInputRecipe {

    public static final String ID = "refinery";

    public RefineryRecipe(Ingredient ingredient, ItemStack result, List<ItemStack> byproducts, int time) {
        super(Type.INSTANCE, Serializer.INSTANCE, ID, ingredient, result, byproducts, time);
    }

    @Override
    public Component getTypeString() {
        return Component.literal("Refine");
    }

    public static class Type implements RecipeType<RefineryRecipe> {
        public static final Type INSTANCE = new Type();
    }

    public static class Serializer {
        public static final RecipeSerializer<RefineryRecipe> INSTANCE = new OneInputRecipeSerializer<>(RefineryRecipe::new);
    }

}
