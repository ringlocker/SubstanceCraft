package com.github.ringlocker.substancecraft.item.items;

import com.github.ringlocker.substancecraft.effect.SubstanceEffectTicker;
import com.github.ringlocker.substancecraft.item.Drug;
import com.github.ringlocker.substancecraft.util.particle.Smoke;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class DrugItem extends Item {

    private final Drug drug;
    private final DrugConsumerItem.SmokeSettings smokeSettings;

    public DrugItem(Properties properties, Drug drug) {
        this(properties, drug, null);
    }

    public DrugItem(Properties properties, Drug drug, DrugConsumerItem.SmokeSettings smokeSettings) {
        super(properties);
        this.drug = drug;
        this.smokeSettings = smokeSettings;
    }

    public @NotNull ItemStack useItemWithDurability(@NotNull ItemStack stack, @NotNull Level level, @NotNull LivingEntity livingEntity) {
        if (!(livingEntity instanceof Player player)) return stack;
        stack.setDamageValue(stack.getDamageValue() + 1);

        if (!level.isClientSide()) SubstanceEffectTicker.playerConsumeDrug((ServerPlayer) player, drug);

        if (smokeSettings != null) {
            Smoke.generateSmokeParticles(
                    player,
                    level,
                    smokeSettings.smokeVelocityMultiplier(),
                    smokeSettings.widthInEighthBlocks(),
                    smokeSettings.minRollsPerSection(),
                    smokeSettings.maxRollsPerSection()
            );
        }

        if (stack.getDamageValue() >= stack.getMaxDamage()) return new ItemStack(Items.AIR);

        return stack;
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack stack, @NotNull Level level, @NotNull LivingEntity livingEntity) {
        if (stack.getMaxDamage() > 1) return useItemWithDurability(stack, level, livingEntity);

        if (!(livingEntity instanceof Player player)) return stack;
        if (!livingEntity.hasInfiniteMaterials()) stack.setCount(stack.getCount() - 1);
        if (!level.isClientSide()) SubstanceEffectTicker.playerConsumeDrug((ServerPlayer) player, drug);


        if (smokeSettings != null) {
            Smoke.generateSmokeParticles(
                    player,
                    level,
                    smokeSettings.smokeVelocityMultiplier(),
                    smokeSettings.widthInEighthBlocks(),
                    smokeSettings.minRollsPerSection(),
                    smokeSettings.maxRollsPerSection()
            );
        }

        return stack;
    }

}
