package com.github.ringlocker.substancecraft.block.blocks;

import com.github.ringlocker.substancecraft.block.MushroomWithGrowthStages;
import com.github.ringlocker.substancecraft.block.SubstanceCraftBlocks;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class PeyoteCactus extends MushroomWithGrowthStages {

    public static IntegerProperty AGE = IntegerProperty.create("age", 0, 2);
    public static final MapCodec<PeyoteCactus> CODEC = simpleCodec(PeyoteCactus::new);

    public PeyoteCactus(Properties properties) {
        super(properties, 2, AGE, false);
    }

    @Override
    protected MapCodec<? extends VegetationBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public Item getDropItem() {
        return SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.PEYOTE_CACTUS);
    }

}
