package com.github.ringlocker.substancecraft.recipe.recipes;

import com.github.ringlocker.substancecraft.recipe.serializer.MultipleInputSerializer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.List;

public class FermentationTankRecipe extends MultipleInputRecipe {

    public static final String ID = "fermentation_tank";

    public FermentationTankRecipe(List<Ingredient> ingredients, ItemStack result, List<ItemStack> byproducts, int time) {
        super(Type.INSTANCE, Serializer.INSTANCE, ingredients, result, byproducts, time);
    }

    @Override
    public Component getTypeString() {
        return Component.literal("Ferment");
    }

    public static class Type implements RecipeType<FermentationTankRecipe> {
        public static final Type INSTANCE = new Type();
    }

    public static class Serializer {
        public static final RecipeSerializer<FermentationTankRecipe> INSTANCE = new MultipleInputSerializer<>(FermentationTankRecipe::new);
    }

}
