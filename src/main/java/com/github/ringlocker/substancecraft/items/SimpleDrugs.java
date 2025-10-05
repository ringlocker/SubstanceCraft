package com.github.ringlocker.substancecraft.items;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
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
            player.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 360, 0));
            player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 360, 0));
            player.addEffect(new MobEffectInstance(MobEffects.POISON, 180, 0));
            player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 360, 0));
            if (!livingEntity.hasInfiniteMaterials()) stack.setCount(stack.getCount() - 1);
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
            player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 20*60, 0));
            if (!livingEntity.hasInfiniteMaterials()) stack.setCount(stack.getCount() - 1);
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
            player.addEffect(new MobEffectInstance(MobEffects.SPEED, 20*600, 0));
            if (!livingEntity.hasInfiniteMaterials()) stack.setCount(stack.getCount() - 1);
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
            player.addEffect(new MobEffectInstance(MobEffects.SPEED, 20*600, 0));
            if (!livingEntity.hasInfiniteMaterials()) stack.setCount(stack.getCount() - 1);
            return stack;
        }
    }
}
