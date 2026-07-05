package com.github.ringlocker.substancecraft.block;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;

public abstract class OneBlockHarvestablePlant extends BushLikeCrop {

    private final VoxelShape[] SHAPE_BY_AGE;
    private final int ageAfterHarvest;

    protected OneBlockHarvestablePlant(Properties properties, IntegerProperty age, VoxelShape[] SHAPE_BY_AGE, int ageAfterHarvest) {
        super(properties, age);
        this.SHAPE_BY_AGE = SHAPE_BY_AGE;
        this.ageAfterHarvest = ageAfterHarvest;
        this.registerDefaultState(this.stateDefinition.any().setValue(this.AGE, 0));
    }

    protected OneBlockHarvestablePlant(Properties properties, IntegerProperty age, VoxelShape[] SHAPE_BY_AGE) {
        this(properties, age, SHAPE_BY_AGE, 2);
    }

    @Override
    protected @NotNull VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE_BY_AGE[state.getValue(this.AGE)];
    }

    protected abstract void harvest(Level level, BlockPos pos);

    @Override
    protected @NotNull InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (MAX_AGE == state.getValue(AGE) && ageAfterHarvest > 0) {
            harvest(level, pos);
            level.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
            BlockState blockState = state.setValue(this.AGE, 2);
            level.setBlock(pos, blockState, 2);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, blockState));
            return InteractionResult.SUCCESS;
        } else {
            return super.useWithoutItem(state, level, pos, player, hitResult);
        }
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        if (MAX_AGE == state.getValue(AGE) && ageAfterHarvest == 0) {
            harvest(level, pos);
            BlockState air =  Blocks.AIR.defaultBlockState();
            level.setBlock(pos, air, 2);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, air));
        } else {
            BlockState air =  Blocks.AIR.defaultBlockState();
            level.setBlock(pos, air, 2);
            popResource(level, pos, new ItemStack(this));
        }
        player.awardStat(Stats.BLOCK_MINED.get(this));
        player.causeFoodExhaustion(0.005F);
    }
}
