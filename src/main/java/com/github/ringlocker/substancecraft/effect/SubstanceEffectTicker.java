package com.github.ringlocker.substancecraft.effect;

import com.github.ringlocker.substancecraft.SubstanceCraft;
import com.github.ringlocker.substancecraft.data.SubstanceWorldData;
import com.github.ringlocker.substancecraft.data.component.SubstanceData;
import com.github.ringlocker.substancecraft.data.component.SubstanceInstance;
import com.github.ringlocker.substancecraft.item.Drug;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;

import java.util.UUID;

public class SubstanceEffectTicker {

    private static SubstanceWorldData data;

    public static void init() {
        ServerTickEvents.START_SERVER_TICK.register(SubstanceEffectTicker::onServerTick);
        ServerLifecycleEvents.SERVER_STARTED.register(SubstanceEffectTicker::serverStart);
        ServerPlayerEvents.JOIN.register(SubstanceEffectTicker::playerJoin);
        ServerPlayerEvents.COPY_FROM.register(SubstanceEffectTicker::playerCopyEvent);
    }

    public static void onServerTick(MinecraftServer minecraftServer) {
        if (minecraftServer.tickRateManager().isFrozen()) {
            if (!minecraftServer.tickRateManager().isSteppingForward()) {
                return;
            }
        }
        for (ServerPlayer player : minecraftServer.getPlayerList().getPlayers()) {
           tickSubstances(player, minecraftServer.getTickCount());
        }
    }

    public static void serverStart(MinecraftServer server) {
        ServerLevel level = server.getLevel(ServerLevel.OVERWORLD);
        if (level == null) {
            SubstanceCraft.LOGGER.warn("Overworld loading failed");
            return;
        }
        data = SubstanceWorldData.get(level);
    }

    public static void playerJoin(ServerPlayer player) {
        data.getData(player.getUUID());
        data.setDirty();
    }

    public static void playerCopyEvent(ServerPlayer old, ServerPlayer newPlayer, boolean dimensionTransfer) {
        if (dimensionTransfer) {
            return;
        }
        UUID uuid = old.getUUID();
        if(!data.clearPlayerData(uuid)) {
            SubstanceCraft.LOGGER.warn("Could not find uuid of player: {}", uuid);
        }
        data.setDirty();
    }

    private static void tickSubstances(ServerPlayer player, int tick) {
        SubstanceData dataComponent = data.getData(player.getUUID());
        dataComponent.substances().forEach(SubstanceInstance::tick);
        if (tick % 20 == 0) dataComponent.substances().forEach(instance -> refreshBaseEffects(player, instance));
        if (tick % 20 == 0) refreshSideEffects(player, dataComponent);
    }

    public static void playerConsumeDrug(ServerPlayer player, Drug drug) {
        SubstanceData dataComponent = data.getData(player.getUUID());
        dataComponent.getInstanceOrCreateIfNotPresent(drug).addDose();
        data.setDirty();
    }

    private static void refreshBaseEffects(ServerPlayer player, SubstanceInstance instance) {
        player.removeEffect(instance.drug().getBaseEffect());
        float threshold = instance.drug().getThreshold();
        float amount = instance.amount();
        if (threshold < amount) {
            player.addEffect(new MobEffectInstance(instance.drug().getBaseEffect(), remainingTime(instance.drug().getDecayFactor(), amount, threshold), 0, false, false, true));
        }
    }
    
    private static void refreshSideEffects(ServerPlayer player, SubstanceData substanceData) {
        SideEffectsSummary summary = new SideEffectsSummary();

        for (SubstanceInstance instance : substanceData.substances()) {
            float amount = instance.amount();
            for (Drug.DrugSideEffect sideEffect : instance.drug().getSideEffects()) {
                if (sideEffect.threshold() < amount) {
                    int amp = calculateAmplifier(amount, sideEffect.threshold(), sideEffect.amplifyEvery(), sideEffect.maxAmplifier());
                    summary.add(sideEffect, amp, instance);
                }
            }
        }
        summary.applyTo(player);
    }

    private static int calculateAmplifier(float amount, int sideEffectThreshold, int sideEffectAmplifyEvery, int max) {
        if (amount < sideEffectThreshold) {
            return 0;
        } else {
            float diff = amount - sideEffectThreshold;
            return Math.min((int) (diff / (float) sideEffectAmplifyEvery), max);
        }
    }

    public static int remainingTime(double decay, double amount, double threshold) {
        return (int) Math.ceil(Math.log(threshold / amount) / Math.log(decay));
    }

}
