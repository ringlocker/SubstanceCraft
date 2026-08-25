package com.github.ringlocker.substancecraft.block.entity.entities;

import com.github.ringlocker.substancecraft.block.entity.RecipeList;
import com.github.ringlocker.substancecraft.recipe.MultipleItemInput;
import com.github.ringlocker.substancecraft.recipe.recipes.ByproductRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public abstract class RecipeTypedBlockEntity<T extends ByproductRecipe> extends BlockEntity implements RecipeList<T> {

    protected final RecipeManager.CachedCheck<MultipleItemInput, T> matchGetter;
    private final RecipeType<T> type;
    private final List<RecipeHolder<T>> recipes;

    protected int selectedRecipeIndex;

    public RecipeTypedBlockEntity(BlockEntityType<?> type, BlockPos worldPosition, BlockState blockState, RecipeType<T> recipeType) {
        super(type, worldPosition, blockState);
        this.matchGetter = RecipeManager.createCheck(recipeType);
        this.type = recipeType;
        this.recipes = new ArrayList<>();
    }

    @Override
    public void setChanged() {
        if (level != null) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
            super.setChanged();
        }
    }

    @Override
    public void setLevel(Level level) {
        super.setLevel(level);
        setupRecipeList(level);
    }

    public void setupRecipeList(Level level) {
        this.recipes.clear();
        List<RecipeHolder<T>> allRecipes = new ArrayList<>(level.recipeAccess().getSynchronizedRecipes().getAllOfType(type));
        recipes.addAll(allRecipes);
        recipes.sort(Comparator.comparing(recipe -> recipe.value().getResult().create().getDisplayName().getString()));
    }

    @Override
    public List<RecipeHolder<T>> getRecipes() {
        return this.recipes;
    }

    public Optional<RecipeHolder<T>> getSelectedRecipe() {
        List<RecipeHolder<T>> recipes = getRecipes();
        if (recipes.isEmpty()) {
            return Optional.empty();
        } else {
            int index = getSelectedRecipeIndex();
            if (index > -1 && index < recipes.size()) {
                return Optional.of(recipes.get(index));
            } else return Optional.empty();
        }
    }

    @Nullable
    public T getRecipe() {
        Optional<RecipeHolder<T>> recipe = getSelectedRecipe();
        return recipe.map(RecipeHolder::value).orElse(null);
    }

    public int getSelectedRecipeIndex() {
        return selectedRecipeIndex;
    }

    public void setSelectedRecipeIndex(int selectedRecipeIndex) {
        this.selectedRecipeIndex = selectedRecipeIndex;
        onSelectRecipeChange();
        setChanged();
    }

    public int inputCount() {
        return getSelectedRecipe().map(tRecipeHolder -> tRecipeHolder.value().getInputs().size()).orElse(1);
    }

    public int byproductCount() {
        return getSelectedRecipe().map(tRecipeHolder -> tRecipeHolder.value().getByproducts().size()).orElse(0);
    }

    public boolean hasCatalyst() {
        return getRecipe().hasCatalyst();
    }

    protected abstract boolean recipeMatches();

    protected abstract void onSelectRecipeChange();


}
