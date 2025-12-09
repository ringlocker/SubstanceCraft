package com.github.ringlocker.substancecraft.gui.menus;

import com.github.ringlocker.substancecraft.block.entity.entities.InputOutputBlockEntity;
import com.github.ringlocker.substancecraft.block.entity.entities.MultiInputBlockEntity;
import com.github.ringlocker.substancecraft.recipe.recipes.MultipleInputRecipe;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.List;

public class MultipleInputMenu<R extends MultipleInputRecipe, T extends MultiInputBlockEntity<R>> extends InputOutputMenu<T> {

    protected MultipleInputMenu(MenuType<? extends MultipleInputMenu> menu, int syncId, Inventory playerInventory, BlockEntity entity, SimpleContainerData blockEntityData) {
        super(menu, syncId, playerInventory, (InputOutputBlockEntity) entity, blockEntityData);
    }

    @Override
    public boolean clickMenuButton(Player player, int id) {
        if (this.isValidRecipeIndex(id)) {
            blockEntity.setSelectedRecipeIndex(id);
            blockEntity.setChanged();
        }
        return true;
    }

    private boolean isValidRecipeIndex(int recipeIndex) {
        return recipeIndex >= 0 && recipeIndex < blockEntity.getRecipes().size();
    }

    public List<RecipeHolder<R>> getRecipes() {
        return blockEntity.getRecipes();
    }


}
