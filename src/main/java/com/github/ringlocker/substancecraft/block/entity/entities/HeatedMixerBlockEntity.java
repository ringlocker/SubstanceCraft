package com.github.ringlocker.substancecraft.block.entity.entities;

import com.github.ringlocker.substancecraft.block.entity.SubstanceCraftBlockEntities;
import com.github.ringlocker.substancecraft.gui.menus.HeatedMixerMenu;
import com.github.ringlocker.substancecraft.recipe.recipes.HeatedMixerRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class HeatedMixerBlockEntity extends WorkstationBlockEntity<HeatedMixerRecipe> {

    public HeatedMixerBlockEntity(BlockPos pos, BlockState state) {
        super(SubstanceCraftBlockEntities.HEATED_MIXER, HeatedMixerRecipe.Type.INSTANCE, pos, state, "Heated Mixer");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new HeatedMixerMenu(i, inventory, this, data);
    }


}
