package com.github.ringlocker.substancecraft.item.items;

import com.github.ringlocker.substancecraft.effect.SubstanceEffectTicker;
import com.github.ringlocker.substancecraft.item.Drug;
import com.github.ringlocker.substancecraft.util.particle.Smoke;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.List;

public class DrugConsumerItem extends Item {

    private final List<Consumable> consumables;
    @Nullable private final SmokeSettings smokeSettings;

    public DrugConsumerItem(Item.Properties properties, List<Consumable> consumables, @Nullable SmokeSettings smokeSettings) {
        super(properties);
        this.consumables = consumables;
        this.smokeSettings = smokeSettings;
    }

    @Override
    public @NotNull InteractionResult use(@NotNull Level level, Player player, @NotNull InteractionHand interactionHand) {
        if (!canUse(player)) return InteractionResult.FAIL;
        return super.use(level, player, interactionHand);
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack itemStack, @NotNull Level level, @NotNull LivingEntity livingEntity) {
        if (!(livingEntity instanceof Player player)) return itemStack;
        Consumable consumable = getTargetConsumable(player);
        ItemStack consumeItem = getTargetItemStack(player, consumable);

        if (consumable != null && consumeItem != null) {
            consumeItem.setCount(consumeItem.getCount() - 1);
            if (!level.isClientSide())
                SubstanceEffectTicker.playerConsumeDrug((ServerPlayer) player, consumable.effect);
            player.getCooldowns().addCooldown(itemStack, 8 * 20);

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

            if (smokeSettings == null) return itemStack;
            Smoke.generateSmokeParticles(
                    player,
                    level,
                    smokeSettings.smokeVelocityMultiplier,
                    smokeSettings.widthInEighthBlocks,
                    smokeSettings.minRollsPerSection,
                    smokeSettings.maxRollsPerSection
            );

        }
        return itemStack;
    }

    private boolean canUse(Player player) {
        for (Consumable consumable : consumables) {
            if (player.getInventory().countItem(consumable.item) > 0) return true;
        }
        return false;
    }

    @Nullable
    private Consumable getTargetConsumable(Player player) {
        if (consumables.isEmpty()) return null;
        ItemStack offhand = player.getOffhandItem();
        for (Consumable consumable : consumables) {
            if (offhand.is(consumable.item)) return consumable;
        }
        for (ItemStack stack : player.getInventory()) {
            for (Consumable consumable : consumables) {
                if (stack.is(consumable.item)) return consumable;
            }
        }
        return null;
    }

    @Nullable
    private ItemStack getTargetItemStack(Player player, Consumable consumable) {
        if (consumables.isEmpty()) return null;
        ItemStack offhand = player.getOffhandItem();
        if (offhand.is(consumable.item)) return offhand;
        for (ItemStack stack : player.getInventory()) {
            if (stack.is(consumable.item)) return stack;
        }
        return null;
    }

    public record Consumable(Item item, Drug effect) {
    }

    public record SmokeSettings(float smokeVelocityMultiplier, int widthInEighthBlocks, int minRollsPerSection, int maxRollsPerSection) {
    }

}
