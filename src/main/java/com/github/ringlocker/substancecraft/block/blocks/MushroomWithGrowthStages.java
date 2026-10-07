package com.github.ringlocker.substancecraft.block.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;

public abstract class MushroomWithGrowthStages extends GrowingPlantBlock implements BonemealableBlock {

    protected boolean spreads = true;

    public MushroomWithGrowthStages(BlockBehaviour.Properties properties, VoxelShape[] shape, IntegerProperty age) {
        super(properties, shape, age);
        registerDefaultState(defaultBlockState().setValue(AGE, 0));
    }

    public void setSpreads(boolean spreads) {
        this.spreads = spreads;
    }

    public abstract Item getDropItem();

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (spreads) trySpread(state, level, pos, random);
        super.randomTick(state, level, pos, random);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.isSolidRender();
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos belowPos = pos.below();
        BlockState below = level.getBlockState(belowPos);
        if (below.is(BlockTags.OVERRIDES_MUSHROOM_LIGHT_REQUIREMENT)) return true;
        return this.mayPlaceOn(below, level, belowPos);
    }

    @Override
    protected List<ItemStack> getDrops(BlockState state, LootParams.Builder params) {
        return List.of(new ItemStack(getDropItem(), state.getValue(AGE) + 1));
    }

    protected void trySpread(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (random.nextInt(25) == 0) {
            int max = 5;
            for (BlockPos blockPos : BlockPos.betweenClosed(pos.offset(-4, -1, -4), pos.offset(4, 1, 4))) {
                if (!level.getBlockState(blockPos).is(this) || --max > 0) continue;
                return;
            }
            BlockPos offset = pos.offset(random.nextInt(3) - 1, random.nextInt(2) - random.nextInt(2), random.nextInt(3) - 1);
            for (int i = 0; i < 4; ++i) {
                if (level.isEmptyBlock(offset) && state.canSurvive(level, offset) && level.getRawBrightness(pos, 0) < 13) {
                    pos = offset;
                }
                offset = pos.offset(random.nextInt(3) - 1, random.nextInt(2) - random.nextInt(2), random.nextInt(3) - 1);
            }
            if (level.isEmptyBlock(offset) && state.canSurvive(level, offset) && level.getRawBrightness(pos, 0) < 13) {
                level.setBlock(offset, defaultBlockState().setValue(AGE, 0), 2);
            }
        }
    }

}
