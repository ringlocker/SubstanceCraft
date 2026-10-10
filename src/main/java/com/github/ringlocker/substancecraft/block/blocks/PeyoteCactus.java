package com.github.ringlocker.substancecraft.block.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PeyoteCactus extends GrowingPlantBlock {

    public static IntegerProperty AGE = IntegerProperty.create("age", 0, 2);
    private static final VoxelShape[] SHAPE = new VoxelShape[]{
            Block.column(4.0, 0.0, 4.0),
            Block.column(8.0, 0.0, 6.0),
            Block.column(10.0, 0.0, 7.0)
    };

    public PeyoteCactus(Properties properties) {
        super(properties, SHAPE, AGE);
        setRequiresFarmland(false);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos belowPos = pos.below();
        BlockState below = level.getBlockState(belowPos);
        return below.is(BlockTags.SAND);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

}
