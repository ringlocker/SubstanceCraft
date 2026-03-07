package com.github.ringlocker.substancecraft.effect.effects;

import com.github.ringlocker.substancecraft.SubstanceCraft;
import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.Std140Builder;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.PostPass;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffectCategory;
import org.lwjgl.system.MemoryStack;

public class DynamicColor extends PostShaderEffect {

    public DynamicColor() {
        super(MobEffectCategory.NEUTRAL, "DynamicColorConfig", Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "dynamic_color"));
    }

    @Override
    public void setBuffer(PostPass postPass, boolean enabled) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            Std140Builder builder = Std140Builder.onStack(stack, 12);
            int time = Minecraft.getInstance().level != null ?
                    Minecraft.getInstance().getSingleplayerServer() == null ?
                            0 :
                            Minecraft.getInstance().getSingleplayerServer().getTickCount() :
                    0;
            builder.putFloat((float) time);
            builder.putFloat(0.05F * (amplifier + 1));
            builder.putInt(enabled ? 1 : 0);
            GpuBuffer newBuf = RenderSystem.getDevice().createBuffer(
                    () -> postPass + " " + uniformName,
                    96,
                    builder.get()
            );
            postPass.customUniforms.put(uniformName, newBuf);
        } catch (Exception e) {
            System.err.println("Erroring creating DynamicColorConfig Buffer: " + e.getMessage());
        }
    }

}
