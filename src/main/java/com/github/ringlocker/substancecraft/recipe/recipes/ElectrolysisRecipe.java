package com.github.ringlocker.substancecraft.recipe.recipes;

import com.github.ringlocker.substancecraft.recipe.serializer.ByproductRecipeSerializer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.List;
import java.util.Optional;

public class ElectrolysisRecipe extends ByproductRecipe {

    public static final String ID = "electrolysis";

    public ElectrolysisRecipe(List<Ingredient> ingredients, ItemStackTemplate result, List<ItemStackTemplate> byproducts, Optional<ItemStackTemplate> catalyst, int time) {
        super(Type.INSTANCE, Serializer.INSTANCE, ingredients, result, byproducts, catalyst, time);
    }

    @Override
    public Component getLabel() {
        return Component.literal("Electrolysis");
    }

    public static class Type implements RecipeType<ElectrolysisRecipe> {
        public static final Type INSTANCE = new Type();
    }

    public static class Serializer {
        private static final ByproductRecipeSerializer<ElectrolysisRecipe> SERIALIZER = new ByproductRecipeSerializer<>(ElectrolysisRecipe::new);
        public static final RecipeSerializer<ElectrolysisRecipe> INSTANCE = new RecipeSerializer<>(SERIALIZER.codec(), SERIALIZER.streamCodec());
    }

}
