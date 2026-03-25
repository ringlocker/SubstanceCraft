package com.github.ringlocker.substancecraft.block;

import com.github.ringlocker.substancecraft.effect.SubstanceEffectTicker;
import com.github.ringlocker.substancecraft.item.Drug;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

public class PlaceableDrugItem extends BlockItem {

    private final Drug drug;

    public PlaceableDrugItem(Block block, Item.Properties properties, Drug drug) {
        super(block, properties);
        this.drug = drug;
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack stack, @NotNull Level level, @NotNull LivingEntity livingEntity) {
        if (!(livingEntity instanceof Player player)) return stack;
        if (!livingEntity.hasInfiniteMaterials()) stack.setCount(stack.getCount() - 1);
        if (!level.isClientSide()) SubstanceEffectTicker.playerConsumeDrug((ServerPlayer) player, drug);
        return stack;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        InteractionResult placeResult = this.place(new BlockPlaceContext(context));
        if (placeResult == InteractionResult.SUCCESS && context.getLevel().getBlockState(context.getClickedPos()).is(BlockTags.MUSHROOM_GROW_BLOCK)) {
            return placeResult;
        } else {
            return super.use(context.getLevel(), context.getPlayer(), context.getHand());
        }
    }

}
