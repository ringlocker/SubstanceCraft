package com.github.ringlocker.substancecraft.block.blocks;

import com.github.ringlocker.substancecraft.block.SubstanceCraftBlocks;
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

public class MarijuanaPlant extends TwoBlockTallPlant {

    public static final MapCodec<MarijuanaPlant> CODEC = simpleCodec(MarijuanaPlant::new);
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
    }

    @Override
    public @NotNull MapCodec<MarijuanaPlant> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE_PROPERTY);
        builder.add(HALF);
    }

    @Override
    protected int getMaxBonemealGrowAmount() {
        return 2;
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
        return false;
    }


    @Override
    protected boolean breakToHarvest() {
        return false;
    }

    @Override
    protected int ageAfterHarvest() {
        return 2;
    }

    @Override
    protected void harvest(Level level, BlockPos pos) {
        popResource(level, pos, new ItemStack(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.MARIJUANA_PLANT), 1));
        popResource(level, pos, new ItemStack(SubstanceCraftItems.MARIJUANA, 1));
        popResource(level, pos, new ItemStack(SubstanceCraftItems.MARIJUANA_TRIM, 1 + level.getRandom().nextInt(2)));
        popResource(level, pos, new ItemStack(SubstanceCraftItems.MARIJUANA_TRIM, 1 + level.getRandom().nextInt(2)));
    }

    @Override
    public int oneBlockMaxAge() {
        return 4;
    }

    @Override
    public boolean synchronizeTopAndBottomAge() {
        return false;
    }
}
