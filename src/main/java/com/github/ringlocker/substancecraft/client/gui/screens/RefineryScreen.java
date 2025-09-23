package com.github.ringlocker.substancecraft.client.gui.screens;

import com.github.ringlocker.substancecraft.block.entity.entities.RefineryBlockEntity;
import com.github.ringlocker.substancecraft.client.gui.OneInputScreen;
import com.github.ringlocker.substancecraft.gui.menus.RefineryMenu;
import com.github.ringlocker.substancecraft.recipe.recipes.RefineryRecipe;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

@Environment(EnvType.CLIENT)
public class RefineryScreen extends OneInputScreen<RefineryRecipe, RefineryBlockEntity, RefineryMenu> {

    public RefineryScreen(RefineryMenu handler, Inventory inventory, Component title) {
        super(handler, inventory, title);
    }

}
