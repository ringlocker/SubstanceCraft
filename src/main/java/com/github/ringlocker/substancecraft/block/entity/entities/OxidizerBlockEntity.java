package com.github.ringlocker.substancecraft.block.entity.entities;

import com.github.ringlocker.substancecraft.block.entity.OneInputBlockEntity;
import com.github.ringlocker.substancecraft.block.entity.SubstanceCraftBlockEntities;
import com.github.ringlocker.substancecraft.gui.menus.OxidizerMenu;
import com.github.ringlocker.substancecraft.recipe.recipes.OxidizerRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class OxidizerBlockEntity extends OneInputBlockEntity<OxidizerRecipe> {

    public OxidizerBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state, "Oxidizer", OxidizerRecipe.Type.INSTANCE, SubstanceCraftBlockEntities.OXIDIZER);
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new OxidizerMenu(i, inventory, this, data);
    }

}
