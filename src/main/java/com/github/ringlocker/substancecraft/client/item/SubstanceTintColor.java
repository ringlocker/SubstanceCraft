package com.github.ringlocker.substancecraft.client.item;

import com.github.ringlocker.substancecraft.item.items.SubstanceItem;
import com.github.ringlocker.substancecraft.item.SubstanceTintColors;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public record SubstanceTintColor(int defaultColor) implements ItemTintSource {

    public static final MapCodec<SubstanceTintColor> MAP_CODEC = RecordCodecBuilder.mapCodec((instance) -> instance
            .group(ExtraCodecs.RGB_COLOR_CODEC
                    .fieldOf("default")
                    .forGetter(SubstanceTintColor::defaultColor))
            .apply(instance, SubstanceTintColor::new));

    public SubstanceTintColor() {
        this(SubstanceTintColors.DEFAULT_COLOR);
    }

    public int calculate(ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entity) {
        if (stack.getItem() instanceof SubstanceItem) {
            return ((SubstanceItem) stack.getItem()).getColor();
        }
        return defaultColor;
    }

    public @NotNull MapCodec<SubstanceTintColor> type() {
        return MAP_CODEC;
    }
}
