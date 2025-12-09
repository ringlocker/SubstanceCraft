package com.github.ringlocker.substancecraft.effect.effects;

import com.github.ringlocker.substancecraft.SubstanceCraft;
import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.Std140Builder;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.renderer.PostPass;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectCategory;
import org.lwjgl.system.MemoryStack;

public class ColorEnhancement extends PostShaderEffect {

    public ColorEnhancement() {
        super(MobEffectCategory.NEUTRAL, "ColorEnhancementConfig", ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "color_enhancement"));
    }

    protected void setBuffer(PostPass postPass, boolean enabled) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            Std140Builder builder = Std140Builder.onStack(stack, 8);
            builder.putFloat(1.0F + ((amplifier + 1) * 0.2F));
            builder.putInt(enabled ? 1 : 0);
            GpuBuffer newBuf = RenderSystem.getDevice().createBuffer(
                    () -> postPass + " " + uniformName,
                        64,
                    builder.get()
            );
            postPass.customUniforms.put(uniformName, newBuf);
        } catch (Exception e) {
            System.err.println("Erroring creating ColorEnhancementConfig Buffer: " + e.getMessage());
        }
    }

}