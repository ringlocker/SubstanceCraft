package com.github.ringlocker.substancecraft.block.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public abstract class BushLikeCrop extends VegetationBlock implements BonemealableBlock {

    public final int MAX_AGE;
    public final IntegerProperty age;

    protected BushLikeCrop(Properties properties, int maxAge, IntegerProperty age) {
        super(properties);
        this.MAX_AGE = maxAge;
        this.age = age;
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return state.getValue(age) < MAX_AGE;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        int rand = (level.getBlockState(pos.below()).is(Blocks.FARMLAND)) ? random.nextInt(7) : random.nextInt(12);
        if (rand == 0) {
            if (state.getValue(age) < MAX_AGE) {
                this.performBonemeal(level, level.random, pos, state);
            }
        }
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return state.getValue(age) < MAX_AGE;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        level.setBlock(pos, state.setValue(age, Math.min(state.getValue(age) + 1, MAX_AGE)), Block.UPDATE_CLIENTS);
    }
}
