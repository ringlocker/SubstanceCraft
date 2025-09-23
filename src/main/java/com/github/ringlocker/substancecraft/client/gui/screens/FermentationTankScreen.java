package com.github.ringlocker.substancecraft.client.gui.screens;

import com.github.ringlocker.substancecraft.block.entity.entities.FermentationTankBlockEntity;
import com.github.ringlocker.substancecraft.client.gui.MultipleInputScreen;
import com.github.ringlocker.substancecraft.gui.menus.FermentationTankMenu;
import com.github.ringlocker.substancecraft.recipe.recipes.FermentationTankRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class FermentationTankScreen extends MultipleInputScreen<FermentationTankRecipe, FermentationTankBlockEntity, FermentationTankMenu> {

    public FermentationTankScreen(FermentationTankMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

}
