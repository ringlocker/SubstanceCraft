package com.github.ringlocker.substancecraft.block.blocks;

import com.github.ringlocker.substancecraft.block.BushLikeCrop;
import com.github.ringlocker.substancecraft.block.SubstanceCraftBlocks;
import com.github.ringlocker.substancecraft.item.SubstanceCraftItems;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class CocaCrop extends OneBlockHarvestablePlant {

    private final MapCodec<CocaCrop> CODEC = simpleCodec(CocaCrop::new);
    public static final IntegerProperty AGE_PROPERTY = BlockStateProperties.AGE_5;

    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 5.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 7.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 13.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D)
    };

    public CocaCrop(Properties properties) {
        super(properties, AGE_PROPERTY, SHAPE_BY_AGE);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE_PROPERTY);
    }

    @Override
    public @NotNull MapCodec<CocaCrop> codec() {
        return CODEC;
    }

    public void harvest(Level level, BlockPos pos) {
        int leavesDropped = 1 + level.getRandom().nextInt(4);
        int seedsDropped = 1 + level.getRandom().nextInt(2);
        popResource(level, pos, new ItemStack(SubstanceCraftItems.COCA_LEAVES, leavesDropped));
        popResource(level, pos, new ItemStack(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.COCA_CROP), seedsDropped));
    }

}
