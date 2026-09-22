package com.github.ringlocker.substancecraft.block.blocks;

import com.github.ringlocker.substancecraft.item.SubstanceCraftItems;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class CornCrop extends TwoBlockTallPlant {

    private final MapCodec<CornCrop> CODEC = simpleCodec(CornCrop::new);
    public static final IntegerProperty AGE_PROPERTY = BlockStateProperties.AGE_7;

    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)
    };

    public CornCrop(Properties properties) {
        super(properties, AGE_PROPERTY, SHAPE_BY_AGE);
    }

    @Override
    public @NotNull MapCodec<CornCrop> codec() {
        return CODEC;
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE_PROPERTY);
        builder.add(HALF);
    }

    @Override
    public int oneBlockMaxAge() {
        return 3;
    }

    @Override
    public boolean synchronizeTopAndBottomAge() {
        return false;
    }

    @Override
    protected boolean breakToHarvest() {
        return true;
    }

    @Override
    protected int ageAfterHarvest() {
        return 0;
    }

    @Override
    protected void harvest(Level level, BlockPos pos) {
        popResource(level, pos, new ItemStack(SubstanceCraftItems.CORN, 2 + level.getRandom().nextInt(3)));
    }

    @Override
    protected int getMaxBonemealGrowAmount() {
        return 3;
    }

    @Override
    protected int getMinBonemealGrowAmount() {
        return 1;
    }

    @Override
    protected int getOptimalConditionGrowChance() {
        return 16;
    }

    @Override
    protected int getNormalGrowChance() {
        return 24;
    }

    @Override
    protected boolean requiresFarmland() {
        return true;
    }
}
