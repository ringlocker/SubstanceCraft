package com.github.ringlocker.substancecraft.client.gui.screens;

import com.github.ringlocker.substancecraft.block.entity.entities.MixerBlockEntity;
import com.github.ringlocker.substancecraft.gui.menus.MixerMenu;
import com.github.ringlocker.substancecraft.recipe.recipes.MixerRecipe;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

@Environment(EnvType.CLIENT)
public class MixerScreen extends MultipleInputScreen<MixerRecipe, MixerBlockEntity, MixerMenu> {

    public MixerScreen(MixerMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

}
