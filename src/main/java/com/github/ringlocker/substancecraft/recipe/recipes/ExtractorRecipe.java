package com.github.ringlocker.substancecraft.recipe.recipes;

import com.github.ringlocker.substancecraft.recipe.serializer.ByproductRecipeSerializer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.List;

public class ExtractorRecipe extends ByproductRecipe {

    public static final String ID = "extractor";

    public ExtractorRecipe(List<Ingredient> ingredient, ItemStack result, List<ItemStack> byproducts, int time) {
        super(Type.INSTANCE, Serializer.INSTANCE, ingredient, result, byproducts, time);
    }

    @Override
    public Component getLabel() {
        return Component.literal("Extract");
    }

    public static class Type implements RecipeType<ExtractorRecipe> {
        public static final Type INSTANCE = new Type();
    }

    public static class Serializer {
        public static final RecipeSerializer<ExtractorRecipe> INSTANCE = new ByproductRecipeSerializer<>(ExtractorRecipe::new);
    }

}
