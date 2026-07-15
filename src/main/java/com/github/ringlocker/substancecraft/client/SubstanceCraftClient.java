package com.github.ringlocker.substancecraft.client;

import com.github.ringlocker.substancecraft.SubstanceCraft;
import com.github.ringlocker.substancecraft.block.entity.SubstanceCraftBlockEntities;
import com.github.ringlocker.substancecraft.client.block.entity.renderer.HashPressBlockEntityRenderer;
import com.github.ringlocker.substancecraft.client.datagen.ModelGenerator;
import com.github.ringlocker.substancecraft.client.entity.render.SubstanceCraftEntityRenderers;
import com.github.ringlocker.substancecraft.client.gui.SubstanceCraftScreens;
import com.github.ringlocker.substancecraft.client.item.SubstanceTintColor;
import com.github.ringlocker.substancecraft.client.shader.ShaderEffectTicker;
import com.github.ringlocker.substancecraft.recipe.SubstanceCraftRecipes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.client.color.item.ItemTintSources;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.resources.Identifier;

@Environment(EnvType.CLIENT)
public class SubstanceCraftClient implements ClientModInitializer, DataGeneratorEntrypoint {

    @Override
    public void onInitializeClient() {
        registerItemColors();
        registerBlockEntityRenderers();

        SubstanceCraftScreens.registerScreens();
        SubstanceCraftEntityRenderers.registerEntityRenderers();
        SubstanceCraftRecipes.synchronizeRecipes();

        ClientTickEvents.START_CLIENT_TICK.register(ShaderEffectTicker::clientTick);
    }

    private void registerItemColors() {
        ItemTintSources.ID_MAPPER.put(Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "substance_item_tint"), SubstanceTintColor.MAP_CODEC);
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
