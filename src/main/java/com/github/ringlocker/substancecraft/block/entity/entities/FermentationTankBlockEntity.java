package com.github.ringlocker.substancecraft.block.entity.entities;

import com.github.ringlocker.substancecraft.block.entity.MultiInputBlockEntity;
import com.github.ringlocker.substancecraft.block.entity.SubstanceCraftBlockEntities;
import com.github.ringlocker.substancecraft.gui.menus.FermentationTankMenu;
import com.github.ringlocker.substancecraft.recipe.recipes.FermentationTankRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class FermentationTankBlockEntity extends MultiInputBlockEntity<FermentationTankRecipe> {

    public FermentationTankBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state, "Fermentation", FermentationTankRecipe.Type.INSTANCE, SubstanceCraftBlockEntities.FERMENTATION_TANK);
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new FermentationTankMenu(i, inventory, this, data);
    }

}
