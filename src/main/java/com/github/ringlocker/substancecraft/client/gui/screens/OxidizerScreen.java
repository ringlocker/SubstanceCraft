package com.github.ringlocker.substancecraft.client.gui.screens;

import com.github.ringlocker.substancecraft.block.entity.entities.OxidizerBlockEntity;
import com.github.ringlocker.substancecraft.client.gui.OneInputScreen;
import com.github.ringlocker.substancecraft.gui.menus.OxidizerMenu;
import com.github.ringlocker.substancecraft.recipe.recipes.OxidizerRecipe;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

@Environment(EnvType.CLIENT)
public class OxidizerScreen extends OneInputScreen<OxidizerRecipe, OxidizerBlockEntity, OxidizerMenu> {

    public OxidizerScreen(OxidizerMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

}
