package com.github.ringlocker.substancecraft.gui.menus;

import com.github.ringlocker.substancecraft.block.entity.entities.CatalyticReformerBlockEntity;
import com.github.ringlocker.substancecraft.gui.OneInputMenu;
import com.github.ringlocker.substancecraft.gui.SubstanceCraftMenus;
import com.github.ringlocker.substancecraft.recipe.recipes.CatalyticReformerRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.level.block.entity.BlockEntity;

public class CatalyticReformerMenu extends OneInputMenu<CatalyticReformerRecipe, CatalyticReformerBlockEntity> {

    public CatalyticReformerMenu(int syncId, Inventory playerInventory, BlockPos pos) {
        this(syncId, playerInventory, playerInventory.player.level().getBlockEntity(pos), new SimpleContainerData(3));
    }

    public CatalyticReformerMenu(int syncId, Inventory inventory, BlockEntity entity, SimpleContainerData arrayPropertyDelegate) {
        super(SubstanceCraftMenus.CATALYTIC_REFORMER, syncId, inventory, entity, arrayPropertyDelegate);
    }

}
