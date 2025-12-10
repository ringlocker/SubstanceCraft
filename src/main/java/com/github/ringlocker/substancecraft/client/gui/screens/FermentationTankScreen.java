package com.github.ringlocker.substancecraft.client.gui.screens;

import com.github.ringlocker.substancecraft.block.entity.entities.FermentationTankBlockEntity;
import com.github.ringlocker.substancecraft.gui.menus.FermentationTankMenu;
import com.github.ringlocker.substancecraft.recipe.recipes.FermentationTankRecipe;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

@Environment(EnvType.CLIENT)
public class FermentationTankScreen extends WorkstationScreen<FermentationTankRecipe, FermentationTankBlockEntity, FermentationTankMenu> {

    public FermentationTankScreen(FermentationTankMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

}
