package com.github.ringlocker.substancecraft.effect;

import com.github.ringlocker.substancecraft.SubstanceCraft;
import com.github.ringlocker.substancecraft.effect.effects.SimpleEffects;
import com.github.ringlocker.substancecraft.item.SubstanceTintColors;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
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

    public static final Holder<MobEffect> FAST = register("fast", new BasicEffect(MobEffectCategory.BENEFICIAL)
            .addAttributeModifier(Attributes.MOVEMENT_SPEED, ResourceLocation.withDefaultNamespace("effect.fast"), 0.075F, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
    public static final Holder<MobEffect> SLOW = register("slow", new BasicEffect(MobEffectCategory.HARMFUL)
            .addAttributeModifier(Attributes.MOVEMENT_SPEED, ResourceLocation.withDefaultNamespace("effect.slow"), -0.075F, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

    public static final Holder<MobEffect> NARCAN = register("narcan", new BasicEffect(MobEffectCategory.BENEFICIAL));
    public static final Holder<MobEffect> OVERDOSE = register("overdose", new SimpleEffects.OpioidOverdose());
    public static final Holder<MobEffect> NOT_HUNGRY = register("not_hungry", new SimpleEffects.NotHungry());
    public static final Holder<MobEffect> HUNGRY = register("hungry", new SimpleEffects.Hungry());

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

    protected static class BasicEffect extends MobEffect {
        public BasicEffect(MobEffectCategory category) {
            super(category, SubstanceTintColors.FULL_TRANSPARENT);
        }
    }

    public static abstract class TickingEffect extends BasicEffect {

        protected TickingEffect(MobEffectCategory category) {
            super(category);
        }

        protected abstract boolean shouldTick(int duration);

        protected abstract void tick(ServerLevel level, LivingEntity entity, int amplifier);

        @Override
        public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
            return this.shouldTick(duration);
        }

        @Override
        public boolean applyEffectTick(ServerLevel level, LivingEntity entity, int amplifier) {
            this.tick(level, entity, amplifier);
            return super.applyEffectTick(level, entity, amplifier);
        }

    }

}
