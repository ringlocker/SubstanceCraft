package com.github.ringlocker.substancecraft.effect;

import com.github.ringlocker.substancecraft.SubstanceCraft;
import com.github.ringlocker.substancecraft.effect.component.components.SubstanceData;
import com.github.ringlocker.substancecraft.effect.component.components.SubstanceInstance;
import com.github.ringlocker.substancecraft.item.Drug;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

import java.util.UUID;


public class SubstanceEffectTicker {

    private static SubstanceWorldData data;
    private static ServerLevel overworld;

    public static void onServerTick(MinecraftServer minecraftServer) {
        if (minecraftServer.tickRateManager().isFrozen()) {
            if (!minecraftServer.tickRateManager().isSteppingForward()) {
                return;
            }
        }
        for (ServerPlayer player : minecraftServer.getPlayerList().getPlayers()) {
           tickSubstances(player);
        }
    }

    public static void serverStart(MinecraftServer server) {
        ServerLevel level = server.getLevel(ServerLevel.OVERWORLD);
        if (level == null) {
            System.out.println("Overworld loading failed!");
            return;
        }
        overworld = level;
        data = SubstanceWorldData.get(level);
        data.getPlayerData().clear();
    }

    public static void playerJoin(ServerPlayer player) {
        SubstanceData playerData = data.getData(player.getUUID());
        SubstanceWorldData.save(overworld, data);
    }

    public static void playerCopyEvent(ServerPlayer old, ServerPlayer newPlayer, boolean dimensionTransfer) {
        if (dimensionTransfer) {
            return;
        }
        UUID uuid = old.getUUID();
        if(!data.clearPlayerData(uuid)) {
            SubstanceCraft.LOGGER.warn("Could not find uuid of player: {}", uuid);
        }
        SubstanceWorldData.save(overworld, data);
    }

    private static void tickSubstances(ServerPlayer player) {
        SubstanceData dataComponent = data.getData(player.getUUID());
        dataComponent.substances().forEach(SubstanceInstance::tick);

    }

    public static void playerConsumeDrug(ServerPlayer player, Drug drug) {
        SubstanceData dataComponent = data.getData(player.getUUID());
        dataComponent.getInstanceOrCreateIfNotPresent(drug).addDose();
        System.out.println(dataComponent);
        SubstanceWorldData.save(overworld, data);

    }

    public static float getAmount(ServerPlayer player, Drug drug) {
        for (SubstanceInstance instance : data.getData(player.getUUID()).substances()) {
            if (instance.drug() == drug) {
                return instance.amount();
            }
        }
        return 0.0F;
    }

    private static SubstanceData makeNewIfNull(SubstanceData data) {
        return data == null ? new SubstanceData() : data;
    }

}
