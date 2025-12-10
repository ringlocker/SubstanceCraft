package com.github.ringlocker.substancecraft.gui.menus;

import com.github.ringlocker.substancecraft.block.entity.entities.RefineryBlockEntity;
import com.github.ringlocker.substancecraft.gui.SubstanceCraftMenus;
import com.github.ringlocker.substancecraft.recipe.recipes.RefineryRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.level.block.entity.BlockEntity;

public class RefineryMenu extends WorkstationMenu<RefineryRecipe, RefineryBlockEntity> {

    public RefineryMenu(int syncId, Inventory playerInventory, BlockPos pos) {
        this(syncId, playerInventory, playerInventory.player.level().getBlockEntity(pos), new SimpleContainerData(3));
    }

    public RefineryMenu(int syncId, Inventory inventory, BlockEntity entity, SimpleContainerData arrayPropertyDelegate) {
        super(SubstanceCraftMenus.REFINERY, syncId, inventory, (RefineryBlockEntity) entity, arrayPropertyDelegate);
    }

}
