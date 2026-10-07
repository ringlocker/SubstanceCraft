package com.github.ringlocker.substancecraft.block.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PeyoteCactus extends GrowingPlantBlock {

    public static IntegerProperty AGE = IntegerProperty.create("age", 0, 2);
    public static final MapCodec<PeyoteCactus> CODEC = simpleCodec(PeyoteCactus::new);
    private static final VoxelShape[] SHAPE = new VoxelShape[]{
            Block.column(6.0, 0.0, 6.0),
            Block.column(6.0, 0.0, 6.0),
            Block.column(6.0, 0.0, 6.0)
    };

    public PeyoteCactus(Properties properties) {
        super(properties, SHAPE, AGE);
        setRequiresFarmland(false);
    }

    @Override
    protected MapCodec<? extends VegetationBlock> codec() {
        return CODEC;
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
