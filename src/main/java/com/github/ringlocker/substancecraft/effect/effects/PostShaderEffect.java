package com.github.ringlocker.substancecraft.effect.effects;

import com.github.ringlocker.substancecraft.SubstanceCraft;
import com.github.ringlocker.substancecraft.effect.SubstanceCraftEffects;
import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelTargetBundle;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.client.renderer.PostPass;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;

public abstract class PostShaderEffect extends BasicEffect {

    private static final HashMap<String, PostShaderEffect> effectsByUniformName = new HashMap<>();
    private static final Identifier id = Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "post_effects");
    protected final String uniformName;
    protected final Identifier mobEffect;
    protected int amplifier = -1;
    protected boolean active = false;

    public PostShaderEffect(MobEffectCategory category, String uniformName, Identifier mobEffect) {
        super(category);
        this.uniformName = uniformName;
        this.mobEffect = mobEffect;
        effectsByUniformName.put(uniformName, this);
    }

    public static void clientTick(Minecraft client) {
        if (client.level == null) return;

        PostChain postChain = client.getShaderManager().getPostChain(id, LevelTargetBundle.MAIN_TARGETS);
        if (postChain == null) return;
        if (postChain.passes.isEmpty()) return;

        for (PostShaderEffect effect : effectsByUniformName.values()) {
            setBufferForPass(effect, postChain, client);
        }

        RenderSystem.queueFencedTask(() -> client.gameRenderer.setPostEffect(id));
    }

    private static void setBufferForPass(PostShaderEffect effect, PostChain postChain, Minecraft client) {
        PostPass postPass = findPostPass(postChain.passes, effect);
        if (postPass == null) return;
        boolean bl = effect.shouldUpdateUniform(client);

        if (effect.active && bl) {
            GpuBuffer old = postPass.customUniforms.get(effect.uniformName);
            if (old != null) old.close();
            effect.setBuffer(postPass, true);
        } else {
            effect.setBuffer(postPass, false);
        }
    }

    @Nullable
    private static PostPass findPostPass(List<PostPass> passes, PostShaderEffect effect) {
        for (PostPass postPass : passes) {
            if (postPass.customUniforms.containsKey(effect.uniformName)) {
                return postPass;
            }
        }
        return null;
    }

    protected abstract void setBuffer(PostPass postPass, boolean enabled);

    protected boolean shouldUpdateUniform(Minecraft client) {
        if (client.player == null) {
            return false;
        }
        if (client.player.hasEffect(SubstanceCraftEffects.getEffect(mobEffect))) {
            MobEffectInstance instance = client.player.getEffect(SubstanceCraftEffects.getEffect(mobEffect));
            if (instance == null) return false;
            updateEffect(instance);
            return true;
        } else {
            active = false;
            amplifier = -1;
            return false;
        }
    }

    private void updateEffect(MobEffectInstance instance) {
        if (active) {
            if (instance.getAmplifier() != amplifier) {
                amplifier = instance.getAmplifier();
            }
        } else {
            amplifier = instance.getAmplifier();
            active = true;
        }
    }
}
