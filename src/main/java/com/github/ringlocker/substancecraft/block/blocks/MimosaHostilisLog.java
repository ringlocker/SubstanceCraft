package com.github.ringlocker.substancecraft.block.blocks;

import com.github.ringlocker.substancecraft.block.SubstanceCraftBlocks;
import com.github.ringlocker.substancecraft.item.SubstanceCraftItems;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class MimosaHostilisLog extends RotatedPillarBlock {

    public MimosaHostilisLog(Properties properties) {
        super(properties);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (stack.is(ItemTags.AXES)) {
            level.setBlock(pos, SubstanceCraftBlocks.STRIPPED_MIMOSA_HOSTILIS_LOG.defaultBlockState(), Block.UPDATE_CLIENTS);
            stack.setDamageValue(stack.getDamageValue() + 1);
            popResource(level, pos, new ItemStack(SubstanceCraftItems.MIMOSA_HOSTILIS_ROOT_BARK));
            return InteractionResult.SUCCESS;
        } else {
            return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
        }
    }
}
