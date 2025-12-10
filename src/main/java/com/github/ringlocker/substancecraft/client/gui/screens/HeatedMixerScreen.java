package com.github.ringlocker.substancecraft.client.gui.screens;

import com.github.ringlocker.substancecraft.block.entity.entities.HeatedMixerBlockEntity;
import com.github.ringlocker.substancecraft.gui.menus.HeatedMixerMenu;
import com.github.ringlocker.substancecraft.recipe.recipes.HeatedMixerRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class HeatedMixerScreen extends WorkstationScreen<HeatedMixerRecipe, HeatedMixerBlockEntity, HeatedMixerMenu> {

    public HeatedMixerScreen(HeatedMixerMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

}
