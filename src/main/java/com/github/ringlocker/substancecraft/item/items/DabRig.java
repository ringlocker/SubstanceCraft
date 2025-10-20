package com.github.ringlocker.substancecraft.item.items;

import com.github.ringlocker.substancecraft.item.SubstanceCraftItems;
import com.github.ringlocker.substancecraft.util.particle.Smoke;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

public class DabRig {

    public static class DabRigItem extends Item {

        public DabRigItem(Properties properties) {
            super(properties);
        }

        @Override
        public @NotNull InteractionResult use(Level level, Player player, InteractionHand interactionHand) {
            if (!(player.getOffhandItem().is(SubstanceCraftItems.HASH))) return InteractionResult.FAIL;
            return super.use(level, player, interactionHand);
        }

        @Override
        public @NotNull ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity) {
            if (!(livingEntity instanceof Player player)) return itemStack;
            if (player.getOffhandItem().is(SubstanceCraftItems.HASH)) {
                ItemStack hash = player.getOffhandItem();
                hash.setCount(hash.getCount() - 1);
                player.getCooldowns().addCooldown(itemStack, 10 * 20);

                Smoke.generateSmokeParticles(player, level, 1.0F, 5, 0, 3);

                Vector3f positionVector = new Vector3f((float) player.getX(), (float) player.getY(), (float) player.getZ())
                                .add(player.getLookAngle().normalize().toVector3f().mul(0.8f))
                                .add(new Vector3f(0.0f, 1.5f, 0.0f));

                level.playSound(
                        player,
                        new BlockPos(new Vec3i(Math.round(positionVector.x), Math.round(positionVector.y), Math.round(positionVector.z))),
                        SoundEvents.BREWING_STAND_BREW,
                        SoundSource.BLOCKS,
                        1.0F,
                        1.0F
                );

                int previousAmplifier = -1;
                int duration = (180 * 20);
                for (MobEffectInstance effect : player.getActiveEffects()) {
                    if (effect.getEffect() == MobEffects.HUNGER) {
                        previousAmplifier = effect.getAmplifier();
                        duration = (effect.getDuration() / 2) + duration;
                    }
                }
                if (previousAmplifier > 3) {
                    int secondaryEffectLevel = Math.min(9, previousAmplifier + 1);
                    player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, duration, secondaryEffectLevel, true, false));
                    player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, duration, Math.min(secondaryEffectLevel, 4), true, false));
                    player.addEffect(new MobEffectInstance(MobEffects.MINING_FATIGUE, duration, Math.min(secondaryEffectLevel, 4), true, false));
                }
                player.addEffect(new MobEffectInstance(MobEffects.HUNGER, duration, Math.min(previousAmplifier + 1, 9), true, false));
                player.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, duration, Math.min((previousAmplifier < 4 ? previousAmplifier : 3) + 1, 9), true, false));
            }
            return itemStack;
        }
    }

    public static class EmptyDabRigItem extends Item {

        public EmptyDabRigItem(Properties properties) {
            super(properties);
        }

        @Override
        public @NotNull InteractionResult use(Level level, Player player, InteractionHand usedHand) {
            ItemStack itemStack = player.getItemInHand(usedHand);
            BlockHitResult blockHitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);
            if (blockHitResult.getType() != HitResult.Type.MISS) {
                if (blockHitResult.getType() == HitResult.Type.BLOCK) {
                    BlockPos blockPos = blockHitResult.getBlockPos();
                    if (!level.mayInteract(player, blockPos)) {
                        return InteractionResult.PASS;
                    }
                    if (level.getFluidState(blockPos).is(FluidTags.WATER)) {
                        level.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.BOTTLE_FILL, SoundSource.NEUTRAL, 1.0F, 1.0F);
                        level.gameEvent(player, GameEvent.FLUID_PICKUP, blockPos);
                        return InteractionResult.SUCCESS.heldItemTransformedTo(this.turnBottleIntoItem(itemStack, player, new ItemStack(SubstanceCraftItems.DAB_RIG)));
                    }
                }
            }
            return InteractionResult.PASS;
        }

        protected ItemStack turnBottleIntoItem(ItemStack bottleStack, Player player, ItemStack filledBottleStack) {
            player.awardStat(Stats.ITEM_USED.get(this));
            return ItemUtils.createFilledResult(bottleStack, player, filledBottleStack);
        }
    }

}