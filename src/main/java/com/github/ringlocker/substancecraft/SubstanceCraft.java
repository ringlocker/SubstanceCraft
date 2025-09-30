package com.github.ringlocker.substancecraft;

import com.github.ringlocker.substancecraft.block.SubstanceCraftBlocks;
import com.github.ringlocker.substancecraft.block.entity.SubstanceCraftBlockEntities;
import com.github.ringlocker.substancecraft.gui.SubstanceCraftMenus;
import com.github.ringlocker.substancecraft.items.SubstanceCraftItems;
import com.github.ringlocker.substancecraft.network.SubstanceCraftNetworking;
import com.github.ringlocker.substancecraft.recipe.SubstanceCraftRecipes;
import com.github.ringlocker.substancecraft.world.SubstanceCraftFeatures;
import com.github.ringlocker.substancecraft.world.SubstanceCraftLootTables;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubstanceCraft implements ModInitializer {

    public static final String MOD_ID = "substancecraft";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        SubstanceCraftItems.registerItems();
        SubstanceCraftBlocks.registerBlocks();
        SubstanceCraftBlockEntities.registerBlockEntities();
        SubstanceCraftRecipes.registerRecipes();
        SubstanceCraftMenus.registerMenus();
        SubstanceCraftFeatures.registerFeatures();
        SubstanceCraftLootTables.registerLootTables();
        SubstanceCraftNetworking.init();
    }

    // TODO: Texture more green coca
    // loot table drops coca
    // new texture for oil shale
    // lang
    //

}
