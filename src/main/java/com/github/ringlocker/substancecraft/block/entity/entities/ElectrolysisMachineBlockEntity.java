package com.github.ringlocker.substancecraft.block.entity.entities;

import com.github.ringlocker.substancecraft.block.entity.SubstanceCraftBlockEntities;
import com.github.ringlocker.substancecraft.gui.menus.ElectrolysisMachineMenu;
import com.github.ringlocker.substancecraft.recipe.recipes.ElectrolysisRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class ElectrolysisMachineBlockEntity extends WorkstationBlockEntity<ElectrolysisRecipe> {

    public ElectrolysisMachineBlockEntity(BlockPos pos, BlockState state) {
        super( SubstanceCraftBlockEntities.ELECTROLYSIS_MACHINE, ElectrolysisRecipe.Type.INSTANCE, pos, state, "Electrolysis");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new ElectrolysisMachineMenu(i, inventory, this, data);
    }

}
