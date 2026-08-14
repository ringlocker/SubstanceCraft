package com.github.ringlocker.substancecraft.datagen;

import com.github.ringlocker.substancecraft.entity.npc.SubstanceCraftTradeSets;
import com.github.ringlocker.substancecraft.entity.npc.SubstanceCraftTrades;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class SubstanceCraftDatagen implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();
        pack.addProvider(BlockTagGenerator::new);
        pack.addProvider(ItemTagGenerator::new);
        pack.addProvider(LootTableGenerator::new);
        pack.addProvider(RecipeGenerator::new);
        pack.addProvider(AdvancementGenerator::new);
        pack.addProvider(VillagerTradeGenerator::new);
        pack.addProvider(TradeProvider::new);

        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            try {
                Class<?> clientEntrypointClass = Class.forName("com.github.ringlocker.substancecraft.client.SubstanceCraftClient");
                DataGeneratorEntrypoint entrypoint = (DataGeneratorEntrypoint) clientEntrypointClass.getConstructor().newInstance();
                entrypoint.onInitializeDataGenerator(generator);
            } catch (ReflectiveOperationException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.VILLAGER_TRADE, SubstanceCraftTrades::bootstrap);
        registryBuilder.add(Registries.TRADE_SET, SubstanceCraftTradeSets::bootstrap);
    }
}
