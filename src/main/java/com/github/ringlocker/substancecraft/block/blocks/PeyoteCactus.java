package com.github.ringlocker.substancecraft.block.blocks;

import com.github.ringlocker.substancecraft.block.SubstanceCraftBlocks;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.LevelReader;
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
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos belowPos = pos.below();
        BlockState below = level.getBlockState(belowPos);
        return below.is(BlockTags.SAND);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }


    @Override
    public Item getDropItem() {
        return SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.PEYOTE_CACTUS);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        int rand = random.nextInt(21);
        if (rand == 0) {
            if (state.getValue(AGE) < maxAge) {
                this.performBonemeal(level, level.getRandom(), pos, state);
            }
        }
    }

}
