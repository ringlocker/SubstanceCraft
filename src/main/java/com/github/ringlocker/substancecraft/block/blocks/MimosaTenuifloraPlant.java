package com.github.ringlocker.substancecraft.block.blocks;

import com.github.ringlocker.substancecraft.block.OneBlockHarvestablePlant;
import com.github.ringlocker.substancecraft.block.SubstanceCraftBlocks;
import com.github.ringlocker.substancecraft.item.SubstanceCraftItems;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MimosaTenuifloraPlant extends OneBlockHarvestablePlant {

    private final MapCodec<MimosaTenuifloraPlant> CODEC = simpleCodec(MimosaTenuifloraPlant::new);

    public static final IntegerProperty AGE_PROPERTY = BlockStateProperties.AGE_4;
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 11.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D),
    };

    public MimosaTenuifloraPlant(Properties properties) {
        super(properties, AGE_PROPERTY, SHAPE_BY_AGE, 0);
    }

    @Override
    protected MapCodec<? extends VegetationBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE_PROPERTY);
    }

    @Override
    protected void harvest(Level level, BlockPos pos) {
        int rootBarkDropped = 1 + level.random.nextInt(2);
        int seedsDropped = 1 + level.random.nextInt(3);
        popResource(level, pos, new ItemStack(SubstanceCraftItems.MIMOSA_TENUIFLORA_ROOT_BARK, rootBarkDropped));
        popResource(level, pos, new ItemStack(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.MIMOSA_TENUIFLORA), seedsDropped));
    }

}
