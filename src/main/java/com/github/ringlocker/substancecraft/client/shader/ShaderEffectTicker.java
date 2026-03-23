package com.github.ringlocker.substancecraft.client.shader;

import com.github.ringlocker.substancecraft.SubstanceCraft;
import com.github.ringlocker.substancecraft.effect.SubstanceCraftEffects;
import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.Std140Builder;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.LevelTargetBundle;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.client.renderer.PostPass;
import net.minecraft.resources.Identifier;
import org.lwjgl.system.MemoryStack;


public class ShaderEffectTicker {

    private static final Identifier postEffectID = Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "shader_effects");
    private static final String uniformName = "Config";

    public static void clientTick(Minecraft client) {
        if (client.level == null) return;

        LocalPlayer localPlayer = client.player;
        if (localPlayer == null) {
            System.out.println("client player null");
            return;
        }

        PostChain postChain = client.getShaderManager().getPostChain(postEffectID, LevelTargetBundle.MAIN_TARGETS);
        if (postChain == null) {
            System.out.println("post chain null");
            return;
        }

        if (postChain.passes.size() != 2) {
            System.out.println("post chain passes size " + postChain.passes.size());
            return;
        }

        PlayerEffectState.tick(localPlayer);
        PostPass postPass = postChain.passes.getFirst();
        if (PlayerEffectState.updateUniforms()) updateBuffer(postPass);
        RenderSystem.queueFencedTask(() -> client.gameRenderer.setPostEffect(postEffectID));
    }

    private static void updateBuffer(PostPass postPass) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            Std140Builder builder = Std140Builder.onStack(stack, 4 * 9);
            builder.putInt(PlayerEffectState.isEnabled(SubstanceCraftEffects.COLOR_ENHANCEMENT) ? 1 : 0);
            builder.putInt(PlayerEffectState.isEnabled(SubstanceCraftEffects.COLOR_RESOLUTION) ? 1 : 0);
            builder.putInt(PlayerEffectState.isEnabled(SubstanceCraftEffects.DYNAMIC_COLOR) ? 1 : 0);
            builder.putInt(PlayerEffectState.isEnabled(SubstanceCraftEffects.MOSAIC) ? 1 : 0);
            builder.putFloat(1.0F + ((PlayerEffectState.strength(SubstanceCraftEffects.COLOR_ENHANCEMENT) + 1) * 0.2F));
            builder.putFloat(23F - (PlayerEffectState.strength(SubstanceCraftEffects.COLOR_RESOLUTION) + 1));
            builder.putFloat((float) getTime());
            builder.putFloat(0.05F * (PlayerEffectState.strength(SubstanceCraftEffects.DYNAMIC_COLOR) + 1));
            builder.putFloat(1.0F + (0.2F * (float) (PlayerEffectState.strength(SubstanceCraftEffects.MOSAIC) + 1)));
            GpuBuffer newBuf = RenderSystem.getDevice().createBuffer(
                    () -> postPass + " " + uniformName,
                    (9 * 4 * 8),
                    builder.get()
            );
            postPass.customUniforms.put(uniformName, newBuf);
        } catch (Exception e) {
            System.err.println("Erroring creating Config Buffer: " + e.getMessage());
        } finally {
            PlayerEffectState.updated();
        }
    }

    private static int getTime() {
        return Minecraft.getInstance().level != null ?
                Minecraft.getInstance().getSingleplayerServer() == null ?
                        0 :
                        Minecraft.getInstance().getSingleplayerServer().getTickCount() :
                0;
    }


}
