package com.github.ringlocker.substancecraft.block.blocks;

import com.github.ringlocker.substancecraft.block.SubstanceCraftBlocks;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PsilocybinMushroom extends MushroomWithGrowthStages {

    public static IntegerProperty AGE = IntegerProperty.create("age", 0, 2);
    private static final VoxelShape[] SHAPE = new VoxelShape[]{
            Block.column(6.0, 0.0, 6.0),
            Block.column(6.0, 0.0, 6.0),
            Block.column(6.0, 0.0, 6.0)
    };

    public PsilocybinMushroom(Properties properties) {
        super(properties, SHAPE, AGE);
    }

    @Override
    public Item getDropItem() {
        return SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.PSILOCYBIN);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

}
