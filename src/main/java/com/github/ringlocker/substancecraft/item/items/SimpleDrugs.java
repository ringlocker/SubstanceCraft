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

public class SimpleDrugs {

    public static class Diphenhydramine extends Item {

        public Diphenhydramine(Properties properties) {
            super(properties);
        }

        @Override
        public @NotNull ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
            if (!(livingEntity instanceof Player player)) return stack;
            if (!livingEntity.hasInfiniteMaterials()) stack.setCount(stack.getCount() - 1);
            if (!level.isClientSide()) SubstanceEffectTicker.playerConsumeDrug((ServerPlayer) player, Drug.DIPHENHYDRAMINE);
            return stack;
        }
    }

    public static class Ketamine extends Item {

        public Ketamine(Properties properties) {
            super(properties);
        }

        @Override
        public @NotNull ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
            if (!(livingEntity instanceof Player player)) return stack;
            if (!livingEntity.hasInfiniteMaterials()) stack.setCount(stack.getCount() - 1);
            if (!level.isClientSide()) SubstanceEffectTicker.playerConsumeDrug((ServerPlayer) player, Drug.KETAMINE);
            return stack;
        }
    }

    public static class Amphetamine extends Item {

        public Amphetamine(Properties properties) {
            super(properties);
        }

        @Override
        public @NotNull ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
            if (!(livingEntity instanceof Player player)) return stack;
            if (!livingEntity.hasInfiniteMaterials()) stack.setCount(stack.getCount() - 1);
            if (!level.isClientSide()) SubstanceEffectTicker.playerConsumeDrug((ServerPlayer) player, Drug.AMPHETAMINE);
            return stack;
        }
    }

    public static class Cocaine extends Item {

        public Cocaine(Properties properties) {
            super(properties);
        }

        @Override
        public @NotNull ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
            if (!(livingEntity instanceof Player player)) return stack;
            if (!livingEntity.hasInfiniteMaterials()) stack.setCount(stack.getCount() - 1);
            if (!level.isClientSide()) SubstanceEffectTicker.playerConsumeDrug((ServerPlayer) player, Drug.COCAINE);
            return stack;
        }
    }
}
