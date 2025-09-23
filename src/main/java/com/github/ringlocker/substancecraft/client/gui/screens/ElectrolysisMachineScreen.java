package com.github.ringlocker.substancecraft.client.gui.screens;

import com.github.ringlocker.substancecraft.block.entity.entities.ElectrolysisMachineBlockEntity;
import com.github.ringlocker.substancecraft.client.gui.OneInputScreen;
import com.github.ringlocker.substancecraft.gui.menus.ElectrolysisMachineMenu;
import com.github.ringlocker.substancecraft.recipe.recipes.ElectrolysisRecipe;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

@Environment(EnvType.CLIENT)
public class ElectrolysisMachineScreen extends OneInputScreen<ElectrolysisRecipe, ElectrolysisMachineBlockEntity, ElectrolysisMachineMenu> {

    public ElectrolysisMachineScreen(ElectrolysisMachineMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

}
