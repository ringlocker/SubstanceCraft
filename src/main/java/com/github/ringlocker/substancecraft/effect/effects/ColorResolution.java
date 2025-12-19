package com.github.ringlocker.substancecraft.effect.effects;

import com.github.ringlocker.substancecraft.SubstanceCraft;
import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.Std140Builder;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.renderer.PostPass;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffectCategory;
import org.lwjgl.system.MemoryStack;

public class ColorResolution extends PostShaderEffect {

    public ColorResolution() {
        super(MobEffectCategory.NEUTRAL, "ColorResolutionConfig", Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "color_resolution"));
    }

    @Override
    protected void setBuffer(PostPass postPass, boolean enabled) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            Std140Builder builder = Std140Builder.onStack(stack, 8);
            builder.putFloat(23F - (amplifier + 1));
            builder.putInt(enabled ? 1 : 0);
            GpuBuffer newBuf = RenderSystem.getDevice().createBuffer(
                    () -> postPass + " " + uniformName,
                    64,
                    builder.get()
            );
            postPass.customUniforms.put(uniformName, newBuf);
        } catch (Exception e) {
            System.err.println("Erroring creating ColorResolutionConfig Buffer: " + e.getMessage());
        }
    }
}
