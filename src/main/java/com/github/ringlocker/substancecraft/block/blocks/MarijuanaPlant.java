package com.github.ringlocker.substancecraft.block.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MarijuanaPlant extends TwoBlockTallPlant {

    public static final IntegerProperty AGE_PROPERTY = BlockStateProperties.AGE_7;

    private static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[] {
            Block.box(0.0, 0.0, 0.0, 16.0, 4.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 6.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 10.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 14.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 4.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 5.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 7.0, 16.0)
    };

    public MarijuanaPlant(Properties properties) {
        super(properties, AGE_PROPERTY, AGE_TO_SHAPE);
        setMaxBonemealGrowAmount(2);
        setOneBlockMaxAge(4);
        setAgeAfterHarvest(2);
        setBreakToHarvest(false);
        setRequiresFarmland(false);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE_PROPERTY);
        builder.add(HALF);
    }

}
