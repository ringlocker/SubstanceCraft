package com.github.ringlocker.substancecraft.block.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class TwoBlockTallPlant extends HarvestablePlant {

    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;

    private int oneBlockMaxAge = 0;
    private boolean synchronizeTopAndBottomAge = false;


    public TwoBlockTallPlant(Properties properties, IntegerProperty age, VoxelShape[] ageToShape) {
        super(properties, age, ageToShape);
        registerDefaultState(defaultBlockState().setValue(AGE, 0).setValue(HALF, DoubleBlockHalf.LOWER));
    }

    public int oneBlockMaxAge() {
        return oneBlockMaxAge;
    }

    public void setOneBlockMaxAge(int oneBlockMaxAge) {
        this.oneBlockMaxAge = oneBlockMaxAge;
    }

    public boolean synchronizeTopAndBottomAge() {
        return synchronizeTopAndBottomAge;
    }

    public void setSynchronizeTopAndBottomAge(boolean synchronizeTopAndBottomAge) {
        this.synchronizeTopAndBottomAge = synchronizeTopAndBottomAge;
    }

    @Override
    protected @NotNull InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        int age = state.getValue(AGE);
        if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
            if (level.getBlockState(pos.above()).is(this)) {
                age = level.getBlockState(pos.above()).getValue(AGE);
            }
        }
        boolean isMaxAge = age == MAX_AGE;
        return !isMaxAge && stack.is(Items.BONE_MEAL) ? InteractionResult.PASS : super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }


    @Override
    protected @NotNull InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        boolean harvest = false;
        if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
            BlockState upper = level.getBlockState(pos.above());
            if (upper.is(this)) {
                int age = upper.getValue(AGE);
                if (age == MAX_AGE) {
                    harvest = true;
                }
            }
        } else {
            int age = state.getValue(AGE);
            if (age == MAX_AGE) {
                harvest = true;
            }
        }
        if (harvest && !breakToHarvest()) {
            Block.dropResources(level.getBlockState(pos), level, pos);
            level.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + level.getRandom().nextFloat() * 0.4F);
            updateBlockStateAfterHarvest(state, level, pos);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, state));
            return InteractionResult.SUCCESS_SERVER;
        } else {
            return InteractionResult.PASS;
        }
    }



    @Override
    public BlockState playerWillDestroy(final Level level, final BlockPos pos, final BlockState state, final Player player) {
        BlockState upper;
        BlockState lower;
        BlockPos upperPos;
        BlockPos lowerPos;
        if (!level.isClientSide()) {

            if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
                upperPos = pos.above();
                upper = level.getBlockState(upperPos);
                lowerPos = pos;
                lower = state;
            } else {
                upper = state;
                lowerPos = pos.below();
                lower = level.getBlockState(lowerPos);
            }

            if ((state.getValue(HALF) == DoubleBlockHalf.LOWER && upper.is(state.getBlock()) && upper.getValue(HALF) == DoubleBlockHalf.UPPER) || state.getValue(HALF) == DoubleBlockHalf.UPPER) {
                BlockState newBlockState = lower.getFluidState().is(Fluids.WATER) ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState();
                level.setBlock(lowerPos, newBlockState, 35);
                level.levelEvent(player, 2001, lowerPos, Block.getId(newBlockState));
            }

        }

        return super.playerWillDestroy(level, pos, state, player);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        level.setBlock(pos, withWaterloggedState(level, pos, defaultBlockState().setValue(HALF, DoubleBlockHalf.LOWER)), Block.UPDATE_ALL);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        if (state.getValue(HALF) == DoubleBlockHalf.UPPER) {
            BlockState blockState = level.getBlockState(pos.below());
            return blockState.is(this) && blockState.getValue(HALF) == DoubleBlockHalf.LOWER;
        }
        return super.canSurvive(state, level, pos);
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return super.isRandomlyTicking(state) && state.getValue(HALF) == DoubleBlockHalf.LOWER;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return switch (state.getValue(HALF)) {
            case DoubleBlockHalf.LOWER -> {
                BlockState upper = level.getBlockState(pos.above());
                if (upper.is(this)) {
                    yield super.isValidBonemealTarget(level, pos, upper);
                }
                else yield super.isValidBonemealTarget(level, pos, state);
            }
            case DoubleBlockHalf.UPPER -> super.isValidBonemealTarget(level, pos, state);
        };
    }

    private void updateBlockStateAfterHarvest(BlockState state, Level level, BlockPos pos) {
        BlockState upper, lower;
        BlockPos upperPos, lowerPos;

        if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
            lower = state;
            lowerPos = pos;
            upper = level.getBlockState(pos.above());
            upperPos = pos.above();
        } else {
            upper = state;
            upperPos = pos;
            lower = level.getBlockState(pos.below());
            lowerPos = pos.below();
        }

        if (breakToHarvest()) {
            level.setBlock(lowerPos, Blocks.AIR.defaultBlockState(), Block.UPDATE_CLIENTS);
            level.setBlock(upperPos, Blocks.AIR.defaultBlockState(), Block.UPDATE_CLIENTS);
            return;
        }

        if (ageAfterHarvest() <= oneBlockMaxAge()) {
            level.setBlock(lowerPos, lower.setValue(this.AGE, ageAfterHarvest()), Block.UPDATE_CLIENTS);
            level.setBlock(upperPos, Blocks.AIR.defaultBlockState(), Block.UPDATE_CLIENTS);
        } else {
            level.setBlock(lowerPos, lower.setValue(this.AGE, ageAfterHarvest()), Block.UPDATE_CLIENTS);
            level.setBlock(upperPos, upper.setValue(this.AGE, ageAfterHarvest()), Block.UPDATE_CLIENTS);
        }

    }

    @Override
    protected void grow(ServerLevel level, BlockPos pos, BlockState state) {
        int age = state.getValue(AGE);
        if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
            if (age < oneBlockMaxAge()) {
                level.setBlock(pos, state.setValue(AGE, age + 1), Block.UPDATE_CLIENTS);
            } else if (age == oneBlockMaxAge() && level.getBlockState(pos.above(1)).is(Blocks.AIR)) {
                level.setBlock(pos.above(1), state.setValue(AGE, age + 1).setValue(HALF, DoubleBlockHalf.UPPER), Block.UPDATE_CLIENTS);
                if (synchronizeTopAndBottomAge())
                    level.setBlock(pos, state.setValue(AGE, age + 1), Block.UPDATE_CLIENTS);
            } else {
                BlockState upper = level.getBlockState(pos.above());
                if (!(upper.is(this) && upper.getValue(HALF) == DoubleBlockHalf.UPPER)) return;
                int upperAge = upper.getValue(AGE);
                if (upperAge < MAX_AGE) {
                    level.setBlock(pos.above(), upper.setValue(AGE, upperAge + 1), Block.UPDATE_CLIENTS);
                    if (synchronizeTopAndBottomAge())
                        level.setBlock(pos, state.setValue(AGE, upperAge + 1), Block.UPDATE_CLIENTS);
                }
            }
        }
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        if (state.getValue(HALF) == DoubleBlockHalf.UPPER) {
            growCrop(level, pos.below(), level.getBlockState(pos.below()), getRandomGrowAmount(random));
        }
        else growCrop(level, pos, state, getRandomGrowAmount(random));
    }

    private BlockState withWaterloggedState(LevelReader levelReader, BlockPos pos, BlockState state) {
        if (state.hasProperty(BlockStateProperties.WATERLOGGED)) {
            return state.setValue(BlockStateProperties.WATERLOGGED, levelReader.isWaterAt(pos));
        }
        return state;
    }

}
