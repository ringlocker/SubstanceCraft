package com.github.ringlocker.substancecraft.effect.effects;

import com.github.ringlocker.substancecraft.SubstanceCraft;
import com.github.ringlocker.substancecraft.effect.SubstanceCraftEffects;
import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.Std140Builder;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelTargetBundle;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.client.renderer.PostPass;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import org.lwjgl.system.MemoryStack;

public class ColorEnhancement extends SubstanceCraftEffects.BasicEffect {

    private static final ResourceLocation COLOR_ENHANCEMENT_EFFECT = ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "color_enhancement");
    private static int amplifier = -1;
    private static boolean active = false;

    public ColorEnhancement() {
        super(MobEffectCategory.NEUTRAL);
    }

    public static void clientTick(Minecraft client) {
        if (client.level == null) return;

        if (!isDirty(client)) return;
        PostChain postChain = client.getShaderManager().getPostChain(COLOR_ENHANCEMENT_EFFECT, LevelTargetBundle.MAIN_TARGETS);
        if (postChain == null) return;
        if (postChain.passes.isEmpty()) return;
        PostPass postPass = postChain.passes.getFirst();

        GpuBuffer old = postPass.customUniforms.get("Config");
        if (old != null) old.close();

        setBuffer(postPass);
        client.gameRenderer.processBlurEffect();
    }

    private static boolean isDirty(Minecraft client) {
        boolean dirty = false;
        if (client.player == null) return false;
        if (client.player.hasEffect(SubstanceCraftEffects.COLOR_ENHANCEMENT)) {
            MobEffectInstance instance = client.player.getEffect(SubstanceCraftEffects.COLOR_ENHANCEMENT);
            if (instance == null) return false;
            RenderSystem.queueFencedTask(() -> {
                client.gameRenderer.setPostEffect(COLOR_ENHANCEMENT_EFFECT);
                client.gameRenderer.processBlurEffect();
            });
            if (active) {
                if (instance.getAmplifier() != amplifier) {
                    amplifier = instance.getAmplifier();
                    dirty = true;
                }
            } else {
                amplifier = instance.getAmplifier();
                active = true;
                dirty = true;
            }
        } else if (client.gameRenderer.currentPostEffect() == COLOR_ENHANCEMENT_EFFECT) {
            client.gameRenderer.clearPostEffect();
            active = false;
            amplifier = -1;
        }
        return dirty;
    }

    private static void setBuffer(PostPass postPass) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            Std140Builder builder = Std140Builder.onStack(stack, 16);
            builder.putFloat(1.8F + ((amplifier + 1) * 0.1F));
            builder.putFloat(0F).putFloat(0F).putFloat(0F);
            GpuBuffer newBuf = RenderSystem.getDevice().createBuffer(
                    () -> postPass + " Config",
                    128,
                    builder.get()
            );
            postPass.customUniforms.put("Config", newBuf);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}