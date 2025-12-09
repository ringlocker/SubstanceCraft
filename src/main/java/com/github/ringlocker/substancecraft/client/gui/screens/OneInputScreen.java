package com.github.ringlocker.substancecraft.client.gui.screens;

import com.github.ringlocker.substancecraft.block.entity.entities.OneInputBlockEntity;
import com.github.ringlocker.substancecraft.gui.menus.OneInputMenu;
import com.github.ringlocker.substancecraft.recipe.recipes.OneInputRecipe;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;

import java.util.ArrayList;
import java.util.List;

@Environment(EnvType.CLIENT)
public class OneInputScreen<R extends OneInputRecipe, E extends OneInputBlockEntity<R>, T extends OneInputMenu<R, E>> extends InputOutputScreen<T> {

    public OneInputScreen(T menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    @SuppressWarnings("deprecation")
    protected List<Component> tooltip(int index) {
        List<RecipeHolder<R>> list = this.menu.getRecipes();
        OneInputRecipe recipe = list.get(index).value();
        ItemStack inputItem = new ItemStack(recipe.getInput().items().findFirst().orElse(Holder.direct(Items.AIR)).value().asItem());
        ItemStack resultItem = recipe.getResult();
        List<Component> tooltip = new ArrayList<>();
        tooltip.add(getItemNameString(resultItem));
        tooltip.add(Component.literal("Requires: "));
        tooltip.add(getItemNameString(inputItem));
        if (!recipe.getByproducts().isEmpty()) {
            tooltip.add(Component.literal("Byproducts: "));
            List<ItemStack> byproducts = recipe.getByproducts();
            for (ItemStack byproduct : byproducts) {
                tooltip.add(getByproductString(byproduct, byproduct.getCount() << 1));
            }
        }
        return tooltip;
    }

    @Override
    protected void renderRecipes(GuiGraphics guiGraphics, int x, int y, int startIndex) {
        List<RecipeHolder<R>> list = this.menu.getRecipes();
        for (int index = this.firstVisibleIndex; index < startIndex && index < this.menu.getNumRecipes(); index++) {
            int relativeIndex = index - this.firstVisibleIndex;
            int renderX = x + relativeIndex % RECIPES_COLUMNS * RECIPES_IMAGE_SIZE_WIDTH;
            int row = relativeIndex / RECIPES_COLUMNS;
            int renderY = y + row * RECIPES_IMAGE_SIZE_HEIGHT + 2;
            guiGraphics.renderItem(list.get(index).value().getResult(), renderX, renderY);
        }
    }

}
