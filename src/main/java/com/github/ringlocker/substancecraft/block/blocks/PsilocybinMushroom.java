package com.github.ringlocker.substancecraft.block.blocks;

import com.github.ringlocker.substancecraft.block.MushroomWithGrowthStages;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class PsilocybinMushroom extends MushroomWithGrowthStages {

    public static IntegerProperty AGE = IntegerProperty.create("age", 0, 2);
    public static final MapCodec<MushroomWithGrowthStages> CODEC = simpleCodec(PsilocybinMushroom::new);

    public PsilocybinMushroom(Properties properties) {
        super(properties, 2, AGE);
    }

    @Override
    protected MapCodec<? extends VegetationBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

}
