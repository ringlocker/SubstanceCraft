package com.github.ringlocker.substancecraft.block.entity.entities;

import com.github.ringlocker.substancecraft.block.entity.OneInputBlockEntity;
import com.github.ringlocker.substancecraft.block.entity.SubstanceCraftBlockEntities;
import com.github.ringlocker.substancecraft.gui.menus.ElectrolysisMachineMenu;
import com.github.ringlocker.substancecraft.recipe.recipes.ElectrolysisRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class ElectrolysisMachineBlockEntity extends OneInputBlockEntity<ElectrolysisRecipe> {

    public ElectrolysisMachineBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state, "Electrolysis", ElectrolysisRecipe.Type.INSTANCE, SubstanceCraftBlockEntities.ELECTROLYSIS_MACHINE);
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new ElectrolysisMachineMenu(i, inventory, this, data);
    }

}
