package com.github.ringlocker.substancecraft.effect.effects.generic;

import com.github.ringlocker.substancecraft.effect.SubstanceCraftEffects;
import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelTargetBundle;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.client.renderer.PostPass;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;

import java.util.ArrayList;

public abstract class PostShaderEffect extends BasicEffect {

    private static final ArrayList<PostShaderEffect> effects = new ArrayList<>();
    protected static final String targetUniform = "Config"
            ;
    private final ResourceLocation id;
    protected int amplifier = -1;
    protected boolean active = false;

    public PostShaderEffect(MobEffectCategory category, ResourceLocation id) {
        super(category);
        this.id = id;
        effects.add(this);
    }

    public static void clientTick(Minecraft client) {
        effects.forEach((effect) -> effect.tick(client));
    }

    protected abstract void setBuffer(PostPass postPass);

    public void tick(Minecraft client) {
        if (client.level == null) return;

        if (!shouldUpdateUniform(client)) return;
        PostChain postChain = client.getShaderManager().getPostChain(id, LevelTargetBundle.MAIN_TARGETS);
        if (postChain == null) return;
        if (postChain.passes.isEmpty()) return;
        PostPass postPass = postChain.passes.getFirst();

        GpuBuffer old = postPass.customUniforms.get(targetUniform);
        if (old != null) old.close();

        setBuffer(postPass);
        client.gameRenderer.processBlurEffect();
    }

    private boolean shouldUpdateUniform(Minecraft client) {
        boolean dirty = false;
        if (client.player == null) {
            return false;
        }
        if (client.player.hasEffect(SubstanceCraftEffects.getEffect(id))) {
            MobEffectInstance instance = client.player.getEffect(SubstanceCraftEffects.getEffect(id));
            if (instance == null) return false;
            RenderSystem.queueFencedTask(() -> {
                client.gameRenderer.setPostEffect(id);
                client.gameRenderer.processBlurEffect();
            });
            dirty = updateEffect(client, instance);
        } else if (client.gameRenderer.currentPostEffect() == id) {
            client.gameRenderer.clearPostEffect();
            active = false;
            amplifier = -1;
        }
        return dirty;
    }

    private boolean updateEffect(Minecraft client, MobEffectInstance instance) {
        RenderSystem.queueFencedTask(() -> {
            client.gameRenderer.setPostEffect(id);
            client.gameRenderer.processBlurEffect();
        });
        if (active) {
            if (instance.getAmplifier() != amplifier) {
                amplifier = instance.getAmplifier();
                return true;
            }
        } else {
            amplifier = instance.getAmplifier();
            active = true;
            return true;
        }
        return false;
    }
}
