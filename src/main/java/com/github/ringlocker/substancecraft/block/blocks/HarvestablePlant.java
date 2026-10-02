package com.github.ringlocker.substancecraft.block.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public abstract class HarvestablePlant extends GrowingPlantBlock {

    protected HarvestablePlant(Properties properties, IntegerProperty age, VoxelShape[] shapeByAge) {
        super(properties, shapeByAge, age);
    }

    protected abstract boolean breakToHarvest();
    protected abstract int ageAfterHarvest();
    protected abstract void harvest(Level level, BlockPos pos);

    @Override
    protected @NotNull InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        System.out.println("super usewithout item");
        if (MAX_AGE == state.getValue(AGE) && !breakToHarvest()) {
            harvest(level, pos);
            level.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + level.getRandom().nextFloat() * 0.4F);
            BlockState blockState = state.setValue(AGE, ageAfterHarvest());
            level.setBlock(pos, blockState, Block.UPDATE_CLIENTS);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, blockState));
            return InteractionResult.SUCCESS;
        } else {
            return super.useWithoutItem(state, level, pos, player, hitResult);
        }
    }

    @Override
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        System.out.println("super playdestory");
        if (MAX_AGE == state.getValue(AGE)) {
            harvest(level, pos);
            BlockState air = Blocks.AIR.defaultBlockState();
            level.setBlock(pos, air, Block.UPDATE_CLIENTS);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, air));
        } else {
            BlockState air = Blocks.AIR.defaultBlockState();
            level.setBlock(pos, air, 2);
            popResource(level, pos, new ItemStack(this));
        }
        player.awardStat(Stats.BLOCK_MINED.get(this));
        player.causeFoodExhaustion(0.005F);
        return level.getBlockState(pos);
    }
}
