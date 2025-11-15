package com.github.ringlocker.substancecraft.effect.effects;

import com.github.ringlocker.substancecraft.SubstanceCraft;
import com.github.ringlocker.substancecraft.effect.effects.generic.PostShaderEffect;
import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.Std140Builder;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.renderer.PostPass;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectCategory;
import org.lwjgl.system.MemoryStack;

public class Mosaic extends PostShaderEffect {

    public Mosaic() {
        super(
                MobEffectCategory.NEUTRAL,
                ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "mosaic"));
    }

    @Override
    protected void setBuffer(PostPass postPass) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            Std140Builder builder = Std140Builder.onStack(stack, 4);
            builder.putFloat(1.0F + (amplifier + 1));
            GpuBuffer newBuf = RenderSystem.getDevice().createBuffer(
                    () -> postPass + " " + targetUniform,
                    32,
                    builder.get()
            );
            postPass.customUniforms.put(targetUniform, newBuf);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
