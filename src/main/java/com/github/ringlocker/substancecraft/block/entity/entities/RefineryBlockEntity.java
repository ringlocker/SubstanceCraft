package com.github.ringlocker.substancecraft.block.entity.entities;

import com.github.ringlocker.substancecraft.block.entity.OneInputBlockEntity;
import com.github.ringlocker.substancecraft.block.entity.SubstanceCraftBlockEntities;
import com.github.ringlocker.substancecraft.gui.menus.RefineryMenu;
import com.github.ringlocker.substancecraft.recipe.recipes.RefineryRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class RefineryBlockEntity extends OneInputBlockEntity<RefineryRecipe> {

    public RefineryBlockEntity(BlockPos pos, BlockState blockState) {
        super(pos, blockState, "Refinery", RefineryRecipe.Type.INSTANCE, SubstanceCraftBlockEntities.REFINERY);
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int syncId, Inventory inventory, Player player) {
        return new RefineryMenu(syncId, inventory, this, data);
    }

}
