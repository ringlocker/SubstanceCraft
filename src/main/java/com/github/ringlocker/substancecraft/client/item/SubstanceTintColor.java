package com.github.ringlocker.substancecraft.client.item;

import com.github.ringlocker.substancecraft.items.SubstanceItem;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.util.ARGB;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public record SubstanceTintColor(int defaultColor) implements ItemTintSource {

    public static final int DEFAULT_COLOR = ARGB.color(255, 224, 7, 239);
    public static final int THICK_BLACK_LIQUID = ARGB.color(225, 28, 26, 35);
    public static final int DARK_BLUE_LIQUID = ARGB.color(165, 19, 17, 155);
    public static final int LIGHT_BLUE_LIQUID = ARGB.color(200, 9, 164, 242);
    public static final int LIGHT_TEAL_LIQUID = ARGB.color(150, 19, 140, 120);
    public static final int LIGHT_YELLOW_GAS = ARGB.color(120, 242, 206, 75);
    public static final int VERY_LIGHT_YELLOW_GAS = ARGB.color(90, 247, 224, 143);
    public static final int VERY_LIGHT_YELLOW_LIQUID = ARGB.color(140, 223, 224, 161);
    public static final int YELLOW_LIQUID = ARGB.color(200, 214, 180, 59);
    public static final int PISS_YELLOW_LIQUID = ARGB.color(200, 216, 166, 39);
    public static final int FOGGY_ORANGE_LIQUID = ARGB.color(155, 206, 93, 18);
    public static final int LIGHT_FOGGY_ORANGE_LIQUID = ARGB.color(140, 183, 89, 27);
    public static final int RED_LIQUID = ARGB.color(200, 229, 41, 41);
    public static final int CLEAR_GAS = ARGB.color(75, 195, 219, 214);
    public static final int CLEAR_LIQUID = ARGB.color(155, 178, 191, 193);

    public static final MapCodec<SubstanceTintColor> MAP_CODEC = RecordCodecBuilder.mapCodec((instance) -> instance
            .group(ExtraCodecs.RGB_COLOR_CODEC
                    .fieldOf("default")
                    .forGetter(SubstanceTintColor::defaultColor))
            .apply(instance, SubstanceTintColor::new));

    public SubstanceTintColor() {
        this(DEFAULT_COLOR);
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
