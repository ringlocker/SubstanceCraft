package com.github.ringlocker.substancecraft.client;

import com.github.ringlocker.substancecraft.block.SubstanceCraftBlocks;
import com.github.ringlocker.substancecraft.block.entity.SubstanceCraftBlockEntities;
import com.github.ringlocker.substancecraft.client.block.entity.renderer.HashPressBlockEntityRenderer;
import com.github.ringlocker.substancecraft.client.datagen.ModelGenerator;
import com.github.ringlocker.substancecraft.client.gui.SubstanceCraftScreens;
import com.github.ringlocker.substancecraft.client.network.SubstanceCraftClientNetworking;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;

@Environment(EnvType.CLIENT)
public class SubstanceCraftClient implements ClientModInitializer, DataGeneratorEntrypoint {

    @Override
    public void onInitializeClient() {
        registerRenderLayers();
        registerBlockEntityRenderers();

        SubstanceCraftScreens.registerScreens();
        SubstanceCraftClientNetworking.init();
    }

    private void registerRenderLayers() {
        BlockRenderLayerMap.putBlock(SubstanceCraftBlocks.MARIJUANA_PLANT, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(SubstanceCraftBlocks.CORN_CROP, ChunkSectionLayer.CUTOUT);
    }

    private void registerBlockEntityRenderers() {
        BlockEntityRenderers.register(SubstanceCraftBlockEntities.HASH_PRESS, context -> new HashPressBlockEntityRenderer());
    }

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(ModelGenerator::new);
    }

}
