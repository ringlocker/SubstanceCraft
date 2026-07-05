package com.github.ringlocker.substancecraft.client.shader;

import com.github.ringlocker.substancecraft.effect.SubstanceCraftEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.server.IntegratedServer;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;

import java.util.HashMap;
import java.util.Map;

public class PlayerEffectState {

    private static final Map<Holder<MobEffect>, Integer> effectAmplifiers = new HashMap<>(Map.of(
            SubstanceCraftEffects.MOSAIC, -1,
            SubstanceCraftEffects.COLOR_ENHANCEMENT, -1,
            SubstanceCraftEffects.COLOR_RESOLUTION, -1,
            SubstanceCraftEffects.DYNAMIC_COLOR, -1,
            SubstanceCraftEffects.SURFACE_WARP, -1,
            SubstanceCraftEffects.DOUBLE_VISION, -1
    ));

    private static final Map<Holder<MobEffect>, Float> transitionState = new HashMap<>(Map.of(
            SubstanceCraftEffects.MOSAIC, 0.0F,
            SubstanceCraftEffects.COLOR_ENHANCEMENT, 0.0F,
            SubstanceCraftEffects.COLOR_RESOLUTION, 0.0F,
            SubstanceCraftEffects.DYNAMIC_COLOR, 0.0F,
            SubstanceCraftEffects.SURFACE_WARP, 0.0F,
            SubstanceCraftEffects.DOUBLE_VISION, 0.0F
    ));

    private static final int secondsToTransition = 5;
    private static final float transitionPerTick = 1.0F / ((float) secondsToTransition * 20F);

    private static boolean updateUniforms = true;

    public static void tick(LocalPlayer player) {
        IntegratedServer ig = Minecraft.getInstance().getSingleplayerServer();
        if (ig != null) if (ig.tickRateManager().isFrozen() && !ig.tickRateManager().isSteppingForward()) return;
        for (Holder<MobEffect> mobEffect : effectAmplifiers.keySet()) {
            if (player.hasEffect(mobEffect)) {
                MobEffectInstance instance = player.getEffect(mobEffect);
                if (instance != null) {
                    updateUniforms = true;
                    int prevAmplifier = effectAmplifiers.get(mobEffect);
                    int currentAmplifier = instance.getAmplifier();
                    if (prevAmplifier != currentAmplifier) {
                        transitionState.put(mobEffect, transitionState.get(mobEffect) + (float) (prevAmplifier - currentAmplifier));
                    }
                    if (transitionState.get(mobEffect) < 0) {
                        transitionState.put(mobEffect, Math.min(transitionState.get(mobEffect) + transitionPerTick, 0.0F));
                    } else if (transitionState.get(mobEffect) > 0) {
                        transitionState.put(mobEffect, Math.max(transitionState.get(mobEffect) - transitionPerTick, 0.0F));
                    }
                    effectAmplifiers.put(mobEffect, instance.getAmplifier());
                }
            } else {
                if (effectAmplifiers.get(mobEffect) != -1) {
                    updateUniforms = true;
                }
                effectAmplifiers.put(mobEffect, -1);
                transitionState.put(mobEffect, 0.0F);
            }
        }
    }

    public static boolean isEnabled(Holder<MobEffect> effect) {
        if (effectAmplifiers.containsKey(effect)) {
            return effectAmplifiers.get(effect) != -1;
        } else return false;
    }

    public static float strength(Holder<MobEffect> effect) {
        return Math.max((float) effectAmplifiers.getOrDefault(effect, 0) + transitionState.getOrDefault(effect, 0.0F), 0.0F);
    }

    public static boolean updateUniforms() {
        return updateUniforms;
    }

    public static void updated() {
        updateUniforms = false;
    }
}
