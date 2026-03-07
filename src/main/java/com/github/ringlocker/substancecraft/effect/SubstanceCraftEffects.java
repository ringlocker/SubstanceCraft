package com.github.ringlocker.substancecraft.effect;

import com.github.ringlocker.substancecraft.SubstanceCraft;
import com.github.ringlocker.substancecraft.effect.effects.ColorResolution;
import com.github.ringlocker.substancecraft.effect.effects.Mosaic;
import com.github.ringlocker.substancecraft.effect.effects.BasicEffect;
import com.github.ringlocker.substancecraft.effect.effects.ColorEnhancement;
import com.github.ringlocker.substancecraft.effect.effects.SimpleEffects;
import com.github.ringlocker.substancecraft.effect.effects.DynamicColor;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.HashMap;

public class SubstanceCraftEffects {

    private static final HashMap<Identifier, Holder<MobEffect>> effects = new HashMap<>();

    public static final Holder<MobEffect> STONED = register("stoned", new BasicEffect(MobEffectCategory.NEUTRAL));
    public static final Holder<MobEffect> DPH = register("dph", new BasicEffect(MobEffectCategory.NEUTRAL));
    public static final Holder<MobEffect> COKE = register("coke", new BasicEffect(MobEffectCategory.NEUTRAL));
    public static final Holder<MobEffect> KETAMINE = register("ketamine", new BasicEffect(MobEffectCategory.NEUTRAL));
    public static final Holder<MobEffect> AMPHETAMINE = register("amphetamine", new BasicEffect(MobEffectCategory.NEUTRAL));
    public static final Holder<MobEffect> TWO_CB = register("two_cb", new BasicEffect(MobEffectCategory.NEUTRAL));

    public static final Holder<MobEffect> FAST = register("fast", new BasicEffect(MobEffectCategory.BENEFICIAL)
            .addAttributeModifier(Attributes.MOVEMENT_SPEED, Identifier.withDefaultNamespace("effect.fast"), 0.12F, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
    public static final Holder<MobEffect> SLOW = register("slow", new BasicEffect(MobEffectCategory.HARMFUL)
            .addAttributeModifier(Attributes.MOVEMENT_SPEED, Identifier.withDefaultNamespace("effect.slow"), -0.09F, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

    public static final Holder<MobEffect> NARCAN = register("narcan", new BasicEffect(MobEffectCategory.BENEFICIAL));
    public static final Holder<MobEffect> OVERDOSE = register("overdose", new SimpleEffects.OpioidOverdose());
    public static final Holder<MobEffect> NOT_HUNGRY = register("not_hungry", new SimpleEffects.NotHungry());
    public static final Holder<MobEffect> HUNGRY = register("hungry", new SimpleEffects.Hungry());
    public static final Holder<MobEffect> ELEVATED_HEART_RATE = register("elevated_heart_rate", new SimpleEffects.Hungry());
    public static final Holder<MobEffect> CARDIAC_ARREST = register("cardiac_arrest", new SimpleEffects.CardiacArrest());
    public static final Holder<MobEffect> COLOR_ENHANCEMENT = register("color_enhancement", new ColorEnhancement());
    public static final Holder<MobEffect> MOSAIC = register("mosaic", new Mosaic());
    public static final Holder<MobEffect> COLOR_RESOLUTION = register("color_resolution", new ColorResolution());
    public static final Holder<MobEffect> DYNAMIC_COLOR = register("dynamic_color", new DynamicColor());
    public static final Holder<MobEffect> TIME_COMPRESSION = register("time_compression", new BasicEffect(MobEffectCategory.NEUTRAL));
    public static final Holder<MobEffect> TIME_DILATION = register("time_dilation", new BasicEffect(MobEffectCategory.NEUTRAL));

    private static Holder<MobEffect> register(String id, MobEffect effect) {
        Identifier identifier = Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, id);
        Holder<MobEffect> effectHolder =  Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, identifier, effect);
        effects.put(identifier, effectHolder);
        return effectHolder;
    }

    public static void registerEffects() {
    }

    public static Holder<MobEffect> getEffect(Identifier id) {
        return effects.getOrDefault(id, STONED);
    }

}
