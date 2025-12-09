package com.github.ringlocker.substancecraft.client;

import com.github.ringlocker.substancecraft.SubstanceCraft;
import com.github.ringlocker.substancecraft.block.SubstanceCraftBlocks;
import com.github.ringlocker.substancecraft.block.entity.SubstanceCraftBlockEntities;
import com.github.ringlocker.substancecraft.client.block.entity.renderer.HashPressBlockEntityRenderer;
import com.github.ringlocker.substancecraft.client.datagen.ModelGenerator;
import com.github.ringlocker.substancecraft.client.entity.render.SubstanceCraftEntityRenderers;
import com.github.ringlocker.substancecraft.client.gui.SubstanceCraftScreens;
import com.github.ringlocker.substancecraft.client.network.SubstanceCraftClientNetworking;
import com.github.ringlocker.substancecraft.client.item.SubstanceTintColor;
import com.github.ringlocker.substancecraft.effect.effects.PostShaderEffect;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.client.color.item.ItemTintSources;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class SubstanceCraftClient implements ClientModInitializer, DataGeneratorEntrypoint {

    @Override
    public void onInitializeClient() {
        registerItemColors();
        registerRenderLayers();
        registerBlockEntityRenderers();

        SubstanceCraftScreens.registerScreens();
        SubstanceCraftClientNetworking.init();
        SubstanceCraftEntityRenderers.registerEntityRenderers();

        ClientTickEvents.START_CLIENT_TICK.register(PostShaderEffect::clientTick);
    }

    private void registerItemColors() {
        ItemTintSources.ID_MAPPER.put(ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "substance_item_tint"), SubstanceTintColor.MAP_CODEC);
    }

    private void registerRenderLayers() {
        BlockRenderLayerMap.putBlock(SubstanceCraftBlocks.MARIJUANA_PLANT, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(SubstanceCraftBlocks.CORN_CROP, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(SubstanceCraftBlocks.COCA_CROP, ChunkSectionLayer.CUTOUT);
    }

    private void registerBlockEntityRenderers() {
        BlockEntityRenderers.register(SubstanceCraftBlockEntities.HASH_PRESS, HashPressBlockEntityRenderer::new);
    }

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(ModelGenerator::new);
    }

}
