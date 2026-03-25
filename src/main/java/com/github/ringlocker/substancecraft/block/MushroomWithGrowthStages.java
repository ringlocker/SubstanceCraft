package com.github.ringlocker.substancecraft.block;

import com.github.ringlocker.substancecraft.item.SubstanceCraftItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;

public abstract class MushroomWithGrowthStages extends VegetationBlock implements BonemealableBlock {

    private static final VoxelShape SHAPE = Block.column(6.0, 0.0, 6.0);

    private final IntegerProperty AGE;
    private final int maxAge;

    public MushroomWithGrowthStages(BlockBehaviour.Properties properties, int maxAge, IntegerProperty age) {
        super(properties);
        this.AGE = age;
        this.maxAge = maxAge;
        registerDefaultState(defaultBlockState().setValue(AGE, 0));
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        trySpread(state, level, pos, random);
        int rand = (level.getBlockState(pos.below()).is(BlockTags.MUSHROOM_GROW_BLOCK)) ? random.nextInt(21) : random.nextInt(36);
        if (rand == 0) {
            if (state.getValue(AGE) < maxAge) {
                this.performBonemeal(level, level.random, pos, state);
            }
        }
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.isSolidRender();
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos belowPos = pos.below();
        BlockState below = level.getBlockState(belowPos);
        if (below.is(BlockTags.MUSHROOM_GROW_BLOCK)) return true;
        return level.getRawBrightness(pos, 0) < 13 && this.mayPlaceOn(below, level, belowPos);
    }


    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return state.getValue(AGE) < maxAge;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        level.setBlock(pos, state.setValue(AGE, Math.min(state.getValue(AGE) + 1, maxAge)), Block.UPDATE_CLIENTS);
    }

    @Override
    protected List<ItemStack> getDrops(BlockState state, LootParams.Builder params) {
        return List.of(new ItemStack(SubstanceCraftItems.PSILOCYBIN, state.getValue(AGE) + 1));
    }

    private void trySpread(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (random.nextInt(25) == 0) {
            int max = 5;
            for (BlockPos blockPos : BlockPos.betweenClosed(pos.offset(-4, -1, -4), pos.offset(4, 1, 4))) {
                if (!level.getBlockState(blockPos).is(this) || --max > 0) continue;
                return;
            }
            BlockPos offset = pos.offset(random.nextInt(3) - 1, random.nextInt(2) - random.nextInt(2), random.nextInt(3) - 1);
            for (int i = 0; i < 4; ++i) {
                if (level.isEmptyBlock(offset) && state.canSurvive(level, offset)) {
                    pos = offset;
                }
                offset = pos.offset(random.nextInt(3) - 1, random.nextInt(2) - random.nextInt(2), random.nextInt(3) - 1);
            }
            if (level.isEmptyBlock(offset) && state.canSurvive(level, offset)) {
                level.setBlock(offset, defaultBlockState().setValue(AGE, 0), 2);
            }
        }
    }

}
