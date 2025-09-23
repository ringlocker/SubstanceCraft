package com.github.ringlocker.substancecraft.client.gui.screens;

import com.github.ringlocker.substancecraft.block.entity.entities.CatalyticReformerBlockEntity;
import com.github.ringlocker.substancecraft.client.gui.OneInputScreen;
import com.github.ringlocker.substancecraft.gui.menus.CatalyticReformerMenu;
import com.github.ringlocker.substancecraft.recipe.recipes.CatalyticReformerRecipe;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

@Environment(EnvType.CLIENT)
public class CatalyticReformerScreen extends OneInputScreen<CatalyticReformerRecipe, CatalyticReformerBlockEntity, CatalyticReformerMenu> {

    public CatalyticReformerScreen(CatalyticReformerMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

}
