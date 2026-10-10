package com.github.ringlocker.substancecraft.block.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.VoxelShape;

public class AnisePlant extends GrowingPlantBlock {

    public static IntegerProperty AGE = IntegerProperty.create("age", 0, 2);
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.column(6.0, 0.0, 8.0),
            Block.column(10.0, 0.0, 13.0),
            Block.column(14.0, 0.0, 16.0)
    };

    public AnisePlant(Properties properties) {
        super(properties, SHAPE_BY_AGE, AGE);
        setRequiresFarmland(false);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

}
