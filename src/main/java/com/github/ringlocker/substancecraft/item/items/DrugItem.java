package com.github.ringlocker.substancecraft.item.items;

import com.github.ringlocker.substancecraft.effect.SubstanceEffectTicker;
import com.github.ringlocker.substancecraft.item.Drug;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class DrugItem extends Item {

    private final Drug drug;

    public DrugItem(Properties properties, Drug drug) {
        super(properties);
        this.drug = drug;
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack stack, @NotNull Level level, @NotNull LivingEntity livingEntity) {
        if (!(livingEntity instanceof Player player)) return stack;
        if (!livingEntity.hasInfiniteMaterials()) stack.setCount(stack.getCount() - 1);
        if (!level.isClientSide()) SubstanceEffectTicker.playerConsumeDrug((ServerPlayer) player, drug);
        return stack;
    }

}
