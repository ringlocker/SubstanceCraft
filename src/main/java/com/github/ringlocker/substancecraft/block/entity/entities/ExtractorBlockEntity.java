package com.github.ringlocker.substancecraft.block.entity.entities;

import com.github.ringlocker.substancecraft.block.entity.SubstanceCraftBlockEntities;
import com.github.ringlocker.substancecraft.gui.menus.ExtractorMenu;
import com.github.ringlocker.substancecraft.recipe.recipes.ExtractorRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class ExtractorBlockEntity extends WorkstationBlockEntity<ExtractorRecipe> {

    public ExtractorBlockEntity(BlockPos pos, BlockState state) {
        super(SubstanceCraftBlockEntities.EXTRACTOR, ExtractorRecipe.Type.INSTANCE, pos, state, "Extractor");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new ExtractorMenu(i, inventory, this, data);
    }
}
