package com.github.ringlocker.substancecraft.recipe.recipes;

import com.github.ringlocker.substancecraft.recipe.MultipleItemInput;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeBookCategories;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public abstract class ByproductRecipe implements Recipe<MultipleItemInput> {

    private final RecipeType<? extends ByproductRecipe> type;
    private final RecipeSerializer<? extends ByproductRecipe> serializer;

    protected final List<Ingredient> ingredients;
    protected final ItemStackTemplate result;
    protected final List<ItemStackTemplate> byproducts;
    protected final Optional<ItemStackTemplate> catalyst;
    protected final int time;

    @Nullable
    private PlacementInfo placementInfo;

    public ByproductRecipe(
            RecipeType<? extends ByproductRecipe> type,
            RecipeSerializer<? extends ByproductRecipe> serializer,
            List<Ingredient> ingredients,
            ItemStackTemplate result,
            List<ItemStackTemplate> byproducts,
            Optional<ItemStackTemplate> catalyst,
            int time)
    {
        this.type = type;
        this.serializer = serializer;
        this.ingredients = ingredients != null ? ingredients : List.of();
        this.result = result;
        this.byproducts = byproducts;
        this.catalyst = catalyst;
        this.time = time;
    }

    public abstract Component getLabel();

    @NotNull
    public List<Ingredient> getInputs() {
        return ingredients;
    }

    @NotNull
    public ItemStackTemplate getResult() {
        return result;
    }

    @NotNull
    public List<ItemStackTemplate> getByproducts() {
        return byproducts;
    }

    @Nullable
    public Optional<ItemStackTemplate> getCatalyst() {
        return catalyst;
    }

    public int getTime() {
        return time;
    }

    public int time() {
        return time;
    }

    @Override
    public boolean matches(MultipleItemInput input, Level level) {
        if (level.isClientSide()) return false;
        if (input.size() != ingredients.size()) return false;
        return checkIngredientsMatch(input);
    }

    private boolean checkIngredientsMatch( MultipleItemInput provided) {
        for (Ingredient ingredient : ingredients) {
            boolean ingredientInInput = false;
            for (ItemStack inputItem : provided.items()) {
                if (ingredient.test(inputItem)) {
                    ingredientInInput = true;
                    break;
                }
            }
            if (!ingredientInInput) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack assemble(MultipleItemInput input) {
        return result.create();
    }

    @Override
    public boolean showNotification() {
        return true;
    }

    @Override
    public String group() {
        return "";
    }

    @Override
    public @NotNull RecipeSerializer<? extends ByproductRecipe> getSerializer() {
        return serializer;
    }

    @Override
    public @NotNull RecipeType<? extends ByproductRecipe> getType() {
        return type;
    }

    @Override
    public @NotNull PlacementInfo placementInfo() {
        if (this.placementInfo == null) {
            this.placementInfo = PlacementInfo.create(this.ingredients);
        }
        return this.placementInfo;
    }

    @Override
    public @NotNull RecipeBookCategory recipeBookCategory() {
        return RecipeBookCategories.CRAFTING_MISC;
    }

    public interface Factory<T extends ByproductRecipe> {
        T create(List<Ingredient> ingredients, ItemStackTemplate result, List<ItemStackTemplate> byproducts, Optional<ItemStackTemplate> catalyst, int time);
    }

}
