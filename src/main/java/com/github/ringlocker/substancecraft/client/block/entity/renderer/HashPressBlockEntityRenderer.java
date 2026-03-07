package com.github.ringlocker.substancecraft.client.block.entity.renderer;

import com.github.ringlocker.substancecraft.block.entity.entities.HashPressBlockEntity;
import com.github.ringlocker.substancecraft.client.block.entity.renderer.renderstates.InputOutputBlockEntityRenderState;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class HashPressBlockEntityRenderer implements BlockEntityRenderer<HashPressBlockEntity, InputOutputBlockEntityRenderState> {

    private final ItemModelResolver itemModelResolver;

    public HashPressBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.itemModelResolver = context.itemModelResolver();
    }

    @Override
    @NotNull public InputOutputBlockEntityRenderState createRenderState() {
        return new InputOutputBlockEntityRenderState();
    }

    @Override
    public void extractRenderState(HashPressBlockEntity blockEntity, InputOutputBlockEntityRenderState renderState, float partialTick, Vec3 cameraPosition, @Nullable ModelFeatureRenderer.CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, renderState, partialTick, cameraPosition, breakProgress);
        ItemStackRenderState itemStackRenderState = new ItemStackRenderState();
        ItemStack itemStack = blockEntity.getRenderStack();
        this.itemModelResolver.updateForTopItem(itemStackRenderState, itemStack, ItemDisplayContext.FIXED, blockEntity.getLevel(), null, 0);
        renderState.itemStackRenderState = itemStackRenderState;
    }

    @Override
    public void submit(InputOutputBlockEntityRenderState renderState, PoseStack matrices, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
        ItemStackRenderState itemStackRenderState = renderState.itemStackRenderState;
        if (!itemStackRenderState.isEmpty()) {
            matrices.pushPose();
            matrices.translate(0.5f, 0.95f, 0.5f);
            matrices.scale(0.35f, 0.35f, 0.35f);
            matrices.mulPose(Axis.XP.rotationDegrees(90));
            itemStackRenderState.submit(matrices, submitNodeCollector, renderState.lightCoords, OverlayTexture.NO_OVERLAY, 0);
            matrices.popPose();
        }
    }
}
