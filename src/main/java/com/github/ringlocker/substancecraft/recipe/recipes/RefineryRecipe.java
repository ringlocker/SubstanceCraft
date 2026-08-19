package com.github.ringlocker.substancecraft.recipe.recipes;

import com.github.ringlocker.substancecraft.recipe.serializer.ByproductRecipeSerializer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.List;
import java.util.Optional;

public class RefineryRecipe extends ByproductRecipe {

    public static final String ID = "refinery";

    public RefineryRecipe(List<Ingredient> ingredients, ItemStackTemplate result, List<ItemStackTemplate> byproducts, Optional<ItemStackTemplate> catalyst, int time) {
        super(Type.INSTANCE, Serializer.INSTANCE, ingredients, result, byproducts, catalyst, time);
    }

    @Override
    public Component getLabel() {
        return Component.literal("Refine");
    }

    public static class Type implements RecipeType<RefineryRecipe> {
        public static final Type INSTANCE = new Type();
    }

    public static class Serializer {
        private static final ByproductRecipeSerializer<RefineryRecipe> SERIALIZER = new ByproductRecipeSerializer<>(RefineryRecipe::new);
        public static final RecipeSerializer<RefineryRecipe> INSTANCE = new RecipeSerializer<>(SERIALIZER.codec(), SERIALIZER.streamCodec());
    }

}
