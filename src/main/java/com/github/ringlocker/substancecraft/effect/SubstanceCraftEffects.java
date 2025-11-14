package com.github.ringlocker.substancecraft.effect;

import com.github.ringlocker.substancecraft.SubstanceCraft;
import com.github.ringlocker.substancecraft.effect.effects.Blur;
import com.github.ringlocker.substancecraft.effect.effects.generic.BasicEffect;
import com.github.ringlocker.substancecraft.effect.effects.ColorEnhancement;
import com.github.ringlocker.substancecraft.effect.effects.SimpleEffects;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.HashMap;

public class SubstanceCraftEffects {

    private static final HashMap<ResourceLocation, Holder<MobEffect>> effects = new HashMap<>();

    public static final Holder<MobEffect> STONED = register("stoned", new BasicEffect(MobEffectCategory.NEUTRAL));
    public static final Holder<MobEffect> DPH = register("dph", new BasicEffect(MobEffectCategory.NEUTRAL));
    public static final Holder<MobEffect> COKE = register("coke", new BasicEffect(MobEffectCategory.NEUTRAL));
    public static final Holder<MobEffect> KETAMINE = register("ketamine", new BasicEffect(MobEffectCategory.NEUTRAL));
    public static final Holder<MobEffect> AMPHETAMINE = register("amphetamine", new BasicEffect(MobEffectCategory.NEUTRAL));
    public static final Holder<MobEffect> TWO_CB = register("two_cb", new BasicEffect(MobEffectCategory.NEUTRAL));

    public static final Holder<MobEffect> FAST = register("fast", new BasicEffect(MobEffectCategory.BENEFICIAL)
            .addAttributeModifier(Attributes.MOVEMENT_SPEED, ResourceLocation.withDefaultNamespace("effect.fast"), 0.12F, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
    public static final Holder<MobEffect> SLOW = register("slow", new BasicEffect(MobEffectCategory.HARMFUL)
            .addAttributeModifier(Attributes.MOVEMENT_SPEED, ResourceLocation.withDefaultNamespace("effect.slow"), -0.09F, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

    public static final Holder<MobEffect> NARCAN = register("narcan", new BasicEffect(MobEffectCategory.BENEFICIAL));
    public static final Holder<MobEffect> OVERDOSE = register("overdose", new SimpleEffects.OpioidOverdose());
    public static final Holder<MobEffect> NOT_HUNGRY = register("not_hungry", new SimpleEffects.NotHungry());
    public static final Holder<MobEffect> HUNGRY = register("hungry", new SimpleEffects.Hungry());
    public static final Holder<MobEffect> ELEVATED_HEART_RATE = register("elevated_heart_rate", new SimpleEffects.Hungry());
    public static final Holder<MobEffect> COLOR_ENHANCEMENT = register("color_enhancement", new ColorEnhancement());
    public static final Holder<MobEffect> BLUR = register("blur", new Blur());

    private static Holder<MobEffect> register(String id, MobEffect effect) {
        ResourceLocation resourceLocation = ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, id);
        Holder<MobEffect> effectHolder =  Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, resourceLocation, effect);
        effects.put(resourceLocation, effectHolder);
        return effectHolder;
    }

    public static void registerEffects() {

    }

    public static Holder<MobEffect> getEffect(ResourceLocation id) {
        return effects.getOrDefault(id, STONED);
    }

}
