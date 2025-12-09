package com.github.ringlocker.substancecraft.client.gui.screens;

import com.github.ringlocker.substancecraft.block.entity.entities.HashPressBlockEntity;
import com.github.ringlocker.substancecraft.gui.menus.HashPressMenu;
import com.github.ringlocker.substancecraft.recipe.recipes.HashPressRecipe;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

@Environment(EnvType.CLIENT)
public class HashPressScreen extends OneInputScreen<HashPressRecipe, HashPressBlockEntity, HashPressMenu> {

    public HashPressScreen(HashPressMenu handler, Inventory inventory, Component title) {
        super(handler, inventory, title);
    }

}