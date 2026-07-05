package com.github.ringlocker.substancecraft.item.items;

import com.github.ringlocker.substancecraft.effect.SubstanceEffectTicker;
import com.github.ringlocker.substancecraft.item.Drug;
import com.github.ringlocker.substancecraft.item.SubstanceCraftItems;
import com.github.ringlocker.substancecraft.util.particle.Smoke;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

public class PenjaminItem extends Item {

    private final Drug drug;
    private final DrugConsumerItem.SmokeSettings smokeSettings;

    public PenjaminItem(Properties properties, Drug drug, DrugConsumerItem.SmokeSettings smokeSettings) {
        super(properties);
        this.drug = drug;
        this.smokeSettings = smokeSettings;
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack itemStack, @NotNull Level level, @NotNull LivingEntity livingEntity) {
        if (!(livingEntity instanceof Player player)) return itemStack;
        if (itemStack.getDamageValue() < itemStack.getMaxDamage()) {
            if (!level.isClientSide()) {
                SubstanceEffectTicker.playerConsumeDrug((ServerPlayer) player, drug);
                itemStack.setDamageValue(itemStack.getDamageValue() + 1);
            }

            player.getCooldowns().addCooldown(itemStack, 5 * 20);

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
                    smokeSettings.smokeVelocityMultiplier(),
                    smokeSettings.widthInEighthBlocks(),
                    smokeSettings.minRollsPerSection(),
                    smokeSettings.maxRollsPerSection()
            );
            return itemStack;
        }

        if (itemStack.getDamageValue() >= itemStack.getMaxDamage()) {
            livingEntity.drop(new ItemStack(SubstanceCraftItems.EMPTY_CART), true, false);
            livingEntity.drop(new ItemStack(SubstanceCraftItems.PEN_BATTERY), true, false);
            return new ItemStack(Items.AIR);
        }

        return itemStack;
    }



}
