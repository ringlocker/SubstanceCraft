package com.github.ringlocker.substancecraft.block.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public abstract class HarvestablePlant extends GrowingPlantBlock {

    private boolean breakToHarvest = false;
    private int ageAfterHarvest = 0;

    protected HarvestablePlant(Properties properties, IntegerProperty age, VoxelShape[] shapeByAge) {
        super(properties, shapeByAge, age);
    }

    public boolean breakToHarvest() {
        return breakToHarvest;
    }

    public int ageAfterHarvest() {
        return ageAfterHarvest;
    }

    public void setBreakToHarvest(boolean breakToHarvest) {
        this.breakToHarvest = breakToHarvest;
    }

    public void setAgeAfterHarvest(int ageAfterHarvest) {
        this.ageAfterHarvest = ageAfterHarvest;
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (MAX_AGE == state.getValue(AGE) && !breakToHarvest()) {
            Block.dropResources(level.getBlockState(pos), level, pos);
            level.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + level.getRandom().nextFloat() * 0.4F);
            BlockState blockState = state.setValue(AGE, ageAfterHarvest());
            level.setBlock(pos, blockState, Block.UPDATE_CLIENTS);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, blockState));
            return InteractionResult.SUCCESS;
        } else {
            return super.useWithoutItem(state, level, pos, player, hitResult);
        }
    }

}
