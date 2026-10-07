package com.github.ringlocker.substancecraft.block.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.Optional;

public abstract class GrowingPlantBlock extends VegetationBlock implements BonemealableBlock {

    protected final IntegerProperty AGE;
    protected final int MAX_AGE;
    protected final VoxelShape[] SHAPE_BY_AGE;

    private int maxBonemealGrowAmount = 1;
    private int minBonemealGrowAmount = 1;
    private int optimalConditionGrowChance = 12;
    private int normalGrowChance = 18;
    private boolean requiresFarmland = true;

    protected GrowingPlantBlock(Properties properties, VoxelShape[] shapeByAge, IntegerProperty ageProperty) {
        super(properties);
        Optional<Property.Value<Integer>> optionalMaxAge = ageProperty.getAllValues().max(Comparator.comparingInt(Property.Value::value));
        AGE = ageProperty;
        SHAPE_BY_AGE = shapeByAge;
        MAX_AGE = optionalMaxAge.map(Property.Value::value).orElse(-1);
        registerDefaultState(stateDefinition.any().setValue(AGE, 0));
    }

    public void setMaxBonemealGrowAmount(int maxBonemealGrowAmount) {
        this.maxBonemealGrowAmount = maxBonemealGrowAmount;
    }

    public void setMinBonemealGrowAmount(int minBonemealGrowAmount) {
        this.minBonemealGrowAmount = minBonemealGrowAmount;
    }

    public void setOptimalConditionGrowChance(int optimalConditionGrowChance) {
        this.optimalConditionGrowChance = optimalConditionGrowChance;
    }

    public void setNormalGrowChance(int normalGrowChance) {
        this.normalGrowChance = normalGrowChance;
    }

    public void setRequiresFarmland(boolean requiresFarmland) {
        this.requiresFarmland = requiresFarmland;
    }

    public int getMaxBonemealGrowAmount() {
        return maxBonemealGrowAmount;
    }

    public int getMinBonemealGrowAmount() {
        return minBonemealGrowAmount;
    }

    public int getOptimalConditionGrowChance() {
        return optimalConditionGrowChance;
    }

    public int getNormalGrowChance() {
        return normalGrowChance;
    }

    public boolean requiresFarmland() {
        return requiresFarmland;
    }

    @Override
    protected @NotNull VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE_BY_AGE == null ? super.getShape(state, level, pos, context) : SHAPE_BY_AGE[state.getValue(AGE)];
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return state.getValue(AGE) < MAX_AGE;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource randomSource) {
        if (requiresFarmland() && !level.getBlockState(pos.below()).is(Blocks.FARMLAND)) return;

        int random = level.getBlockState(pos.below()).is(Blocks.FARMLAND) ?
                        randomSource.nextInt(getOptimalConditionGrowChance()) :
                        randomSource.nextInt(getNormalGrowChance());

        if (random == 0) {
            if (state.getValue(AGE) < MAX_AGE) {
                growCrop(level, pos, state, 1);
            }
        }
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!state.canSurvive(level, pos)) {
            level.destroyBlock(pos, true);
        }
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
       if (requiresFarmland()) return level.getBlockState(pos).is(Blocks.FARMLAND);
       else return super.mayPlaceOn(state, level, pos);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return state.getValue(AGE) < MAX_AGE;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        growCrop(level, pos, state, getRandomGrowAmount(random));
    }

    protected int getRandomGrowAmount(RandomSource randomSource) {
        return getMinBonemealGrowAmount() + randomSource.nextInt(1 + (getMaxBonemealGrowAmount() - getMinBonemealGrowAmount()));
    }

    protected void growCrop(ServerLevel level, BlockPos pos, BlockState state, int amount) {
        for (int i = 0; i < amount; i++) {
            grow(level, pos, state);
        }
    }

    protected void grow(ServerLevel level, BlockPos pos, BlockState state) {
        level.setBlock(pos, state.setValue(AGE, Math.min(state.getValue(AGE) + 1, MAX_AGE)), Block.UPDATE_CLIENTS);
    }


}
