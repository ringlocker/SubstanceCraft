package com.github.ringlocker.substancecraft.client.shader;

import com.github.ringlocker.substancecraft.effect.SubstanceCraftEffects;
import net.minecraft.client.player.LocalPlayer;
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
            SubstanceCraftEffects.DYNAMIC_COLOR, -1
    ));

    private static boolean updateUniforms = true;

    public static void tick(LocalPlayer player) {
        for (Holder<MobEffect> mobEffect : effectAmplifiers.keySet()) {
            if (player.hasEffect(mobEffect)) {
                MobEffectInstance instance = player.getEffect(mobEffect);
                if (instance != null) {
                    int amplifier = instance.getAmplifier();
                    if (effectAmplifiers.get(mobEffect) != amplifier || mobEffect.equals(SubstanceCraftEffects.DYNAMIC_COLOR)) {
                        updateUniforms = true;
                    }
                    effectAmplifiers.put(mobEffect, amplifier);
                }
            } else {
                if (effectAmplifiers.get(mobEffect) != -1) {
                    updateUniforms = true;
                }
                effectAmplifiers.put(mobEffect, -1);
            }
        }
    }

    public static boolean isEnabled(Holder<MobEffect> effect) {
        if (effectAmplifiers.containsKey(effect)) {
            return effectAmplifiers.get(effect) != -1;
        } else return false;
    }

    public static int strength(Holder<MobEffect> effect) {
        return effectAmplifiers.getOrDefault(effect, -1);
    }

    public static boolean updateUniforms() {
        return updateUniforms;
    }

    public static void updated() {
        updateUniforms = false;
    }
}
