package com.github.ringlocker.substancecraft.block.entity.entities;

import com.github.ringlocker.substancecraft.block.entity.MultiInputBlockEntity;
import com.github.ringlocker.substancecraft.block.entity.SubstanceCraftBlockEntities;
import com.github.ringlocker.substancecraft.gui.menus.HeatedMixerMenu;
import com.github.ringlocker.substancecraft.recipe.recipes.HeatedMixerRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class HeatedMixerBlockEntity extends MultiInputBlockEntity<HeatedMixerRecipe> {

    public HeatedMixerBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state, "Heated Mixer", HeatedMixerRecipe.Type.INSTANCE, SubstanceCraftBlockEntities.HEATED_MIXER);
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new HeatedMixerMenu(i, inventory, this, data);
    }


}
