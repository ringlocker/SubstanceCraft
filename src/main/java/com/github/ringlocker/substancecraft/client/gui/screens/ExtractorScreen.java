package com.github.ringlocker.substancecraft.client.gui.screens;

import com.github.ringlocker.substancecraft.block.entity.entities.ExtractorBlockEntity;
import com.github.ringlocker.substancecraft.gui.menus.ExtractorMenu;
import com.github.ringlocker.substancecraft.recipe.recipes.ExtractorRecipe;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

@Environment(EnvType.CLIENT)
public class ExtractorScreen extends WorkstationScreen<ExtractorRecipe, ExtractorBlockEntity, ExtractorMenu> {

    public ExtractorScreen(ExtractorMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

}
